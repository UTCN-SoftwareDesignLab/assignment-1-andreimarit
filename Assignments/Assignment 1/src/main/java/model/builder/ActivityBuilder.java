package model.builder;

import model.Activity;

public class ActivityBuilder {

    private Activity activity;

    public ActivityBuilder(){activity= new Activity();}

    public ActivityBuilder setId(int id){
        activity.setId(id);
        return this;
    }

    public ActivityBuilder setUserId(int userid){
        activity.setUserId(userid);
        return this;
    }

    public ActivityBuilder setDescription(String description){
        activity.setDescription(description);
        return this;
    }

    public ActivityBuilder setDate(String date){
        activity.setDate(date);
        return this;
    }

    public Activity build(){return activity;}
}
