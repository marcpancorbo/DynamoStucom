package view;

import controller.DynamoManager;

public class Main {
    private static DynamoManager manager = DynamoManager.getInstance();
    public static void main(String[] args) {
        manager.testTables();
    }

}
