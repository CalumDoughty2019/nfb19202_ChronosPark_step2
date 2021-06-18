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

class RecommendationsTest {
    Recommendations reco = new Recommendations();
    Ride ride = new Ride();
    /*
    name = "Testing";
    typeOfHeight = 1; ">"
    anotherTypeOfHeight = 0;
    heightMax = 3;
    heightMin = 2;
    wheelchair = true;
    groupMax = 4;
    groupMin = 2;
    theme = 1;
    adrenaline = true;
    horror = true;
    kids = true;
    water = true;
    */

    @Test
    void suitabilityTest() {
    }

    //check that this method changes String values to boolean
    @Test
    void changeToBooleanTest(){

        boolean holder = reco.changeToBoolean("Y");
        assertTrue(holder);

        holder = reco.changeToBoolean("N");
        assertFalse(holder);
    }

    //check to see if the group check functionality works
    @Test
    void groupCheckTest(){

        boolean holder = reco.GroupCheck(ride, 4);
        assertTrue(holder);

        holder = reco.GroupCheck(ride, 7);
        assertFalse(holder);
    }

    //check to see if the height check functionality works
    @Test
    void heightCheckTest(){

        boolean holder = reco.HeightCheck(2.7, ride);
        assertTrue(holder);

        holder = reco.HeightCheck(1.7, ride);
        assertFalse(holder);
    }

}
