package model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.apache.commons.lang3.EnumUtils;

import java.util.Date;

@DynamoDBTable(tableName = "Evento")
public class Evento {
    private String id;
    private String type;
    private Date date;
    private String employee;

    @DynamoDBAttribute(attributeName = "EventType")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        if (EnumUtils.isValidEnum(TipoEvento.class, type.toUpperCase()))
            this.type = type.toUpperCase();
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

    @Override
    public String toString() {
        return "Evento{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", employee='" + employee + '\'' +
                '}';
    }
}
