package dao;

import models.Operation;
import models.Location;
import models.Assessment;

import java.util.List;

public interface AssessmentDao {

    //create
    void add(Assessment review);

    //read
    List<Assessment> getAllAssessmentsByLocation(int locationId);

    //update


    //delete
    void deleteById(int id);
}

