package Interface.exceptions;

import Interface.View;

public class InvalidRedirect extends RuntimeException {
    public InvalidRedirect(Class<? extends View> target) {
        super("View" + target.getName() + " is not registered in base");
    }
}
