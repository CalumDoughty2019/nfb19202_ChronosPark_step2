/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

/*
Ride is a blueprint which can be used to build all rides off of
 */
public class Ride {

    //attributes
    private int rideId;
    private String name;
    private int typeOfHeight; //0="Not Applicable", 1=">", 2=">=", 3="<", 4="<="
    private int anotherTypeOfHeight; //0="Not Applicable", 1=">", 2=">=", 3="<", 4="<="
    private double heightMax;
    private double heightMin;
    private boolean wheelchair;
    private int groupMax;
    private int groupMin;
    private int theme; //1=Medieval, 2=Futuristic, 3=Jurassic, 4=Industrial
    private boolean adrenaline;
    private boolean horror;
    private boolean kids;
    private boolean water;

    //constructors
    //default constructor
    public Ride(){
        rideId = 8;
        name = "Testing";
        typeOfHeight = 1;
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
    }

    //full constructor
    public Ride(int rideId, String name, int typeOfHeight, int anotherTypeOfHeight, double heightMax, double heightMin, boolean wheelchair, int groupMax, int groupMin, int theme, boolean adrenaline,
                     boolean horror, boolean kids, boolean water){
        this.rideId = rideId;
        this.name = name;
        this.typeOfHeight = typeOfHeight;
        this.anotherTypeOfHeight = anotherTypeOfHeight;
        this.heightMax = heightMax;
        this.heightMin = heightMin;
        this.wheelchair = wheelchair;
        this.groupMax = groupMax;
        this.groupMin = groupMin;
        this.theme = theme;
        this.adrenaline = adrenaline;
        this.horror = horror;
        this.kids = kids;
        this.water = water;
    }


    //getters and setters
    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeOfHeight() {
        return typeOfHeight;
    }

    public void setTypeOfHeight(int typeOfHeight) {
        this.typeOfHeight = typeOfHeight;
    }

    public int getAnotherTypeOfHeight() {
        return anotherTypeOfHeight;
    }

    public void setAnotherTypeOfHeight(int anotherTypeOfHeight) {
        this.anotherTypeOfHeight = anotherTypeOfHeight;
    }

    public double getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(double heightMax) {
        this.heightMax = heightMax;
    }

    public double getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(double heightMin) {
        this.heightMin = heightMin;
    }

    public boolean isWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(boolean wheelchair) {
        this.wheelchair = wheelchair;
    }

    public int getGroupMax() {
        return groupMax;
    }

    public void setGroupMax(int groupMax) {
        this.groupMax = groupMax;
    }

    public int getGroupMin() {
        return groupMin;
    }

    public void setGroupMin(int groupMin) {
        this.groupMin = groupMin;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public boolean isAdrenaline() {
        return adrenaline;
    }

    public void setAdrenaline(boolean adrenaline) {
        this.adrenaline = adrenaline;
    }

    public boolean isHorror() {
        return horror;
    }

    public void setHorror(boolean horror) {
        this.horror = horror;
    }

    public boolean isKids() {
        return kids;
    }

    public void setKids(boolean kids) {
        this.kids = kids;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }
}
