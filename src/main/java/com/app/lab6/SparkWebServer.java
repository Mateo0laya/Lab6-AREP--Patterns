package com.app.lab6;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.get;

public class SparkWebServer {

    public static void main(String... args) {

        getPort();

        staticFiles.location("/public");

        get("log", (req, res) -> RRInvoker.invoke(req.queryParams("msg")));

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}