import Interface.Interface;
import views.ViewA;
public class Main {
    private static Interface app = new Interface();

    public static void main(String[] args) {
        app.registerView(ViewA.class);
        app.start();
    }
}
