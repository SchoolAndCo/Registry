package Interface;

import java.util.Scanner;

import Interface.exceptions.InvalidViewActionReturn;
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

    public Boolean selectView(Class<? extends View> viewClass) {
        View new_view = View.instantiate(viewClass);
        selectedView = new_view;
        return new_view.init();
    }
    
    private Boolean cycle() {
        Helper.clearScreen();
        selectedView.draw();

        String view_prompt = (selectedView.viewPrompt==null ? "" : selectedView.viewPrompt);
        System.out.print("\n" + view_prompt + "> ");
        String userInputRaw = scanner.nextLine();

        Action viewResponse = selectedView.onCommand(userInputRaw);

        if (viewResponse == null) {
            return true;
        }

        if (viewResponse instanceof Action) {
            return viewResponse.execute(this);
        } 

        // should not even come here but nice to have
        throw new InvalidViewActionReturn();
    }

    private void mainLoop() {
        selectView(v_select.class);

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
