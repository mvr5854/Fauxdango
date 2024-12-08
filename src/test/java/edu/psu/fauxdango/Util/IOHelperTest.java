package edu.psu.fauxdango.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IOHelperTest {
    private ByteArrayOutputStream consoleOut;

    @BeforeEach
    void setUp() {
        consoleOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOut));
    }

    void setInputStream(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testSingleton__pass_none__return_onlyOneStaticMethod() {
        Method[] methods = IOHelper.class.getDeclaredMethods();
        Method[] allStaticMethods = Arrays.stream(methods)
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .toArray(Method[]::new);

        assertEquals(1, allStaticMethods.length);
        assertEquals("getInstance", allStaticMethods[0].getName());
    }

    @Test
    @Order(1)
    void readValidNameFromKeyboard__pass_validName__return_name() {
        setInputStream("John\nalpha45\nJohn\njohn@psu.edu\nte$t@email.me\njohn@psu.edu\n");
        String result = IOHelper.readValidNameFromKeyboard("Enter your name");
        assertEquals("John", result);

    }

    @Test
    @Order(2)
    void readValidNameFromKeyboard__pass_invalidName__prompt_again() {
        setInputStream("alpha45\nJohn\n");
        String result = IOHelper.readValidNameFromKeyboard("Enter your name");
        String output = consoleOut.toString().replaceAll("\r", "");

        assertEquals("John", result);
        assertTrue(output.contains("Name can only contain letters\nand must begin with a capital letter"));
    }

    @Test
    @Order(3)
    void readValidEmailAddressFromKeyboard__pass_validEmail__return_email() {
        setInputStream("john@psu.edu\n");
        String result = IOHelper.readValidEmailAddressFromKeyboard("Enter your email address");
        assertEquals("john@psu.edu", result);
    }

    @Test
    @Order(4)
    void readValidEmailAddressFromKeyboard__pass_invalidEmail__return_email() {
        setInputStream("te$t@email.me\njohn@psu.edu\n");
        String result = IOHelper.readValidEmailAddressFromKeyboard("Enter your email address");
        String output = consoleOut.toString().replaceAll("\r", "");

        assertTrue(output.contains("Invalid email address! try again"));
        assertEquals("john@psu.edu", result);
    }


    @Test
    void checkRegex__pass_validString_matchedPattern__return_true() {
        assertTrue(IOHelper.checkRegex("hello", "\\b[a-zA-Z@$]+\\b"));
    }

    @Test
    void checkRegex__pass_validString_unmatchedPattern__return_false() {
        assertFalse(IOHelper.checkRegex("hello", "^[0-9()-]+$"));
    }

    @Test
    void checkRegex__pass_validString_nullPattern__throw_exception() {
        assertThrows(NullPointerException.class, () -> IOHelper.checkRegex("hello", null));
    }

    @Test
    void checkRegex__pass_specialChar_matchedPattern__return_true() {
        assertTrue(IOHelper.checkRegex("te$t", "\\b[a-zA-Z@$]+\\b"));
    }

    @Test
    void checkRegex__pass_null_matchedPattern__throw_exception() {
        assertThrows(NullPointerException.class, () -> IOHelper.checkRegex(null, "\\b[a-zA-Z@$]+\\b"));
    }

    @Test
    void checkRegex__pass_emptyString_matchedPattern__return_false() {
        assertFalse(IOHelper.checkRegex("", "\\b[a-zA-Z@$]+\\b"));
    }
}