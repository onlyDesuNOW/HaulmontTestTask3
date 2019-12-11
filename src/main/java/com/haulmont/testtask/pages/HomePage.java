package com.haulmont.testtask.pages;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class HomePage extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        ThemeResource resource = new ThemeResource("images/pic.png");
        Image image = new Image("", resource);
        VerticalLayout layout = new VerticalLayout(image);
        layout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        addComponent(layout);
    }
}