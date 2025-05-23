package Interface.intern;

import Interface.Color;

import Interface.Action;
import Interface.View;
import Interface.action.a_redirect;

public class v_select extends View {
    private String viewList;
    private String lastSelectionWrong;

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
        
        if (lastSelectionWrong != null) {
            System.out.println(Color.ANSI_RED + "Something went wrong with: " + Color.ANSI_CYAN + "\"" + lastSelectionWrong + "\"" + Color.ANSI_RESET);
            lastSelectionWrong = null;
        } else {
            System.out.println();
        }

    }

    @Override
    public Action onCommand(String command) {
        Class<? extends View> selectedView = View.signatureParse(command);

        if (selectedView == null) {
            lastSelectionWrong = command;
            return null;
        }

        return new a_redirect(selectedView);

    }

    @Override
    public Boolean onSelection(String userInputRaw) {
        return false;
    }
}
