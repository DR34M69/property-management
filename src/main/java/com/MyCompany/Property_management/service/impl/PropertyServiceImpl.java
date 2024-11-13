package com.MyCompany.Property_management.service.impl;


import com.MyCompany.Property_management.converter.PropertyConverter;
import com.MyCompany.Property_management.dto.PropertyDTO;
import com.MyCompany.Property_management.entity.PropertyEntity;
import com.MyCompany.Property_management.repository.PropertyRepository;
import com.MyCompany.Property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);


        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllproperties() {
        List<PropertyEntity> listofProps = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO> proplist = new ArrayList<PropertyDTO>();

        for( PropertyEntity pe : listofProps){
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            proplist.add(dto);
        }
        return proplist;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEn=propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEn.isPresent()){

            PropertyEntity pe = optEn.get();//Data from database
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setAddress(propertyDTO.getAddress());
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();//Data from database
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;

    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
            Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
            PropertyDTO dto = null;
            if(optEn.isPresent()){
                PropertyEntity pe = optEn.get();//Data from database
                pe.setPrice(propertyDTO.getPrice());
                dto = propertyConverter.convertEntitytoDTO(pe);
                propertyRepository.save(pe);
            }
            return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);

    }
}
