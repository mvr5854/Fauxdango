package Controller;

import Model.User;
import View.ConsoleDisplay.UserDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class FauxdangoTest {


    private ByteArrayOutputStream consoleOut;

    @Mock
    private UserDisplay mockUserDisplay;

    @InjectMocks
    private Fauxdango fauxdango;

    @BeforeEach
    void setUp() {
        fauxdango = new Fauxdango();
        consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));
    }

    public void setInputStream(String input) {
        InputStream consoleIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(consoleIn);
    }

    public String getNormalisedOutput(String output) {
        output = output.replaceAll("\r", "");
        String normalisedOutput = "";
        String[] outList = output.split("Choice: |\n===");

        for (String out : Arrays.copyOfRange(outList, 1, outList.length)) {
            if (!out.contains("Fauxdango")) {
                normalisedOutput += out.trim() + "\n";
            }
        }

        return normalisedOutput;
    }

    @Test
    void demo__choose_register__prompt_registration() {
        String expectedOutput = "Welcome, John Doe (test@email.com)";
        setInputStream("1\n0\n");
        when(mockUserDisplay.registerUser()).thenReturn(new User());

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertTrue(normalisedOutput.contains(expectedOutput));
    }

    @Test
    void demo__choose_listAllMovies__print_allMovies() {
        String expectedOutput = "Top Gun (PG, 1986) [ACTION, DRAMA] {110 min}\n" +
                "This Is Spinal Tap (R, 1984) [COMEDY, DOCUMENTARY] {84 min}\n" +
                "Halloween (R, 1978) [HORROR] {91 min}\n" +
                "Escape from New York (R, 1981) [ACTION] {99 min}\n" +
                "Goodbye\n";
        setInputStream("2\n0\n");

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertEquals(expectedOutput, normalisedOutput);
    }

    @Test
    void demo__choose_listAllTheaters__print_allMovies() {
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
    void demo__choose_listAllActors__print_allMovies() {
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
    void demo__choose_listAllShowings__print_allMovies() {
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
    void demo__choose_searchActors__print_allMovies() {
        String expectedOutput = "Tom Cruise (1963-07-03)\n" +
                "Donald Pleasence (1919-10-05)\n" +
                "Kurt Russell (1951-03-17)\n" +
                "Goodbye\n";
        setInputStream("6\nes\n0\n");

        fauxdango.demo();
        String printResult = consoleOut.toString();
        String normalisedOutput = getNormalisedOutput(printResult);
        assertEquals(expectedOutput, normalisedOutput);
    }
}