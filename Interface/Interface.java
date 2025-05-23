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
    public int[] __terminalSize;
    public int __renderCycleLines;

    public void registerView(Class<? extends View> viewClass) {
        global.registeredViews.add(viewClass);
    }

    public Boolean selectView(Class<? extends View> viewClass) {
        View new_view = View.instantiate(viewClass);
        selectedView = new_view;
        return new_view.init();
    }
    
    private Boolean cycle() {
        System.out.print("\r\n"); // Force a clean break before screen clear
        System.out.flush(); 
        this.__terminalSize = TerminalSize.getTerminalSize();
        this.__renderCycleLines = 0;

        Helper.clearScreen();
        selectedView.draw();
        System.out.flush(); 

        int overheadLines = 2; // prompt + debug (or just prompt if no debug)
        int linesToFill = Math.max(0, this.__terminalSize[0] - __renderCycleLines - overheadLines);

        for (int i = 0; i < linesToFill; i++) {
            System.out.println();
        }

        System.out.printf("TerminalSize: [%d, %d] RenderCycleLines: %d LinesToFill: %d%n",
            this.__terminalSize[0], this.__terminalSize[1], this.__renderCycleLines, linesToFill);

        String view_prompt = (selectedView.viewPrompt==null ? "" : selectedView.viewPrompt);
        System.out.print(view_prompt + "> ");
        String userInputRaw = scanner.nextLine();
        System.out.println();

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
        View.__base = this;
        registerView(v_help.class);
        registerView(v_select.class);

        mainLoop();
    }
}
