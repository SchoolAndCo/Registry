package Interface.intern;

import Interface.View;
import Interface.Action;

public class v_help extends View {
    @Override
    public Boolean init() {
        viewPrompt = "HELP";
        viewSignature = "help";
        helperText = "view a help view for this Interface module";
        return true;
    }

    @Override
    public void draw() {
        return;
    }

    @Override
    public Action onCommand(String command) {
        return null;

    }

    @Override
    public Boolean onSelection(String userInputRaw) {
        return true;
    }
}
