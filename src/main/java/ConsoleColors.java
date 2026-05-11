public class ConsoleColors {

    // Reset
    public static final String RESET = "[0m";

    // Colors
    public static final String RED = "[31m";
    public static final String GREEN = "[32m";
    public static final String YELLOW = "[33m";
    public static final String CYAN = "[36m";

    // Formatting
    public static final String BOLD = "[1m";

    // Helper methods
    public static String success(String message) {
        return GREEN + message + RESET;
    }

    public static String error(String message) {
        return RED + message + RESET;
    }

    public static String warning(String message) {
        return YELLOW + message + RESET;
    }

    public static String info(String message) {
        return CYAN + message + RESET;
    }

    public static String header(String message) {
        return BOLD + CYAN + message + RESET;
    }

    public static String prompt(String message) {
        return YELLOW + message + RESET;
    }
}
