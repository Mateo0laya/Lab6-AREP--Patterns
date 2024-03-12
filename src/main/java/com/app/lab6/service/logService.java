package com.app.lab6.service;

import com.app.lab6.persistence.Mongo;

import static spark.Spark.get;
import static spark.Spark.port;

public class LogService {

    public static void main(String[] args) {

        port(getPort());

        new Mongo();

        get("logservice", (req, res) -> {
            Mongo.addLog(req.queryParams("log"));
            return Mongo.listLogs();
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35001;
    }
}