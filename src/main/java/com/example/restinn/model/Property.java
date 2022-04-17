package com.example.restinn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("properties")
public class Property {
    @Id
    private String databaseId;
    private String title;
    private double price;
    private String img;
    private boolean featured;
    @Field("property_type_id")
    private int propertyTypeId;
    private String description;
    @Field("house_rules")
    private String houseRules;
    private List<String> amenities;
    private String location;
    private double distance;
    private double rating;
}
