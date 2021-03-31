package DTO;

import model.Activity;

public class ActivityDTO {

    private int id;
    private int userId;
    private String description;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityDTO(Activity activity) {
        this.id = activity.getId();
        this.userId = activity.getUserId();
        this.description = activity.getDescription();
        this.date = activity.getDate();
    }
}
