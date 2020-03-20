package controller;

import com.amazonaws.services.dynamodbv2.document.Table;
import model.Empleado;
import model.Evento;
import model.Incidencia;
import model.dao.DAOInterfaceImpl;

import java.util.List;

public class DynamoManager {
    private static DynamoManager instance = null;
    private static DAOInterfaceImpl dao;
    private Empleado workerActive;

    private DynamoManager() {
        dao = new DAOInterfaceImpl();
    }

    public static DynamoManager getInstance() {
        if (instance == null) {
            instance = new DynamoManager();
        }
        return instance;
    }

    public Empleado getWorkerActive() {
        return workerActive;
    }

    public List<Empleado> getAllWorkers(){
        return dao.selectAllEmpleados();
    }

    public List<Incidencia> getAllIncidencia(){
        return dao.selectAllIncidencias();
    }

    public void createTableWorker() throws InterruptedException {
        dao.createTable("Worker");
    }

    public Table getTableByName(String name) {
        return dao.getTableByName(name);
    }

    public void createTableIncidencia() throws InterruptedException {
        dao.createTable("Incidencia");
    }

    public void storeIncidencia(Incidencia incidencia) {
        dao.insertIncidencia(incidencia);
    }

    public void storeWorker(Empleado worker) {
        dao.insertEmpleado(worker);
    }

    public Empleado getWorkerById(String id) {
        return (Empleado) dao.getPOJOById(id, Empleado.class);
    }

    public void createTableEvento() throws InterruptedException {
        dao.createTable("Evento");
    }

    public Incidencia getIncidenciaById(String id) {
        return (Incidencia) dao.getPOJOById(id, Incidencia.class);
    }

    public List<Incidencia> getIncidenciaByOrigen(Empleado empleado) {
        return dao.getIncidenciaByOrigen(empleado);
    }

    public List<Incidencia> getIncidenciaByDestino(Empleado empleado) {
        return dao.getIncidenciaByDestino(empleado);
    }

    public List<Incidencia> findIncidencia() {
        return dao.getAllPOJOFromTable(Incidencia.class, "Incidencia");
    }

    public List<Empleado> findEmpleado() {
        return dao.getAllPOJOFromTable(Empleado.class, "Worker");
    }

    public void removeEmpleado(Empleado empleado) {
        dao.removeEmpleado(empleado);
    }

    public void updateEmpleado(Empleado e) {
        dao.updateEmpleado(e);
    }

    public Empleado getWorkerByUsername(String username) {
        List<Empleado> empleados = dao.getEmpleadoByUsername(username);
        return empleados.isEmpty() ? null : empleados.get(0);
    }

    public void deleteTable(String tableName) {
        dao.deleteTable(tableName);
    }

    public boolean login(String username, String pass) {
        if (dao.loginEmpleado(username, pass)) {
            workerActive = dao.getEmpleadoByUsername(username).get(0);
            return true;
        } else {
            return false;
        }
    }
    public List<Evento> getAllEventos(){
        return dao.getAllEventos();
    }
    public Evento getUltimoInicioSesion(Empleado worker){
        return dao.getUltimoInicioSesion(worker);
    }

    public boolean checkPasswordWorker(String pass, Empleado empleado){
        boolean correct=false;
            if(pass.equals(empleado.getPassword())){
                correct=true;
            }
        return correct;
    }

    public boolean checkNewPassword(String newPass, String newPass2){
        boolean correct=false;
        if(newPass.equals(newPass2)){
            correct=true;
        }
        return correct;
    }
}
