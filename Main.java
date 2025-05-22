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

            System.out.print("\nCREATE > ");
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
            } else if (command.equals("cancel")) {
                System.out.print("Are you sure you want to cancel?\ndata will be lost\n(type Y to confirm/N is default) > ");
                String choice = scanner.nextLine();

                if (choice.equals("Y")) {
                    return true;
                }
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

    public boolean list() {
        List<Person> persons = db.getAllEntries();
        Pagination<Person> pagination = new Pagination<>(persons);

        while (true) {
            Helper.clear();
            System.out.println("Listing all persons");
            System.out.println(Helper.tabLevel(1) + "ID\tName");

            List<Person> currentItems = pagination.getCurrentItems();

            for (Person person : currentItems) {
                System.out.println(person.getId() + ": " + person.getName());
            }

            if (currentItems.isEmpty()) {
                System.out.println("--- empty ---");
            }

            System.out.println(pagination.getStatus());
            System.out.println("Commands: next, prev, page [n], size [n], exit");
            System.out.print("LIST > ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) break;

            if (command.equalsIgnoreCase("next")) {
                pagination.nextPage();
            } else if (command.equalsIgnoreCase("prev")) {
                pagination.prevPage();
            } else if (command.startsWith("page ")) {
                try {
                    int page = Integer.parseInt(command.substring(5).trim());
                    pagination.goToPage(page);
                } catch (NumberFormatException ignored) {}
            } else if (command.startsWith("size ")) {
                try {
                    int size = Integer.parseInt(command.substring(5).trim());
                    pagination.setPageSize(size);
                } catch (NumberFormatException ignored) {}
            }
        }

        return true;
    }
}

public class Main {
    private static App app = new App();

    public static void fillFakeData() {
        app.db.addEntry(new Person(1, "Alice", "Smith", "12345", "Vienna", "Main Street", "12A", "Austria"));
        app.db.addEntry(new Person(2, "Bob", "Johnson", "23456", "Graz", "Baker Street", "3B", "Austria"));
        app.db.addEntry(new Person(3, "Charlie", "Williams", "34567", "Linz", "Elm Street", "5", "Austria"));
        app.db.addEntry(new Person(4, "Diana", "Brown", "45678", "Salzburg", "Oak Avenue", "7", "Austria"));
        app.db.addEntry(new Person(5, "Eve", "Jones", "56789", "Innsbruck", "Pine Road", "9C", "Austria"));
        app.db.addEntry(new Person(6, "Frank", "Garcia", "67890", "Klagenfurt", "Maple Street", "11", "Austria"));
        app.db.addEntry(new Person(7, "Grace", "Miller", "78901", "Villach", "Cedar Lane", "13", "Austria"));
        app.db.addEntry(new Person(8, "Hank", "Davis", "89012", "Wels", "Birch Street", "15", "Austria"));
        app.db.addEntry(new Person(9, "Ivy", "Rodriguez", "90123", "Sankt Pölten", "Ash Street", "17", "Austria"));
        app.db.addEntry(new Person(10, "Jack", "Martinez", "01234", "Dornbirn", "Fir Avenue", "19A", "Austria"));
        app.db.addEntry(new Person(11, "Karen", "Hernandez", "11223", "Feldkirch", "Willow Road", "21", "Austria"));
        app.db.addEntry(new Person(12, "Leo", "Lopez", "22334", "Bregenz", "Chestnut Street", "23B", "Austria"));
        app.db.addEntry(new Person(13, "Mona", "Gonzalez", "33445", "Steyr", "Cypress Street", "25", "Austria"));
        app.db.addEntry(new Person(14, "Ned", "Wilson", "44556", "Wiener Neustadt", "Spruce Lane", "27", "Austria"));
        app.db.addEntry(new Person(15, "Olga", "Anderson", "55667", "Leoben", "Poplar Street", "29", "Austria"));
        app.db.addEntry(new Person(16, "Paul", "Thomas", "66778", "Krems", "Hemlock Road", "31", "Austria"));
        app.db.addEntry(new Person(17, "Quinn", "Taylor", "77889", "Traun", "Redwood Street", "33", "Austria"));
        app.db.addEntry(new Person(18, "Rita", "Moore", "88990", "Amstetten", "Linden Street", "35", "Austria"));
        app.db.addEntry(new Person(19, "Steve", "Jackson", "99001", "Lustenau", "Beech Street", "37", "Austria"));
        app.db.addEntry(new Person(20, "Tina", "Martin", "10112", "Tulln", "Sequoia Road", "39", "Austria"));
        app.db.addEntry(new Person(21, "Uma", "Lee", "11122", "Mödling", "Palm Avenue", "41", "Austria"));
        app.db.addEntry(new Person(22, "Victor", "Perez", "12132", "Hallein", "Cork Street", "43", "Austria"));
        app.db.addEntry(new Person(23, "Wendy", "White", "13142", "Zell am See", "Bamboo Lane", "45", "Austria"));
        app.db.addEntry(new Person(24, "Xander", "Harris", "14152", "Knittelfeld", "Olive Street", "47", "Austria"));
        app.db.addEntry(new Person(25, "Yara", "Clark", "15162", "Schwechat", "Myrtle Road", "49", "Austria"));
        app.db.addEntry(new Person(26, "Zane", "Lewis", "16172", "Ternitz", "Hazel Street", "51", "Austria"));
        app.db.addEntry(new Person(27, "Anna", "Robinson", "17182", "Kapfenberg", "Laurel Lane", "53", "Austria"));
        app.db.addEntry(new Person(28, "Ben", "Walker", "18192", "Eisenstadt", "Magnolia Street", "55", "Austria"));
        app.db.addEntry(new Person(29, "Cleo", "Hall", "19202", "Judenburg", "Sycamore Road", "57", "Austria"));
        app.db.addEntry(new Person(30, "Dean", "Allen", "20212", "Bad Ischl", "Aspen Street", "59", "Austria"));
        app.db.addEntry(new Person(31, "Elle", "Young", "21222", "Spittal", "Juniper Lane", "61", "Austria"));
        app.db.addEntry(new Person(32, "Finn", "King", "22232", "Gänserndorf", "Dogwood Road", "63", "Austria"));
        app.db.addEntry(new Person(33, "Gina", "Scott", "23242", "Melk", "Alder Street", "65", "Austria"));
        app.db.addEntry(new Person(34, "Hugo", "Green", "24252", "Perg", "Sassafras Lane", "67", "Austria"));
        app.db.addEntry(new Person(35, "Isla", "Baker", "25262", "Neunkirchen", "Buckeye Street", "69", "Austria"));
        app.db.addEntry(new Person(36, "Jake", "Adams", "26272", "Bludenz", "Cedar Avenue", "71", "Austria"));
        app.db.addEntry(new Person(37, "Kira", "Nelson", "27282", "Gmunden", "Hickory Road", "73", "Austria"));
        app.db.addEntry(new Person(38, "Liam", "Carter", "28292", "Wolfsberg", "Mulberry Lane", "75", "Austria"));
        app.db.addEntry(new Person(39, "Mia", "Mitchell", "29302", "Vöcklabruck", "Elder Street", "77", "Austria"));
        app.db.addEntry(new Person(40, "Noah", "Campbell", "30312", "Stockerau", "Cherry Road", "79", "Austria"));
    }

    public static void main(String[] args) {
        fillFakeData();
        app.register("create", () -> app.create());
        app.register("list", () -> app.list());
        app.run();
    }
}
