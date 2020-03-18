package view;

import controller.DynamoManager;
import model.Empleado;

import java.util.List;

public class Main {

    private static DynamoManager manager = DynamoManager.getInstance();

    public static void main(String[] args) throws InterruptedException {

        List<Empleado> empleados = manager.findEmpleado();
        empleados.forEach(empleado -> {
            System.out.println(empleado.getUserName());
        });
        Empleado worker=manager.getWorkerByUsername("Marc");
        worker.setUserName("Venhime");
        worker.setPhoneNumber("654023488");
        worker.setName("Marc");
        manager.updateEmpleado(worker);
        List<Empleado> empleadoss = manager.findEmpleado();
        empleadoss.forEach(empleado -> {
            System.out.println("Username: "+empleado.getUserName() + "Name : "+empleado.getName() + "PhoneNumber: "+empleado.getPhoneNumber());
        });

    }
}
