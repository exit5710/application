package command.Tv;

public class Tv {
    private String location;
    private String input;
    private int channel;

    public Tv(String location) {
        setLocation(location);
    }

    public void on() {
        System.out.println(getLocation() + " TV is on");
    }

    public void off() {
        System.out.println(getLocation() + " TV is off");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        System.out.println(getLocation() + " TV input is set for " + getInput());
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}