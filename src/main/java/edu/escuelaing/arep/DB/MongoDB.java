package edu.escuelaing.arep.DB;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class MongoDB {
    public static MongoClient getClient() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        return mongoClient;
    }

}
