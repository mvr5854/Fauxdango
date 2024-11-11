package Util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationHelperTest {

    @Test
    void isIntInRange__pass_allPositive__return_true() {
        assertTrue(ValidationHelper.isIntInRange(5, 3, 8));
    }

    @Test
    void isIntInRange__pass_positive_positive_zero__return_false() {
        assertFalse(ValidationHelper.isIntInRange(5, 3, 0));
    }

    @Test
    void isIntInRange__pass_positive_positive_negative__return_false() {
        assertFalse(ValidationHelper.isIntInRange(5, 3, -6));
    }

    @Test
    void isIntInRange__pass_positive_zero_positive__return_true() {
        assertTrue(ValidationHelper.isIntInRange(5, 0, 8));
    }

    @Test
    void isIntInRange__pass_positive_negative_positive__return_true() {
        assertTrue(ValidationHelper.isIntInRange(5, -4, 8));
    }

    @Test
    void isIntInRange__pass_zero_positive_positive__return_false() {
        assertFalse(ValidationHelper.isIntInRange(0, 3, 8));
    }

    @Test
    void isIntInRange__pass_negative_positive_positive__return_false() {
        assertFalse(ValidationHelper.isIntInRange(-2, 3, 8));
    }
}