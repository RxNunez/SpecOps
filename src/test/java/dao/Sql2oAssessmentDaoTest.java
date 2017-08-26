package dao;

import models.Location;
import models.Assessment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oAssessmentDaoTest {

    private Connection conn;
    private Sql2oAssessmentDao assessmentDao;
    private Sql2oLocationDao locationDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        assessmentDao = new Sql2oAssessmentDao(sql2o);
        locationDao = new Sql2oLocationDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingAssessmentSetsId() throws Exception {
        Location testLocation = setupLocation();
        locationDao.add(testLocation);
        Assessment testAssessment = new Assessment("Maui", 3,testLocation.getId());
        int originalAssessmentId=testAssessment.getId();
        assessmentDao.add(testAssessment);
        assertNotEquals(originalAssessmentId,testAssessment.getId());
    }

    @Test
    public void getAllAssessmentsByLocation() throws Exception {

        Location testLocation = setupLocation();
        locationDao.add(testLocation);

        Location newLocation = setupLocation();
        locationDao.add(newLocation);

        Assessment testAssessment = new Assessment("Maui", 3,testLocation.getId());
        assessmentDao.add(testAssessment);

        Assessment otherAssessment = new Assessment("Moana", 1,testLocation.getId());
        assessmentDao.add(otherAssessment);


        assertEquals(2, assessmentDao.getAllAssessmentsByLocation(testLocation.getId()).size());
        assertEquals(0, assessmentDao.getAllAssessmentsByLocation(newLocation.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
    }

    //helpers

    public Location setupLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard", "DMZ", "grid.jpg");
    }

}