package view;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import controller.DynamoManager;
import model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static DynamoManager manager = DynamoManager.getInstance();

    public static void main(String[] args) throws InterruptedException {

        Empleado worker = new Empleado();
        worker.setName("Paco");
        worker.setPassword("1234");
        worker.setPhoneNumber("654023488");
        worker.setId(1);
        worker.setUserName("Pakito");
        manager.storeWorker(worker);
        Empleado worker2 =manager.getWorkerById(1);
        System.out.println(worker2.getUserName());
    }

}
