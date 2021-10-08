package wesley;


public class CommonRoom extends Room{
    public static enum RoomType{ Gym, Library, Laundry }
    public RoomType roomType;

    public CommonRoom(int ID, RoomType roomType){
        super(ID);
        this.roomType = roomType;
    }
    
    public String collectData(){
        return String.format("Common Room: ID - %d, Type: %s", this.ID, this.roomType );
    }
}
