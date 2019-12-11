package com.haulmont.testtask.pages;

import com.haulmont.testtask.dbcontrollers.DataController;
import com.haulmont.testtask.dbcontrollers.RecipeSearchController;
import com.haulmont.testtask.entities.Recipe;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecipePage extends VerticalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        Grid<Recipe> grid = new Grid<>();
        grid.setItems(DataController.getRecipes());
        grid.addColumn(Recipe::getDescription).setCaption("Description").setResizable(false);
        grid.addColumn(r -> DataController.getPatientByID(r.getIdPatient()).toString()).setCaption("Patient");
        grid.addColumn(r -> DataController.getDoctorByID(r.getIdDoctor()).toString()).setCaption("Doctor");
        grid.addColumn(r -> (new SimpleDateFormat("dd-MM-yyyy")).format(r.getCreationDate())).setCaption("Creation Date");
        grid.addColumn(r -> (new SimpleDateFormat("dd-MM-yyyy")).format(r.getValidityDate())).setCaption("Validity Date");
        grid.addColumn(Recipe::getPriority).setCaption("Priority");
        grid.setSizeFull();

        TextField descriptionField = new TextField();
        descriptionField.setPlaceholder("Description");
        TextField patientNameField = new TextField();
        patientNameField.setPlaceholder("Patient's Name");
        NativeSelect<Recipe.Priority> prioritySelect = new NativeSelect<>();
        prioritySelect.setItems(Recipe.Priority.values());
        Button srchBtn = new Button("Search");

        srchBtn.addClickListener(clickEvent -> {

            List<Recipe> result = DataController.getRecipes();

            if (!descriptionField.isEmpty()){
                result = RecipeSearchController.searchDescription(result,descriptionField.getValue());
            }

            if (!patientNameField.isEmpty()){
                result = RecipeSearchController.searchPatient(result, patientNameField.getValue());
            }

            if (prioritySelect.getSelectedItem().isPresent()){
                Recipe.Priority selectedPrior = prioritySelect.getSelectedItem().get();
                result = RecipeSearchController.searchPriority(result, selectedPrior);
            }

            grid.setItems(result);

        });

        Button addBtn = new Button("Add");
        addBtn.addClickListener(clickEvent -> {

            RecipeAddEditWindow window = new RecipeAddEditWindow();
            UI.getCurrent().addWindow(window);

            window.getOk().addClickListener(clickEvent1 -> {

                if (window.isAccepted()) {

                    updateRecipes(grid);
                    grid.setItems(DataController.getRecipes()); //для сброса фильтра, если он стоит
                }
            });
        });

        Button edBtn = new Button("Edit");
        edBtn.addClickListener(clickEvent -> {

            if (grid.getSelectionModel().getFirstSelectedItem().isPresent()){

                RecipeAddEditWindow dialog = new RecipeAddEditWindow(grid.getSelectionModel().getFirstSelectedItem().get());
                UI.getCurrent().addWindow(dialog);

                dialog.getOk().addClickListener(clickEvent1 -> {
                    if (dialog.isAccepted()) {
                        updateRecipes(grid);
                        grid.setItems(DataController.getRecipes()); //для сброса фильтра, если он стоит
                    }
                });
            }
        });

        Button delBtn = new Button("Delete");
        delBtn.addClickListener(clickEvent -> {

            if ((grid.getSelectionModel().getFirstSelectedItem().isPresent())) {

                DeleteWindow window = new DeleteWindow(grid.getSelectionModel().getFirstSelectedItem().get(),
                        "Delete Recipe");
                UI.getCurrent().addWindow(window);

                window.getOk().addClickListener(clickEvent1 -> {
                    if (window.isAccepted()) {
                        updateRecipes(grid);
                        grid.setItems(DataController.getRecipes()); //для сброса фильтра, если он стоит
                    }
                });
            }

        });

        HorizontalLayout searchLayout = new HorizontalLayout(descriptionField, patientNameField, prioritySelect, srchBtn);
        VerticalLayout layout = new VerticalLayout(grid);
        HorizontalLayout actionLayout = new HorizontalLayout(addBtn, edBtn, delBtn);
        addComponent(new Label("If you use the search and perform any action with the table," +
                              " the search is reset. Remember this."));
        addComponent(searchLayout);
        addComponent(layout);
        addComponent(actionLayout);

    }

    private void updateRecipes(Grid<Recipe> grid){

        grid.setItems(DataController.getRecipes());
    }
}
