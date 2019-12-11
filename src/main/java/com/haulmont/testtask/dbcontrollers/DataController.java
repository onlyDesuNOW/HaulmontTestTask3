package com.haulmont.testtask.dbcontrollers;

import com.haulmont.testtask.entities.Doctor;
import com.haulmont.testtask.entities.Patient;
import com.haulmont.testtask.entities.Recipe;

import java.util.List;

public class DataController {

    private static DoctorControllerDB doctorController = new DoctorControllerDB();
    private static PatientControllerDB patientController = new PatientControllerDB();
    private static RecipeControllerDB recipeController = new RecipeControllerDB();

    public static List<Doctor> getDoctors(){ return doctorController.getAll(); }

    //public static List<Doctor> findDoctor(String excerpt){ return doctorController.find(excerpt); }

    public static Doctor getDoctorByID(long id){ return doctorController.getEntityById(id); }

    public static void addDoctor(Doctor entity){ doctorController.create(entity); }

    public static void updateDoctor(Doctor doctor){ doctorController.update(doctor); }


    public static void deleteDoctor(Doctor doctor) throws Exception {

        if (!doctorController.delete(doctor.getId())) {

            throw new Exception("It's not possible to delete this doctor because he has at least one recipe.");
        }
    }


    public static List<Patient> getPatients(){ return patientController.getAll(); }

    public static List<Patient> findPatient(String excerpt){ return patientController.find(excerpt); }

    public static Patient getPatientByID(long id){ return patientController.getEntityById(id); }

    public static void addPatient(Patient entity){ patientController.create(entity); }

    public static void updatePatient(Patient patient){ patientController.update(patient); }

    public static void deletePatient(Patient patient) throws Exception {

        if (!patientController.delete(patient.getId())) {

            throw new Exception("It's not possible to delete this patient because he has at least one recipe.");
        }
    }


    public static List<Recipe> getRecipes(){ return recipeController.getAll(); }

    public static Recipe getRecipeByID(long id){ return recipeController.getEntityById(id); }

    public static void addRecipe(Recipe entity){ recipeController.create(entity); }

    public static void updateRecipe(Recipe recipe){ recipeController.update(recipe); }

    public static void deleteRecipe(Recipe recipe) throws Exception {

        if (!recipeController.delete(recipe.getId())) {

            throw new Exception("Cannot delete this recipe!");
        }
    }

    public static List<Recipe> getDoctorRecipe(Doctor doctor){

        return recipeController.filterByDoctor(doctor.getId());
    }
}
