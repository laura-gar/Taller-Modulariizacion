package edu.escuelaing.arep;

import edu.escuelaing.arep.services.LogService;

import java.util.ArrayList;

import static spark.Spark.*;


public class App {
    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        LogService logService = new LogService();

        port(getPort());

        get("/hello", (req, res) -> "Hello Docker");

        // Allow CORS
        options("/*",
                (request, response) -> {
                    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                    }
                    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                    }
                    return "OK";
                });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/message", (req, res) -> {
            res.type("application/json");
            logService.createConnection();

            ArrayList<String> allItems = logService.getAllItems();

            logService.closeConnection();

            return allItems;
        });

        post("/message", (req, res) -> {
            res.type("application/json");
            logService.createConnection();

            if (req.body() != null) {
                logService.addItem(req.body());
            }

            ArrayList<String> allItems = logService.getAllItems();
            logService.closeConnection();

            return allItems;
        });


    }


    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}