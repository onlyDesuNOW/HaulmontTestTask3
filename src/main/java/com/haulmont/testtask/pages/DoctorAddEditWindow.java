package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.entities.Doctor;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

    class DoctorAddEditWindow extends Window {

        private VerticalLayout layout = new VerticalLayout();
        private TextField lastNameField = new TextField("Last Name");
        private TextField firstNameField = new TextField("First Name");
        private TextField patronymicField = new TextField("Patronymic (optionally)");
        private TextField specialityField = new TextField("Specialization (first letter is upper)");

        private Button ok = new Button("Okay");
        private Button cancel = new Button("Cancel");

        private boolean accepted = false;

        boolean isAccepted() {
            return accepted;
        }

        Button getOk() {
            return ok;
        }

        private Doctor getDoctor(long id){

            return new Doctor(
                    id,
                    firstNameField.getValue(),
                    lastNameField.getValue(),
                    patronymicField.getValue(),
                    specialityField.getValue()
            );
        }


        private boolean invalidValues(){

            return  (lastNameField.isEmpty())||(isNotName(lastNameField.getValue()))
                    || (firstNameField.isEmpty()) ||(isNotName(firstNameField.getValue()))
                    ||(!patronymicField.getValue().equals("") && isNotName(patronymicField.getValue()))
                    ||(specialityField.isEmpty())||(isNotName(specialityField.getValue()));

        }

        DoctorAddEditWindow(String caption) {

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

        void addDoctor(){
            setIcon(VaadinIcons.PLUS);
            layout.addComponent(specialityField,3);

            ok.addClickListener(clickEvent -> {

                if (invalidValues()){
                    new ErrorWindow(new Exception("Adding is not possible - the entered values are incorrect.\n" +
                            "Check the values for correctness."));
                } else {
                    DataController.addDoctor(getDoctor(0));
                    accepted = true;
                    close();
                }
            });
        }

        void editDoctor(Doctor entity){
            setIcon(VaadinIcons.EDIT);
            layout.addComponent(specialityField,3);

            lastNameField.setValue(entity.getLastName());
            firstNameField.setValue(entity.getFirstName());
            patronymicField.setValue(entity.getPatronymic());

            TextField spec = (TextField) layout.getComponent(3);
            spec.setValue(entity.getSpeciality());

            ok.addClickListener(clickEvent -> {


                if (invalidValues()){
                    new ErrorWindow(new Exception("Editing is not possible - the entered values are incorrect.\n" +
                            "Check the values for correctness."));
                } else {
                    DataController.updateDoctor(getDoctor(entity.getId()));
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

