package dao;

import models.Operation;
import models.Location;
import models.Assessment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.ArrayList;
import java.util.List;

public class Sql2oLocationDao implements LocationDao{

    private final Sql2o sql2o;

    public Sql2oLocationDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Location location) {
        String sql = "INSERT INTO locations (name, terrain, condition, mission, site, map) VALUES (:name, :terrain, :condition, :mission, :site, :map)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(location)
                    .executeUpdate()
                    .getKey();
            location.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Location> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM locations")
                    .executeAndFetch(Location.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from locations WHERE id = :id"; //raw sql
        String deleteJoin = "DELETE from locations_operations WHERE locationid = :locationId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("locationId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Location findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM locations WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
        }
    }

    @Override
    public void update(int id, String newName, String newTerrain, String newCondition, String newMission, String newSite, String newMap){
        String sql = "UPDATE locations SET (name, terrain, condition, mission, site, map) = (:name, :terrain, :condition, :mission, :site, :map) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("terrain", newTerrain)
                    .addParameter("condition", newCondition)
                    .addParameter("mission", newMission)
                    .addParameter("site", newSite)
                    .addParameter("map", newMap)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addLocationsToOperation(Location location, Operation operation){
        String sql = "INSERT INTO locations_operations (locationid, operationid) VALUES (:locationId, :operationId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("locationId", location.getId())
                    .addParameter("operationId", operation.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Operation> getAllOperationsByLocation(int locationId) {
        ArrayList<Operation> operations = new ArrayList<>();

        String joinQuery = "SELECT operationid FROM locations_operations WHERE locationid = :locationId";

        try (Connection con = sql2o.open()) {
            List<Integer> allOperationsIds = con.createQuery(joinQuery)
                    .addParameter("locationId", locationId)
                    .executeAndFetch(Integer.class);
            for (Integer operationId : allOperationsIds){
                String operationQuery = "SELECT * FROM operations WHERE id = :operationId";
                operations.add(
                        con.createQuery(operationQuery)
                                .addParameter("operationId", operationId)
                                .executeAndFetchFirst(Operation.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return operations;
    }

}


