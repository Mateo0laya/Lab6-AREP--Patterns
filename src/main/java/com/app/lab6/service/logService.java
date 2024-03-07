package com.app.lab6.service;

import static spark.Spark.get;
import static spark.Spark.port;


public class logService {

    public static void main(String[] args) {
        port(5000);
        get("logservice" , (req, res) -> "{\"msg\": \"Primer mensaje, 2024-07-03\"}");
    }
}
