package facade;

public class HomeTheaterFacadeDriver {
    public static void main(String[] args) {
        Amplifier amplifier = new Amplifier();
        CdPlayer cdPlayer = new CdPlayer();
        DvdPlayer dvdPlayer = new DvdPlayer();
        PopcornPopper popcornPopper = new PopcornPopper();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLight theaterLight = new TheaterLight();
        Tuner tuner = new Tuner();

        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(amplifier, tuner, dvdPlayer, cdPlayer, projector, theaterLight, screen, popcornPopper);
        homeTheaterFacade.watchMovie("Avengers EndGame");
        homeTheaterFacade.endMovie();
    }
}