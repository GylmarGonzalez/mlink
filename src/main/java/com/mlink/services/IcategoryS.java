package com.mlink.services;

import java.util.List;
import java.util.Optional;

import com.mlink.entities.Category;

public interface IcategoryS {
    
    Optional<Category> findRecordById(Long id);
	List<Category> findAll();
	Long save(Category entidad);
	void delete(Long id);

}
