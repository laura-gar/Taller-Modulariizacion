package edu.escuelaing.arep.services;

import com.mongodb.MongoClient;
import edu.escuelaing.arep.DB.MongoDB;

import java.net.UnknownHostException;

public class MessageService {
    MongoClient mongoDB;

    {
        try {
            mongoDB = MongoDB.getClient();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setupMongo(){
        System.out.println(mongoDB.getCredential());
    }






}
