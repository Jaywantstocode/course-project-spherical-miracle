import Schedule.Boundary.ScheduleOutputBoundary;
import User.Boundary.UserOutputBoundary;

import java.time.DayOfWeek;
import java.util.*;

public class Presenter implements UserOutputBoundary, ScheduleOutputBoundary {

    private Scanner in = new Scanner(System.in);

    public static class Messages {
        static final String WELCOME_MESSAGE = "Welcome! Here are your options:";
        static final String INVALID_INPUT = "Invalid input. Try again.";
        static final String EDIT_SCHEDULE_OPTIONS = "Type: \n" +
                "'n' to change the name of this schedule \n" +
                "'c' to make changes to a day\n" +
                "'s' to save and return to the main menu";
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    public int getNumberBetweenInclusive(int min, int max) {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                int n = Integer.parseInt(in.nextLine());
                if (min <= n && n <= max)
                    return n;
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a number between " + min + " and " + max);
        }
    }

    @Override
    public void loginMessage(boolean loggedIn) {
        if (loggedIn)
            System.out.println("Login successful!");
        else
            System.out.println("Username and password does not match. Please try again.");
    }

    @Override
    public void logoutMessage(String name) {
        System.out.println("You have been logged out. Goodbye " + name + "!");
    }

    @Override
    public void signupMessage(boolean signedUp) {
        if (signedUp)
            System.out.println("Sign up successful!");
        else
            System.out.println("Unsuccessful signup. Username is already taken.");
    }

    @Override
    public void scheduleList(String listSchedules) {

    }

    @Override
    public void addWeightHeightPrompt() {

    }
    @Override
    //todo: add cm display and pound display
    public void currentHeightWeight(Double height, Double weight){
        if (height == 0.0 && weight ==0.0) {
            System.out.println("Height: N/A. Weight: N/A.");
        } else if (height == 0.0){
            System.out.println("Height: N/A. Weight: " + weight + ". ");
        } else if (weight == 0.0){
            System.out.println("Height: "+ height + ".  Weight: N/A" );
        } else {
            System.out.println("Height: "+ height + ".  Weight: " + weight + ". " );
        }
    }

    @Override
    public void bmiMessage(double bmi, String weightCategory) {
        if (weightCategory.equals("N/A")){
            System.out.println("Unable to calculate BMI. Please make sure your weight and height inputted are greater than 0");
        } else {
            System.out.format("Your BMI is: %.2f. Your weight category is: " + weightCategory + ". \n", bmi);
        }
    }

    @Override
    public void scheduleMadeMessage(String returnMessage) {

    }

    @Override
    public void scheduleInfoMessage(String info) {
        System.out.println(info);
    }

    @Override
    public void something(boolean signedUp) {
        
    }

    @Override
    public void listSchedules(List<String> schedules) {
        if (schedules.isEmpty())
            return;
        System.out.println("Here are your schedules:");
        int index = 0;
        for (String scheduleName: schedules) {
            System.out.println(index + ". " + scheduleName);
            index++;
        }
    }

    @Override
    public void currentActiveSchedule(String scheduleName){
        System.out.println("Your current active schedule is " + scheduleName);
    }

    @Override
    public void noActiveSchedule(){
        System.out.println("You don't have an active schedule yet :(");
    }

    @Override
    public void deleteSchedule(String username, String scheduleName) {
            System.out.println("Schedule " + scheduleName + " has been successfully deleted!");
    }

    @Override
    public String viewEditDeleteActivateOptions(){
        while (true){
            System.out.println("Type: \n" +
                    "'view' to view the details of this schedule\n" +
                    "'edit' to make edits to this schedule\n" +
                    "'delete' to delete this schedule\n" +
                    "'activate' to activate this schedule\n" +
                    "'r' to return to the main menu");
            String option = in.nextLine();
            if (option.equalsIgnoreCase("view") ||
                    option.equalsIgnoreCase("edit") ||
                    option.equalsIgnoreCase("delete") ||
                    option.equalsIgnoreCase("activate") ||
                    option.equalsIgnoreCase("r")){
                return option;
            } else {
                System.out.println(Messages.INVALID_INPUT);
            }
        }
    }

    @Override
    public void setActive(String scheduleName) {
        System.out.println(scheduleName + " is now your active schedule.");
    }

    @Override
    public boolean isPublic() {
        System.out.println("Would you like to make this schedule public? Type\n" +
                "'t' for public\n" +
                "'f' for private");
        String option;
        while (true) {
            option = in.nextLine();
            if (option.equals("t"))
                return true;
            else if (option.equals("f"))
                return false;
            System.out.println(Messages.INVALID_INPUT);
        }

    }

    @Override
    public int activeSchedulePrompt(int size) {
        return chooseObjectFromList(size,
                "Enter the # for the schedule that you want to activate. Enter -1 to go back.",
                "schedules");
    }

    @Override
    public int chooseScheduleFromList(int size) {
        return chooseObjectFromList(size, "Enter the # for the schedule that you want to view, edit, delete, or activate.\n" +
                "Enter -1 to go back.", "schedules");
    }

    public int chooseWorkoutFromList(int size) {
        return chooseObjectFromList(size, "Enter the # for the workout that you want to remove.\n" +
                "Enter -1 to go back", "workouts");
    }

    private int chooseObjectFromList(int size, String message, String objectType){
        Scanner in = new Scanner(System.in);
        if (size == 0) {
            System.out.println("There are no " + objectType + " available here. Go create some!");
            return -1;
        }
        System.out.println(message);
        while (true) {
            try {
                int index = Integer.parseInt(in.nextLine());
                if (-1 <= index && index <= size - 1)
                    return index;
            } catch (NumberFormatException e ) {}
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public String selectEditOptions() {
        System.out.println(Messages.EDIT_SCHEDULE_OPTIONS);
        String option;
        while (true) {
            option = in.nextLine();
            if (option.equals("c") || option.equals("s") || option.equals("n"))
                return option;
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public DayOfWeek selectDay() {
        System.out.println("Please select a day (1-7):");
        for (DayOfWeek d : DayOfWeek.values()) {
            System.out.println(d.getValue() + ". " + d.name());
        }
        int dayOfWeek;
        while (true) {
            try {
                dayOfWeek = Integer.parseInt(in.nextLine());
                if (1 <= dayOfWeek && dayOfWeek <= 7)
                    return DayOfWeek.of(dayOfWeek);
            } catch (NumberFormatException e) {}
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public String getName() {
        System.out.println("Enter a new name:");
        String name = in.nextLine();
        while (name.isBlank()) {
            System.out.println("The name cannot be blank. Try again.");
            System.out.println("Enter a new name:");
            name = in.nextLine();
        }
        return name;
    }

    @Override
    public void showNameChange(String oldName, String newName) {
        System.out.println("The name has changed from " + oldName + " -> " + newName + ".");
    }

    @Override
    public Map<String, String> getNameAndCalories(String type) {
        Map<String, String> nameAndCalories = new HashMap<>();
        System.out.println("Enter the name of the " + type);
        String name = in.nextLine();
        nameAndCalories.put("name", name);
        System.out.println("Enter the calories for the " + type);
        int calories;
        while (true) {
            try {
                calories = Integer.parseInt(in.nextLine());
                if (calories >= 0) {
                    nameAndCalories.put("calories", String.valueOf(calories));
                    return nameAndCalories;
                }
            } catch (NumberFormatException e) {}
            System.out.println(Messages.INVALID_INPUT);
        }
    }

    @Override
    public void showAddWorkoutResult(int result, String name) {
        String output;
        if (result == 0) {
            output = "You have added the Workout " + name;
        }
        else if (result == 1) {
            output = "There are too many workouts in this day. Try again.";
        }
        else {
            assert result == 2;
            output = "There is already a workout in this day with the same name. Try again.";
        }
        System.out.println(output);
    }

    @Override
    public String createDayPrompt() {
        System.out.println("Type\n" +
                "'cw' to create a workout\n" +
                "'cm' to create a meal\n" +
                "'rw' to remove a workout\n" +
                "'rm' to remove a meal\n" +
                "'f' if you are finished for this day");
        String option;
        while (true) {
            option = in.nextLine();
            Set<String> availableOptions = new HashSet<>(Arrays.asList("cw", "cm", "rw", "rm", "f"));
            if (availableOptions.contains(option))
                return option;
            System.out.println(Messages.INVALID_INPUT);
        }

    }

    @Override
    public String selectByName(List<String> names) {
        System.out.println("Here are the names to pick from:");
        for (String name: names) {
            System.out.println(name);
        }
        System.out.println("Choose a name from the list:");
        String selectedName = in.nextLine();
        while (!names.contains(selectedName)) {
            System.out.println("Invalid input. Try again.");
            selectedName = in.nextLine();
        }
        return selectedName;
    }

    public void noBMI(String message){
        System.out.println(message);
    }

    //todo: we probably can design this better
    @Override
    public String[] askUnitType(){
        while (true) {
            System.out.println("Select your preferred unit for height. If centimeters, enter 'cm', if feet, enter 'f'.");
            String[] units = new String[2];
            units[0] = in.nextLine();
            if (units[0].equals("cm") || units[0].equals("f")) {
                System.out.println("Select your preferred unit for weight. If kilograms, enter 'kg', if lbs, enter 'lbs'.");
                units[1] = in.nextLine();
                while(!units[1].equals("kg") && !units[1].equals("lbs")) {
                    System.out.println("Incorrect input. Try again.");
                    units[1] = in.nextLine();
                }
                return units;
            } else{
                System.out.println(Messages.INVALID_INPUT);
            }
        }
    }

    @Override
    public Double askMeasurements(String message) {
        while (true) {
            System.out.println("Input your " + message + ", if it didn't change from last time, put -1.");
            try {
                double measurement = in.nextDouble();
                if (!(measurement == -1) && measurement <= 0) {
                    System.out.println("Value must be positive value. Please try again.");
                }
                return measurement;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input. Try again.");
            }

        }
    }
}
