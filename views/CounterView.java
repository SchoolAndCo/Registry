package views;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class CounterView extends View {
    private int counter = 0;

    @Override
    public Boolean init() {
        viewPrompt = "Counter";
        viewSignature = "counter";
        helperText = "A counter view UwU";
        return true;
    }

    @Override
    public void draw() {
        print("CounterView (counter: " + counter + ")\n");
        print("Type any mix of '+' and '-' like '++-+-'\n");
        print("Type 'b' to go to ViewB\n");
        print("Type 'back' to return to selection\n");
    }

    @Override
    public Action onCommand(String command) {
        if (command.matches("[+-]+")) {
            for (char c : command.toCharArray()) {
                if (c == '+') counter++;
                else if (c == '-') counter--;
            }
            return new a_nop();
        }

        switch (command) {
            case "b":
                return new a_redirect(BeeView.class);
            case "back":
                return new a_redirect(v_select.class);
            default:
                return new a_nop();
        }
    }

    @Override
    public Boolean onSelection(String selection) {
        if (selection.equalsIgnoreCase("counter")) {
            viewPrompt = "Counter!";
            return true;
        }

        return false;
    }
}
