/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

/*
This class is the blueprint for all visitors
 */
public class Visitors {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();

    //variables
    private double height;
    private boolean child;
    private boolean heightPass;
    private boolean wheelchairQ;
    private boolean kidsQ;
    private boolean waterQ;
    private boolean horrorQ;
    private boolean adrenalineQ;
    private ArrayList<Ride> myRideList = new ArrayList<>();

    //constructor
    public Visitors(){
    }

    //full constructor
    public Visitors(double height, boolean child, boolean heightPass, Boolean wheelchairQ, Boolean kidsQ, Boolean waterQ, Boolean horrorQ, Boolean adrenalineQ){
        this.height = height;
        this.child = child;
        this.heightPass = heightPass;
        this.wheelchairQ = wheelchairQ;
        this.kidsQ = kidsQ;
        this.waterQ = waterQ;
        this.horrorQ = horrorQ;
        this.adrenalineQ = adrenalineQ;
    }

    //most constructor minus height pass
    public Visitors(double height, boolean child, Boolean wheelchairQ, Boolean kidsQ, Boolean waterQ, Boolean horrorQ, Boolean adrenalineQ){
        this.height = height;
        this.child = child;
        this.wheelchairQ = wheelchairQ;
        this.kidsQ = kidsQ;
        this.waterQ = waterQ;
        this.horrorQ = horrorQ;
        this.adrenalineQ = adrenalineQ;
    }

    //half constructor
    public Visitors(double height, boolean heightPass, Boolean wheelchairQ){
        this.height = height;
        this.heightPass = heightPass;
        this.wheelchairQ = wheelchairQ;
    }

    //getters and setters
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isChild() {
        return child;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    public boolean isHeightPass() {
        return heightPass;
    }

    public void setHeightPass(boolean heightPass) {
        this.heightPass = heightPass;
    }

    public boolean isWheelchairQ() {
        return wheelchairQ;
    }

    public void setWheelchairQ(boolean wheelchairQ) {
        this.wheelchairQ = wheelchairQ;
    }

    public boolean isKidsQ() {
        return kidsQ;
    }

    public void setKidsQ(boolean kidsQ) {
        this.kidsQ = kidsQ;
    }

    public boolean isWaterQ() {
        return waterQ;
    }

    public void setWaterQ(boolean waterQ) {
        this.waterQ = waterQ;
    }

    public boolean isHorrorQ() {
        return horrorQ;
    }

    public void setHorrorQ(boolean horrorQ) {
        this.horrorQ = horrorQ;
    }

    public boolean isAdrenalineQ() {
        return adrenalineQ;
    }

    public void setAdrenalineQ(boolean adrenalineQ) {
        this.adrenalineQ = adrenalineQ;
    }

    public ArrayList<Ride> getMyRideList() {
        return myRideList;
    }

    public void setMyRideList(ArrayList<Ride> myRideList) {
        this.myRideList = myRideList;
    }

    public void addToMyRideList(Ride ride){
        myRideList.add(ride);
    }

    //Methods
    //add new visitor
    public void newVisitor(double height, boolean heightPass, Boolean wheelchairQ, Boolean kidsQ, Boolean waterQ, Boolean horrorQ, Boolean adrenalineQ){
        this.height = height;
        this.heightPass = heightPass;
        this.wheelchairQ = wheelchairQ;
        this.kidsQ = kidsQ;
        this.waterQ = waterQ;
        this.horrorQ = horrorQ;
        this.adrenalineQ = adrenalineQ;
    }

}
