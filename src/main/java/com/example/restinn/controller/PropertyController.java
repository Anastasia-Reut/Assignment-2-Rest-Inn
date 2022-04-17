package com.example.restinn.controller;

import com.example.restinn.model.Property;
import com.example.restinn.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resorts")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @GetMapping
    public List<Property> getAllProperties() {
        return service.retrieveAllProperties();
    }

    @GetMapping("/by-title-or-type")
    public List<Property> retrievePropertiesByTitleOrType(@RequestParam(required = false) String title, @RequestParam(required = false) String type) {
        return service.retrievePropertiesByTitleOrType(title, type);
    }

    @GetMapping("/bestseller")
    public List<Property> retrieveBestseller(@RequestParam(defaultValue = "4.7") double minRating) {
        return service.retrieveBestseller(minRating);
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable String id) {
        return service.getPropertyById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProperty(@PathVariable String id, @RequestBody Property property) {
        service.updateProperty(property, id);
        return ResponseEntity.status(HttpStatus.OK).body("Property updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable String id) {
        service.deletePropertyById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Property deleted.");
    }
}
