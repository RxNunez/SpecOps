package dao;

import models.Operation;
import models.Location;
import models.Assessment;

import java.util.List;

public interface OperationDao {


    //create
    void add(Operation operation);
    void addOperationsToLocations(Operation operation, Location location);


    //read
    List<Operation> getAll();
    List<Location> getLocationsByOperations(int id);

    //update


    //delete
    void deleteById(int id);

}
