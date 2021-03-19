package br.com.holelocations.repository;

import br.com.holelocations.entities.HoleLocation;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoleLocationRepository {


    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public String save(HoleLocation holeLocation) {
        dynamoDBMapper.save(holeLocation);
        return holeLocation.getId();
    }

    public HoleLocation findById(String id) {
        return dynamoDBMapper.load(HoleLocation.class, id);
    }

    public String delete(String id) {
        HoleLocation emp = dynamoDBMapper.load(HoleLocation.class, id);
        dynamoDBMapper.delete(emp);
        return "Hole Location Deleted!";
    }

    public String update(String id, HoleLocation holeLocation) {
        dynamoDBMapper.save(holeLocation,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
        return id;
    }

    public List<HoleLocation> findAll() {
        return dynamoDBMapper.scan(HoleLocation.class, new DynamoDBScanExpression());
    }
}