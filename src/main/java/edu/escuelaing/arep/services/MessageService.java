package edu.escuelaing.arep.services;

import com.mongodb.ConnectionString;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;


public class MessageService {
    private MongoClient mongoClient;
    private final static String URL = "mongodb://admin:admin@localhost:8094/retryWrites=true&w=majority";

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MessageService() {
        ConnectionString connection = new ConnectionString(URL);
        this.mongoClient = MongoClients.create(connection);
        this.database = this.mongoClient.getDatabase("items");
        this.collection = this.database.getCollection("myItems");
    }

    public void addItem(String item){
        System.out.println(" --- ITEM ---");
        System.out.println(item);

        Document myDocument = new Document();
        myDocument.put("message", item);
        myDocument.put("date", new Date());

        System.out.println(myDocument);

        this.collection.insertOne(myDocument);
    }

    public ArrayList<String> getAllItems() {
        ArrayList<String> messages = new ArrayList<>();

        FindIterable fit = this.collection.find();
        ArrayList<Document> docs = new ArrayList<>();

        fit.into(docs);
        docs.forEach(document -> {
            String message = document.toJson();
            messages.add(message);
        });

        return messages;
    }



}







