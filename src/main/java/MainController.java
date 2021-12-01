import Schedule.ScheduleController;
import User.UserController;

import java.time.DayOfWeek;
import java.util.*;

public class MainController {

    private final UserController userController;
    private final ScheduleController scheduleController;
    private final SessionController sessionController;
    private final Presenter presenter;

    public MainController(DataAccess databaseGateway, Presenter presenter) {
        userController = new UserController(databaseGateway, presenter);
        scheduleController = new ScheduleController(databaseGateway, presenter);
        sessionController = new SessionController(databaseGateway, presenter);
        this.presenter = presenter;
    }

    public boolean signup(String username, String password, String name, String email) {
        if (userController.createUser(username, password, name, email)) {
            return login(username, password);
        }
        return false;
    }

    public boolean login(String username, String password) {
        return sessionController.login(username, password);
    }

    public void logout() {
        sessionController.logout();
    }

    public void createSchedule(String scheduleName) {
        String username = sessionController.getUsernameOfLoggedInUser();
        String id = scheduleController.createSchedule(scheduleName, username);
        sessionController.setWorkingScheduleID(id);
    }

    public void viewMySchedules() {
        String username = sessionController.getUsernameOfLoggedInUser();
        List<String> ids = scheduleController.viewListOfSchedule(username);
        scheduleController.selectAndDisplaySchedule(ids);
    }

    public void setActiveSchedules(){
        String username = sessionController.getUsernameOfLoggedInUser();
        List<String> ids = scheduleController.viewListOfSchedule(username);
        scheduleController.setActiveSchedule(ids, username);

    }

    public void viewPublicSchedules() {
        scheduleController.viewPublicSchedules();
    }

    public void sendReminderForDay(DayOfWeek dayOfWeek) {
        String username = sessionController.getUsernameOfLoggedInUser();
        scheduleController.reminderFor(username, dayOfWeek);
    }
}