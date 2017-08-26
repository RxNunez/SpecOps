package dao;

import models.Assessment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAssessmentDao implements AssessmentDao  {

    private final Sql2o sql2o;

    public Sql2oAssessmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Assessment assessment) {
        String sql = "INSERT INTO assessments (rater, rating, locationid) VALUES (:rater, :rating, :locationid)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("rater", assessment.getRater())
                    .addParameter("rating", assessment.getRating())
                    .addParameter("locationid", assessment.getLocationId())
//                    .addParameter("createdat", "createdat")
//                    .addColumnMapping("CREATEDAT", "createdat")
                    .addColumnMapping("RATER", "rater")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("LOCATIONID", "locationid")
                    .executeUpdate()
                    .getKey();
            assessment.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from assessments WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Assessment> getAllAssessmentsByLocation(int locationId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM assessments WHERE locationId = :locationid")
                    .addColumnMapping("LOCATIONID", "locationid")
                    .addParameter("locationId", locationId)
                    .executeAndFetch(Assessment.class);
        }
    }

}

