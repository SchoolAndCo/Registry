import java.util.List;

public class Helper {
    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void clearLastLine() {
        System.out.print("\u001B[F\u001B[2K");
    }

    public static List<Object> parseAssignCommand(String command) {
        String[] command_parts = command.split("=", 2);

        if (command_parts.length != 2) {
            return List.of();
        }

        String key = command_parts[0];
        String value = command_parts[1];

        return List.of(key, value);
    }

    public static String tabLevel(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
