package mcon364.las.touro.edu;

import java.util.Optional;

public class Main {
    static Optional<String> getUserName(String envVar) {
        var userName = System.getenv(envVar);
        return Optional.ofNullable(userName);
    }
    static String getGreeting(String envVar) {
        var userNameOpt = getUserName(envVar);
        var builder = new StringBuilder("Hello, ");
        userNameOpt.ifPresentOrElse(
            name -> builder.append(name),
            () -> builder.append("Guest")
        );
        return builder.toString();
    }

    public static void main(String[] args) {
        getGreeting("USER");
    }
}
