package dao;

import models.Operation;
import models.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;



public class Sql2oOperationDaoTest {
    private Sql2oOperationDao operationDao;
    private Sql2oLocationDao locationDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        locationDao = new Sql2oLocationDao(sql2o);
        operationDao = new Sql2oOperationDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingOpsSetsId() throws Exception {
        Operation testOperation = setupNewOperation();
        int originalOperationId = testOperation.getId();
        operationDao.add(testOperation);
        assertNotEquals(originalOperationId,testOperation.getId());
    }

    @Test
    public void addedOperationsAreReturnedFromGetAll() throws Exception {
        Operation testoperation = setupNewOperation();
        operationDao.add(testoperation);
        assertEquals(1, operationDao.getAll().size());
    }

    @Test
    public void noOperationsReturnsEmptyList() throws Exception {
        assertEquals(0, operationDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectOperation() throws Exception {
        Operation operation = setupNewOperation();
        operationDao.add(operation);
        operationDao.deleteById(operation.getId());
        assertEquals(0, operationDao.getAll().size());
    }

    @Test
    public void addOperationToLocationAddsTypeCorrectly() throws Exception {

        Location testLocation = setupLocation();
        Location altLocation = setupAltLocation();

        locationDao.add(testLocation);
        locationDao.add(altLocation);

        Operation testOperation = setupNewOperation();

        operationDao.add(testOperation);

        operationDao.addOperationsToLocations(testOperation, testLocation);
        operationDao.addOperationsToLocations(testOperation, altLocation);


        assertEquals(2, operationDao.getLocationsByOperations(testOperation.getId()).size());

    }
    @Test
    public void deleteOperationAlsoUpdatesJoinTable() throws Exception {

        Location testLocation = setupLocation();

        locationDao.add(testLocation);

        Operation testOperation = setupNewOperation();
        Operation otherOperation = new Operation("Renegade");

        operationDao.add(testOperation);
        operationDao.add(otherOperation);

        operationDao.addOperationsToLocations(testOperation, testLocation);
        operationDao.addOperationsToLocations(otherOperation,testLocation);

        operationDao.deleteById(testLocation.getId());

        assertEquals(0, operationDao.getLocationsByOperations(testOperation.getId()).size());
    }


    public Operation setupNewOperation(){
        return new Operation("Storm");
    }

    public Location setupLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard", "DMZ", "grid.jpg");

    }

    public Location setupAltLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard");

    }

}