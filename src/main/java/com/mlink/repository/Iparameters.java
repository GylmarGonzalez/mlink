package com.mlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mlink.entities.Parameters;

@Repository
public interface Iparameters extends JpaRepository<Parameters, String>{
    
}
