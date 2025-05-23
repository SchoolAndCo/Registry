package views;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class HelloView extends View {
    private String name;

    @Override
    public Boolean init() {
        viewPrompt = "Hello";
        viewSignature = "hello=<name>";
        helperText = "Greets someone by name";
        return true;
    }

    @Override
    public void draw() {
        if (name != null && !name.isEmpty()) {
            print("Hello, " + name + "! ☀️\n");
            print("Type 'whoami' to see your name\n");
            print("Type 'reset' to forget your name\n");
        } else {
            print("Hello, mysterious being!\n");
            print("Enter 'hello=<name>' in selection to set your name\n");
        }
        print("Type 'back' to return to selection\n");
    }

    @Override
    public Action onCommand(String command) {
        switch (command.toLowerCase()) {
            case "back":
                return new a_redirect(v_select.class);
            case "whoami":
                if (name != null && !name.isEmpty()) {
                    print("You are " + name + ". UwU\n");
                } else {
                    print("You are... still mysterious. 👀\n");
                }
                return new a_nop();
            case "reset":
                name = null;
                print("Name forgotten. Who are you now?\n");
                return new a_nop();
            default:
                return new a_nop();
        }
    }

    @Override
    public Boolean onSelection(String selection) {
        if (selection.toLowerCase().startsWith("hello=")) {
            String inputName = selection.substring("hello=".length()).trim();
            if (!inputName.isEmpty()) {
                name = inputName;
                return true;
            }
        }
        return false;
    }
}
