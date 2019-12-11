package com.haulmont.testtask.dbcontrollers;

import com.haulmont.testtask.entities.Recipe;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchController {

    public static List<Recipe> searchDescription(List<Recipe> recipes, String field){

        if (field.isEmpty()) return recipes;

        List<Recipe> result = new ArrayList<>();

        for (Recipe recipeEntity: recipes) {

            if (recipeEntity.getDescription().toLowerCase().contains(field.toLowerCase()))

                result.add(recipeEntity);
        }

        return result;
    }

    public static List<Recipe> searchPatient(List<Recipe> recipes, String field){

        if (field.isEmpty()) return recipes;

        List<Recipe> result = new ArrayList<>();

        for (Recipe recipeEntity: recipes) {

            if (DataController.getPatientByID(recipeEntity.getIdPatient()).contains(field))

                result.add(recipeEntity);
        }

        return result;
    }

    public static List<Recipe> searchPriority(List<Recipe> recipes, Recipe.Priority priority){

        if (priority == null) return recipes;

        List<Recipe> result = new ArrayList<>();

        for (Recipe recipeEntity: recipes) {

            if (recipeEntity.getPriority().equals(priority))

                result.add(recipeEntity);
        }

        return result;
    }
}