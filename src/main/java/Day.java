//import statements go here
import java.util.ArrayList;

/**
 * A general day in the user's schedule that is not tied to a particular weekday.
 */

//how to attach the specific weekday to a day object?

// will need to also update Schedule based on new structure of storing workouts

// when implementing meals + be a part of the daily summaries
// how should we represent meals? just the name with the calories it has?

public class Day {
    private Workout[] workouts;
    private ArrayList<String> meals;
    private int intake;
    private int calBurnt;
    // possible implement dates in the future: ex. November 11,2021

    /**
     * Construct a Day
     */
    public Day(){
        // hard coded limit of 5 different types of workouts per day
        workouts = new Workout[5];
        meals = new ArrayList<>();
        intake = 0;
        calBurnt = 0;
    }

    /**
     * Add a workout to the list of workouts and return true as long as the list isn't already filled.
     * @param workout workout to be added for the day
     * @return if adding the workout was successful or not
     */
    public boolean addWorkout(Workout workout){
        int i = 0;
        while ((workouts[i] != null) && (i < 5)){
            i = i + 1;
        }
        if (i < 5){
            workouts[i] = workout;
            calBurnt = calBurnt + workout.getCaloriesBurnt();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Remove a workout from the list of workouts and return true as long as the workout was removed.
     * @param workout workout to be removed from day
     * @return if removing the workout was successful or not
     */
    public boolean removeWorkout(Workout workout){
        for (int i = 0; i < 5; i++){
            if (workouts[i].getName().equals(workout.getName())){
                calBurnt = calBurnt - workout.getCaloriesBurnt();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the net calories burnt for the day.
     */
    public int getCalories(){ return intake - calBurnt; }

    /**
     * Return the absolute calories burnt for the day.
     */
    public int getOuttake(){ return calBurnt; }

    /**
     * Return the intake of calories for the day.
     */
    public int getIntake(){ return intake; }

    /**
     * Return a string representation of the day.
     */
    public String getDay(){
        StringBuilder stringWorkouts = new StringBuilder();
        for(Workout workout: workouts){
            if (!(workout == null)){
                stringWorkouts.append(workout.getName());
                stringWorkouts.append(", ");
            }
        }

//        StringBuilder stringMeals = new StringBuilder();
//        for(Meal meal: meals){
//            stringMeals.append(meal);
//            stringMeals.append(", ");
//        }
        //
        stringWorkouts = new StringBuilder(stringWorkouts.substring(0, stringWorkouts.length() - 2));
        return stringWorkouts.toString();
    }
}
