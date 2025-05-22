package Interface.intern;

import Interface.Action;
import Interface.View;

public class v_select extends View {
    @Override
    public void init() {
    }

    @Override
    public void draw() {
        System.out.println("HELLO this is selection UwU");
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
