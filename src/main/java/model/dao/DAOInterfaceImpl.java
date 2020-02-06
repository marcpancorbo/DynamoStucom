package model.dao;

import model.Empleado;
import model.Evento;
import model.Incidencia;

import java.util.List;

public class DAOInterfaceImpl implements DAOInterface {
    @Override
    public void insertEmpleado(Empleado e) {

    }

    @Override
    public boolean loginEmpleado(String user, String pass) {
        return false;
    }

    @Override
    public void updateEmpleado(Empleado e) {

    }

    @Override
    public void removeEmpleado(Empleado e) {

    }

    @Override
    public Incidencia getIncidenciaById(int id) {
        return null;
    }

    @Override
    public List<Incidencia> selectAllIncidencias() {
        return null;
    }

    @Override
    public void insertIncidencia(Incidencia i) {

    }

    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        return null;
    }

    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        return null;
    }

    @Override
    public void insertarEvento(Evento e) {

    }

    @Override
    public Evento getUltimoInicioSesion(Empleado e) {
        return null;
    }
    /*
    @Override
    public List<RankingTO> getRankingEmpleados() {
        return null;
    }
     */
}
