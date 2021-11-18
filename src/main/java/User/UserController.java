package User;

/**
 * A controller that delegates actions for managing a database of users.
 */
public class UserController {

    private final UserDataAccess databaseInterface;

    /**

     * Constructs a controller that handles user-related actions.
     *
     * @param databaseInterface the access interface to the database.
     */
    public UserController(UserDataAccess databaseInterface) {
        this.databaseInterface = databaseInterface;
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
        CreateUserInputBoundary createUserInputBoundary = new CreateUserUseCase(databaseInterface);
        boolean success = createUserInputBoundary.createUser(username, password, name, email);
        if (success)
            System.out.println("User added!");
        else
            System.out.println("Username already taken. Please try again.");
        return success;
    }

    /**
     * Remove the user from the UserDatabase.
     * @param username the user's username
     */
    public void removeUser(String username) {
        //TODO: validating inputs
        // users.remove(username);
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
}
