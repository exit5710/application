package facade;

public class DvdPlayer {
    private String dvdTitle;

    public void on() {
        System.out.println("Top-O-Line DVD player on");
    }

    public void play(String dvdTitle) {
        setDvdTitle(dvdTitle);
        System.out.println("Top-O-Line DVD player playing \"" + getDvdTitle() + "\"");
    }

    public void stop() {
        System.out.println("Top-O-Line DVD player stopped \"" + getDvdTitle() + "\"");
    }

    public void eject() {
        System.out.println("Top-O-Line DVD player eject");
    }

    public void off() {
        System.out.println("Top-O-Line DVD player off");
    }

    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }
}