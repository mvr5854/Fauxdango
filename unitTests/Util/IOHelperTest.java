package Util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IOHelperTest {

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