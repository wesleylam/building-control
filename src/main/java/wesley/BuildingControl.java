package wesley;
import java.util.Scanner;

public class BuildingControl{
    // program entry point
    public static void main(String[] args) throws ClassNotFoundException{
        Building building;
        if (args.length == 1){
            // set custom building temperature
            building = new Building(Integer.valueOf(args[0]));
        } else if (args.length == 0){
            // use default temperature 
            building = new Building();
        } else { 
            throw new IllegalArgumentException("Too many argument: Only input building temperature set point"); 
        }

        // add all required rooms
        building.addRoom(new Apartment(101, "Wesley"));
        building.addRoom(new Apartment(102, "John"));
        building.addRoom(new CommonRoom(201, CommonRoom.RoomType.Gym));
        building.addRoom(new CommonRoom(202, CommonRoom.RoomType.Library));
        // initial control
        building.tempControl();

        // Allow custom input from user
        Scanner scanner = new Scanner(System.in);  
        String input = "";
        Double temp;
        // main loop
        while (true){
            // prompt user input
            System.out.println("Enter a new temperature for the building OR quit: ");
            input = scanner.next();
            
            // quit program
            if (input.toLowerCase().equals("quit")){ break; }

            // set building temperature
            try { 
                temp = Double.valueOf(input); 
                System.out.println(String.format("Building temperature set to %.2f", temp));
                building.tempSetPoint = temp;
                // alter control
                building.tempControl();
            }
            catch ( NumberFormatException e ) {
                System.out.println("Input a valid number OR quit");
            };
            
        }
        
        // close scanner
        scanner.close();

    }
}