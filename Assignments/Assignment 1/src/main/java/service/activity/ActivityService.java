package service.activity;

import DTO.ClientDTO;
import model.Activity;
import repository.EntityNotFoundException;

import java.util.ArrayList;

public interface ActivityService {

    public void printAllActivitiesWithCondition(String conditionDate) throws EntityNotFoundException;

    public boolean saveActivity(Activity activity);
}
