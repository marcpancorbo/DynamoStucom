package controller;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

public class DynamoManager {
    private static DynamoManager instance = null;

    private DynamoManager() {
    }

    public static DynamoManager getInstance() {
        if (instance == null) {
            instance = new DynamoManager();
        }
        return instance;
    }
}
