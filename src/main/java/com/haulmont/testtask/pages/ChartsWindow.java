package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.entities.Doctor;

import com.vaadin.ui.*;
import com.vaadin.ui.Label;

import java.util.List;

public class ChartsWindow extends Window {

    ChartsWindow(){
        super("Statistics");
        setModal(true);
        setResizable(false);
        setClosable(false);
        setDraggable(false);

        VerticalLayout layout = new VerticalLayout();
        List<Doctor> list = DataController.getDoctors();

        for (Doctor doctor : list) {

            HorizontalLayout subLayout = new HorizontalLayout();

            Label name = new Label(doctor.toString());
            Label amount = new Label("receipts: " + DataController.getDoctorRecipe(doctor).size());
            subLayout.addComponents(name,amount);
            subLayout.setComponentAlignment(name, Alignment.MIDDLE_LEFT);
            subLayout.setComponentAlignment(amount,Alignment.MIDDLE_RIGHT);
            subLayout.setSizeFull();
            layout.addComponent(subLayout);
        }

        Button ok = new Button("OK");
        ok.addClickListener(clickEvent1 -> close());

        layout.addComponent(ok);

        setContent(layout);
    }
}
