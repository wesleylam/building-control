package wesley;
import java.util.TimerTask;

class TimePass extends TimerTask {
    Building building;
    public TimePass(Building building){
        super();
        this.building = building;
    }

    public void run() {
        // pass time for every room
        for (Room room : this.building.getRooms()){
            room.passTime();
        }
        // reset cooling/heating control
        this.building.tempControl(false);
    }
}