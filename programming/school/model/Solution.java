package programming.school.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Solution {
    private int id;
    private int exerciseId;
    private int userId;
    private Timestamp created;
    private Timestamp updated;
    private String description;



    public Solution(int id, int exercise_id, int user_id, Timestamp created, Timestamp updated, String description) {
        this.id = id;
        this.exerciseId = exercise_id;
        this.userId = user_id;
        this.created = created;
        this.updated=updated;
        this.description=description;
    }
    public Solution(int exercise_id, int user_id) {
        this.exerciseId = exercise_id;
        this.userId = user_id;
        this.created = Timestamp.valueOf(LocalDateTime.now());
    }
    public Solution() {

    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", exerciseId=" + exerciseId +
                ", userId=" + userId +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
