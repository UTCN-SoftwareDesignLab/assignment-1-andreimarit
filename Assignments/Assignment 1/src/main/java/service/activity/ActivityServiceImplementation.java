package service.activity;

import DTO.ActivityDTO;
import DTO.UserDTO;
import model.Activity;
import model.User;
import repository.EntityNotFoundException;
import repository.activity.ActivityRepository;
import repository.client.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class ActivityServiceImplementation implements ActivityService{

    private final ActivityRepository activityRepository;

    public ActivityServiceImplementation(ActivityRepository activityRepository){this.activityRepository=activityRepository;}

    public void printAllActivitiesWithCondition(String conditionDate) throws EntityNotFoundException {

        //ArrayList<ActivityDTO> activitiesDTO = new ArrayList<>();
        List<Activity> activities = activityRepository.findAll();

        for(Activity a : activities){


            ///lets say that we have the format YYYY-MM-DD, it is enough to compare this strings
            // to see if the activity was done before or after the date that we give
            if(stringCompare(a.getDate(), conditionDate) < 0){
                //activitiesDTO.add(new ActivityDTO(a));
                System.out.println(a.toString());
            }
        }

    }

    public int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        if (l1 != l2) {
            return l1 - l2;
        }

        else {
            return 0;
        }
    }

    @Override
    public boolean saveActivity(Activity activity){
        return activityRepository.save(activity);
    }

}
