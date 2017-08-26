package dao;

import models.Operation;
import models.Location;
import models.Assessment;

import java.util.List;

public interface LocationDao {

    //create
    void add (Location location);
    void addLocationToOperation(Location location, Operation operation);

    //read
    List<Location> getAll();
    List<Operation> getAllOperationsByLocation(int locationId);

    Location findById(int id);

    //update
    void update(int id, String name, String terrain, String condition, String mission, String site, String map);

    //delete
    void deleteById(int id);
}

