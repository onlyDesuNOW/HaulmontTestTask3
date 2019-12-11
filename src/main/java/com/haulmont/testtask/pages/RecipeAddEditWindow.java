package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.entities.Doctor;
import com.haulmont.testtask.entities.Patient;
import com.haulmont.testtask.entities.Recipe;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class RecipeAddEditWindow extends Window {


    private List<Doctor> doctorList = DataController.getDoctors();
    private List<Patient> patientList = DataController.getPatients();
    private TextArea descriptionText = new TextArea("Description:");
    private NativeSelect<Doctor> doctorSelect = new NativeSelect<>("Doctor: ");
    private NativeSelect<Patient> patientSelect = new NativeSelect<>("Patient: ");
    private DateField creationDateField = new DateField("creation date: ");
    private DateField validityDateField = new DateField("validation time (after cr.date): ");
    private NativeSelect<Recipe.Priority> prioritySelect = new NativeSelect<>("Priority:");

    private Button ok = new Button("Okay");
    private Button cancel = new Button("Cancel");
    HorizontalLayout buttons = new HorizontalLayout(ok, cancel);
    private boolean accepted = false;

    boolean isAccepted() {
        return accepted;
    }

    Button getOk() {
        return ok;
    }

    private void init(){

        setModal(true);
        setResizable(false);
        setClosable(false);
        setDraggable(false);

        descriptionText.setPlaceholder("Please, write recipe.");

        doctorSelect.setItems(doctorList);
        patientSelect.setItems(patientList);
        creationDateField.setValue(LocalDate.now());
        validityDateField.setValue(LocalDate.now().plusDays(1));
        prioritySelect.setItems(Recipe.Priority.values());

        VerticalLayout layout = new VerticalLayout(doctorSelect,patientSelect, descriptionText,creationDateField,
                validityDateField, prioritySelect, buttons);

        cancel.addClickListener(clickEvent -> close());
        setContent(layout);
    }

    private boolean wrongValues(){

        try {

            if (!patientSelect.getSelectedItem().isPresent()
                    || !doctorSelect.getSelectedItem().isPresent()
                    || !prioritySelect.getSelectedItem().isPresent()
                    || descriptionText.isEmpty()
                    || (creationDateField.getValue().isAfter(validityDateField.getValue())))
            {
                throw new Exception("Adding is not possible - the entered values are incorrect.\n" +
                        "Check the values for correctness.");
            }

            return false;

        } catch (Exception e){

            new ErrorWindow(e);
            return true;
        }
    }

    private Recipe getEntity(long id){
        System.out.println(Date.valueOf(creationDateField.getValue()));
        System.out.println(Date.valueOf(validityDateField.getValue()));
        return new Recipe(id,
                descriptionText.getValue(),
                patientSelect.getSelectedItem().get().getId(),
                doctorSelect.getSelectedItem().get().getId(),
                Date.valueOf(creationDateField.getValue()),
                Date.valueOf(validityDateField.getValue()),
                prioritySelect.getSelectedItem().get());
    }

    RecipeAddEditWindow(){

        super("New Recipe");
        setIcon(VaadinIcons.PLUS);
        init();

        ok.addClickListener(clickEvent -> {

            if (wrongValues()) return;

            DataController.addRecipe(getEntity(0));

            accepted = true;
            close();
        });
    }

    RecipeAddEditWindow(Recipe recipe){

        super("Edit Recipe");
        setIcon(VaadinIcons.EDIT);
        init();

        descriptionText.setValue(recipe.getDescription());

        for (Doctor d: doctorList) {

            if (d.getId() == recipe.getIdDoctor()){
                doctorSelect.setSelectedItem(d);
                break;
            }
        }

        for (Patient p: patientList) {

            if (p.getId() == recipe.getIdPatient()){
                patientSelect.setSelectedItem(p);
                break;
            }
        }

        validityDateField.setValue(new Date(recipe.getValidityDate().getTime()).toLocalDate());
        creationDateField.setValue(new Date(recipe.getCreationDate().getTime()).toLocalDate());
        prioritySelect.setSelectedItem(Recipe.Priority.get(recipe.getPriority().ordinal()));

        ok.addClickListener(clickEvent -> {

            if (wrongValues()) return;
            DataController.updateRecipe(getEntity(recipe.getId()));
            accepted = true;
            close();
        });
    }
}
