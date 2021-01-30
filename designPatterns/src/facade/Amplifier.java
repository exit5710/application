package facade;

public class Amplifier {
    private int volume;

    public void on() {
        System.out.println("Top-O-Line Amplifier on");
    }

    public void setDvd() {
        System.out.println("Top-O-Line Amplifier setting DVD player to Top-O-Line DVD player");
    }

    public void setSurroundSound() {
        System.out.println("Top-O-Line Amplifier surround sound on (5 speakers, 1 sub woofer)");
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Top-O-Line amplifier setting volume to " + getVolume());
    }

    public void off() {
        System.out.println("Top-O-Line Amplifier off");
    }
}