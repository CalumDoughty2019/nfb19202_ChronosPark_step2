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

class VisitorsTest {

    @Test
    void visitorCreateTest(){
        //Visitors(double height, boolean child, boolean heightPass, Boolean wheelchairQ, Boolean kidsQ, Boolean waterQ, Boolean horrorQ, Boolean adrenalineQ)
        Visitors visitor = new Visitors(1.3, true, false, true, true, true, true, true);

        assertEquals(visitor.getHeight(), 1.3);
        assertTrue(visitor.isChild());
        visitor.setChild(false);
        assertFalse(visitor.isChild());
    }

}
