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

    public Empleado getWorkerById(String id){
        return (Empleado)dao.getPOJOById(id,Empleado.class);
    }

    public void createTableEvento()throws InterruptedException{
        dao.createTable("Evento");
    }

    public Incidencia getIncidenciaById(String id){
        return (Incidencia)dao.getPOJOById(id, Incidencia.class);
    }
    public List<Incidencia> getIncidenciaByOrigen(Empleado empleado){return dao.getIncidenciaByOrigen(empleado);}
    public List<Incidencia> getIncidenciaByDestino(Empleado empleado){return dao.getIncidenciaByDestino(empleado);}
    public List<Incidencia> findIncidencia(){
        return dao.getAllPOJOFromTable(Incidencia.class, "Incidencia");
    }
    public List<Empleado> findEmpleado(){
        return dao.getAllPOJOFromTable(Empleado.class, "Worker");
    }

    public void removeEmpleado(Empleado empleado){
        dao.removeEmpleado(empleado);
    }

    public void updateEmpleado(Empleado e){
        dao.updateEmpleado(e);
    }

    public Empleado getWorkerByUsername(String username){
        List<Empleado> empleados = dao.getEmpleadoByUsername(username);
        return empleados.isEmpty() ? null : empleados.get(0) ;
    }
}
