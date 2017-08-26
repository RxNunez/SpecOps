package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Assessment {

    private int id;
    private String rater;
    private int rating;
    private Timestamp createdAt;
    private String content;
    private int locationId;

    public Assessment(String rater, int rating, int locationId) {
        this.rater = rater;
        this.rating = rating;
//        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
//        this.content = content;
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assessment assessment = (Assessment) o;

        if (id != assessment.id) return false;
        if (rating != assessment.rating) return false;
        if (locationId != assessment.locationId) return false;
        if (!rater.equals(assessment.rater)) return false;
        return content != null ? content.equals(assessment.content) : assessment.content == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rater.hashCode();
        result = 31 * result + rating;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + locationId;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRater() {
        return this.rater;
    }

    public void setRater(String rater) {
        this.rater = rater;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

//    public String getCreatedAt() {
//    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLocationId() {
        return this.locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

}
