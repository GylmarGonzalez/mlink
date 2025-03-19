package com.mlink.services;

import java.util.List;
import java.util.Optional;
import com.mlink.entities.Parameters;

public interface IparametersS {

    Optional<Parameters> findRecordById(String id);
	List<Parameters> findAll();
	String save(Parameters entidad);
	void delete(String id);
    
}
