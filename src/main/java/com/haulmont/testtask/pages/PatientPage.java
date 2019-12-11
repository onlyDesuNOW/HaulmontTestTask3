package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.entities.Patient;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;

public class PatientPage extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Grid<Patient> grid = new Grid<>();
        grid.setItems(DataController.getPatients());
        grid.addColumn(Patient::getLastName).setCaption("Last Name").setResizable(false);
        grid.addColumn(Patient::getFirstName).setCaption("First Name").setResizable(false);
        grid.addColumn(Patient::getPatronymic).setCaption("Patronymic").setResizable(false);
        grid.addColumn(Patient::getPhoneNumber).setCaption("Phone Number").setResizable(false);
        grid.setSizeFull();

        Button addBtn = new Button("Add");
        addBtn.addClickListener(clickEvent -> {

            PatientAddEditWindow window = new PatientAddEditWindow("New patient");
            window.addPatient();
            UI.getCurrent().addWindow(window);

            window.getOk().addClickListener(clickEvent1 -> {
                if (window.isAccepted()) {
                    updatePatients(grid);
                }
            });
        });

        Button edBtn = new Button("Edit");
        edBtn.addClickListener(clickEvent -> {

            if (grid.getSelectionModel().getFirstSelectedItem().isPresent()) {
                PatientAddEditWindow window = new PatientAddEditWindow("Edit patient");
                window.editPatient(grid.getSelectionModel().getFirstSelectedItem().get());
                UI.getCurrent().addWindow(window);

                window.getOk().addClickListener(clickEvent1 -> {
                    if (window.isAccepted()) {
                        updatePatients(grid);
                    }
                });
            }
        });

        Button delBtn = new Button("Delete");
        delBtn.addClickListener(clickEvent -> {

            if (grid.getSelectionModel().getFirstSelectedItem().isPresent()) {

                DeleteWindow window = new DeleteWindow(grid.getSelectionModel().getFirstSelectedItem().get(),
                        "Delete Patient");
                UI.getCurrent().addWindow(window);

                window.getOk().addClickListener(clickEvent1 -> {
                    if (window.isAccepted()) {
                        updatePatients(grid);
                    }
                });
            }
        });

        VerticalLayout layout = new VerticalLayout(grid);
        HorizontalLayout actionLayout = new HorizontalLayout(addBtn, edBtn, delBtn);
        addComponent(layout);
        addComponent(actionLayout);
    }

    private void updatePatients(Grid<Patient> patientGrid){ patientGrid.setItems(DataController.getPatients()); }

}
