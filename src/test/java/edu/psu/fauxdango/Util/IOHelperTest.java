package edu.psu.fauxdango.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class IOHelperTest {
    @Test
    void testSingleton__get_allStaticMethod__return_onlyOneStaticMethod() {
        Method[] methods = IOHelper.class.getDeclaredMethods();
        Method[] allStaticMethods = Arrays.stream(methods)
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .toArray(Method[]::new);

        assertEquals(1, allStaticMethods.length);
        assertEquals("getInstance", allStaticMethods[0].getName());
    }

    @Test
    void testSingleton__get_constructorModifier__return_privateModifier() {
        Constructor<IOHelper> constructor = null;
        try {
            constructor = IOHelper.class.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            fail("Constructor not found");
        }
        assertNotNull(constructor);
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    void testSingleton__create_multipleInstance__return_sameInstance() {
        IOHelper instance1 = IOHelper.getInstance();
        IOHelper instance2 = IOHelper.getInstance();
        assertSame(instance1, instance2);
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