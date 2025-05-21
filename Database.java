import java.util.ArrayList;

public class Database {
    private int maxId;
    private ArrayList<Person> entries;

    public Database() {
        this.maxId = 0;
        this.entries = new ArrayList<>();
    }

    public void addEntry(Person person) {
        this.entries.add(person);
        person.setId(this.maxId);
        this.maxId++;
    }

    public Person getEntry(int id) {
        if (id < 0 || id >= this.entries.size()) {
            return null;
        }
        return this.entries.get(id);
    }

    public void updateEntry(int id, String key, String value) {
        Person person = getEntry(id);
        if (person != null) {
            person.update(key, value);
        }
    }

    public void deleteEntry(int id) {
        if (id < 0 || id >= this.entries.size()) {
            return;
        }
        this.entries.remove(id);
    }

    public ArrayList<Person> getAllEntries() {
        return this.entries;
    }
}
