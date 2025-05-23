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

    public static View signatureParse(String userInputRaw) {
        Boolean response;
        View viewInstance;

        for (Class<? extends View> ViewClass : global.registeredViews) {
            viewInstance = View.instantiate(ViewClass);
            response = viewInstance.onSelection(userInputRaw);

            if (response) {
                return viewInstance;
            }
        }

        return null;
    }

    public static void print(String text) {
        __base.__renderCycleLines += 1;
        System.out.print(text);
    }
}
