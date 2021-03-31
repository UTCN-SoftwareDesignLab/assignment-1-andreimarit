package repository.activity;

import model.Activity;
import repository.EntityNotFoundException;

import java.util.List;

public interface ActivityRepository {

    Activity findById(int id) throws EntityNotFoundException;

    boolean save(Activity activity);

    List<Activity> findAll() throws EntityNotFoundException;

}
