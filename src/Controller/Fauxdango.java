package Controller;

import Model.*;
import Util.IOHelper;
import View.ConsoleDisplay.AdvertisementDisplay;
import View.ConsoleDisplay.UserDisplay;
import edu.psu.consolemenu.Menu;
import edu.psu.consolemenu.MenuChoice;
import edu.psu.consolemenu.MenuDisplay;

import javax.xml.crypto.Data;

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

                Datastore.getMovies().forEach(movie -> System.out.println(movie.toString()));
            } else if (chosen == choiceMainListAllTheaters) {

                Datastore.getTheaters().forEach(theater -> System.out.println(theater.toString()));
            } else if (chosen == choiceMainListAllActors) {
                for (Actor actor : Datastore.getActors()) {
                    System.out.println(actor.toString());
                }
            } else if (chosen == choiceMainListAllShowings) {
                for (Showing showing : Datastore.getShowings()) {
                    System.out.println(showing.toString());
                }
            } else if (chosen == choiceMainSearchActorsByName) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter part of the name");
                for (Actor actor : Datastore.searchActorsByName(text)) {
                    System.out.println(actor.toString());
                }
            } else if (chosen == choiceMainSearchTheatersByName) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter part of the name");
                for (Theater theater : Datastore.searchTheatersByName(text)) {
                    System.out.println(theater.toString());
                }
            } else if (chosen == choiceMainSearchTheatersByZipcode) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter zipcode");
                for (Theater theater : Datastore.searchTheatersByZipcode(text)) {
                    System.out.println(theater.toString());
                }
            } else if (chosen == choiceMainSearchMoviesByTitle) {
                String text = IOHelper.readNonBlankStringFromKeyboard("Enter part of the title");
                for (Movie movie : Datastore.searchMoviesByTitle(text)) {
                    System.out.println(movie.toString());
                }
            } else if (chosen == choiceMainExit) {
                System.out.println("Goodbye");
            }

        }

    }
}
