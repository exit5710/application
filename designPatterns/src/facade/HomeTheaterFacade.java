package facade;

public class HomeTheaterFacade {
    Amplifier amplifier;
    Tuner tuner;
    DvdPlayer dvdPlayer;
    CdPlayer cdPlayer;
    Projector projector;
    TheaterLight theaterLight;
    Screen screen;
    PopcornPopper popcornPopper;

    public HomeTheaterFacade(Amplifier amplifier, Tuner tuner, DvdPlayer dvdPlayer, CdPlayer cdPlayer, Projector projector, TheaterLight theaterLight, Screen screen, PopcornPopper popcornPopper) {
        this.amplifier = amplifier;
        this.tuner = tuner;
        this.dvdPlayer = dvdPlayer;
        this.cdPlayer = cdPlayer;
        this.projector = projector;
        this.theaterLight = theaterLight;
        this.screen = screen;
        this.popcornPopper = popcornPopper;
    }

    public void watchMovie(String dvdTitle) {
        System.out.println("Get ready to watch a movie...");
        popcornPopper.on();
        popcornPopper.pop();

        theaterLight.dim(10);

        screen.down();

        projector.on();
        projector.wideScreenMode();

        amplifier.on();
        amplifier.setDvd();
        amplifier.setSurroundSound();
        amplifier.setVolume(5);

        cdPlayer.off();

        tuner.on();

        dvdPlayer.on();
        dvdPlayer.play(dvdTitle);
    }

    public void endMovie() {
        System.out.println("\nShutting movie theater down...");
        popcornPopper.off();

        theaterLight.on();

        screen.up();

        projector.off();

        amplifier.off();

        tuner.off();

        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
    }
}