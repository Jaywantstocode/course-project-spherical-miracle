package Database;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ScheduleDataAccess {

    String workoutName = "workoutName";
    String calories = "calories";
    String mealName= "mealName";

    /**
     * Returns the details of the schedule with id scheduleID.
     * @param scheduleID - the id of the desired schedule
     * @return an instance of ScheduleInfo containing the details of the schedule
     */
    ScheduleInfo loadScheduleWithID(String scheduleID);

    /**
     * Returns a list of the details of schedules associated with the specified user.
     * @param username - the username of the user
     * @return list of ScheduleInfo, each containing the details of a schedule
     */
    List<ScheduleInfo> loadSchedulesFor(String username);

    /**
     * 
     * @param username
     * @return
     */
    ScheduleInfo loadActiveSchedule(String username);
    
    /**
     * Saves the given schedule into the database.
     * @param scheduleInfo
     * @param username - the username associated with the given schedule
     * @param isPublic - whether the schedule is public
     */
    void createSchedule(ScheduleInfo scheduleInfo, String username, boolean isPublic);

    /**
     * Updates a user's schedule's status based on if it is currently an active schedule.
     * @param username user that the schedule being updated belongs to
     * @param scheduleId id of the schedule being updated
     */
    void updateCurrentSchedule(String username, String scheduleId);

    /**
     * Returns a list of the details of all public schedules.
     * @return list of ScheduleInfo, each containing the details of a schedule
     */

    List<ScheduleInfo> loadPublicSchedules();

    void deleteUserSchedule(String username, String scheduleId);

    /**
     * A class encapsulating the details of a schedule.
     */
    class ScheduleInfo {
        private final String name;
        private String id;
        private final List<List<List<Map<String, String>>>> details;

        /**
         * Constructs a ScheduleInfo object with the given information.
         * @param id - the id of the schedule
         * @param name - the name of the schedule
         * @param details - the details of the schedule
         */
        public ScheduleInfo(String id, String name, List<List<List<Map<String, String>>>> details) {
            this.id = id;
            this.name = name;
            this.details = details;
        }

        public String getId() {
            return id;
        }

        public void randomizeId(){ id = UUID.randomUUID().toString();}

        public String getName() {
            return name;
        }

        public List<List<List<Map<String, String>>>> getDetails() {
            return details;
        }
    }
}