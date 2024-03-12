package com.app.lab6.persistence;

import com.mongodb.client.MongoDatabase;

public class Mongo {

    public static void addLog(String log) {
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);
        logDAO.addLog(log);
    }

    public static String listLogs() {
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);
        return logDAO.listLogs();
    }
}