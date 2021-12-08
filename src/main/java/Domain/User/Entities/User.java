package Domain.User.Entities;

import Domain.User.ConvertStrategies.HeightStrategies.CmStrategy;
import Domain.User.ConvertStrategies.HeightStrategies.FtAndInStrategy;
import Domain.User.ConvertStrategies.HeightStrategies.HeightConverter;
import Domain.User.ConvertStrategies.WeightStrategies.LbsStrategy;
import Domain.User.ConvertStrategies.WeightStrategies.WeightConverter;
import org.mindrot.jbcrypt.BCrypt;

/**
 * A user's account, which contains private details about the user.
 */
public class User {

    private String name;
    private String username;
    private String password;
    private String email;
    private double weight;
    private double height;
    private HeightConverter heightConverter;
    private WeightConverter weightConverter;

    /**
     * Constructs a User, giving them the name, username, email, and password.
     * @param name the name of the user
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.weight = 0;
        this.height = 0;
    }

    /**
     * Constructs a User, giving them the name, username, email, and password.
     * @param name the name of the user
     * @param username the username of the user
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String username, String password, String name, String email, double height, double weight) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    /**
     * Returns the name of this User.
     * @return user's name
     **/
    public String getName() {
        return name;
    }

    /**
     * Returns the username of this User.
     * @return user's username
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email of this User.
     * @return user's email
     **/
    public String getEmail() {
        return email;
    }

    /**
     * Returns the weight of this User.
     * @return user's weight
     **/
    public double getWeight(){
        return weight;
    }

    /**
     * Sets the weight of this User.
     * @return user's current BMI, kg/m^2 (return error message if invalid input for height/weight).
     */
    public Object getBMI(){
        double output = this.weight / Math.pow(this.height, 2);
        if (Double.isNaN(output)) {
            return "Incalculable. You have to have to add your Height/Weight in order to calculate your BMI.";
        }else{
            return output;
        }
    }

    /**
     * Returns the height of this User.
     * @return user's height
     */
    public double getHeight(){
        return height;
    }

    /**
     * Returns if tryPassword matches this User's password
     * @param tryPassword the password that the user input to attempt logging in
     * @return True iff tryPassword matches this User's password.
     */
    public boolean passwordMatches(String tryPassword) {
        return BCrypt.checkpw(tryPassword, password);
    }

    /**
     * Changes this User's password to newPassword if the oldPassword matches the user's current password.
     * Return true iff password is successfully changed.
     * @param newPassword the new password for this User
     * @return whether the password was successfully changed or not
     **/
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.passwordMatches(oldPassword)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }
}
