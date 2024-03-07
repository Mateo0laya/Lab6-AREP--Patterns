package com.app.lab6;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.get;

public class SparkWebServer {

    public static void main(String... args) {

        staticFiles.location("/public");

        
        get("log", (req, res) -> RRInvoker.invoke());

    }

}

