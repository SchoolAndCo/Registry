package Interface.action;

import Interface.Action;
import Interface.Interface;
import Interface.View;
import Interface.exceptions.InvalidRedirect;

public class a_redirect extends Action{
    public Class<? extends View> target;

    public a_redirect(Class<? extends View> target) {
        this.target = target;
    }

    public Boolean execute(Interface base) {
        if (!global.registeredViews.contains(target)) {
            throw new InvalidRedirect(target);
        }

        return base.selectView(target);
    }
}
