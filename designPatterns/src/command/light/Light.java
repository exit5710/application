package command.light;

public class Light {
    private String location;

    public Light() {
    }

    public Light(String location) {
        setLocation(location);
    }

    public void on() {
        System.out.println(getLocation() + " light on");
    }

    public void off() {
        System.out.println(getLocation() + " light off");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}