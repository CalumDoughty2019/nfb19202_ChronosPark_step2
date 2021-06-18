/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import java.io.IOException;

/*/
This is the runner class where the program is run from
 */
public class Play {

    //Run Implementation
    public static void main(String[] args) throws IOException, InterruptedException {
        Menu menu = new Menu();
        menu.CLI();
    }
}
