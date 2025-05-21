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

    private boolean cycle() {
        if (invalidCommand != null) {
            System.out.println("Unknown command: " + invalidCommand);
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
