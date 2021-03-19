package br.com.holelocations.dto;

import br.com.holelocations.entities.HoleLocation;
import br.com.holelocations.entities.User;
import br.com.holelocations.enums.HoleGravity;
import br.com.holelocations.enums.HoleStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoleLocationDTO {
    private String id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp createdAt;
    private User userCreation;
    private HoleStatus status;
    private HoleGravity gravity;

    public HoleLocation dtoToEntity() {
        HoleLocation entity = new HoleLocation();

        entity.setId(this.getId());
        entity.setLatitude(this.getLatitude());
        entity.setLongitude(this.getLongitude());
        entity.setUserCreation(this.getUserCreation());
        entity.setHoleGravity(this.getGravity());
        entity.setStatus(this.getStatus());

        return entity;
    }

    public HoleLocationDTO entityToDto(HoleLocation entity) {
        this.setId(entity.getId());
        this.setLatitude(entity.getLatitude());
        this.setLongitude(entity.getLongitude());
        this.setUserCreation(entity.getUserCreation());
        this.setGravity(entity.getHoleGravity());
        this.setStatus(entity.getStatus());

        return this;
    }
}
