package command.ceilingFan;

public class CeilingFan {
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    private String location;
    private int speed;

    public CeilingFan(String location) {
        setLocation(location);
        setSpeed(OFF);
    }

    public void high() {
        setSpeed(HIGH);
        System.out.println(getLocation() + " ceiling fan is high");
    }

    public void medium() {
        setSpeed(MEDIUM);
        System.out.println(getLocation() + " ceiling fan is medium");
    }

    public void low() {
        setSpeed(LOW);
        System.out.println(getLocation() + " ceiling fan is low");
    }

    public void off() {
        setSpeed(OFF);
        System.out.println(getLocation() + " ceiling fan is off");
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}