package wesley;

public class Apartment extends Room {
    public String owner; 

    public Apartment(int ID, String owner) throws IllegalArgumentException{
        super(ID);
        if (owner.equals("")){ throw new IllegalArgumentException("Owner cannot be empty string"); }
        this.owner = owner;

    }

    public String collectData(){
        // return String.format("Apartment: ID - %d, Owner: %s, Heating: %s, Cooling: %s", this.ID, this.owner, (this.heatingOn ? "On" : "Off"), (this.coolingOn ? "On" : "Off") );
        return String.format("Apartment: ID - %d, Owner: %s", this.ID, this.owner );
    }
}
