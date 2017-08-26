import com.google.gson.Gson;
import dao.Sql2oOperationDao;
import dao.Sql2oLocationDao;
import dao.Sql2oAssessmentDao;
import exceptions.ApiException;
import models.Operation;
import models.Location;
import models.Assessment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oOperationDao operationDao;
        Sql2oLocationDao locationDao;
        Sql2oAssessmentDao assessmentDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        locationDao = new Sql2oLocationDao(sql2o);
        operationDao = new Sql2oOperationDao(sql2o);
        assessmentDao = new Sql2oAssessmentDao(sql2o);
        conn = sql2o.open();


        post("/locations/:locationId/assessments/new", "application/json", (req, res) -> {
            int locationId = Integer.parseInt(req.params("locationId"));
            Assessment assessment = gson.fromJson(req.body(), Assessment.class);
            assessment.setLocationId(locationId);
            assessmentDao.add(assessment);
            res.status(201);
            return gson.toJson(assessment);
        });


        post("/operations/new", "application/json", (req, res) -> {
            Operation operation = gson.fromJson(req.body(), Operation.class);
            operationDao.add(operation);
            res.status(201);;
            return gson.toJson(operation);
        });


        //READ

        get("/locations", "application/json", (req, res) -> {
            return gson.toJson(locationDao.getAll());
        });

        get("/locations/:id", "application/json", (req, res) -> {
            int locationId = Integer.parseInt(req.params("id"));

            Location locationToFind = locationDao.findById(locationId);

            if (locationToFind == null){
                throw new ApiException(404, String.format("No location with the id: \"%s\" exists",                   req.params("id")));
            }

            return gson.toJson(locationToFind);
        });

        get("/locations/:id/reviews", "application/json", (req, res) -> {
            int locationId = Integer.parseInt(req.params("id"));

            List<Assessment> allAssessments = assessmentDao.getAllAssessmentsByLocation(locationId);
            return gson.toJson(allAssessments);
        });

        get("/operations", "application/json", (req, res) -> {
            return gson.toJson(operationDao.getAll());
        });



        //CREATE

        post("/locations/new", "application/json", (req, res) -> {
            Location location = gson.fromJson(req.body(), Location.class);
            locationDao.add(location);
            res.status(201);;
            return gson.toJson(location);
        });


        //FILTERS

        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }


    }
