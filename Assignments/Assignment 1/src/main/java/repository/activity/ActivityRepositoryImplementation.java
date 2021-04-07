package repository.activity;

import model.Activity;
import model.builder.ActivityBuilder;
import repository.EntityNotFoundException;
import repository.user.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepositoryImplementation implements ActivityRepository{

    private final Connection connection;

    public ActivityRepositoryImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Activity> findAll() throws EntityNotFoundException {
        List<Activity> activities = new ArrayList<Activity>();
        try{
            Statement statement = connection.createStatement();
            String fetchActivitySql = "Select * from activity";
            ResultSet activityResultSet = statement.executeQuery(fetchActivitySql);
            while(activityResultSet.next()){
                Activity activity =  new ActivityBuilder()
                        .setId(activityResultSet.getInt("id"))
                        .setUserId(activityResultSet.getInt("user_id"))
                        .setDescription(activityResultSet.getString("description"))
                        .setDate(activityResultSet.getString("date"))
                        .build();

                activities.add(activity);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public Activity findById(int id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String fetchActivitySql = "Select * from activity WHERE id = " + id;
            ResultSet activityResultSet = statement.executeQuery(fetchActivitySql);
            if (activityResultSet.next()) {
                return new ActivityBuilder()
                        .setId(activityResultSet.getInt("id"))
                        .setUserId(activityResultSet.getInt("user_id"))
                        .setDescription(activityResultSet.getString("description"))
                        .setDate(activityResultSet.getString("date"))
                        .build();
            } else {
                throw new EntityNotFoundException((long) id, Activity.class.getSimpleName());
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new EntityNotFoundException((long) id, Activity.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Activity activity) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO activity values (null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setLong(1, activity.getUserId());
            insertUserStatement.setString(2, activity.getDescription());
            insertUserStatement.setString(3, activity.getDate());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
