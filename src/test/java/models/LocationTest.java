package models;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //getter tests

    @Test
    public void getNameReturnsCorrectName() throws Exception {
        Location testLocation = setupLocation();
        assertEquals("Korea", testLocation.getName());
    }

    @Test
    public void getTerrainReturnsCorrectTerrain() throws Exception {
        Location testLocation = setupLocation();
        assertEquals("Jungle", testLocation.getTerrain());
    }

    @Test
    public void getConditionReturnsCorrectCondition() throws Exception {
        Location testLocation = setupLocation();
        assertEquals("Hostile", testLocation.getCondition());
    }
    @Test
    public void getMissionReturnsCorrectMission() throws Exception {
        Location testLocation = setupLocation();
        assertEquals("Guard", testLocation.getMission());
    }

    @Test
    public void getSiteReturnsCorrectSite() throws Exception {
        Location testLocation = setupAltLocation();
        assertEquals("no site listed", testLocation.getSite());
    }

    @Test
    public void getMapReturnsCorrectMap() throws Exception {
        Location testLocation = setupAltLocation();
        assertEquals("/resources/images/uploads/no_image.jpg", testLocation.getMap());
    }



    //setter tests

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Location testLocation = setupLocation();
        testLocation.setName("Japan");
        assertNotEquals("Korea",testLocation.getName());
    }

    @Test
    public void setTerrainSetsCorrectTerrain() throws Exception {
        Location testLocation = setupLocation();
        testLocation.setTerrain("Urban");
        assertNotEquals("Jungle", testLocation.getTerrain());
    }

    @Test
    public void setConditionSetsCorrectCondition() throws Exception {
        Location testLocation = setupLocation();
        testLocation.setCondition("Friendly");
        assertNotEquals("Hostile", testLocation.getCondition());
    }
    @Test
    public void setMissionSetsCorrectMission() throws Exception {
        Location testLocation = setupLocation();
        testLocation.setMission("Capture");
        assertNotEquals("Guard", testLocation.getMission());
    }

    @Test
    public void setSiteSetsCorrectSite() throws Exception {
        Location testLocation = setupLocation();
        testLocation.setSite("Soyasong");
        assertNotEquals("DMZ", testLocation.getSite());
    }


    @Test
    public void setMapSetsCorrectMap() throws Exception {
        Location testLocation = setupLocation();
        testLocation.setMap("aerial.jpg");
        assertNotEquals("grid.jpg", testLocation.getMap());
    }

    //helpers

    public Location setupLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard", "DMZ", "grid.jpg");

    }

    public Location setupAltLocation (){
        return new Location("Korea", "Jungle", "Hostile", "Guard");

    }

}