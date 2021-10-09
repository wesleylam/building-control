package wesley;


public class CommonRoom extends Room{
    public static enum RoomType{ Gym, Library, Laundry }
    public RoomType roomType;

    public CommonRoom(int ID, RoomType roomType){
        super(ID);
        this.roomType = roomType;
    }
    
    public String collectData(){
        return String.format("Common Room: ID - %d, Type: %s, Temperature: %.2f%s%s", 
            this.ID, 
            this.roomType, 
            this.temperature,
            (this.heatingOn ? ", Heating: On" : ""), 
            (this.coolingOn ? ", Cooling: On" : "")  
        );
    }
}
