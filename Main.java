import java.util.List;

class App extends Interface {
    public Database db = new Database();

    public boolean create() {        
        Boolean finished = false;
        Person new_person = new Person();
        String errorMessage = null;
        String command;

        while (!finished) {
            Helper.clear();
            System.out.println("Creating new Person");
            System.out.println(Helper.tabLevel(1)+"Person so far:\n" + new_person.getFullBody(2));
            
            if (errorMessage != null) {
                System.out.println(errorMessage);
                errorMessage = null;
            }

            System.out.print("\n> ");
            command = scanner.nextLine();
            Helper.clearLastLine();

            if (command.equals("create")) {
                String[] errors = new_person.validate();
                if (errors.length > 0) {
                    errorMessage = "Some Errors happened:\n";
                    for (String error : errors) {
                        errorMessage += Helper.tabLevel(1) + error + "\n";
                    }
                    continue;
                }

                finished = true;
                continue;
            }

            List<Object> command_parts = Helper.parseAssignCommand(command);

            if (command_parts.size() == 0) {
                errorMessage = "syntax error: " + command;
                continue;
            }

            String key = (String) command_parts.get(0);
            String value = (String) command_parts.get(1);

            if (!new_person.update(key, value)) {
                errorMessage = "invalid key: " + key;
            }
        }

        Helper.clear();

        db.addEntry(new_person);
        System.out.println("Person created with ID: " + new_person.getId());
        System.out.println(new_person.getFullBody(1));
        System.out.println("Press any key to continue...");
        scanner.nextLine();
        
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.register("create", () -> app.create());
        app.run();
    }
}
