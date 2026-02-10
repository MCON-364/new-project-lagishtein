package mcon364.las.touro.edu;

import java.util.Optional;

public class Demo {
    public Demo() {
    }
    public Optional<String> returnOptionalEmpty() {
        return Optional.empty();
    }
    public Optional<String>returnOptionalOfNullable(String value) {
        return Optional.ofNullable(value);
    }
    public Optional<String> returnOptionalOf(String value) {
        return Optional.of(value);
    }

    public void showOptionalUsage() {
        System.out.println("=== Optional Usage Demonstration ===\n");

        // 1. Working with Optional.empty()
        Optional<String> emptyOpt = returnOptionalEmpty();
        System.out.println("1. Optional.empty():");
        System.out.println("   isPresent: " + emptyOpt.isPresent());
        System.out.println("   orElse: " + emptyOpt.orElse("default value"));
        System.out.println("   orElseGet: " + emptyOpt.orElseGet(() -> "generated value"));

        // 2. Working with Optional.ofNullable() - with null
        Optional<String> nullableEmpty = returnOptionalOfNullable(null);
        System.out.println("\n2. Optional.ofNullable(null):");
        System.out.println("   isPresent: " + nullableEmpty.isPresent());
        System.out.println("   orElse: " + nullableEmpty.orElse("no value provided"));
        System.out.println("   orElseGet: " + nullableEmpty.orElseGet(() -> "generated value"));

        // 3. Working with Optional.ofNullable() - with value
        Optional<String> nullableWithValue = returnOptionalOfNullable("Hello, Optional!");
        System.out.println("\n3. Optional.ofNullable(\"Hello, Optional!\"):");
        System.out.println("   isPresent: " + nullableWithValue.isPresent());
        System.out.println("   get: " + nullableWithValue.get());
        nullableWithValue.ifPresent(val -> System.out.println("   ifPresent: " + val));

        // 4. Working with Optional.of()
        Optional<String> optionalOf = returnOptionalOf("Guaranteed value");
        System.out.println("\n4. Optional.of(\"Guaranteed value\"):");
        System.out.println("   isPresent: " + optionalOf.isPresent());
        System.out.println("   get: " + optionalOf.get());

        // 5. Using map() transformation
        Optional<String> mapped = returnOptionalOfNullable("java")
            .map(String::toUpperCase);
        System.out.println("\n5. Using map() to transform:");
        System.out.println("   Mapped to uppercase: " + mapped.orElse("N/A"));

        // 6. Using filter()
        Optional<String> filtered = returnOptionalOfNullable("test")
            .filter(s -> s.length() > 5);
        System.out.println("\n6. Using filter():");
        System.out.println("   Filter (length > 5): " + filtered.orElse("filtered out"));

        // 7. Chaining operations
        String result = returnOptionalOfNullable("optional")
            .map(String::toUpperCase)
            .filter(s -> s.startsWith("O"))
            .orElse("no match");
        System.out.println("\n7. Chaining map() and filter():");
        System.out.println("   Result: " + result);

        // 8. Using orElseThrow()
        System.out.println("\n8. Using orElseThrow():");
        try {
            String value = returnOptionalOfNullable("exists").orElseThrow(() -> 
                new IllegalStateException("Value not present"));
            System.out.println("   Value retrieved: " + value);
        } catch (IllegalStateException e) {
            System.out.println("   Exception: " + e.getMessage());
        }

        // 8. Using orElseThrow()
        System.out.println("\n8. Using orElseThrow():");
        try {
            String value = returnOptionalOfNullable(null).orElseThrow(() ->
                    new IllegalStateException("Value not present"));
            System.out.println("   Value retrieved: " + value);
        } catch (IllegalStateException e) {
            System.out.println("   Exception: " + e.getMessage());
        }
        
        System.out.println("\n=== End of Demonstration ===");
    }

    public void showUsagesOfModernSwitch() {
        System.out.println("\n=== Modern Switch Usage Demonstration ===\n");

        // 1. Traditional switch statement
        String day = "MONDAY";
        System.out.println("1. Traditional switch statement:");
        switch (day) {
            case "MONDAY":
            case "TUESDAY":
            case "WEDNESDAY":
            case "THURSDAY":
            case "FRIDAY":
                System.out.println("   " + day + " is a weekday");
                break;
            case "SATURDAY":
            case "SUNDAY":
                System.out.println("   " + day + " is a weekend");
                break;
            default:
                System.out.println("   Invalid day");
        }

        // 2. Switch expression with arrow syntax (no fall-through)
        System.out.println("\n2. Switch expression with arrow syntax:");
        String dayType = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "weekday";
            case "SATURDAY", "SUNDAY" -> "weekend";
            default -> "invalid";
        };
        System.out.println("   " + day + " is a " + dayType);

        // 3. Switch expression with yield
        System.out.println("\n3. Switch expression with yield:");
        int dayNumber = 3;
        String dayName = switch (dayNumber) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6, 7 -> {
                System.out.println("   Weekend day detected!");
                yield dayNumber == 6 ? "Saturday" : "Sunday";
            }
            default -> {
                System.out.println("   Invalid day number");
                yield "Unknown";
            }
        };
        System.out.println("   Day " + dayNumber + " is " + dayName);

        // 4. Switch with multiple values per case
        System.out.println("\n4. Switch with multiple values per case:");
        int month = 2;
        int daysInMonth = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28;
            default -> 0;
        };
        System.out.println("   Month " + month + " has " + daysInMonth + " days");

        // 5. Switch with enum
        System.out.println("\n5. Switch with enum:");
        DayOfWeek today = DayOfWeek.WEDNESDAY;
        String activity = switch (today) {
            case MONDAY -> "Start of work week";
            case TUESDAY, WEDNESDAY, THURSDAY -> "Mid-week grind";
            case FRIDAY -> "Get Ready for Shabbos!";
            case SATURDAY, SUNDAY -> "Relax and enjoy";
        };
        System.out.println("   " + today + ": " + activity);

        // 6. Switch expression returning different types with common supertype
        System.out.println("\n6. Switch with complex logic:");
        String grade = "B";
        String feedback = switch (grade) {
            case "A" -> "Excellent work!";
            case "B" -> "Good job!";
            case "C" -> "Satisfactory";
            case "D" -> "Needs improvement";
            case "F" -> "Failed";
            default -> {
                System.out.println("   Invalid grade: " + grade);
                yield "Unknown grade";
            }
        };
        System.out.println("   Grade " + grade + ": " + feedback);

        // 7. Switch as statement vs expression
        System.out.println("\n7. Switch statement vs expression:");
        int score = 85;
        // Old style - statement
        String level;
        switch (score / 10) {
            case 10:
            case 9:
                level = "A";
                break;
            case 8:
                level = "B";
                break;
            case 7:
                level = "C";
                break;
            default:
                level = "F";
        }
        System.out.println("   Old style - Score " + score + " is level: " + level);

        // New style - expression
        String levelNew = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            default -> "F";
        };
        System.out.println("   New style - Score " + score + " is level: " + levelNew);

        System.out.println("\n=== End of Modern Switch Demonstration ===");
    }

    public void showLabelledContinue() {
        System.out.println("\n=== Labelled Continue Demonstration ===\n");

        // 1. Without labelled continue - skips to next iteration of inner loop only
        System.out.println("1. Regular continue (no label):");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    continue; // skips only inner loop iteration
                }
                System.out.println("   i=" + i + ", j=" + j);
            }
        }

        // 2. With labelled continue - skips to next iteration of outer loop
        System.out.println("\n2. Labelled continue (skips to outer loop):");
        outerLoop:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    continue outerLoop; // skips to next iteration of outer loop
                }
                System.out.println("   i=" + i + ", j=" + j);
            }
            System.out.println("   End of inner loop for i=" + i); // This won't print when continue outerLoop is executed
        }

        // 3. Practical example: Processing a matrix, skip rows with certain conditions
        System.out.println("\n3. Practical example - Processing matrix:");
        int[][] matrix = {
            {1, 2, 3},
            {4, 0, 6},  // Contains 0, skip this row
            {7, 8, 9}
        };

        rowLoop:
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("   Row " + row + ": ");
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    System.out.println("(skipped - contains 0)");
                    continue rowLoop; // Skip entire row if any element is 0
                }
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println("(processed)");
        }

        // 4. Multiple nested loops with labels
        System.out.println("\n4. Multiple levels of nesting:");
        outer:
        for (int i = 1; i <= 2; i++) {
            middle:
            for (int j = 1; j <= 2; j++) {
                for (int k = 1; k <= 3; k++) {
                    if (k == 2 && j == 1) {
                        System.out.println("   i=" + i + ", j=" + j + ", k=" + k + " -> continue middle");
                        continue middle; // Continue to next iteration of middle loop
                    }
                    if (k == 3 && i == 2) {
                        System.out.println("   i=" + i + ", j=" + j + ", k=" + k + " -> continue outer");
                        continue outer; // Continue to next iteration of outer loop
                    }
                    System.out.println("   i=" + i + ", j=" + j + ", k=" + k);
                }
            }
        }

        // 5. Real-world scenario: Searching for valid data
        System.out.println("\n5. Real-world example - Finding valid coordinates:");
        String[][] grid = {
            {"X", "O", "X"},
            {"O", "!", "O"},  // ! indicates invalid row
            {"X", "O", "X"}
        };

        searchLoop:
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col].equals("!")) {
                    System.out.println("   Row " + row + " is invalid, skipping...");
                    continue searchLoop;
                }
            }
            System.out.println("   Row " + row + " is valid: " + String.join(" ", grid[row]));
        }

        System.out.println("\n=== End of Labelled Continue Demonstration ===");
    }

    public void showLabelledBreak() {
        System.out.println("\n=== Labelled Break Demonstration ===\n");

        // 1. Without labelled break - breaks only inner loop
        System.out.println("1. Regular break (no label):");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    break; // breaks only inner loop
                }
                System.out.println("   i=" + i + ", j=" + j);
            }
            System.out.println("   After inner loop for i=" + i);
        }

        // 2. With labelled break - breaks out of outer loop
        System.out.println("\n2. Labelled break (breaks outer loop):");
        outerLoop:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    System.out.println("   Breaking out of outer loop at i=" + i + ", j=" + j);
                    break outerLoop; // breaks out of outer loop completely
                }
                System.out.println("   i=" + i + ", j=" + j);
            }
            System.out.println("   After inner loop for i=" + i); // Won't print when break outerLoop is executed
        }
        System.out.println("   Exited outer loop");

        // 3. Practical example: Finding a value in a 2D array
        System.out.println("\n3. Practical example - Searching in 2D array:");
        int[][] numbers = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        int target = 7;
        boolean found = false;

        searchLoop:
        for (int row = 0; row < numbers.length; row++) {
            for (int col = 0; col < numbers[row].length; col++) {
                System.out.println("   Checking position [" + row + "][" + col + "] = " + numbers[row][col]);
                if (numbers[row][col] == target) {
                    System.out.println("   Found " + target + " at position [" + row + "][" + col + "]");
                    found = true;
                    break searchLoop; // Stop searching once found
                }
            }
        }
        if (!found) {
            System.out.println("   " + target + " not found");
        }

        // 4. Multiple nested loops with different break points
        System.out.println("\n4. Multiple levels of nesting with labels:");
        outer:
        for (int i = 1; i <= 3; i++) {
            System.out.println("   Outer loop i=" + i);
            middle:
            for (int j = 1; j <= 3; j++) {
                System.out.println("      Middle loop j=" + j);
                for (int k = 1; k <= 3; k++) {
                    System.out.println("         Inner loop k=" + k);
                    if (k == 2 && j == 2 && i == 1) {
                        System.out.println("         Breaking middle loop");
                        break middle;
                    }
                    if (k == 2 && j == 1 && i == 2) {
                        System.out.println("         Breaking outer loop");
                        break outer;
                    }
                }
            }
        }
        System.out.println("   Finished loop structure");

        // 5. Real-world example: Input validation
        System.out.println("\n5. Real-world example - Validating grid data:");
        String[][] dataGrid = {
            {"OK", "OK", "OK"},
            {"OK", "ERROR", "OK"},
            {"OK", "OK", "OK"}
        };

        boolean hasError = false;
        validationLoop:
        for (int row = 0; row < dataGrid.length; row++) {
            for (int col = 0; col < dataGrid[row].length; col++) {
                System.out.println("   Validating cell [" + row + "][" + col + "]: " + dataGrid[row][col]);
                if (dataGrid[row][col].equals("ERROR")) {
                    System.out.println("   ERROR found at [" + row + "][" + col + "]! Stopping validation.");
                    hasError = true;
                    break validationLoop;
                }
            }
        }
        System.out.println("   Validation result: " + (hasError ? "FAILED" : "PASSED"));

        // 6. Breaking from switch inside a loop
        System.out.println("\n6. Break from switch vs break from loop:");
        processLoop:
        for (int i = 1; i <= 3; i++) {
            System.out.println("   Processing item " + i);
            if (i == 2) {
                System.out.println("      Special case - breaking entire loop");
                break processLoop; // Breaks the loop
            }
            String status = switch (i) {
                case 1 -> "Starting";
                case 3 -> "Ending";
                default -> "Processing";
            };
            System.out.println("   Status: " + status);
        }
        System.out.println("   Loop terminated");

        System.out.println("\n=== End of Labelled Break Demonstration ===");
    }

    // Enum for demonstration
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        System.out.println("Demo class");
        
        Demo demo = new Demo();
        demo.showOptionalUsage();
        demo.showUsagesOfModernSwitch();
        demo.showLabelledContinue();
        demo.showLabelledBreak();
    }
}
