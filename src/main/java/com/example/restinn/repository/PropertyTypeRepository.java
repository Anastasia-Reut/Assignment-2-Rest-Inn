package com.example.restinn.repository;

import com.example.restinn.model.PropertyType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PropertyTypeRepository extends MongoRepository<PropertyType, String> {
    @Query("{'title': {$regex: '?0', $options: 'i'}}")
    List<PropertyType> findByPropertyType(String type);
}
