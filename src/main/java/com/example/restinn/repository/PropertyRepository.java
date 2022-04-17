package com.example.restinn.repository;

import com.example.restinn.model.Property;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property, String> {
    @Query("{'$or': [{rating : {$gte : :#{#minRating}} }, {'featured': true}]}")
    List<Property> findBestseller(double minRating);

    @Query("{'title': {$regex: '?0', $options: 'i'}}")
    List<Property> findByTitle(String title);

    @Query("{ 'type' : {$in: ?0} }")
    List<Property> findByTypes(List<Integer> types);
}
