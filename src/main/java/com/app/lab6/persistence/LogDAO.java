package com.app.lab6.persistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import com.mongodb.client.FindIterable;

public class LogDAO {

    private final MongoCollection<Document> logsCollection;

    public LogDAO(MongoDatabase database) {
        this.logsCollection = database.getCollection("logs");
    }

    public void addLog(String log) {
        Document newLog = new Document("Message", log).append("Date", new java.util.Date().toString());
        logsCollection.insertOne(newLog);
    }

    public String listLogs() {
        String response = "";
        FindIterable<Document> logs = logsCollection.find().sort(Sorts.descending("Date")).limit(10);
        for (Document log : logs) {
            log.remove("_id");
            response += log.toJson() + "\n";
            System.out.println(log.toJson());
        }
        return response;
    }
}
