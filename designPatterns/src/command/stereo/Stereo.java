package command.stereo;

public class Stereo {
    private int volume;
    private String location;

    public Stereo(String location) {
        setLocation(location);
    }

    public void on() {
        System.out.println(getLocation() + " stereo on");
    }

    public void off() {
        System.out.println(getLocation() + " stereo off");
    }

    public void setCd() {
        System.out.println(getLocation() + " stereo setCd");
    }

    public void setDvd() {
        System.out.println(getLocation() + " stereo setDvd");
    }

    public void setRadio() {
        System.out.println(getLocation() + " stereo setRadio");
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(getLocation() + " stereo volume is " + getVolume());
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}