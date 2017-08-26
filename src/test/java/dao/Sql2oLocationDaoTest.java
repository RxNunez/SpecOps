package dao;

import models.Location;
import models.Operation;
import models.Assessment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class Sql2oLocationDaoTest {

    private Connection conn;
    private Sql2oLocationDao locationDao;
    private Sql2oOperationDao operationDao;
    private Sql2oAssessmentDao assessmentDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        locationDao = new Sql2oLocationDao(sql2o);
        operationDao = new Sql2oOperationDao(sql2o);
        assessmentDao = new Sql2oAssessmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingOpsSetsId() throws Exception {
        Location testLocation = setupLocation();
        int originalLocationId = testLocation.getId();
        locationDao.add(testLocation);
        assertNotEquals(originalLocationId,testLocation.getId());
    }

    @Test
    public void addedLocationsAreReturnedFromGetAll() throws Exception {
        Location testLocation = setupLocation();
        locationDao.add(testLocation);
        assertEquals(1, locationDao.getAll().size());
    }

    @Test
    public void noLocationsReturnsEmptyList() throws Exception {
        assertEquals(0, locationDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectRestaurant() throws Exception {
        Location testLocation = setupLocation();
        locationDao.add(testLocation);
        locationDao.deleteById(testLocation.getId());
        assertEquals(0, locationDao.getAll().size());
    }
    @Test
    public void getAllOperationsByLocationsReturnsOperationsCorrectly() throws Exception {
        Operation testOperation  = new Operation("Renegade");
        operationDao.add(testOperation);

        Operation otherOperation  = new Operation("Storm");
        operationDao.add(otherOperation);

        Location testLocation = setupLocation();
        locationDao.add(testLocation);
        locationDao.addLocationToOperation(testLocation,testOperation);
        locationDao.addLocationToOperation(testLocation,otherOperation);
        System.out.println(locationDao.getAllOperationsByLocation(testLocation.getId()));

        Operation[] operations = {testOperation, otherOperation};

        assertNotEquals(locationDao.getAllOperationsByLocation(testLocation.getId()), Arrays.asList(operations));
    }

    @Test
    public void deleteLocationAlsoUpdatesJoinTable() throws Exception {
        Operation testOperation  = new Operation("Renegade");
        operationDao.add(testOperation);

        Location testLocation = setupLocation();
        operationDao.add(testOperation);

        Location altLocation = setupAltLocation();
        locationDao.add(altLocation);

        locationDao.addLocationToOperation(testLocation, testOperation);
        locationDao.addLocationToOperation(altLocation, testOperation);

        locationDao.deleteById(testLocation.getId());
        assertEquals(0, locationDao.getAllOperationsByLocation(testLocation.getId()).size());
    }

    //helpers

    public Location setupLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard", "DMZ", "grid.jpg");

    }

    public Location setupAltLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard");

    }

}

