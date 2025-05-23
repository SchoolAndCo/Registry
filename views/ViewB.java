package views;

import Interface.Action;
import Interface.View;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class ViewB extends View {
    @Override
    public Boolean init() {
        viewPrompt = "B";
        viewSignature = "B";
        helperText = "b view";
        return true;
    }

    @Override
    public void draw() {
        System.out.println("B VIEW YEEY");
    }

    @Override
    public Action onCommand(String command) {
        if (command.equals("a")) {
            return new a_redirect(ViewA.class);
        }

        if (command.equals("back")) {
            return new a_redirect(v_select.class);
        }

        return null;
    }

    @Override
    public Boolean onSelection(String selection) {
        return selection.equals("b");
    }
}
