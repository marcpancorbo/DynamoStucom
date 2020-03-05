package view;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import controller.DynamoManager;
import model.Empleado;
import model.Incidencia;
import model.TipoUrgencia;

import java.util.ArrayList;
import java.util.Date;
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
