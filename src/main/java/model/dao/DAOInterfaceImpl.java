package model.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import model.Empleado;
import model.Evento;
import model.Incidencia;

import java.util.ArrayList;
import java.util.List;

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
        return false;
    }

    @Override
    public void updateEmpleado(Empleado e) {

    }

    @Override
    public void removeEmpleado(Empleado e) {

    }

    @Override
    public Incidencia getIncidenciaById(int id) {
        return null;
    }

    @Override
    public List<Incidencia> selectAllIncidencias() {
        return null;
    }

    @Override
    public void insertIncidencia(Incidencia i) {
        mapper.save(i);
    }

    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        return null;
    }

    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        return null;
    }

    @Override
    public void insertarEvento(Evento e) {
        mapper.save(e);
    }

    @Override
    public Evento getUltimoInicioSesion(Empleado e) {
        return null;
    }

    @Override
    public void createTable(String tableName) throws InterruptedException {
        List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id").withAttributeType("N"));
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
    public <T> Object getPOJOById(int id, Class<T> clazz){
        return mapper.load(clazz,id);
    }

    /*
    @Override
    public List<RankingTO> getRankingEmpleados() {
        return null;
    }
     */
}
