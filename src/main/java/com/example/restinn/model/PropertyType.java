package com.example.restinn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("property_types")
public class PropertyType {
    @Id
    private String databaseId;
    private int id;
    private String title;
    private String img;
}
