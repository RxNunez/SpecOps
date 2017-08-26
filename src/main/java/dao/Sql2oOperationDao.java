package dao;

import models.Operation;
import models.Location;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oOperationDao implements OperationDao{

    private final Sql2o sql2o;

    public Sql2oOperationDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Operation operation) {
        String sql = "INSERT INTO operations (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(operation)
                    .executeUpdate()
                    .getKey();
            operation.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from operations WHERE id = :id";
        String deleteJoin = "DELETE from locations_operations WHERE operationid = :operationid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("operationid", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Operation> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM operations")
                    .executeAndFetch(Operation.class);
        }
    }

    @Override
    public void addOperationsToLocations(Operation operation, Location location) {
        String sql = "INSERT INTO locations_operations (locationId, operationId) VALUES (:locationId, :operationId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("locationId", location.getId())
                    .addParameter("operationId", operation.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Location> getLocationsByOperations(int operationId) {
        ArrayList<Location> locations = new ArrayList<>();
        String joinQuery = "SELECT locationId FROM locations_operations WHERE operationId = :operationId";
        try (Connection con = sql2o.open()) {
            List<Integer> allLocationIds = con.createQuery(joinQuery)
                    .addParameter("operationId", operationId)
                    .executeAndFetch(Integer.class);
            for (Integer locationId : allLocationIds) {
                String locationQuery = "SELECT * FROM locations WHERE id = :locationId";
                locations.add(
                        con.createQuery(locationQuery)
                                .addParameter("locationId", locationId)
                                .executeAndFetchFirst(Location.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return locations;
    }
}

