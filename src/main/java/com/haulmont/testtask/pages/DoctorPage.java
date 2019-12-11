package com.haulmont.testtask.pages;

import com.haulmont.testtask.entities.Doctor;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import com.haulmont.testtask.dbcontrollers.DataController;

public class DoctorPage extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Grid<Doctor> grid = new Grid<>();
        grid.setItems(DataController.getDoctors());
        grid.addColumn(Doctor::getLastName).setCaption("Last Name").setResizable(false);
        grid.addColumn(Doctor::getFirstName).setCaption("First Name").setResizable(false);
        grid.addColumn(Doctor::getPatronymic).setCaption("Patronymic").setResizable(false);
        grid.addColumn(Doctor::getSpeciality).setCaption("Speciality").setResizable(false);
        grid.setSizeFull();

        Button addBtn = new Button("Add");
        addBtn.addClickListener(clickEvent -> {

            DoctorAddEditWindow window = new DoctorAddEditWindow("New doctor");
            window.addDoctor();
            UI.getCurrent().addWindow(window);

            window.getOk().addClickListener(clickEvent1 -> {
                if (window.isAccepted()) {
                    updateDoctors(grid);
                }
            });
        });

        Button edBtn = new Button("Edit");
        edBtn.addClickListener(clickEvent -> {

            if (grid.getSelectionModel().getFirstSelectedItem().isPresent()) {
                DoctorAddEditWindow window = new DoctorAddEditWindow("Edit doctor");
                window.editDoctor(grid.getSelectionModel().getFirstSelectedItem().get());
                UI.getCurrent().addWindow(window);

                window.getOk().addClickListener(clickEvent1 -> {
                    if (window.isAccepted()) {
                        updateDoctors(grid);
                    }
                });
            }
        });

        Button delBtn = new Button("Delete");
        delBtn.addClickListener(clickEvent -> {

            if ((grid.getSelectionModel().getFirstSelectedItem().isPresent())) {
                DeleteWindow window = new DeleteWindow(grid.getSelectionModel().getFirstSelectedItem().get(),
                        "Delete doctor");
                UI.getCurrent().addWindow(window);

                window.getOk().addClickListener(clickEvent1 -> {
                    if (window.isAccepted()) {
                        updateDoctors(grid);
                    }
                });
            }
        });

        Button stBtn = new Button("Statistics");
        //Изначально собирался сделать графиком, но столкнулся с трудностями,
        //которые не решить "прямо сейчас".
        stBtn.addClickListener(clickEvent -> UI.getCurrent().addWindow(new ChartsWindow()));

        VerticalLayout layout = new VerticalLayout(grid);
        HorizontalLayout actionLayout = new HorizontalLayout(addBtn, edBtn, delBtn, stBtn);
        addComponent(layout);
        addComponent(actionLayout);
    }

    private void updateDoctors(Grid<Doctor> gridName){
        gridName.setItems(DataController.getDoctors());
    }
}
