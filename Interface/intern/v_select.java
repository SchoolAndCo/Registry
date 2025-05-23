package Interface.intern;

import Interface.Action;
import Interface.View;

public class v_select extends View {
    private String viewList;

    @Override
    public Boolean init() {
        StringBuilder sb = new StringBuilder();
        for (Class<? extends View> viewClass : global.registeredViews) {
            if (viewClass == v_select.class) continue;
            View viewInstance = View.instantiate(viewClass);
            viewInstance.init();
            if (viewInstance.viewSignature != null && !viewInstance.viewSignature.isEmpty()) {
                sb.append(": " + viewInstance.viewSignature + " - " + viewInstance.helperText).append("\n");
            } else {
                sb.append(": " + viewInstance.helperText).append("\n");
            }
        }
        viewList = sb.toString();
        return true;
    }

    @Override
    public void draw() {
        System.out.println("Select a action:");
        System.out.println(viewList);
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
