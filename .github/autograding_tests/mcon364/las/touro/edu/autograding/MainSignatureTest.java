package mcon364.las.touro.edu;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MainSignatureTest {

    /**
     * Test that getUserName method exists with correct signature
     * Required:
     * - Class: mcon364.las.touro.edu.Main
     * - Method: public static Optional<String> getUserName()
     *
     * Behavior (in autograder):
     * - The environment variable USERNAME is set (e.g., "Chana")
     * - getUserName() must return Optional.of(the env value)
     */
    @Test
    void main_has_static_getUserName_returning_optional() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");

        Method m = clazz.getDeclaredMethod("getUserName", String.class);

        assertTrue(Modifier.isStatic(m.getModifiers()), "getUserName must be static");
        assertTrue(Modifier.isPublic(m.getModifiers()), "getUserName must be public");
        assertEquals(Optional.class, m.getReturnType(), "getUserName must return java.util.Optional");

        Object result = m.invoke(null, "NO_SUCH_ENV_VAR");
        assertNotNull(result, "getUserName must not return null");
        assertTrue(result instanceof Optional, "getUserName must return Optional");
    }

    /**
     * Test that getUserName returns the correct environment variable value
     */
    @Test
    void getUserName_returns_student_name_env_var_when_present() throws Exception {
        String expected = System.getenv("USERNAME");
        assertNotNull(expected, "Autograder misconfigured: USERNAME env var must be set");
        assertFalse(expected.isBlank(), "Autograder misconfigured: USERNAME must be non-blank");

        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("getUserName", String.class);

        @SuppressWarnings("unchecked")
        Optional<String> result = (Optional<String>) m.invoke(null, "USERNAME");

        assertNotNull(result, "getUserName must not return null");
        assertTrue(result.isPresent(), "Expected Optional.of(USERNAME) when USERNAME is set");
        assertEquals(expected, result.get(), "getUserName must return the exact value of USERNAME");
    }

    /**
     * Test that getUserName returns empty Optional for non-existent variable
     */
    @Test
    void getUserName_returns_empty_optional_when_var_not_present() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("getUserName", String.class);

        @SuppressWarnings("unchecked")
        Optional<String> result = (Optional<String>) m.invoke(null, "NO_SUCH_ENV_VAR_12345");

        assertNotNull(result, "getUserName must not return null");
        assertFalse(result.isPresent(), "Expected Optional.empty() when env var not set");
    }

    /**
     * Test that getGreeting method exists with correct signature
     */
    @Test
    void main_has_static_getGreeting_returning_string() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");

        Method m = clazz.getDeclaredMethod("getGreeting", String.class);

        assertTrue(Modifier.isStatic(m.getModifiers()), "getGreeting must be static");
        assertTrue(Modifier.isPublic(m.getModifiers()), "getGreeting must be public");
        assertEquals(String.class, m.getReturnType(), "getGreeting must return String");
    }

    /**
     * Test that getGreeting returns a personalized greeting when env var is present
     */
    @Test
    void getGreeting_returns_personalized_greeting_when_env_var_present() throws Exception {
        String userName = System.getenv("USERNAME");
        assertNotNull(userName, "USERNAME env var must be set for this test");

        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("getGreeting", String.class);

        String result = (String) m.invoke(null, "USERNAME");

        assertNotNull(result, "getGreeting must not return null");
        assertTrue(result.contains(userName),
            "Greeting should contain the username: expected to contain '" + userName + "', but got '" + result + "'");
    }

    /**
     * Test that getGreeting returns a default greeting when env var is not present
     */
    @Test
    void getGreeting_returns_default_greeting_when_env_var_not_present() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("getGreeting", String.class);

        String result = (String) m.invoke(null, "NO_SUCH_ENV_VAR_12345");

        assertNotNull(result, "getGreeting must not return null");
        assertTrue(result.contains("Guest") || result.contains("World"),
            "Greeting should contain 'Guest' or 'World' when env var not present, but got: " + result);
    }

    /**
     * Test that processValues method exists with correct signature
     */
    @Test
    void main_has_static_processValues() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");

        Method m = clazz.getDeclaredMethod("processValues", List.class);

        assertTrue(Modifier.isStatic(m.getModifiers()), "processValues must be static");
        assertTrue(Modifier.isPublic(m.getModifiers()), "processValues must be public");
        assertEquals(void.class, m.getReturnType(), "processValues must return void");
    }

    /**
     * Test that processValues executes without throwing exceptions
     */
    @Test
    void processValues_executes_successfully() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("processValues", List.class);

        List<List<Integer>> testData = List.of(
            List.of(1, 2, 3),
            List.of(4, 5, 6)
        );

        // Should not throw any exception
        assertDoesNotThrow(() -> m.invoke(null, testData),
            "processValues should execute without throwing exceptions");
    }

    /**
     * Test that processValues handles labeled break and continue correctly
     */
    @Test
    void processValues_handles_special_values() throws Exception {
        Class<?> clazz = Class.forName("mcon364.las.touro.edu.Main");
        Method m = clazz.getDeclaredMethod("processValues", List.class);

        // Test with 0 (should trigger labeled continue)
        List<List<Integer>> testDataWithZero = List.of(
            List.of(1, 2, 3),
            List.of(4, 0, 6),
            List.of(7, 8, 9)
        );

        assertDoesNotThrow(() -> m.invoke(null, testDataWithZero),
            "processValues should handle 0 values with labeled continue");

        // Test with 99 (should trigger labeled break)
        List<List<Integer>> testDataWithNinetyNine = List.of(
            List.of(1, 2, 3),
            List.of(4, 99, 6),
            List.of(7, 8, 9)
        );

        assertDoesNotThrow(() -> m.invoke(null, testDataWithNinetyNine),
            "processValues should handle 99 values with labeled break");
    }
}
