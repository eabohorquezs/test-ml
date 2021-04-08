package com.mercadolibre.ipmonitor.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import io.github.cdimascio.dotenv.Dotenv;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class BasicDao {
	
	private final static Dotenv dotenv = Dotenv.load();

    protected static Datastore datastore;

    public static void initializeDB() {
        try {
            
            Morphia morphia = new Morphia();
            morphia.mapPackage("com.mercadolibre.ipmonitor.model");
            
            MongoClient dbClient = new MongoClient(new MongoClientURI(dotenv.get("DATABASE_URI")));
            
            datastore = morphia.createDatastore(dbClient, dotenv.get("DATABASE_NAME"));

            datastore.ensureIndexes();
            
        } catch (Exception e) {
            System.err.println("Error inicializando la base de datos: " + e);
        }
    }

}
