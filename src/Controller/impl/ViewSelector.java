package Controller.impl;

import View.interfaces.IView;
import View.impl.Window;

public class ViewSelector {
    private static ViewSelector instance = null;
    private ViewSelector() {}

    public static ViewSelector getInstance() {
        if (instance == null) {
            instance = new ViewSelector();
        }
        return instance;
    }

    public IView getView(ViewType viewType) {
        IView view = null;
        switch (viewType) {
            case GraphicView: {
                view = new Window();
                break;
            }
        }
        return view;
    }
}
