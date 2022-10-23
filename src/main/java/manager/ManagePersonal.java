package manager;

import model.*;

import java.util.List;

public interface ManagePersonal {

    public void createSalesMan( SalesMan record );

    public void addPerformanceRecord( EvaluationRecord record , int sid );

    public SalesMan readSalesMan( int sid );

    public List<SalesMan> querySalesMan(String attribute , String key );

    public EvaluationRecord readEvaluationRecords( int sid );

    public void updateSalesMan(int sid, String attribute, String value);

    public void updateEvaluationRecord(int sid, String attribute, String value);


    public void dropSalesMan(int sid);

    public void dropEvaluationRecord(int sid);//supprimer evaluation record par id

    public void dropAllSalesMan();

    public void dropAllEvaluationRecord();
}
