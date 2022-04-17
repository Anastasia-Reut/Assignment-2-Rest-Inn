package com.example.restinn.controller;

import com.example.restinn.model.PropertyType;
import com.example.restinn.service.PropertyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property_types")
public class PropertyTypeController {

    @Autowired
    private PropertyTypeService service;

    @GetMapping
    public List<PropertyType> getAllPropertyTypes(){

        List<PropertyType> allPropertyTypes = service.getAllPropertyTypes();
        return allPropertyTypes;
    }

    @GetMapping("/{id}")
    public PropertyType getPropertyTypeById(@PathVariable String id){
        return service.getPropertytypeById(id);
    }
}
