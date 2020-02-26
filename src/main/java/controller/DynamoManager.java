package controller;

import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import model.Empleado;
import model.Incidencia;
import model.dao.DAOInterfaceImpl;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.ArrayList;
import java.util.List;

public class DynamoManager {
    private static DynamoManager instance = null;
    private static DAOInterfaceImpl dao;

    private DynamoManager() {
        dao = new DAOInterfaceImpl();
    }

    public static DynamoManager getInstance() {
        if (instance == null) {
            instance = new DynamoManager();
        }
        return instance;
    }

    public void createTableWorker() throws InterruptedException {

        dao.createTable("Worker");
    }
    public Table getTableByName(String name){return dao.getTableByName(name);}
    public void createTableIncidencia() throws InterruptedException {
        dao.createTable("Incidencia");
    }

    public void storeIncidencia(Incidencia incidencia){dao.insertIncidencia(incidencia);}
    public void storeWorker(Empleado worker){
        dao.insertEmpleado(worker);
    }
    public Empleado getWorkerById(int id){
        return (Empleado)dao.getPOJOById(id,Empleado.class);
    }

    public void createTableEvento()throws InterruptedException{
        dao.createTable("Evento");
    }

    public Incidencia getIncidenciaById(int id){
        return (Incidencia)dao.getPOJOById(id, Incidencia.class);
    }
}
