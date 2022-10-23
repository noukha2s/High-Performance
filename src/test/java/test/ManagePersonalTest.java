package test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import manager.ManagePersonal;
import manager.ManagePersonelmpl;
import model.SalesMan;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagePersonalTest {
ManagePersonal managePersonal;


    MongoClient client;
    MongoDatabase supermongo;
    MongoCollection<Document> salesmen;
    MongoCollection<Document> performancerecord;

    @BeforeEach
    void setUp() {
        //instanciate ManagePersonelImlp
        managePersonal = new ManagePersonelmpl();
        //
        client = new MongoClient("localhost", 27017);
        // Get database 'highperformance' (creates one if not available)
        supermongo = client.getDatabase("highperformance");
        salesmen = supermongo.getCollection("salesmen");
        performancerecord = supermongo.getCollection("performancerecord");

    }

    @AfterEach
    void setAfter() {
        managePersonal.dropAllSalesMan();
        managePersonal.dropAllEvaluationRecord();
    }

    @Test
    void insertSalesMan() {
        SalesMan  salesMan= new SalesMan("Nada", "Oukhai", 1);
        managePersonal.createSalesMan(salesMan);
        Document docSalesMan =salesmen.find(eq("id", 1)).first();
        assertEquals(salesMan.getFirstname(),docSalesMan.get("firstname"));
        assertEquals(salesMan.getLastname(),docSalesMan.get("lastname"));
        assertEquals(salesMan.getId(),docSalesMan.get("id"));
        salesmen.drop();
    }

    @Test
    void readSalesMan() {
        SalesMan  salesMan;

        salesmen.insertOne(new Document()
                .append("firstname", "Nada")
                .append("lastname", "Oukhai")
                .append("id",1));

        salesMan=managePersonal.readSalesMan(1);
        assertEquals("Nada",salesMan.getFirstname());
        assertEquals("Oukhai",salesMan.getLastname());
        assertEquals(1,salesMan.getId());
        salesmen.drop();
    }

    @Test
    void insertSalesManMoreObjectOriented() {

    }
}