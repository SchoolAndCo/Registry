package Interface;

import java.util.Scanner;

import Interface.intern.v_help;
import Interface.intern.v_select;

public class Interface {
    public final Scanner scanner = new Scanner(System.in);
    private Global global = Global.getInstance();
    private Boolean active = true;
    private View selectedView;

    public void registerView(Class<? extends View> viewClass) {
        global.registeredViews.add(viewClass);
    }

    private void selectedView(Class<? extends View> viewClass) {
        View new_view = View.instantiate(viewClass);
        new_view.init();
        selectedView = new_view;
    }

    private Boolean cycle() {
        selectedView.draw();

        String view_prompt = (selectedView.viewPrompt==null ? "" : selectedView.viewPrompt);
        System.out.print(view_prompt + "> ");
        String userInputRaw = scanner.nextLine();

        Action viewResponse = selectedView.onCommand(userInputRaw);

        if (viewResponse == null) {
            return true;
        }

        // later redirect and other actions for menus

        return true;
    }

    private void mainLoop() {
        selectedView(v_select.class);

        while (active) {
            active = cycle();
        }
    }

    public void start() {
        View.scanner = scanner;
        registerView(v_help.class);
        registerView(v_select.class);

        mainLoop();
    }
}
