package model.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import model.Empleado;
import model.Evento;
import model.Incidencia;
import model.TipoEvento;

import java.util.*;

public class DAOInterfaceImpl implements DAOInterface {
    private AmazonDynamoDB client;
    private DynamoDB dynamoDB;
    private DynamoDBMapper mapper;

    public DAOInterfaceImpl() {
        client = AmazonDynamoDBClientBuilder.standard().build();
        dynamoDB = new DynamoDB(client);
        mapper = new DynamoDBMapper(client);
    }

    @Override
    public void insertEmpleado(Empleado e) {
        mapper.save(e);
    }

    @Override
    public boolean loginEmpleado(String user, String pass) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1",new AttributeValue().withS(user));
        eav.put(":val2", new AttributeValue().withS(pass));
        DynamoDBScanExpression expression = new DynamoDBScanExpression().withFilterExpression("Username = :val1 and Password = :val2").withExpressionAttributeValues(eav);
         List <Empleado> empleados = mapper.scan(Empleado.class, expression);
         if (empleados.isEmpty()){
             return false;
         }else{
            generarEvento(user, TipoEvento.I);
         }
         return true;
    }

    @Override
    public void updateEmpleado(Empleado e) {
        Table table = dynamoDB.getTable("Worker");
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id",e.getId())
                    .withUpdateExpression("set Username =:u, Password =:p, Nombre =:n, Phonenumber =:pn")
                    .withValueMap(new ValueMap().withString(":u",e.getUserName()).withString(":p",e.getPassword()).withString(":n",e.getName()).withString(":pn",e.getPhoneNumber()))
                    .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
    }

    @Override
    public void removeEmpleado(Empleado e) {
        Table table = dynamoDB.getTable("Worker");
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey(new PrimaryKey("Id", e.getId()));
        table.deleteItem(deleteItemSpec);
    }

    @Override
    public Incidencia getIncidenciaById(String id) {
        return mapper.load(Incidencia.class, id);
    }

    @Override
    public List<Incidencia> selectAllIncidencias() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        return mapper.scan(Incidencia.class, expression);
    }

    @Override
    public List<Empleado> selectAllEmpleados() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        return mapper.scan(Empleado.class, expression);
    }

    @Override
    public void insertIncidencia(Incidencia i) {
        if (i.getType().equalsIgnoreCase("URGENTE"))
            generarEvento(i.getOrigin(), TipoEvento.U);
        mapper.save(i);
    }

    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1",new AttributeValue().withS(e.getUserName()));
        DynamoDBScanExpression expression = new DynamoDBScanExpression().withFilterExpression("Destination = :val1").withExpressionAttributeValues(eav);
        generarEvento(e.getUserName(), TipoEvento.C);
        return mapper.scan(Incidencia.class, expression);
    }

    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1",new AttributeValue().withS(e.getUserName()));
        DynamoDBScanExpression expression = new DynamoDBScanExpression().withFilterExpression("Origin = :val1").withExpressionAttributeValues(eav);
        return mapper.scan(Incidencia.class, expression);
    }

    @Override
    public void insertarEvento(Evento e) {
        mapper.save(e);
    }

    @Override
    public Evento getUltimoInicioSesion(Empleado e) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1",new AttributeValue().withS(e.getUserName()));
        DynamoDBScanExpression expression = new DynamoDBScanExpression().withFilterExpression("Employee = :val1").withExpressionAttributeValues(eav);
        List<Evento> eventos = mapper.scan(Evento.class, expression);
        return eventos.stream().min(Comparator.comparing(Evento::getDate)).get();
    }

    @Override
    public void createTable(String tableName) throws InterruptedException {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("S"));
        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));
        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName).withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions).withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));
        Table table = dynamoDB.createTable(request);
        table.waitForActive();
    }

    @Override
    public Table getTableByName(String table) {
        return dynamoDB.getTable(table);
    }

    @Override
    public <T> List<T>  getAllPOJOFromTable(Class<T> clazz, String table) {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        return mapper.scan(clazz, expression);
    }

    @Override
    public List<Empleado> getEmpleadoByUsername(String username) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1",new AttributeValue().withS(username));
        DynamoDBScanExpression expression = new DynamoDBScanExpression().withFilterExpression("Username = :val1").withExpressionAttributeValues(eav);
        return mapper.scan(Empleado.class, expression);
    }

    @Override
    public void deleteTable(String tableName) {
        dynamoDB.getTable(tableName).delete();
    }

    @Override
    public void generarEvento(String empleado, TipoEvento tipo) {
        Evento eventoLogin = new Evento();
        eventoLogin.setEmployee(empleado);
        eventoLogin.setDate(new Date());
        eventoLogin.setType(String.valueOf(tipo));
        insertarEvento(eventoLogin);
    }

    @Override
    public List<Evento> getAllEventos() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        return mapper.scan(Evento.class, expression);
    }

    @Override
    public <T> Object getPOJOById(String id, Class<T> clazz){
        return mapper.load(clazz,id);
    }

    /*
    @Override
    public List<RankingTO> getRankingEmpleados() {
        return null;
    }
     */
}
