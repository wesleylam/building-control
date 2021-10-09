package wesley;
import java.util.Scanner;
import java.util.Timer;

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
        building.tempControl(true);

        // change temperature every 0.1 second
        Timer timer = new Timer();
        timer.schedule(new TimePass(building), 0, 100);

        // Allow custom input from user
        Scanner scanner = new Scanner(System.in);  
        String input = "";
        Double temp;
        Boolean quit = false;
        // main loop
        while (! quit){
            // prompt user input
            System.out.println("Enter an option: \n - A new temperature for the building \n - quit/q (quit program)");
            input = scanner.next();
            
            // quit program
            if (input.toLowerCase().equals("quit") || input.toLowerCase().equals("q")){ 
                quit = true;
            }
            else {
                // set building temperature
                try { 
                    temp = Double.valueOf(input); 
                    System.out.println(String.format("Building temperature set to %.2f", temp));
                    building.tempSetPoint = temp;
                    // alter control
                    building.tempControl(true);
                }
                catch ( NumberFormatException e ) {
                    System.out.println("Input a valid number OR quit");
                };
            }
            
        }
        
        // close scanner
        scanner.close();
        // stop timer
        timer.cancel();

    }
}