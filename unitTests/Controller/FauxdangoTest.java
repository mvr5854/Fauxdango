package Controller;

import Model.User;
import Util.IOHelper;
import View.ConsoleDisplay.UserDisplay;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FauxdangoTest {
    private Fauxdango fauxdango;
    private ByteArrayOutputStream consoleOut;

    @BeforeEach
    void setUp() {
        fauxdango = new Fauxdango();
        consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));
    }

    void setInputStream(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    String getNormalisedOutput(String output) {
        output = output.replaceAll("\r", "");
        StringBuilder normalisedOutput = new StringBuilder();
        String[] outList = output.split("Choice: |\n===");

        for (String out : Arrays.copyOfRange(outList, 1, outList.length)) {
            if (!out.contains("Fauxdango")) {
                normalisedOutput.append(out.trim()).append("\n");
            }
        }

        return normalisedOutput.toString();
    }

    User mockUserRegistration() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailAddress("john@psu.edu");
        return user;
    }

    @Test
    @Order(1)
    void demo__choose_register__prompt_registration() {
        String expectedOutput = "Welcome, John Doe (john@psu.edu)";
        setInputStream("1\n0\n2\n0\n3\n0\n4\n0\n5\n0\n6\n0\n7\n0\n8\n0\n9\n0\n");
        try (MockedStatic<UserDisplay> mockedUserDisplay = Mockito.mockStatic(UserDisplay.class)) {
            mockedUserDisplay.when(UserDisplay::registerUser)
                    .thenReturn(mockUserRegistration());
            fauxdango.demo();
            String printResult = consoleOut.toString();
            assertTrue(printResult.contains(expectedOutput));
        }
    }

    @Test
    @Order(2)
    void demo__choose_listAllMovies__print_allMovies() {
        String expectedOutput = "Top Gun (PG, 1986) [ACTION, DRAMA] {110 min}\n" +
                "This Is Spinal Tap (R, 1984) [COMEDY, DOCUMENTARY] {84 min}\n" +
                "Halloween (R, 1978) [HORROR] {91 min}\n" +
                "Escape from New York (R, 1981) [ACTION] {99 min}\n" +
                "Goodbye\n";
        setInputStream("2\n0");

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertEquals(expectedOutput, normalisedOutput);
    }

    @Test
    @Order(3)
    void demo__choose_listAllTheaters__print_allTheaters() {
        String expectedOutput = "AMC Neshaminy 24 (660 Neshaminy Mall, Bensalem, PA 19020) [(215) 396-8050]\n" +
                "Regal UA Oxford Valley (403 Middletown Blvd, Langhorne, PA 19047) [(844) 462-7342]\n" +
                "Goodbye\n";
        setInputStream("3\n0\n");

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertEquals(expectedOutput, normalisedOutput);
    }

    @Test
    @Order(4)
    void demo__choose_listAllActors__print_allActors() {
        String expectedOutput = "Tom Cruise (1963-07-03)\n" + "Kelly McGillis (1957-07-09)\n" +
                "Michael McKean (1947-10-17)\n" + "Christopher Guest (1948-02-05)\n" +
                "Jaimie Lee Curtis (1958-11-22)\n" + "Donald Pleasence (1919-10-05)\n" +
                "Kurt Russell (1951-03-17)\n" + "Goodbye\n";
        setInputStream("4\n0\n");

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertEquals(expectedOutput, normalisedOutput);
    }

    @Test
    @Order(5)
    void demo__choose_listAllShowings__print_allShowings() {
        String expectedOutput = "Top Gun (PG, 1986) [ACTION, DRAMA] {110 min} {13:00-14:50}\n" +
                "Escape from New York (R, 1981) [ACTION] {99 min} {16:00-17:39}\n" +
                "Halloween (R, 1978) [HORROR] {91 min} {18:00-19:31}\n" +
                "Top Gun (PG, 1986) [ACTION, DRAMA] {110 min} {17:30-19:20}\n" +
                "Escape from New York (R, 1981) [ACTION] {99 min} {19:15-20:54}\n" +
                "This Is Spinal Tap (R, 1984) [COMEDY, DOCUMENTARY] {84 min} {10:00-11:24}\n" +
                "This Is Spinal Tap (R, 1984) [COMEDY, DOCUMENTARY] {84 min} {12:45-14:09}\n" +
                "Halloween (R, 1978) [HORROR] {91 min} {23:00-00:31}\n" +
                "Goodbye\n";
        setInputStream("5\n0\n");

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertEquals(expectedOutput, normalisedOutput);
    }

    @Test
    @Order(6)
    void demo__choose_searchActors__print_foundActors() {
        String expectedOutput = "Tom Cruise (1963-07-03)\n" +
                "Donald Pleasence (1919-10-05)\n" +
                "Kurt Russell (1951-03-17)\n" +
                "Goodbye\n";
        setInputStream("6\n0\n");

        try (MockedStatic<IOHelper> mockedIOHelper = Mockito.mockStatic(IOHelper.class)) {
            mockedIOHelper.when(() -> IOHelper.readNonBlankStringFromKeyboard("Enter part of the name")).thenReturn("se");
            fauxdango.demo();
            String printResult = consoleOut.toString();
            String normalisedOutput = getNormalisedOutput(printResult);
            assertEquals(expectedOutput, normalisedOutput);
        }
    }

    @Test
    @Order(7)
    void demo__choose_searchTheatersByName__print_foundTheaters() {
        String expectedOutput = "AMC Neshaminy 24 (660 Neshaminy Mall, Bensalem, PA 19020) [(215) 396-8050]\n" +
                "Goodbye\n";
        setInputStream("7\n0\n");

        try (MockedStatic<IOHelper> mockedIOHelper = Mockito.mockStatic(IOHelper.class)) {
            mockedIOHelper.when(() -> IOHelper.readNonBlankStringFromKeyboard("Enter part of the name")).thenReturn("amc");
            fauxdango.demo();
            String printResult = consoleOut.toString();
            String normalisedOutput = getNormalisedOutput(printResult);
            assertEquals(expectedOutput, normalisedOutput);
        }
    }

    @Test
    @Order(8)
    void demo__choose_searchTheatersByZipcode__print_foundTheaters() {
        String expectedOutput = "Regal UA Oxford Valley (403 Middletown Blvd, Langhorne, PA 19047) [(844) 462-7342]\n" +
                "Goodbye\n";
        setInputStream("8\n0\n");

        try (MockedStatic<IOHelper> mockedIOHelper = Mockito.mockStatic(IOHelper.class)) {
            mockedIOHelper.when(() -> IOHelper.readNonBlankStringFromKeyboard("Enter zipcode")).thenReturn("19047");
            fauxdango.demo();
            String printResult = consoleOut.toString();
            String normalisedOutput = getNormalisedOutput(printResult);
            assertEquals(expectedOutput, normalisedOutput);
        }
    }

    @Test
    @Order(9)
    void demo__choose_searchMovies__print_foundMovies() {
        String expectedOutput = "Escape from New York (R, 1981) [ACTION] {99 min}\n" +
                "Goodbye\n";
        setInputStream("9\n0\n");

        try (MockedStatic<IOHelper> mockedIOHelper = Mockito.mockStatic(IOHelper.class)) {
            mockedIOHelper.when(() -> IOHelper.readNonBlankStringFromKeyboard("Enter part of the title")).thenReturn("escape");
            fauxdango.demo();
            String printResult = consoleOut.toString();
            String normalisedOutput = getNormalisedOutput(printResult);
            assertEquals(expectedOutput, normalisedOutput);
        }
    }
}