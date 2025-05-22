package Interface.intern;

import Interface.View;
import Interface.Action;

public class v_help extends View{
    @Override
    public void init() {
        viewPrompt = "HELP";
        viewSignature = "help";
        helperText = "show the help view";
    }

    @Override
    public void draw() {
        
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
