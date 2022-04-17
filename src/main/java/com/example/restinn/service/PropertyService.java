package com.example.restinn.service;

import com.example.restinn.model.Property;
import com.example.restinn.model.PropertyType;
import com.example.restinn.repository.PropertyRepository;
import com.example.restinn.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.el.PropertyNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    public List<Property> retrieveAllProperties() {
        return propertyRepository.findAll();
    }

    public void createProperty(Property property) {
        verifyProperty(property);
        propertyRepository.save(property);
    }

    public Property getPropertyById(String id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) {
            throw new PropertyNotFoundException();
        }
        return optionalProperty.get();
    }

    public void deletePropertyById(String id) {
        propertyRepository.deleteById(id);
    }

    public void updateProperty(Property property, String id) {
        verifyProperty(property);
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isEmpty()) {
            throw new PropertyNotFoundException();
        }
        propertyRepository.save(property);
    }

    public List<Property> retrieveBestseller(double minRating) {
        return propertyRepository.findBestseller(minRating);
    }

    public List<Property> retrievePropertiesByTitleOrType(String title, String type) {
        List<Property> result;
//        if (!StringUtils.hasText(type)) {
//            result = propertyRepository.findByTitle(title);
//        } else if (!StringUtils.hasText(title)) {
//            List<PropertyType> propertyTypes = propertyTypeRepository.findByPropertyType(type);
//            List<Integer> typeIds = propertyTypes.stream().map(PropertyType::getId).collect(Collectors.toList());
//            result = propertyRepository.findByTypes(typeIds);
//        } else {
//            List<Property> properties = propertyRepository.findByTitle(title);
//            List<PropertyType> propertyTypes = propertyTypeRepository.findByPropertyType(type);
//            List<Integer> typeIds = propertyTypes.stream().map(PropertyType::getId).collect(Collectors.toList());
//            result = properties.stream().filter(p -> typeIds.contains(p.getPropertyTypeId())).collect(Collectors.toList());
//        }

        List<Property> properties;
        if (!StringUtils.hasText(title)) {
            properties = propertyRepository.findAll();
        } else {
            properties = propertyRepository.findByTitle(title);
        }
        List<PropertyType> propertyTypes;
        if (!StringUtils.hasText(type)) {
            propertyTypes = propertyTypeRepository.findAll();
        } else {
            propertyTypes = propertyTypeRepository.findByPropertyType(type);
        }
        List<Integer> typeIds = propertyTypes.stream().map(PropertyType::getId).collect(Collectors.toList());
        result = properties.stream().filter(p -> typeIds.contains(p.getPropertyTypeId())).collect(Collectors.toList());

        return result;
    }

    private void verifyProperty(Property property) {
        if (!StringUtils.hasText(property.getImg())) {
            throw new IllegalArgumentException("Property Image can not be null or empty string");
        }
        if (!StringUtils.hasText(property.getTitle())) {
            throw new IllegalArgumentException("Property title can not be null or empty string");
        }
        if (!StringUtils.hasText(property.getDescription())) {
            throw new IllegalArgumentException("Property description can not be null or empty string");
        }
        if (!StringUtils.hasText(property.getHouseRules())) {
            throw new IllegalArgumentException("Property title can not be null or empty string");
        }
        if (!StringUtils.hasText(property.getLocation())) {
            throw new IllegalArgumentException("Property location can not be null or empty string");
        }
        if (property.getAmenities() == null || property.getAmenities().isEmpty()) {
            throw new IllegalArgumentException("Amenities can not be null or empty");
        }
    }
}
