package wesley;

public class Apartment extends Room {
    public String owner; 

    public Apartment(int ID, String owner) throws IllegalArgumentException{
        super(ID);
        if (owner.equals("")){ throw new IllegalArgumentException("Owner cannot be empty string"); }
        this.owner = owner;

    }

    public String collectData(){
        return String.format("Apartment: ID - %d, Owner: %s, Temperature: %.2f%s%s", 
            this.ID, 
            this.owner, 
            this.temperature,
            (this.heatingOn ? ", Heating: On" : ""), 
            (this.coolingOn ? ", Cooling: On" : "") 
        );
    }
}
