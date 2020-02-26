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
        Incidencia incidencia = new Incidencia();
        incidencia.setDate(new Date());
        incidencia.setOrigin("Gava");
        incidencia.setDestination("Barcelona");
        incidencia.setDetail("Detail");
        incidencia.setType("normal");
        manager.storeIncidencia(incidencia);
        Incidencia incidencia1 = manager.getIncidenciaById(2);
        System.out.println(incidencia1.getDestination());
    }

}
