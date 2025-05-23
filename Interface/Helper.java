package Interface;

public class Helper {
    public static void clearScreen() {
        System.out.print("[H[2J[3J");
    }

    public static int countWrappedLines(String text, int consoleWidth) {
        if (text == null || text.isEmpty()) return 0;
        if (consoleWidth <= 0) throw new IllegalArgumentException("Console width must be positive");

        int lines = 1;
        int currentLineLength = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '\n') {
                lines++;
                currentLineLength = 0;
                continue;
            }

            currentLineLength++;

            if (currentLineLength > consoleWidth) {
                lines++;
                currentLineLength = 1; // current char starts the new line
            }
        }

        return lines;
    }
}
