package Schedule;

import java.time.DayOfWeek;
import java.util.UUID;

/**
 * A seven-day schedule for a user to customise.
 */
public class Schedule {

    private String name;
    private UUID id;

    private Day[] plan = new Day[7];

    /**
     * Constructs a Schedule with the specified name.
     * @param name name of the Schedule
     */
    public Schedule(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }
    /**
     * Construct a Schedule with the specified name and id.
     * @param name name of the Schedule
     * @param id unique id of the Schedule
     */
    public Schedule(String name, String id) {
        this.name = name;
        this.id = UUID.fromString(id);
    }

    /**
     * Returns the name of this Schedule.
     * @return name of this Schedule.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique identifier of this schedule.
     * @return the unique identifier of this schedule.
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Sets the name of the schedule to be the given name.
     * @param name - the specified new name of this schedule
     */
    public void setName(String name) {
        this.name = name;
    }

    // TODO: Possibly track the exact hour of the workout
    /**
     * Sets a given day into the schedule.
     * @param dayOfWeek the days of the week from 0 being Sunday to 6 being Saturday
     * @param day the day being added
     */
    public void setDay(DayOfWeek dayOfWeek, Day day) {
        plan[dayOfWeek.getValue() - 1] = day;
    }

    /**
     * Remove the day from the list of days for a specific date.
     * @param date the specified day
     * Precondition: the given date exists in this current schedule
     */
    public void cancelDay(int date) { plan[date] = null; }

    /**
     * Return the workout plan for a specific day.
     * @param dayOfWeek the specific day
     * @return a Day
     */
    public Day getDay(DayOfWeek dayOfWeek) {
        return plan[dayOfWeek.getValue()];
    }

    /**
     * Print a string representation of a user's specific schedule.
     */
    public String printSchedule(){
        String workout;
        String meal;
        String outputMsg = "";
        for (DayOfWeek c: DayOfWeek.values()){
            if (this.getDay(c) == null) {
                workout = "Rest Day";
                meal = "No meals";
            } else {
                workout = this.getDay(c).getWorkoutString();
                meal = this.getDay(c).getMealString();
            }
            //String workout = sched.getWorkout(0).getName();
            outputMsg += "This is your plan(s) for day " + (c.getValue()) + ": \n Workouts: " + workout + "\n" +
                    " Meal: " + meal + "\n ";
        }
        return outputMsg;
    }
}