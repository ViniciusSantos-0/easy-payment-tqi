package com.easypayment.gerenciadorusers.dynamodbconfig;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.easypayment.gerenciadorusers.dto.Request;
import com.easypayment.gerenciadorusers.entity.Cliente;

import java.util.ArrayList;
import java.util.List;

public class Handler implements RequestHandler<Request, Object> {
    @Override
    public Object handleRequest(Request request, Context context) {

        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper mapper = new DynamoDBMapper(db);
        Cliente cliente = null;

        switch (request.getHttpMethod()) {
            case "GET":
                if (request.getId() == 0) {
                    List<Cliente> clientes = new ArrayList<>();
                    clientes = mapper.scan(Cliente.class, new DynamoDBScanExpression());
                    return clientes;
                } else {
                    cliente = mapper.load(Cliente.class, request.getId());
                    return cliente;
                }
            case "POST":
                cliente = request.getCliente();
                mapper.save(cliente);
                return cliente;
            case "DELETE":
                cliente = mapper.load(Cliente.class, request.getId());
                if (cliente != null)
                    mapper.delete(cliente);
                return cliente;
        }
        return null;
    }
}