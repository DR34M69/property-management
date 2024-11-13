package com.MyCompany.Property_management.repository;

import com.MyCompany.Property_management.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository <PropertyEntity, Long> {



}
