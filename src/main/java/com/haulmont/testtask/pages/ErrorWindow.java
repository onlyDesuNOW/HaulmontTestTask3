package com.haulmont.testtask.pages;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

class ErrorWindow extends Window {

    ErrorWindow(Exception e){

        setModal(true);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
        setIcon(VaadinIcons.FROWN_O);
        center();
        Label label = new Label(e.getMessage());
        Button ok = new Button("Thank");
        VerticalLayout vertical = new VerticalLayout(label, ok);
        vertical.setComponentAlignment(ok, Alignment.BOTTOM_CENTER);
        ok.addClickListener(clickEvent1 -> close());
        setContent(vertical);
        UI.getCurrent().addWindow(this);
    }
}