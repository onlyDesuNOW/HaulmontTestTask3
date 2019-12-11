package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.entities.Doctor;
import com.haulmont.testtask.entities.Patient;
import com.haulmont.testtask.entities.Recipe;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

public class DeleteWindow extends Window{
    private boolean accepted = false;

    private Label label = new Label();
    private Button ok = new Button("Okay");
    private Button cancel = new Button("Cancel");
    private HorizontalLayout buttons = new HorizontalLayout(ok,cancel);
    private VerticalLayout layout = new VerticalLayout(label, buttons);

    Button getOk() {
        return ok;
    }

    boolean isAccepted() {
        return accepted;
    }

    private void init(Object entity){
        setModal(true);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
        setIcon(VaadinIcons.TRASH);
        label.setValue("Are you sure you want to delete this entry?");
        cancel.addClickListener(clickEvent -> close());
        setContent(layout);
    }

    DeleteWindow(Doctor entity, String caption){
        super(caption);
        init(entity);

        ok.addClickListener(clickEvent -> {
            try {

                DataController.deleteDoctor(entity);
                accepted = true;
                close();


            } catch (Exception e) {
                new ErrorWindow(e);
            }
        });
    }

    DeleteWindow(Patient entity, String caption){
        super(caption);
        init(entity);

        ok.addClickListener(clickEvent -> {
            try {

                DataController.deletePatient(entity);
                accepted = true;
                close();

            } catch (Exception e) {
                new ErrorWindow(e);
            }
        });
    }

    DeleteWindow(Recipe entity, String caption){
        super(caption);
        init(entity);

        ok.addClickListener(clickEvent -> {
            try {

                DataController.deleteRecipe(entity);
                accepted = true;
                close();

            } catch (Exception e) {
                new ErrorWindow(e);
            }
        });
    }
}
