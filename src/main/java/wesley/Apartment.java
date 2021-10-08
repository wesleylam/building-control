package wesley;

public class Apartment extends Room {
    public String owner; 

    public Apartment(int ID, String owner){
        super(ID);
        this.owner = owner;
    }

    public String collectData(){
        return String.format("Apartment: ID - %d, Owner: %s", this.ID, this.owner );
    }
}
