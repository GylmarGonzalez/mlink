package com.mlink.services;

import java.util.List;
import java.util.Optional;

import com.mlink.entities.Info;

public interface IinfoS {

    Optional<Info> findRecordById(Long id);
	List<Info> findAll();
	Long save(Info entidad);
	void delete(Long id);
    
}
