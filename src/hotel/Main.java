package hotel;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static boolean isExitCommand(String cmd){
        final String exitCommand = "exit";
        if(cmd.equals(exitCommand)){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String cmd;
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        System.out.println("Available commands: ");
        System.out.println("1) Add hotel;");
        System.out.println("2) Select hotel;");
        System.out.println("3) Add room;");
        System.out.println("4) Remove room;");

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.print("Enter number of command: ");
                cmd = consoleReader.readLine().toLowerCase();

                switch (cmd) {
                    case ("1"):{
                        System.out.print("Enter the name of your hotel: ");
                        String name = consoleReader.readLine();
                        if(isExitCommand(name)){
                            break;
                        }
                        System.out.print("Enter address of your hotel: ");
                        String address = consoleReader.readLine();
                        if(isExitCommand(address)){
                            break;
                        }
                        Integer stars = -1;
                        do {
                            try {
                                System.out.print("Enter number of stars hotel of your hotel: ");
                                String tempStars = consoleReader.readLine();
                                if(isExitCommand(tempStars)){
                                    break;
                                }
                                stars = Integer.parseInt(tempStars);
                            }
                            catch (Exception e){
                                System.out.println("Invalid value! Excepted value from 1 to 5.");
                                stars = -1;
                            }
                        } while(stars <= 0 || stars > 5);
                        Integer roomsNum = -1;
                        do {
                            try {
                                System.out.print("Enter number of rooms in your hotel: ");
                                String tempRooms = consoleReader.readLine();
                                if(isExitCommand(tempRooms)){
                                    break;
                                }
                                roomsNum = Integer.parseInt(tempRooms);
                            }
                            catch (Exception e){
                                System.out.println("Invalid value! Excepted positive integer value.");
                                roomsNum = -1;
                            }
                        } while(stars <= 0 || stars > 5);
                        hotels.add(new Hotel(name, address, stars, roomsNum));
                    }
                    break;
                    case ("2"):{
                        if(hotels.size() == 0){
                            System.out.println("No available hotels.");
                        }
                        else{
                            for (int i = 0; i < hotels.size(); i++) {
                                Hotel tempHotel = hotels.get(i);
                                System.out.printf("%d) %s %s %s [%d/%d]\n",i+1, tempHotel.getName(),tempHotel.getAddress(), "*".repeat(tempHotel.getStars()),tempHotel.getRooms().size(), tempHotel.getRooms().size());
                            }
                        }
                    }
                    break;
                }
            } while(!isExitCommand(cmd));
        }
        catch (Exception e){

        };
    }
}
