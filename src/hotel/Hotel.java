package hotel;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private Integer id;
    private String name;
    private String address;
    private Integer stars;
    private List<Room> rooms;
    private List<Room> freeRooms;
    private static List<Hotel> availableHotels = new ArrayList<>();

    public Hotel(String name, String address, Integer stars, Integer roomsNum){
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.rooms = new ArrayList<>();
        this.freeRooms = new ArrayList<>();
        for (int i = 0; i < roomsNum; i++){
            Room newRoom = new Room(i+1);
            this.rooms.add(newRoom);
            this.freeRooms.add(newRoom);
        }
        availableHotels.add(this);
    }

    public static List<Hotel> getAvailableHotels() {
        return availableHotels;
    }

    public static void setAvailableHotels(List<Hotel> availableHotels) {
        Hotel.availableHotels = availableHotels;
    }

    public void addRoom(){
        Room newRoom = new Room(rooms.size()+1);
        this.rooms.add(newRoom);
        this.freeRooms.add(newRoom);
    }

    public void removeRoom(){
        this.rooms.remove(rooms.size()-1);
        this.freeRooms.remove(freeRooms.size()-1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(List<Room> freeRooms) {
        this.freeRooms = freeRooms;
    }


    private class Room {
        private Integer number;
        private Boolean isFree;

        private Room(Integer number){
            this.number = number;
            this.isFree = true;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Boolean getFree() {
            return isFree;
        }

        public void setFree(Boolean free) {
            isFree = free;
        }
    }
}