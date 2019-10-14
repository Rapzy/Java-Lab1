package hotel;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    final private static String exitCommand = "exit";
    private static Hotel selectedHotel;
    final private static String successText = "[Done]";

    public static boolean isExitCommand(String cmd) {
        return cmd.equals(exitCommand);
    }

    public static String readConsole(String message) {
        String tempStr;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print(message);
            try {
                tempStr = consoleReader.readLine();
            } catch (Exception e) {
                System.out.println("[Error] Invalid input!");
                return exitCommand;
            }
        } while (tempStr.isEmpty());
        return tempStr;
    }

    public static Integer readConsole(String message, Integer from, Integer to) {
        String tempStr;
        Integer tempInt;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print(message);
            try {
                tempStr = consoleReader.readLine();
                if (isExitCommand(tempStr)) {
                    return 0;
                }
                tempInt = Integer.parseInt(tempStr);
            } catch (Exception e) {
                System.out.println("[Error] Invalid value! Excepted positive integer value.");
                tempInt = from - 1;
            }
        } while (tempInt < from || tempInt > to);
        return tempInt;
    }

    public static void addHotel() {
        String name, address;
        Integer stars, roomsNum;
        name = readConsole("Enter the name of your hotel: ");
        if (isExitCommand(name)) {
            return;
        }
        address = readConsole("Enter address of your hotel: ");
        if (isExitCommand(address)) {
            return;
        }
        stars = readConsole("Enter number of stars hotel of your hotel: ", 1, 5);
        if (stars == 0) {
            return;
        }
        roomsNum = readConsole("Enter number of rooms in your hotel: ", 1, Integer.MAX_VALUE);
        if (roomsNum == 0) {
            return;
        }
        new Hotel(name, address, stars, roomsNum);
        System.out.println(successText);
    }

    public static void selectHotel() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        List<Hotel> hotels = Hotel.getAvailableHotels();
        if (hotels.isEmpty()) {
            System.out.println("No available hotels.");
        } else {
            for (int i = 0; i < hotels.size(); i++) {
                Hotel tempHotel = hotels.get(i);
                System.out.printf("%d) %s | %s | %s | Free rooms: [%d/%d]\n", i + 1, tempHotel.getName(), tempHotel.getAddress(), "*".repeat(tempHotel.getStars()), tempHotel.getFreeRooms().size(), tempHotel.getRooms().size());
            }
            Integer numHotel = -1;
            do {
                try {
                    System.out.print("Select hotel: ");
                    String tempHotel = consoleReader.readLine();
                    if (isExitCommand(tempHotel)) {
                        return;
                    }
                    numHotel = Integer.parseInt(tempHotel);
                } catch (Exception e) {
                    System.out.println("[Error] Invalid value! Excepted positive integer value.");
                    numHotel = -1;
                }
            } while (numHotel <= 0 || numHotel > hotels.size());
            selectedHotel = hotels.get(numHotel - 1);
            System.out.println(successText);
        }
    }
    public static void editHotel() {
        System.out.printf("1) Name = %s\n", selectedHotel.getName());
        System.out.printf("2) Address = %s\n", selectedHotel.getAddress());
        System.out.printf("3) Stars = %d\n", selectedHotel.getStars());
        System.out.println("Choose field to edit: ");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        Integer numChoice = -1;
        do {
            try {
                String strChoice = consoleReader.readLine();
                if (isExitCommand(strChoice) || strChoice.isEmpty()) {
                    return;
                }
                numChoice = Integer.parseInt(strChoice);
            } catch (Exception e) {
                System.out.println("[Error] Invalid value! Excepted positive integer value.");
                numChoice = -1;
            }
        } while (numChoice < 1 && numChoice > 3);
        System.out.print("Enter new value: ");
        do {
            try {
                String newValue = consoleReader.readLine();
                if (isExitCommand(newValue) || newValue.isEmpty()) {
                    return;
                }
                if (numChoice == 1){
                    selectedHotel.setName(newValue);
                }
                else if (numChoice == 2){
                    selectedHotel.setAddress(newValue);
                }
                else if (numChoice == 3) {
                    Integer newStars;
                    do {
                        try {
                            newStars = Integer.parseInt(newValue);
                            selectedHotel.setStars(newStars);
                        } catch (Exception e) {
                            System.out.println("[Error] Invalid value! Excepted positive integer value.");
                            newStars = -1;
                        }
                    } while (newStars < 1 && newStars > 5);
                }
            } catch (Exception e) {
                System.out.println("[Error] Invalid value! Excepted positive integer value.");
                numChoice = -1;
            }
        } while (numChoice < 1 && numChoice > 3);
        System.out.println(successText);
    }

    public static void main(String[] args) {
        String cmd;
        new Hotel("Отель DoubleTree by Hilton", "Проспект Победителей, 9", 5, 100);
        new Hotel("Гостиница Беларусь", "Сторожевская Улица, 15", 3, 100);

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Available commands: ");
            System.out.println("1) Add hotel;");
            System.out.println("2) Select hotel;");
            System.out.println("3) Add room;");
            System.out.println("4) Remove room;");
            System.out.println("5) Edit hotel;");
            System.out.println("6) Delete hotel");
            System.out.print("Enter number of command: ");
            try {
                cmd = consoleReader.readLine().toLowerCase();
                if (cmd.equals("1")) {
                    addHotel();
                }
                else if (cmd.equals("2")) {
                    selectHotel();
                }
                else if (cmd.equals("3")) {
                    if (selectedHotel == null) {
                        System.out.println("[Error] No selected hotel.");
                    } else {
                        selectedHotel.addRoom();
                        System.out.println(successText);
                    }
                }
                else if (cmd.equals("4")) {
                    if (selectedHotel == null) {
                        System.out.println("[Error] No selected hotel.");
                    } else {
                        selectedHotel.removeRoom();
                        System.out.println(successText);
                    }
                }
                else if (cmd.equals("5")) {
                    if (selectedHotel == null) {
                        System.out.println("[Error] No selected hotel.");
                    } else {
                        editHotel();
                    }
                }
                else if (cmd.equals("6")) {
                    if (selectedHotel == null) {
                        System.out.println("[Error] No selected hotel.");
                    } else {
                        Hotel.getAvailableHotels().remove(selectedHotel);
                        System.out.println(successText);
                    }
                }
            } catch (Exception e) {
                System.out.println("[Error]");
                cmd = "";
            }
        } while (!isExitCommand(cmd));
    }
}
