package Controller;

import Model.*;
import Util.IOHelper;
import View.ConsoleDisplay.AdvertisementDisplay;
import View.ConsoleDisplay.UserDisplay;
import edu.psu.consolemenu.Menu;
import edu.psu.consolemenu.MenuChoice;
import edu.psu.consolemenu.MenuDisplay;

import java.util.List;

public class Fauxdango {
    Menu menuMain = new Menu("Fauxdango");
    MenuChoice choiceMainRegister = menuMain.addMenuChoice("Register");
    MenuChoice choiceMainListAllMovies = menuMain.addMenuChoice("List All Movies");
    MenuChoice choiceMainListAllTheaters = menuMain.addMenuChoice("List All Theaters");
    MenuChoice choiceMainListAllActors = menuMain.addMenuChoice("List All Actors");
    MenuChoice choiceMainListAllShowings = menuMain.addMenuChoice("List All Showings");
    MenuChoice choiceMainSearchActorsByName = menuMain.addMenuChoice("Search Actors By Name");
    MenuChoice choiceMainSearchTheatersByName = menuMain.addMenuChoice("Search Theaters By Name");
    MenuChoice choiceMainSearchTheatersByZipcode = menuMain.addMenuChoice("Search Theaters By Zipcode");
    MenuChoice choiceMainSearchMoviesByTitle = menuMain.addMenuChoice("Search Movies By Title");
    MenuChoice choiceMainExit = menuMain.getMenuChoiceQuit();

    public void demo() {
        MenuDisplay md = new MenuDisplay(menuMain);
        AdvertisementBank adBank = new AdvertisementBank();

        AdvertisementDisplay adDisplay = new AdvertisementDisplay(adBank);

        User user;
        MenuChoice chosen = null;
        while (chosen != choiceMainExit) {
            adDisplay.displayNextAd();
            chosen = md.displayAndChoose();

            if (chosen == choiceMainRegister) {
                user = UserDisplay.registerUser();
                System.out.println();
                System.out.println("Welcome, " + user.toString());
            } else if (chosen == choiceMainListAllMovies) {
                IOHelper.printList(Datastore.getMovies());
            } else if (chosen == choiceMainListAllTheaters) {
                IOHelper.printList(Datastore.getTheaters());
            } else if (chosen == choiceMainListAllActors) {
                IOHelper.printList(Datastore.getActors());
            } else if (chosen == choiceMainListAllShowings) {
                IOHelper.printList(Datastore.getShowings());
            } else if (chosen == choiceMainSearchActorsByName) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter part of the name");
                List<Actor> foundActors = Datastore.searchActorsByName(text);
                IOHelper.printList(foundActors);
            } else if (chosen == choiceMainSearchTheatersByName) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter part of the name");
                List<Theater> foundTheaters = Datastore.searchTheatersByName(text);
                IOHelper.printList(foundTheaters);
            } else if (chosen == choiceMainSearchTheatersByZipcode) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter zipcode");
                List<Theater> foundTheaters = Datastore.searchTheatersByZipcode(text);
                IOHelper.printList(foundTheaters);
            } else if (chosen == choiceMainSearchMoviesByTitle) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter part of the title");
                List<Movie> foundMovies = Datastore.searchMoviesByTitle(text);
                IOHelper.printList(foundMovies);
            } else if (chosen == choiceMainExit) {
                System.out.println("Goodbye");
            }
        }
    }
}
