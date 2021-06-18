/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
This is the main meu class where the user will make their decision for which action they would like to take
 */
public class Menu {

    public void CLI() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Recommendations reco = new Recommendations();

        //build all rides
        Rides rides = new Rides();
        ArrayList<Ride> ridesArray = new ArrayList<>();
        ridesArray = rides.buildRides();


        System.out.println();
        System.out.println("=========================================");
        System.out.println("Welcome to Chronos Park");

        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("   1. Get recommendations for a single ride");
        System.out.println("   2. Get recommendations for the entire park");
        System.out.println("   quit  -This can be typed at any options screen to exit the program");
        System.out.println("   menu  -This can be typed at any options screen to come back to menu");
        System.out.println();

        String input;
        boolean validInput;
        do {
            validInput = false;
            System.out.print(">>> ");

            input = scanner.nextLine();
            System.out.println("");

            //Call appropriate functionality from input
            switch (input) {
                case "1":
                    validInput = true;
                    System.out.println("Get your recommendation");
                    reco.singleRide(ridesArray);
                    break;
                case "2":
                    validInput = true;
                    System.out.println("Get your recommendation");
                    reco.allRides(ridesArray);
                    break;
                case "menu":
                    validInput = true;
                    CLI();
                    break;
                case "quit":
                    validInput = true;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Please select one of the options above");
                    break;
            }
        } while (!validInput);
    }


    /*
    Press enter button to proceed to next program stage
     */
    public void pressEnterToContinue() {
        System.out.println("** Press ENTER to return to menu **");
        try {
            System.in.read();
            CLI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    This is used to allow users to return to menu or quit throughout the program
     */
    public void hotKeys(String hotKey) throws IOException, InterruptedException {
        if (hotKey.equals("quit") || hotKey.equals("QUIT")) {
            System.exit(0);
        } else if (hotKey.equals("menu") || hotKey.equals("MENU")) {
            CLI();
        }
    }
}
