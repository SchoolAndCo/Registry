package Interface.intern;

import Interface.Color;

import Interface.Action;
import Interface.View;
import Interface.action.a_nop;
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
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
        viewList = sb.toString();
        return true;
    }

    @Override
    public void draw() {
        println("Select a action:");
        for (String line : viewList.split("\n")) println(line);
        newLine();

        if (lastSelectionWrong != null) {
            println(Color.ANSI_RED + "Something went wrong with: " + lastSelectionWrong + "\"" + Color.ANSI_RESET);
            newLine();
            lastSelectionWrong = null;
        } else {
            newLine();
        }
    }

    @Override
    public Action onCommand(String command) {
        if (command.isEmpty()) {
            return new a_nop();
        }

        Class<? extends View> selectedView = View.signatureParse(command);

        if (selectedView == null) {
            lastSelectionWrong = command;
            return new a_nop();
        }

        return new a_redirect(selectedView);
    }

    @Override
    public Boolean onSelection(String userInputRaw) {
        return false;
    }
}
