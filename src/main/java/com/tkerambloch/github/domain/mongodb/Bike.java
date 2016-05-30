package com.tkerambloch.github.domain.mongodb;

import com.tkerambloch.github.domain.mongodb.util.AbstractAuditingEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by tkerambloch on 26/05/2016.
 */
@Document(collection = "BIKE")
public class Bike extends AbstractAuditingEntity {

    @Id
    private String  id;

    private String  name;

    private String  color;

    private Double  maxSpeed;

    private Double  price;

    private Boolean isdeleted = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }
}
