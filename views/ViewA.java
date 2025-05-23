package views;

import Interface.Action;
import Interface.View;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class ViewA extends View {
    @Override
    public Boolean init() {
        viewPrompt = "A";
        viewSignature = "A";
        helperText = "type a to switch here and get redirected to v_select";
        return true;
    }

    @Override
    public void draw() {
        System.out.println("ViewA");
    }

    @Override
    public Action onCommand(String command) {
        return new a_redirect(v_select.class);
    }

    @Override
    public Boolean onSelection(String selection) {
        return selection=="a";
    }
}
