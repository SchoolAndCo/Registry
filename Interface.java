import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

public class Interface {
    protected static Scanner scanner = new Scanner(System.in);
    private HashMap<String, Supplier<Boolean>> registeredMenuItems = new HashMap<>();
    private String invalidCommand;

    public void register(String name, Supplier<Boolean> action) {
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

    private boolean cycle() {
        System.out.println("Registry v1.0 :3\nCommands listed:\n"+getStatsView()+"\n");

        if (invalidCommand != null) {
            System.out.println("Unknown command: " + invalidCommand + "\nplease only use listed commands or help if implemented");
            invalidCommand = null;
        }

        System.out.print("> ");
        String command = scanner.nextLine();
        Helper.clearLastLine();

        if (command.equals("#quit")) {
            return false;
        }

        Supplier<Boolean> action = registeredMenuItems.get(command);

        if (action == null) {
            this.invalidCommand = command;
            return true;
        }

        return action.get();
    }

    public void run() {
        boolean active = true;

        while (active) {
            Helper.clear();
            active = cycle();
        }
    }
}
