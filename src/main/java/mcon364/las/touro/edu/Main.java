package mcon364.las.touro.edu;

import java.util.List;
import java.util.Optional;

public class Main {

    /**
     * Reads an environment variable and returns it as an Optional
     * @param envVarName the name of the environment variable to read
     * @return Optional containing the value if present, Optional.empty() otherwise
     */
    public static Optional<String> getUserName(String envVarName) {
        var userName = System.getenv(envVarName);
        return Optional.ofNullable(userName);
    }

    /**
     * Creates a greeting message based on the presence of an environment variable
     * Uses switch expression with yield and StringBuilder
     * @param envVarName the name of the environment variable to read
     * @return a greeting string
     */
    public static String getGreeting(String envVarName) {
        var userNameOpt = getUserName(envVarName);

        var greeting = switch (userNameOpt.isEmpty() ? "empty" : "present") {
            case "empty" -> {
                var builder = new StringBuilder();
                builder.append("Hello, Guest");
                yield builder.toString();
            }
            case "present" -> {
                var builder = new StringBuilder();
                builder.append("Hello, ");
                builder.append(userNameOpt.get());
                yield builder.toString();
            }
            default -> {
                var builder = new StringBuilder();
                builder.append("Hello, World");
                yield builder.toString();
            }
        };

        return greeting;
    }

    /**
     * Processes a list of lists with labeled break and continue
     * Demonstrates labeled control flow:
     * - Skips inner lists containing 0 (labeled continue)
     * - Stops processing entirely if value 99 is found (labeled break)
     * @param data the list of lists to process
     */
    public static void processValues(List<List<Integer>> data) {
        System.out.println("Processing values with labeled control flow:");

        outerLoop:
        for (int i = 0; i < data.size(); i++) {
            var innerList = data.get(i);
            System.out.println("  Processing list " + i + ": " + innerList);

            for (var value : innerList) {
                // If we find 0, skip to next outer list (labeled continue)
                if (value == 0) {
                    System.out.println("    Found 0, skipping rest of this list");
                    continue outerLoop;
                }

                // If we find 99, stop all processing (labeled break)
                if (value == 99) {
                    System.out.println("    Found 99, terminating all processing");
                    break outerLoop;
                }

                System.out.println("    Processing value: " + value);
            }

            System.out.println("    Finished processing list " + i);
        }

        System.out.println("Processing complete");
    }

    public static void main(String[] args) {
        System.out.println("=== Modern Java Features Demo ===\n");

        // Call getGreeting twice with different environment variables
        System.out.println("1. Greeting with USERNAME:");
        var greeting1 = getGreeting("USERNAME");
        System.out.println("   " + greeting1);

        System.out.println("\n2. Greeting with NO_SUCH_VAR:");
        var greeting2 = getGreeting("NO_SUCH_VAR");
        System.out.println("   " + greeting2);

        // Example 1: Normal processing (no special values)
        System.out.println("\n3a. Process values - Normal case:");
        var normalData = List.of(
            List.of(1, 2, 3),
            List.of(4, 5, 6),
            List.of(7, 8, 9)
        );
        processValues(normalData);

        // Example 2: Labeled continue (skip list with 0)
        System.out.println("\n3b. Process values - Labeled continue (skip lists with 0):");
        var continueData = List.of(
            List.of(10, 20, 30),
            List.of(40, 0, 60),    // Contains 0, should skip rest of this list
            List.of(70, 80, 90),   // Should process this list
            List.of(100, 0, 120)   // Contains 0, should skip rest of this list
        );
        processValues(continueData);

        // Example 3: Labeled break (terminate on 99)
        System.out.println("\n3c. Process values - Labeled break (terminate on 99):");
        var breakData = List.of(
            List.of(11, 22, 33),
            List.of(44, 55, 66),
            List.of(77, 99, 88),   // Contains 99, should terminate all processing
            List.of(111, 222, 333) // Should never be reached
        );
        processValues(breakData);

        // Example 4: Combined example (both continue and break)
        System.out.println("\n3d. Process values - Combined (continue on 0, break on 99):");
        var combinedData = List.of(
            List.of(5, 10, 15),
            List.of(20, 0, 25),    // Contains 0, skip to next list
            List.of(30, 35, 40),   // Process normally
            List.of(45, 99, 50),   // Contains 99, terminate everything
            List.of(55, 60, 65)    // Never reached
        );
        processValues(combinedData);
    }
}
