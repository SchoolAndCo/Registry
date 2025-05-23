package Interface;

import java.util.Scanner;

public abstract class View {
    public static Scanner scanner;
    public static Interface __base;
    protected static Global global = Global.getInstance();
    public String viewPrompt;
    public String viewSignature;
    public String helperText;

    public abstract Boolean init();
    public abstract void draw();
    public abstract Action onCommand(String command);
    public abstract Boolean onSelection(String userInputRaw);

    public static View instantiate(Class<? extends View> viewClass) {
        try {
            return viewClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate view: " + viewClass.getName(), e);
        }
    }

    public static Class<? extends View> signatureParse(String userInputRaw) {
        Boolean response;
        View viewInstance;

        for (Class<? extends View> ViewClass : global.registeredViews) {
            viewInstance = View.instantiate(ViewClass);
            response = viewInstance.onSelection(userInputRaw);

            if (response) {
                return ViewClass;
            }
        }

        return null;
    }

    public static void println(String text) {
        int lines = Helper.countWrappedLines(text + "\n", __base.__terminalSize[1]);
        __base.__renderCycleLines += lines;
        System.out.println(text);
    }

    public static void newLine() {
        __base.__renderCycleLines++;
        System.out.println();
    }

    public static void print(String text) {
        __base.__renderCycleLines += Helper.countWrappedLines(text, __base.__terminalSize[1]);
        System.out.print(text);
    }
}
