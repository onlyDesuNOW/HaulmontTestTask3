package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.entities.Patient;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

public class PatientAddEditWindow extends Window {

    private VerticalLayout layout = new VerticalLayout();

    private TextField lastNameField = new TextField("Last Name");
    private TextField firstNameField = new TextField("First Name");
    private TextField patronymicField = new TextField("Patronymic (optionally)");
    private TextField phoneNumberField = new TextField("Phone number (7 or 11 numbers)");

    private Button ok = new Button("Okay");
    private Button cancel = new Button("Cancel");

    private boolean accepted = false;

    boolean isAccepted() {
        return accepted;
    }

    Button getOk() {
        return ok;
    }

    private Patient getPatient(long id){

        return new Patient(
                id,
                firstNameField.getValue(),
                lastNameField.getValue(),
                patronymicField.getValue(),
                phoneNumberField.getValue()
        );
    }

    private boolean invalidValues(){
        long num = Long.parseLong(phoneNumberField.getValue());

        return (lastNameField.isEmpty())||(isNotName(lastNameField.getValue()))
                || (firstNameField.isEmpty()) ||(isNotName(firstNameField.getValue()))
                ||(patronymicField.getValue()!="" && isNotName(patronymicField.getValue()))
                || (phoneNumberField.isEmpty())
                ||!((num > 999999  &&  num < 10000000)||(num > 9999999999L  &&  num < 100000000000L));

    }

    PatientAddEditWindow(String caption) {

        super(caption);
        setModal(true);
        setClosable(false);
        setResizable(false);
        setDraggable(false);

        Layout buttons = new HorizontalLayout();
        buttons.addComponents(ok, cancel);
        layout.addComponents(lastNameField, firstNameField, patronymicField, buttons);

        cancel.addClickListener(clickEvent -> close());
        setContent(layout);
    }

    void addPatient(){

        setIcon(VaadinIcons.PLUS);
        layout.addComponent(phoneNumberField, 3);

        ok.addClickListener(clickEvent -> {

            if (invalidValues()){
                new ErrorWindow(new Exception("Adding is not possible - the entered values are incorrect.\n" +
                        "Check the values for correctness."));
            } else {
                DataController.addPatient(getPatient(0));
                accepted = true;
                close();
            }
        });
    }

    void editPatient(Patient entity){

        setIcon(VaadinIcons.EDIT);
        layout.addComponent(phoneNumberField, 3);

        lastNameField.setValue(entity.getLastName());
        firstNameField.setValue(entity.getFirstName());
        patronymicField.setValue(entity.getPatronymic());

        TextField phone = (TextField) layout.getComponent(3);
        phone.setValue(entity.getPhoneNumber());

        ok.addClickListener(clickEvent -> {

            if (invalidValues()){
                new ErrorWindow(new Exception("Editing is not possible - the entered values are incorrect.\n" +
                        "Check the values for correctness."));
            } else {
                DataController.updatePatient(getPatient(entity.getId()));
                accepted = true;
                close();
            }
        });
    }

    public boolean isNotName(String name)
    {
        if(name.length()==0) return false;
        if (Character.isUpperCase(name.charAt(0))) {
            for(int i=1; i < name.length(); i++) {
                if(!(Character.isLowerCase(name.charAt(i))))
                    return true;
            }
            return false;
        }
        return true;
    }


}
