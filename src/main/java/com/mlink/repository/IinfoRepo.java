package com.mlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mlink.entities.Info;

@Repository
public interface IinfoRepo extends JpaRepository<Info, Long>{
    
}
