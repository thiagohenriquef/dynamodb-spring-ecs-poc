package br.com.holelocations.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@DynamoDBDocument
public class User implements Serializable {
    private static final long serialVersionUID = -5434061572879394824L;

    @DynamoDBAttribute
    private Long id;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String cpf;

}
