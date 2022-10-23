package manager;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import model.EvaluationRecord;
import model.SalesMan;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class ManagePersonelmpl implements ManagePersonal{

    MongoClient client;// create client
    MongoDatabase supermongo;// connect to data base
    MongoCollection<Document> salesmen;
    MongoCollection<Document> performancerecord;

    public ManagePersonelmpl() {

        client = new MongoClient("localhost", 27017);
        // Get database 'highperformance' (creates one if not available)
        supermongo = client.getDatabase("highperformance");
        salesmen = supermongo.getCollection("salesmen");
        performancerecord = supermongo.getCollection("performancerecord");
    }

    @Override
    public void createSalesMan(SalesMan record) {
        salesmen.insertOne(record.toDocument());
    }

    @Override
    public void addPerformanceRecord(EvaluationRecord record, int sid) {
        record.setSalesManID(sid);
        performancerecord.insertOne(record.toDocument());
    }



    @Override
    public SalesMan readSalesMan(int sid) {
       Document docSalesMan =salesmen.find(eq("id", sid)).first();
        Gson gson = new Gson();
        SalesMan model = gson.fromJson(docSalesMan.toJson(), SalesMan.class);
        return model;
    }

    @Override
    public List<SalesMan> querySalesMan(String attribute, String key) {
        List<SalesMan> s= new ArrayList<>();
        MongoCursor<Document> cursor = salesmen.find().iterator();
        try {
            while (cursor.hasNext()) {

                Gson gson = new Gson();
                SalesMan model = gson.fromJson(cursor.next().toJson(), SalesMan.class);
                s.add(model);
            }
        } finally {
            cursor.close();
        }
        return s;
    }

    @Override
    public EvaluationRecord readEvaluationRecords(int sid) {
        Document docEvaluationRecord =performancerecord.find(eq("salesManID", sid)).first();
        Gson gson = new Gson();
        EvaluationRecord model = gson.fromJson(docEvaluationRecord.toJson(), EvaluationRecord.class);
        return model;
    }

    @Override
    public void updateSalesMan(int sid, String attribute, String value)
    {
        Bson updates = Updates.combine(
                Updates.set(attribute, value));

        salesmen.updateOne(eq("id", sid),updates);
    }

    @Override
    public void updateEvaluationRecord(int sid, String attribute, String value)
    {
        Bson updates = Updates.combine(
                Updates.set(attribute, value));

        salesmen.updateOne(eq("salesManId", sid),updates);
    }



    @Override
    public void dropSalesMan(int sid)
    {
        salesmen.deleteOne(eq("id", sid));
    }
    @Override
    public void dropEvaluationRecord(int sid)
    {
        performancerecord.deleteOne(eq("salesManID", sid));
    }

    @Override
    public void dropAllSalesMan()
    {
        salesmen.drop();
    }
    @Override
    public void dropAllEvaluationRecord()
    {
        performancerecord.drop();
    }
}
