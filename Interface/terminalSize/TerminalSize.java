package Interface.terminalSize;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TerminalSize {
    public static int[] getTerminalSize() {
        try {
            Process process = new ProcessBuilder("./tty-size").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String output = reader.readLine(); // get first line
            if (output != null && output.matches("\\d+ \\d+")) {
                String[] parts = output.trim().split(" ");
                int rows = Integer.parseInt(parts[0]);
                int cols = Integer.parseInt(parts[1]);
                return new int[]{rows, cols};
            }
        } catch (Exception e) {
            // squirrel panic handler
        }
        return new int[]{2400, 80};
    }
}
