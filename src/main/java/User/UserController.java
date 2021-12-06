package User;

import User.Boundary.*;
import User.UseCase.*;

/**
 * A controller that delegates actions for managing a database of users.
 */
public class UserController {

    private final UserDataAccess databaseInterface;
    private final UserOutputBoundary outputBoundary;


    /**

     * Constructs a controller that handles user-related actions.
     *  @param databaseInterface the access interface to the database
     * @param outputBoundary output boundary for User
     */
    public UserController(UserDataAccess databaseInterface, UserOutputBoundary outputBoundary) {
        this.databaseInterface = databaseInterface;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Returns true iff successfully creates a user with the given information. All fields must be non-empty.
     * @param username the user's username
     * @param password the user's password
     * @param name the user's name
     * @param email the user's email
     * @return whether the user's info was valid and the user was added to the database or not
     **/
    public boolean createUser(String username, String password, String name, String email) {
        if (!userInfoIsValid(username, password, name, email))
            return false;
        CreateUserInputBoundary createUserInputBoundary = new CreateUserUseCase(databaseInterface, outputBoundary);
        return createUserInputBoundary.createUser(username, password, name, email);
    }

    /**
     * Remove the user from the UserDatabase.
     * @param username the user's username
     */
    public void removeUser(String username) {
        //TODO: validating inputs
        // users.remove(username);
    }

    public void getCurrentWeightHeightBMI(String username){
        FetchUserUseCase fetch = new FetchUserUseCase(databaseInterface);
        BMIUseCase bmiUseCase = new BMIUseCase(fetch, outputBoundary);
        bmiUseCase.BMIMessage(username);
    }

    public void addHeightWeight(String username){
        AddHeightWeightUseCase addHeightWeightUseCase = new AddHeightWeightUseCase(databaseInterface, outputBoundary);
        addHeightWeightUseCase.addHeightWeight(username);

    }

    private boolean userInfoIsValid(String username, String password, String name, String email) {
        String[] userInfo = {username, password, name, email};
        for (String info : userInfo) {
            // Could add more checks
            if (info.isBlank())
                return false;
        }
        return true;
    }

    public void viewListOfHeightWeightOvertime(String username){
        HeightWeightOvertimeUseCase heightWeightOvertimeUseCase = new HeightWeightOvertimeUseCase(outputBoundary,
                databaseInterface);
        heightWeightOvertimeUseCase.displayHeightWeightOvertime(username);
    }
}
