package com.mlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlink.entities.Category;

@Repository
public interface IcategoryRepo extends JpaRepository<Category, Long>{
    
}
