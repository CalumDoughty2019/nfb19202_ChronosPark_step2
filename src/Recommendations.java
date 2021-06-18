/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
//FOR helping me understand Regex patterns for email validation
https://www.tutorialspoint.com/javaexamples/regular_email.htm
//FOR helping me with decision tree
https://stackoverflow.com/questions/16159123/guessing-game-binary-tree-java
 */


/*
This class is used to identify if a user or group of users is eligible for specific rides
 */
public class Recommendations {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    RecoTreeBuilder decision = new RecoTreeBuilder();
    AllRidesTreeBuilder allRidesTree = new AllRidesTreeBuilder();

    //variables
    private String fname = null;
    private String email = null;
    ArrayList<Visitors> visitorArray = new ArrayList<>();
    ArrayList<Ride> comboRidesOne = new ArrayList<>();
    ArrayList<Ride> comboRidesTwo = new ArrayList<>();
    ArrayList<Ride> collatedRides = new ArrayList<>();

    SimpleDateFormat datestamp = new SimpleDateFormat("dd/MM/yyyy");
    public Date date = new Date();
    String storedDate;
    SimpleDateFormat timestamp = new SimpleDateFormat("HHmm");
    public Date time = new Date();
    String storedTime;


    String YES = "Y";
    String NO = "N";

    private double height = 0;
    private int partyNo;
    private int count = 0;
    private boolean allRidesNow;

    private String underage = null;
    private String wheelchairQ = null;
    private String kidsQ = null;
    private String waterQ = null;
    private String horrorQ = null;
    private String adrenalineQ = null;

    private int kidsOnly = 0;
    private int adultsOnly = 0;
    private int heightFailures = 0;
    private int wheelchairFailures = 0;
    private int kidsFailures = 0;
    private int waterFailures = 0;
    private int horrorFailures = 0;
    private int adrenalineFailures = 0;



    //getters and setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPartyNo() {
        return partyNo;
    }

    public void setPartyNo(int partyNo) {
        this.partyNo = partyNo;
    }

    //methods
    /*
    Use this to fulfill option 1
    Allow users to compare their group details to specific ride criteria to see if they are eligible to ride
     */
    public void singleRide(ArrayList<Ride> ridesArray) throws IOException, InterruptedException {
        allRidesNow = false;
        main(ridesArray);
        Ride rideSelected = RideSelection(ridesArray); //POTENTIALLY MOVE THIS into suitability for easier movement if they get groupSize error
        System.out.println();

        //group check
        if(!GroupCheck(rideSelected, partyNo)){
            System.out.println("**** Your party size is outside the bounds for this ride ****");
            System.out.println(rideSelected.getName() + " requires " + rideSelected.getGroupMin() + "-" + rideSelected.getGroupMax() + " people");
            TimeUnit.SECONDS.sleep(3);
            menu.CLI();
        }

        //get the suitability of a chosen ride for the current party
        for(int i = 0; i < partyNo; i++) {
            count = i + 1;
            suitability(getPartyNo());
            decision.runner(visitorArray.get(i)); //call decision to check an individuals suitability for the selected ride
            //height check
            boolean heightPass = HeightCheck(height, rideSelected);
            visitorArray.get(i).setHeightPass(heightPass);
        }
        System.out.println("Getting your recommendations....");
        System.out.println();

        //for each visitor in the array we wish to add their information together to check party's suitability for ride selected
        for (Visitors vis:visitorArray) {
            collate(vis, rideSelected);
        }

        //check success is the final step.
        //Here we notify the user if they are eligible or not
        checkSuccess();

        //reset all settings
        reset();

        //revision allows the client to go back and change their party's details
        revision(ridesArray);

        System.out.println("Goodbye!");
        System.out.println();
        menu.pressEnterToContinue();
    }


    /*
    Use this to fulfill option 2
    Allow users to compare their group details to all rides and see what rides they are eligible for
 */
    public void allRides(ArrayList<Ride> ridesArray) throws IOException, InterruptedException {
        allRidesNow = true;

        //clear all arrays used
        comboRidesOne.clear();
        comboRidesTwo.clear();
        collatedRides.clear();

        //get user basic info
        main(ridesArray);
        System.out.println();


        //get the suitability of all rides for all the party members
        for(int i = 0; i < partyNo; i++) {
            count = i + 1;
            suitability(getPartyNo());
            String answer = allRidesTree.runner(visitorArray.get(i));//call decision to check an individuals suitability

            //check message from tree to see which ride is viable
            for(int j = 0; j < answer.length(); j++){
                if (answer.charAt(j) == '1' && (HeightCheck(visitorArray.get(i).getHeight(), ridesArray.get(j)))) { //check height passes as well
                    if(!ridesArray.get(j).isWheelchair() && visitorArray.get(i).isWheelchairQ()){ //check to see if they pass the wheelchair check
                        //nothing
                    }else{
                        if(GroupCheck(ridesArray.get(j), partyNo)){ //check to see if this ride meets the group size expectations
                            if(ridesArray.get(j).getName().equals("Hatchling Nest")){ //Special case for Hatchling nest. height may not be necessary here
                                if(visitorArray.get(i).isChild()){ //check to ensure visitor is a child
                                    visitorArray.get(i).addToMyRideList(ridesArray.get(j));
                                }
                            }else{
                                visitorArray.get(i).addToMyRideList(ridesArray.get(j));
                            }
                        }
                    }
                }
            }

            //add a visitors ride choices to a single array
            comboRidesOne.addAll(visitorArray.get(i).getMyRideList());
        }

        //if rideId doesnt exist sizeOfParty times then bin it
        int counter = 0;
        for(int k = 0; k < comboRidesOne.size(); k++){ //for every Ride in the ridesArray, which is all 20
                for(int l = 0; l < comboRidesOne.size(); l++){ //then for each value in collated rides
                    if(comboRidesOne.get(l).getRideId() == comboRidesOne.get(k).getRideId()){ //check to see if there is more than one instance
                        counter++;
                    }
                }
                //if there is partySize amount of a single ride then add it to the second array as this is a ride agreed by all
                if(counter == visitorArray.size()){
                    comboRidesTwo.add(comboRidesOne.get(k));
                }
            counter = 0;
        }

        //now we are filtering to remove duplicates
        //then remove all duplicates by adding each instance of a ride into a new array
        for (Ride ride:comboRidesTwo) {
            if(!collatedRides.contains(ride)){
                collatedRides.add(ride);
            }
        }

        System.out.println("Getting your recommendations....");
        System.out.println();

        //print the results for the user
        int number = 1;
        int lastTheme = 0;
        System.out.println("Recommendations for " + getFname() + "'s party created on " + storedDate + " @" + storedTime);
        for (Ride ride: collatedRides) {
            if(ride.getTheme() != lastTheme){
                switch(ride.getTheme()){
                    case 1:
                        System.out.println("Medieval");
                        break;
                    case 2:
                        System.out.println("Futuristic");
                        break;
                    case 3:
                        System.out.println("Jurassic");
                        break;
                    case 4:
                        System.out.println("Industrial");
                        break;
                }
            }
            lastTheme = ride.getTheme();
            System.out.println("   " + number + ": " + ride.getName());
            number++;
        }
        if(number == 1){
            //if no suitable rides then print this
            System.out.println("    ***No suitable rides for your group. Please revise your choices.");
        }
        System.out.println();

        //reset the characteristics/variables used
        reset();

        //revision allows the client to go back and change their party's details
        revision(ridesArray);

        System.out.println("Goodbye!");
        System.out.println();
        menu.pressEnterToContinue();
    }


    /*
    main is used to get the initial user details
     */
    public void main(ArrayList<Ride> ridesArray) throws IOException, InterruptedException {
        String input;
        nameDetail(); //get users name

        //find users email preference
        boolean validInput;
        do {
            System.out.println("Hi " + getFname() + ", would you prefer me to print your results or email them to you? (Enter P to print OR E to mail)");
            validInput = false;
            System.out.print(">>> ");

            input = scanner.nextLine().toUpperCase();
            System.out.println("");
            menu.hotKeys(input);

            //Call appropriate email functionality from input
            switch (input) {
                case "P":
                    validInput = true;
                    System.out.println("You have selected Print");
                    break;
                case "E":
                    validInput = true;
                    System.out.println("You have chosen Email.");
                    emailDetail(); //calls a method to retrieve users email
                    break;
                default:
                    System.out.println("Please enter P OR E");
                    break;
            }
        } while (!validInput);

        //below is used to get amount of people in a users party
        setPartyNo(0);
        do {
            System.out.println("How many people are in your party?");
            System.out.print(">>> ");
            while (!scanner.hasNextInt()) {
                String checker = scanner.next();
                System.out.println(checker + " is not a valid number");
                System.out.print(">>> ");
            }
            partyNo = scanner.nextInt();
            if(partyNo > 8){
                System.out.println("No groups larger than 8");
            }
        }while(getPartyNo() < 0 || getPartyNo() > 8);
        System.out.println();

        //Set timestamps for later
        storedDate = datestamp.format(date);
        storedTime = timestamp.format(time);

    }


    /*
    Ride selection is used by the user to identify which ride they would like to check their party details against
     */
    public Ride RideSelection(ArrayList<Ride> ridesArray) throws IOException, InterruptedException {

        int number = 1;
        int input;
        Ride selection = null;
        int lastTheme = 0;

        System.out.println("---------------------------");
        System.out.println("RIDE SELECTION");
        System.out.println("---------------------------");
        for (Ride ride:ridesArray) {
            if(ride.getTheme() != lastTheme){
                switch(ride.getTheme()){
                    case 1:
                        System.out.println("Medieval");
                        break;
                    case 2:
                        System.out.println("Futuristic");
                        break;
                    case 3:
                        System.out.println("Jurassic");
                        break;
                    case 4:
                        System.out.println("Industrial");
                        break;
                }
            }
            lastTheme = ride.getTheme();
            System.out.println("   " + number + ": " + ride.getName());
            number++;
        }

        do {
            System.out.println("Please select a ride");
            System.out.print(">>> ");

            while(!scanner.hasNextInt()){
                String checker = scanner.next();
                menu.hotKeys(checker);
                System.out.println(checker + " is not a valid number");
                System.out.print(">>> ");
            }
            input = scanner.nextInt();
            if(input > ridesArray.size()){
                System.out.println("There are " + ridesArray.size() + " choices. Please select one of these.");
            }
            else{
                selection = ridesArray.get(input-1);
            }
        } while (input > ridesArray.size());
        return selection;
    }


    /*
    get the name of the user
     */
    public void nameDetail(){
        System.out.println("Please enter your first name: ");
        System.out.print(">>> ");
        String input = scanner.nextLine();
        setFname(input);
    }


    /*
    get the email of the user.
    Use a REGEX to ensure that the email format is met before accepting
     */
    public void emailDetail(){
        boolean checker;
        do {
            System.out.println("Please enter a valid email address:");
            String tempMail = "";
            tempMail = scanner.nextLine();

            String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            checker = tempMail.matches(EMAIL_REGEX);
            if (!checker) {
                System.out.println("Sorry, this (" + tempMail + ") is not a valid email address.");
            } else {
                System.out.println("Thank you, your recommendations will be emailed to " + tempMail);
                setEmail(tempMail);
            }
        } while (!checker);
        System.out.println();
    }


    /*
    Resets
     */
    public void reset(){
        //in case the user chooses to revise their inputs, we need an empty array
        //we also need to reset the counts
        count = 0; //reset count
        visitorArray.clear();
        kidsOnly = 0;
        adultsOnly = 0;
        heightFailures = 0;
        wheelchairFailures = 0;
        kidsFailures = 0;
        waterFailures = 0;
        horrorFailures = 0;
        adrenalineFailures = 0;
    }


    /*
    check party suitability to give recommendations of ride
     */
    public void suitability(int partyNo) throws IOException, InterruptedException {



        //Input process
        //for(int i = 0; i < partyNo; i++){
            //int count = i+1;
        heightMeasure();



            //Take in all relevant details
            do {
                System.out.print("Are you a wheelchair user? (Y/N): ");
                wheelchairQ = scanner.next().toUpperCase();

                if((!wheelchairQ.equals(NO)) && (!wheelchairQ.equals(YES))){
                    System.out.println("Invalid input");
                }
            }while((!wheelchairQ.equals(NO)) && (!wheelchairQ.equals(YES)));

            do {
                System.out.print("Is this person under 12 years of age? (Y/N): ");
                underage = scanner.next().toUpperCase();

                if((!underage.equals(NO)) && (!underage.equals(YES))){
                    System.out.println("Invalid input");
                }
            }while((!underage.equals(NO)) && (!underage.equals(YES)));

            System.out.println("Which of the following types of rides do you like? (Y/N)");
            do {
                System.out.print("  Kids       : ");
                kidsQ = scanner.next().toUpperCase();
                if((!kidsQ.equals(NO)) && (!kidsQ.equals(YES))){
                    System.out.println("Invalid input");
                }
            }while((!kidsQ.equals(NO)) && (!kidsQ.equals(YES)));

            do {
                System.out.print("  Water      : ");
                waterQ = scanner.next().toUpperCase();
                if((!waterQ.equals(NO)) && (!waterQ.equals(YES))){
                    System.out.println("Invalid input");
                }
            }while((!waterQ.equals(NO)) && (!waterQ.equals(YES)));

            do {
                System.out.print("  Horror     : ");
                horrorQ = scanner.next().toUpperCase();
                if((!horrorQ.equals(NO)) && (!horrorQ.equals(YES))){
                    System.out.println("Invalid input");
                }
            }while((!horrorQ.equals(NO)) && (!horrorQ.equals(YES)));

            do {
                System.out.print("  Adrenaline : ");
                adrenalineQ = scanner.next().toUpperCase();
                if((!adrenalineQ.equals(NO)) && (!adrenalineQ.equals(YES))){
                    System.out.println("Invalid input");
                }
            }while((!adrenalineQ.equals(NO)) && (!adrenalineQ.equals(YES)));
            System.out.println("");


            //changing these attributes to boolean to save memory later when passing through the Binary Tree
            boolean wheelchairBoolean = changeToBoolean(wheelchairQ);
            boolean child = changeToBoolean(underage);
            boolean kidsBoolean = changeToBoolean(kidsQ);
            boolean waterBoolean = changeToBoolean(waterQ);
            boolean horrorBoolean = changeToBoolean(horrorQ);
            boolean adrenalineBoolean = changeToBoolean(adrenalineQ);

            //create a new Visitor with these attributes
            Visitors visitor = new Visitors(height, child, wheelchairBoolean, kidsBoolean, waterBoolean, horrorBoolean, adrenalineBoolean);
            //decision.runner(visitor); //call decision to check an individuals suitability for the selected ride
            visitorArray.add(visitor); //add this user to the array
            System.out.println();

        //}
    }


    /*

     */
    private void heightMeasure() {
        System.out.println("PersonNo: " + count);
        System.out.println("Please use the height chart next to this terminal to measure your height");
        do {
            System.out.print("What height are you? (in metres e.g. 1.40): ");
            while (!scanner.hasNextDouble()) {
                String checker = scanner.next();
                System.out.println(checker + " is not a valid number");
                System.out.print("What height are you? (in metres e.g. 1.40): ");
            }
            height = scanner.nextDouble();
        }while(height < 0);

        if(height > 2.2){
            System.out.println("Are you really that tall?");
            do {
                System.out.print("Please re-input your height: ");
                while(!scanner.hasNextDouble()){
                    String checker = scanner.next();
                    System.out.println(checker + " is not a valid number");
                    System.out.print("What height are you? (in metres e.g. 1.40): ");
                }
                height = scanner.nextDouble();
            }while(height < 0);
        }
    }


    /*
    revision allows the client to go back and change their party's details
     */
    public void revision(ArrayList<Ride> ridesArray) throws IOException, InterruptedException {
        String input;
        String buffer = scanner.nextLine(); //picks up broken keypress so false error isn't raised

        boolean validInput;
        do {
            System.out.println("Do you want to revise your preferences or continue to print/email? (Enter R to revise OR C to continue)");
            validInput = false;
            System.out.print(">>> ");

            input = scanner.nextLine().toUpperCase();
            System.out.println("");
            menu.hotKeys(input);

            //Call appropriate email functionality from input
            switch (input) {
                case "R":
                    validInput = true;
                    System.out.println("You have selected Revision");
                    //Go back to suitability to change party details
                    if(allRidesNow){
                        allRides(ridesArray);
                    }else{
                        singleRide(ridesArray);
                    }
                    break;
                case "C":
                    validInput = true;
                    if(getEmail() != null){
                        System.out.println("Thank you " + getFname() + ", your recommendations have been emailed to " + getEmail() + "");
                        System.out.println("Please enjoy the rest of your day at Chronos Park");
                    }
                    else{
                        //when printing give a delay so that there is time for the document to print
                        System.out.print("Printing");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.print(".");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.print(".");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.print(".");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Please enter R OR C");
                    break;
            }
        } while (!validInput);
    }


    /*
    check success is the final step.
    Here we notify the user if they are eligible or not
     */
    public void checkSuccess() {
        System.out.println("Recommendations for " + getFname() + "'s party created on " + storedDate + " @" + storedTime);
        if((heightFailures == 0) && (kidsOnly == 0) && (wheelchairFailures == 0) && (kidsFailures == 0) && (waterFailures == 0) && (horrorFailures == 0) && (adrenalineFailures == 0)){
            System.out.println("Based on your inputs, this ride IS suitable for your party !!!");
            System.out.println("Congrats, we hope you enjoy the ride and everything Chronos Park has to offer :)");
        }else{
            System.out.println("Based on your inputs, this ride is NOT suitable for your party because:");
            if(heightFailures > 0){
                System.out.println(heightFailures + " out of " + partyNo + " of your party are outwith the height range.");
            }
            if(kidsOnly > 0){
                System.out.println(kidsOnly + " out of " + partyNo + " of your party are not children/too tall and this ride is for children ONLY.");
            }
            if(adultsOnly > 0){
                System.out.println(adultsOnly + " out of " + partyNo + " of your party ARE children and this ride is for adults ONLY.");
            }
            if(wheelchairFailures > 0){
                System.out.println(wheelchairFailures + " out of " + partyNo + " of your party require wheelchairs.");
            }
            if(kidsFailures > 0){
                System.out.println(kidsFailures + " out of " + partyNo + " in your party do not like kids rides.");
            }
            if(waterFailures > 0){
                System.out.println(waterFailures + " out of " + partyNo + " in your party do not like water rides.");
            }
            if(horrorFailures > 0){
                System.out.println(horrorFailures + " out of " + partyNo + " in your party do not like horror rides.");
            }
            if(adrenalineFailures > 0){
                System.out.println(adrenalineFailures + " out of " + partyNo + " in your party do not like adrenaline rides.");
            }
        }
        System.out.println();
    }


    /*
    judge each persons input and collate this information to logically identify whether the party is elligible for this ride
     */
    public void collate(Visitors vis, Ride rideSelected) {
        if(!vis.isHeightPass()){heightFailures++;} //already checked to mark 'true' or 'false' earlier
        //if the ride selected is EXCLUSIVELY for kids && the visitor is NOT a kid then fail
        if((rideSelected.getName().equals("Hatchling Nest")) && ((!vis.isChild()) || (height >= 1))){kidsOnly++;}
        //if the ride selected is NOT suitable for kids && the visitor IS a kid then fail
        if((!rideSelected.isKids()) && (vis.isChild())){adultsOnly++;}
        //if the ride selected is NOT wheelchair friendly && the visitor has a wheelchair then fail
        if(!rideSelected.isWheelchair() && vis.isWheelchairQ()){wheelchairFailures++;}
        //if the ride selected IS for kids && the visitor doesnt like kids rides then fail
        if(rideSelected.isKids() && !vis.isKidsQ()){kidsFailures++;}
        //if the ride selected IS water ride && the visitor doesnt like water rides then fail
        if(rideSelected.isWater() && !vis.isWaterQ()){waterFailures++;}
        //if the ride selected IS horror ride && the visitor doesnt like horror rides then fail
        if(rideSelected.isHorror() && !vis.isHorrorQ()){horrorFailures++;}
        //if the ride selected IS adrenaline ride && the visitor doesnt like adrenaline rides then fail
        if(rideSelected.isAdrenaline() && !vis.isAdrenalineQ()){adrenalineFailures++;}

        //if(vis.isAdrenalineQ() == rideSelected.isAdrenaline()){adrenalineFailures++;}
    }


    /*
    change a string value to a boolean
     */
    public boolean changeToBoolean(String toBoolean) {
        return toBoolean.equals(YES);
    }


    /*
    Check if the group size is too big or small for the ride selected
     */
    public static boolean GroupCheck(Ride rideSelected, int partyNo) {
        if(rideSelected.getGroupMax() == 0 && rideSelected.getGroupMin() == 0){
            return true;
        } else if(partyNo <= rideSelected.getGroupMax() && partyNo >= rideSelected.getGroupMin()){
            return true;
        }else{
            return false;
        }
    }


    /*
    Check if a users height is too big or small for a ride
     */
    public static boolean HeightCheck(double height, Ride rideSelected) {
        //if the ride has a second height spec then do this
        //0="Not Applicable", 1=">", 2=">=", 3="<", 4="<="
        if(rideSelected.getAnotherTypeOfHeight() > 0){
            //do ranged approach rather than long, intricate switch statement
            if(height > rideSelected.getHeightMin() && height < rideSelected.getHeightMax()){
                return true;
            }else{
                return false;
            }
        }else{
            //if the visitor CAN ride (is within acceptable height range) then they go RIGHT(Y)
            switch(rideSelected.getTypeOfHeight()){
                case 0:
                    return true;
                case 1:
                    //1=">"
                    if(height > rideSelected.getHeightMin()){
                        //if the visitor is greater than the minimum height requirement then go right
                        return true;
                    }else{
                        //if the visitor is shorter than the minimum height requirement then go left
                        return false;
                    }
                case 2:
                    //2=">="
                    if(height >= rideSelected.getHeightMin()){
                        return true;
                    }else{
                        return false;
                    }
                case 3:
                    //3="<"
                    if(height < rideSelected.getHeightMax()){
                        return true;
                    }else{
                        return false;
                    }
                case 4:
                    //3="<"
                    if(height <= rideSelected.getHeightMax()){
                        return true;
                    }else{
                        return false;
                    }
            }
        }
        return false;
    }

}
