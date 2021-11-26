package Schedule;

import Schedule.Boundary.CreateScheduleInputBoundary;
import Schedule.Boundary.ScheduleOutputBoundary;
import Schedule.UseCase.CreateScheduleUseCase;
import Schedule.UseCase.FetchSchedulesUseCase;

import java.util.List;
import java.util.Map;

/**
 * A controller that manages a schedule for a user.
 */

public class ScheduleController {

    private final ScheduleDataAccess scheduleDatabase;
    private final ScheduleOutputBoundary scheduleOutputBoundary;

    /**
     * Construct a list of the information needed to create a new user and the UserDatabase data.
     * @param scheduleDatabase the dataAccessInterface to the database for schedules.
     */
    public ScheduleController(ScheduleDataAccess scheduleDatabase, ScheduleOutputBoundary scheduleOutputBoundary){
        this.scheduleDatabase= scheduleDatabase;
        this.scheduleOutputBoundary = scheduleOutputBoundary;
    }

    /**
     * Create a new schedule with the given name.
     * Otherwise, return false.
     **/
    public void createSchedule(String scheduleName, String username, boolean isPublic, List<List<List<Map<String, String>>>> days) {
        //TODO: validating inputs
        //boolean is_valid = ;
        //if (is_valid) {
        CreateScheduleInputBoundary createScheduleInputBoundary= new CreateScheduleUseCase(scheduleDatabase, scheduleOutputBoundary);
        createScheduleInputBoundary.createSchedule(scheduleName, username, isPublic, days);
        //}
        //return false;
    }

    /**
     * Removes the schedule from a user's database of schedules.
     * @param name the name of the schedule being removed
     */
    public void removeSchedule(String name) {
        //TODO: validating inputs


    }

    public void viewListOfSchedule(String username){
//        InputBoundary InputBoundary = new (scheduleDatabase, scheduleOutputBoundary);
        FetchSchedulesUseCase fetch = new FetchSchedulesUseCase(scheduleDatabase, scheduleOutputBoundary);
        fetch.getScheduleAssociatedWith(username);

    }

}
