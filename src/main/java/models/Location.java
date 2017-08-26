package models;

public class Location {

    private String name;
    private String terrain;
    private String condition;
    private String mission;
    private String site;
    private String map;
    private int id;

    public Location(String name, String terrain, String condition, String mission) {
        this.name = name;
        this.terrain = terrain;
        this.condition = condition;
        this.mission = mission;
        this.site = "no site listed";
        this.map = "/resources/images/uploads/no_image.jpg";

    }

    public Location(String name, String terrain, String condition, String mission, String site, String map) {
        this.name = name;
        this.terrain = terrain;
        this.condition = condition;
        this.mission = mission;
        this.site = site;
        this.map = map;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location that = (Location) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!terrain.equals(that.terrain)) return false;
        if (!condition.equals(that.condition)) return false;
        if (!mission.equals(that.mission)) return false;
        if (site != null ? !site.equals(that.site) : that.site != null) return false;
        return (map != null ? !map.equals(that.map) : that.map == null);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + terrain.hashCode();
        result = 31 * result + condition.hashCode();
        result = 31 * result + mission.hashCode();
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}

