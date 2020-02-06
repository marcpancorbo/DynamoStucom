package view;

import controller.DynamoManager;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

public class Main {
    private static DynamoManager manager = DynamoManager.getInstance();
    public static void main(String[] args) {
        manager.testTables();
    }

}
