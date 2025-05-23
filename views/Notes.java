package views;

import java.util.ArrayList;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
import Interface.action.a_redirect;
import Interface.intern.v_select;

public class Notes extends View {
    private ArrayList<String> notes = new ArrayList<String>();

    @Override
    public Boolean init() {
        viewPrompt = "NOTES";
        viewSignature = "notes";
        helperText = "notes UwU";
        return true;
    }

    @Override
    public void draw() {
        println("Notes:");
        println("Type back to get back to selection");

        for (String note : notes) {
            println("- " + note);
        }
    }

    @Override
    public Action onCommand(String command) {
        if (command.equals("back")) {
            return new a_redirect(v_select.class);
        }

        notes.add(command);
        return new a_nop();
    }

    @Override
    public Boolean onSelection(String selection) {
        return selection.equals("notes");
    }
}
