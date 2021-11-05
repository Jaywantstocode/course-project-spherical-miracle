package User;

public class ManageDataUseCase {

    private User currentUser;

    public ManageDataUseCase(User user){
        this.currentUser = user;
    }
    /**
     * Change this User.User's password to newPassword if the oldPassword matches the user's current password.
     * Return true iff password is successfully changed.
     * @param newPassword the new password for this User.User
     * @return whether the password was successfully changed or not
     **/
    public boolean passwordChange(String oldPassword, String newPassword) {
        return currentUser.changePassword(oldPassword, newPassword);
    }
}
