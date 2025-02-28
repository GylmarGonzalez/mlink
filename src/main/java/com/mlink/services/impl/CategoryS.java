package com.mlink.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlink.entities.Category;
import com.mlink.repository.IcategoryRepo;
import com.mlink.services.IcategoryS;

@Service
public class CategoryS implements IcategoryS{

    private IcategoryRepo categoryRepo;

    @Autowired
    public void setCategoryRepo(IcategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    
    @Override
    public Optional<Category> findRecordById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll()
    }

    @Override
    public Long save(Category entidad) {
        return categoryRepo.save(entidad).getPk();
    }

    @Override
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }

}
