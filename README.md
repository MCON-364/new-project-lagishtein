[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/vRBaX6X3)
# Java Modern Features Assignment

This assignment demonstrates modern Java features including Optional, var, switch expressions, and labeled control flow.

## Program Requirements

### 1. Create a public static method `getUserName`
- Takes the name of an environment variable as a parameter
- Attempts to read the value using `System.getenv`
- Returns the result as an `Optional<String>`

```java
public static Optional<String> getUserName(String envVarName);
```

### 2. Create a public static method `getGreeting`
- Calls `getUserName`
- Uses `var` for local variables where the type is obvious
- Uses a switch expression with `yield` to decide how to build the greeting based on whether the username is present
- Constructs the greeting using `StringBuilder`

```java
public static String getGreeting(String envVarName);
```

### 3. Create a public static method `processValues`
- Iterates over a `List<List<Integer>>`
- Uses a labeled `continue` to skip to the next outer list when a condition is met
- Uses a labeled `break` to exit processing early when a terminating condition is met

```java
public static void processValues(List<List<Integer>> data);
```

#### Example Data for `processValues`:

**Normal processing (no special values):**
```java
List.of(
    List.of(1, 2, 3),
    List.of(4, 5, 6),
    List.of(7, 8, 9)
)
```
All values are processed normally.

**Labeled continue example (skip lists containing 0):**
```java
List.of(
    List.of(10, 20, 30),    // Processes completely
    List.of(40, 0, 60),     // Finds 0, skips rest of this list
    List.of(70, 80, 90),    // Continues processing this list
    List.of(100, 0, 120)    // Finds 0, skips rest of this list
)
```
When a `0` is found, use `continue outerLoop` to skip to the next list.

**Labeled break example (terminate on 99):**
```java
List.of(
    List.of(11, 22, 33),    // Processes completely
    List.of(44, 55, 66),    // Processes completely
    List.of(77, 99, 88),    // Finds 99, terminates ALL processing
    List.of(111, 222, 333)  // Never reached
)
```
When `99` is found, use `break outerLoop` to exit all processing immediately.

**Combined example (both continue and break):**
```java
List.of(
    List.of(5, 10, 15),     // Processes completely
    List.of(20, 0, 25),     // Finds 0, skips to next list
    List.of(30, 35, 40),    // Processes completely
    List.of(45, 99, 50),    // Finds 99, terminates everything
    List.of(55, 60, 65)     // Never reached
)
```
Demonstrates both labeled continue (on 0) and labeled break (on 99).

### 4. In the `main` method
- Call `getGreeting` twice:
  - Once with `"USERNAME"`
  - Once with `"NO_SUCH_VAR"`
- Call `processValues` with sample data to demonstrate labeled control flow

## Package Structure
Use this package structure:
```
src/main/java/mcon364/las/touro/edu/Main.java
src/test/java/mcon364/las/touro/edu/ - your unit tests
```

## Testing
Unit tests are provided for:
- `getUserName`
- `getGreeting`
- `processValues`

You do not need to test the main method. Tests must be placed in the correct Maven test directory.

## Autograding
- GitHub Actions runs `mvn test` to validate your implementation
- In the autograder, `USERNAME` environment variable is set to a known value

## Running Tests Locally
```bash
mvn test
```
