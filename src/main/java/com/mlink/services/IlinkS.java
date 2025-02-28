package com.mlink.services;

import java.util.List;
import java.util.Optional;
import com.mlink.entities.Link;

public interface IlinkS {
    
    Optional<Link> findRecordById(Long id);
	List<Link> findAll();
	Long save(Link entidad);
	void delete(Long id);


}
