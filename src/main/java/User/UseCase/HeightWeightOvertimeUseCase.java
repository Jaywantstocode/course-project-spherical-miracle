package User.UseCase;

import User.Boundary.UserOutputBoundary;
import Database.UserDataAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeightWeightOvertimeUseCase {

    private final UserDataAccess databaseInterface;
    private final UserOutputBoundary outputBoundary;

    public HeightWeightOvertimeUseCase(UserOutputBoundary outputBoundary, UserDataAccess databaseInterface){
        this.outputBoundary = outputBoundary;
        this.databaseInterface = databaseInterface;
    }

    /**
     * Find dates between user's asked date and the present.
     * Give relevant output boundary a list of maps containing height, weight, and particular date.
     * @param username the given username of user
     */
    public void displayHeightWeightOvertime(String username){
        LocalDate date = outputBoundary.askDate(); // the desired start date
        UserDataAccess.BodyMeasurementRecord bmr= databaseInterface.getHeightsWeightsFor(username);
        List<LocalDate> dates = bmr.getDates();
        List<Double> heights = bmr.getHeights();
        List<Double> weights = bmr.getWeights();
        List<Map<String, Object>> record = new ArrayList<>();
        if (dates.size() == 0) {
            outputBoundary.noScheduleFoundMessage(true);
            return;
        }
        int curr = 0;
        while (curr < dates.size() && date.compareTo(dates.get(curr)) > 0 ) {
            curr++;
        }
        for (int i = curr; i < dates.size(); i++){
            // put together the list we are returning
            HashMap<String, Object> measurement = new HashMap<>();
            measurement.put("date", dates.get(i));
            measurement.put("height", heights.get(i));
            measurement.put("weight", weights.get(i));
            record.add(measurement);
        }
        boolean bool = outputBoundary.printListOfHeightWeight(record);
        if (!bool){
            if (dates.size() != 0) {
                outputBoundary.noScheduleFoundMessage(dates.get(dates.size() - 1));
            }
        }
    }
}