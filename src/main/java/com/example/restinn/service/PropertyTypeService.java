package com.example.restinn.service;

import com.example.restinn.model.PropertyType;
import com.example.restinn.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.List;

@Service
public class PropertyTypeService {
    @Autowired
    private PropertyTypeRepository repository;

    public List<PropertyType> getAllPropertyTypes(){
        return repository.findAll();
    }

    public PropertyType getPropertytypeById(String id){
        if(!repository.existsById(id)){
            throw new PropertyNotFoundException("Property with id " + id + " not found.");
        }
        PropertyType propertyType = repository.findById(id).get();
        return propertyType;
    }
}
