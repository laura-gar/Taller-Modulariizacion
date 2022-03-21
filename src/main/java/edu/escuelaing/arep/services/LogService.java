package edu.escuelaing.arep.services;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;


public class LogService {
    private String url = "db";
    private int port = 27017;
    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;
    private MongoCollection<Document> mongoCollection;

    public void createConnection() {
        try {
            this.mongoClient = new MongoClient(this.url);
            this.mongoDatabase = this.mongoClient.getDatabase("items");
            this.mongoCollection = this.mongoDatabase.getCollection("myItems");
        } catch (MongoException ex){
            System.out.println(ex.toString());
        }
    }

    public void closeConnection() {
        this.mongoClient.close();
    }

    public ArrayList<String> addItem(String item){
        ArrayList<String> documents = new ArrayList<>();

        Document myDocument = new Document();
        myDocument.put("text", item);
        myDocument.put("date", new Date().toString());

        this.mongoCollection.insertOne(myDocument);
        documents.add(myDocument.toJson());

        return documents;
    }

    public ArrayList<String> getAllItems() {
        ArrayList<String> messages = new ArrayList<>();

        mongoCollection = mongoDatabase.getCollection("myItems");

        mongoCollection.find().forEach((Consumer<? super Document>) (Document d) -> messages.add(d.toJson()));

//        FindIterable<Document> result = this.mongoCollection.find();
//
//        result.forEach((Consumer<? super Document>) document -> messages.add(document.toJson()));




        return messages;
    }
}







