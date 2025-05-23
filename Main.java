import Interface.Interface;
import views.*;
public class Main {
    private static Interface app = new Interface();

    public static void main(String[] args) {
        app.registerView(CounterView.class);
        app.registerView(BeeView.class);
        app.registerView(Notes.class);
        app.registerView(HelloView.class);
        app.start();
    }
}
