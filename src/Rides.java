/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import java.util.ArrayList;

/*
this is where all of the rides will be built
 */
public class Rides extends Ride {

    //THEMES::: 1=Medieval, 2=Futuristic, 3=Jurassic, 4=Industrial
    //TYPE OF HEIGHT::: 0="Not Applicable", 1=">", 2=">=", 3="<", 4="<="
    public ArrayList<Ride> buildRides(){
        ArrayList<Ride> rides = new ArrayList<>();
        //Ride ToT = new Ride("Tower of Terror", 0, 0, 0, true, 0, 0, 1, false, true, false, true);
        //rides.add(ToT);

        //Medieval
        /*1*/rides.add(new Ride(1, "Tower of Terror", 0, 0, 0, 0, true, 0, 0, 1, false, true, false, true));
        rides.add(new Ride(2, "The Iron Jaws", 2, 0, 0, 1, false, 0, 0, 1, true, false, false, false));
        rides.add(new Ride(3, "Cloister of Cruelty", 0, 0, 0, 0, true, 6, 5, 1, true, true, false, true));
        rides.add(new Ride(4, "Pony Jousts", 1, 3, 1.2, 0.8, false, 0, 0, 1, false, false, true, false));
        rides.add(new Ride(5, "Mystic Fortunes", 0, 0, 0, 0, true, 1, 1, 1, true, true, false, false));

        //Futuristic
        rides.add(new Ride(6, "Crisis at Farpoint", 0, 0, 0, 0, true, 8, 4, 2, true, false, true, true));
        rides.add(new Ride(7, "Build a Bot", 0, 0, 0, 0, true, 0, 0, 2, false, false, true, false));
        rides.add(new Ride(8, "Laser Lock", 1, 0, 0, 1.1, false, 6, 2, 2, true, true, false, false));
        rides.add(new Ride(9, "Trench Chase", 3, 0, 1.6, 0, true, 0, 0, 2, true, false, true, false));
        rides.add(new Ride(10, "Robot Conflicts", 0, 0, 0, 0, true, 0, 0, 2, true, true, true, false));

        //Jurassic
        rides.add(new Ride(11, "Rex Rampage", 0, 0, 0, 0, false, 2, 2, 3, true, true, false, true));
        rides.add(new Ride(12, "Pet a Saur", 3, 0, 1, 0, true, 0, 0, 3, false, false, true, false));
        rides.add(new Ride(13, "SauroPods", 1, 0, 1, 0, false, 0, 0, 3, true, false, false, true));
        rides.add(new Ride(14, "Raptor Races", 0, 0, 0, 0, true, 2, 2, 3, true, false, true, false));
        rides.add(new Ride(15, "Hatchling Nest", 3, 0, 1, 0, true, 0, 0, 3, false, false, true, false));

        //Industrial
        rides.add(new Ride(16, "High Noon", 0, 0, 0, 0, true, 2, 2, 4, true, false, true, false));
        rides.add(new Ride(17, "Hall O Mirrors", 0, 0, 0, 0, true, 0, 0, 4, true, false, false, false));
        rides.add(new Ride(18, "Steampunk Cups", 0, 0, 0, 0, false, 6, 3, 4, true, true, true, false));
        rides.add(new Ride(19, "The Pain Train", 1, 0, 0, 1, true, 8, 2, 4, true, true, false, true));
        rides.add(new Ride(20, "The Descent", 0, 0, 0, 0, false, 0, 0, 4, true, true, true, true));

        return rides;
    }
}
