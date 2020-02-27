package model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable(tableName = "Evento")
public class Evento {
    private String id;
    private TipoEvento type;
    private Date date;
    private String employee;

    @DynamoDBAttribute(attributeName = "Type")
    public TipoEvento getType() {
        return type;
    }
    public void setType(TipoEvento type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "Date")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "Employee")
    public String getEmployee() {
        return employee;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }


    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey()
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
