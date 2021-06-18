/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideTest {

    @Test
    void rideDefaultTest(){
        //Ride(String name, int typeOfHeight, int anotherTypeOfHeight, double heightMax, double heightMin, boolean wheelchair, int groupMax, int groupMin, int theme, boolean adrenaline, boolean horror, boolean kids, boolean water)
        Ride ride = new Ride();

        assertEquals(ride.getName(), "Testing");
        assertNotEquals(ride.getTheme(), 2); //actual = 1

    }

    @Test
    void rideCreateTest(){
        //Ride(String name, int typeOfHeight, int anotherTypeOfHeight, double heightMax, double heightMin, boolean wheelchair, int groupMax, int groupMin, int theme, boolean adrenaline, boolean horror, boolean kids, boolean water)
        Ride ride = new Ride(8, "Tester", 1, 0, 0, 0, true, 0, 0, 1, false, true, false, true);

        assertEquals(ride.getHeightMin(), 0);

    }
}
