package controller;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
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
}
