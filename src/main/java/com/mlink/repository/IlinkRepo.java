package com.mlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mlink.entities.Link;

@Repository
public interface IlinkRepo extends JpaRepository<Link, Long>{
    
}
