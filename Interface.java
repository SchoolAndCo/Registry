import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Interface {
    protected static Scanner scanner = new Scanner(System.in);
    private HashMap<String, Function<String, Boolean>> registeredMenuItems = new HashMap<>();
    private String errorMessage;

    public void register(String name, Function<String, Boolean> action) {
        if (registeredMenuItems.containsKey(name)) {
            throw new IllegalStateException("Menu " + name + " got set a second time");
        }

        registeredMenuItems.put(name, action);
    }

    private String getStatsView() {
        StringBuilder sb = new StringBuilder();
        for (String key : registeredMenuItems.keySet()) {
            sb.append(Helper.tabLevel(1)+" "+key+"\n");
        }
        return sb.toString();
    }

    private List<String> parseCommand(String command) {
        if (command.contains(":")) {
            String[] command_parts = command.split(":", 2);
            String command_name = command_parts[0]+":";
            String command_arg = command_parts[1];
            return List.of(command_name, command_arg);
        }

        if (registeredMenuItems.containsKey(command+":")) {
            return List.of();
        }

        return Arrays.asList(command, null);
    } 

    public void showInfo(String error) {
        errorMessage = error;
    }

    private boolean cycle() {
        System.out.println("Registry v1.0 :3\nCommands listed:\n"+getStatsView()+"\n");

        if (errorMessage != null) {
            System.out.println(errorMessage);
            errorMessage = null;
        }

        System.out.print("> ");
        String userInput = scanner.nextLine();
        Helper.clearLastLine();

        if (userInput.equals("#quit")) {
            return false;
        }

        List<String> command_parts = parseCommand(userInput);

        if (command_parts.isEmpty()) {
            errorMessage = "Syntax error: " + userInput + " needs a arg with command:arg";
            return true;
        }

        String command = command_parts.get(0);
        String args = command_parts.get(1);

        Function<String, Boolean> action = registeredMenuItems.get(command);

        if (action == null) {
            errorMessage = "Unknown command: " + command + "\nplease only use listed commands or help if implemented";
            return true;
        }

        return action.apply(args == null ? null : args);
    }

    public void run() {
        boolean active = true;

        while (active) {
            Helper.clear();
            active = cycle();
        }
    }
}
