package views;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class ViewA extends View {
    @Override
    public Boolean init() {
        viewPrompt = "A";
        viewSignature = "A";
        helperText = "A VIEW UwU";
        return true;
    }

    @Override
    public void draw() {
        print("ViewA\n");
    }

    @Override
    public Action onCommand(String command) {
        if (command.equals("b")) {
            return new a_redirect(ViewB.class);
        }

        if (command.equals("back")) {
            return new a_redirect(v_select.class);
        }

        return new a_nop();
    }

    @Override
    public Boolean onSelection(String selection) {
        return selection.equals("a");
    }
}
