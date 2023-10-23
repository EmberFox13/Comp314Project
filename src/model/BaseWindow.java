package model;

import model.WindowModel;

import javax.swing.*;

public abstract class BaseWindow implements WindowModel {
    protected final JFrame previousWindow;

    public BaseWindow(JFrame previousWindow) {
        this.previousWindow = previousWindow;

    }

    @Override
    public abstract void openWindow();

}
