package hotel;
import java.util.ArrayList;

public class Hotel {
    private Integer id;
    private String name;
    private String address;
    private Integer stars;
    private ArrayList<Room> rooms;

    public Hotel(String name, String address, Integer stars, Integer roomsNum){
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.rooms = new ArrayList<Room>();
        for (int i = 0; i < roomsNum; i++){
            rooms.add(new Room(i+1));
        }
    }
    public void addRoom(){
        this.rooms.add(new Room(rooms.size()+1));
    }

    public void removeRoom(){
        this.rooms.remove(rooms.size()-1);
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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