package Schedule;

import java.util.ArrayList;
import java.util.List;

public interface ScheduleDataAccess {
    ArrayList<String> loadScheduleWithID(String scheduleID);
    List<Object> loadUserSchedules(String username);
    void saveSchedule(String id, String scheduleName, String username, boolean isPublic, ArrayList<ArrayList<ArrayList<ArrayList<Object>>>> days);
    void updateCurrentSchedule(String username, String scheduleId);
    List<Object> loadPublicSchedules();
    //TODO: DO we want to have editSchedule()?
}