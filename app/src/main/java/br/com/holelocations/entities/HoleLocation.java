package br.com.holelocations.entities;

import br.com.holelocations.enums.HoleGravity;
import br.com.holelocations.enums.HoleStatus;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Component
@DynamoDBTable(tableName = "tbl_hole_locations")
public class HoleLocation implements Serializable {
    private static final long serialVersionUID = -502056323431102056L;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private BigDecimal latitude;
    @DynamoDBAttribute
    private BigDecimal longitude;
    @DynamoDBAttribute
    private Timestamp createdAt;

    @DynamoDBAttribute
    private User userCreation;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    private HoleStatus status;
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    private HoleGravity holeGravity;

}

