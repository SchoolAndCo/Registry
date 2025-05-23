package views;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class BeeView extends View {
    @Override
    public Boolean init() {
        viewPrompt = "🐝";
        viewSignature = "buzz";
        helperText = "A buzzing bee of motivation!";
        return true;
    }

    @Override
    public void draw() {
        print("BZZZ... welcome to BeeView!\n");
        print("Remember:\n");
        print("- You are fluffy.\n");
        print("- You are capable.\n");
        print("- You are one powerful squirrel.\n");
        print("Type 'counter' to return to Counter\n");
        print("Type 'back' to go back to selection\n");
    }

    @Override
    public Action onCommand(String command) {
        if (command.equals("counter")) {
            return new a_redirect(CounterView.class);
        }

        if (command.equals("back")) {
            return new a_redirect(v_select.class);
        }

        return new a_nop();
    }

    @Override
    public Boolean onSelection(String selection) {
        return selection.equalsIgnoreCase("buzz");
    }
}
