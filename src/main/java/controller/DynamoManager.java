package controller;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;

public class DynamoManager {
    private static DynamoManager instance = null;
    private DynamoDbClient ddb = DynamoDbClient.create();
    private DynamoManager(){}

    public static DynamoManager getInstance(){
        if (instance == null){
            instance = new DynamoManager();
        }
        return instance;
    }
    public void testTables(){
        boolean more_tables = true;
        String last_name = null;
        while(more_tables) {
            try {
                ListTablesResponse response = null;
                if (last_name == null) {
                    ListTablesRequest request = ListTablesRequest.builder().build();
                    response = ddb.listTables(request);
                }
                else {
                    ListTablesRequest request = ListTablesRequest.builder()
                            .exclusiveStartTableName(last_name).build();
                    response = ddb.listTables(request);
                }

                List<String> table_names = response.tableNames();

                if (table_names.size() > 0) {
                    for (String cur_name : table_names) {
                        System.out.format("* %s\n", cur_name);
                    }
                } else {
                    System.out.println("No tables found!");
                    System.exit(0);
                }

                last_name = response.lastEvaluatedTableName();
                if (last_name == null) {
                    more_tables = false;
                }
            } catch (DynamoDbException e) {
                System.exit(1);
            }
        }
    }
}
