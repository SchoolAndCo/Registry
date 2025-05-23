package views;

import java.util.ArrayList;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class Notes extends View {
    private ArrayList<String> notes = new ArrayList<>();

    @Override
    public Boolean init() {
        viewPrompt = "NOTES";
        viewSignature = "notes";
        helperText = "Fluffy personal notepad UwU";
        return true;
    }

    @Override
    public void draw() {
        print("📓 Notes:\n");

        if (notes.isEmpty()) {
            print("You don't have any notes yet... it's so quiet...\n");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                print((i + 1) + ". " + notes.get(i) + "\n");
            }
        }

        print("\n");
        print("Type a note to add it\n");
        print("Type 'remove <number>' to delete\n");
        print("Type 'forget' to delete everything\n");
        print("Type 'show' to redraw\n");
        print("Type 'back' to return to selection\n");
    }

    @Override
    public Action onCommand(String command) {
        command = command.trim();

        if (command.equalsIgnoreCase("back")) {
            return new a_redirect(v_select.class);
        }

        if (command.equalsIgnoreCase("show")) {
            return new a_nop();
        }

        if (command.equalsIgnoreCase("forget")) {
            notes.clear();
            print("All notes vanished like dust in the wind... ✨\n");
            return new a_nop();
        }

        if (command.toLowerCase().startsWith("remove ")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < notes.size()) {
                        String removed = notes.remove(index);
                        print("Removed note: \"" + removed + "\"\n");
                    } else {
                        print("No note at that index. 🥺\n");
                    }
                } catch (NumberFormatException e) {
                    print("That's not a number, silly. 😅\n");
                }
            } else {
                print("Usage: remove <note-number>\n");
            }
            return new a_nop();
        }

        notes.add(command);
        print("Noted it! 🐾\n");
        return new a_nop();
    }

    @Override
    public Boolean onSelection(String selection) {
        return selection.equalsIgnoreCase("notes");
    }
}
