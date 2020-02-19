package model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

public class Identifiable {


    private Integer id;

    @DynamoDBHashKey(attributeName = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
