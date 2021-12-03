package User.Boundary;

public interface UserOutputBoundary {
    void loginMessage(boolean loggedIn);
    void logoutMessage(String name);
    void signupMessage(boolean signedUp);
    void scheduleList(String listSchedules);
    void addWeightHeightPrompt();
    void bmiMessage(double bmi, String weightCategory);
    String createDayPrompt();
    String[] askUnitType();
    Double askMeasurements(String message);
    void noBMI(String message);
}
