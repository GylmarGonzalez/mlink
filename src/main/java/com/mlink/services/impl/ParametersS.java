package com.mlink.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlink.entities.Parameters;
import com.mlink.repository.Iparameters;
import com.mlink.services.IparametersS;

@Service
public class ParametersS implements IparametersS{

    private Iparameters parameters;

    @Autowired
    public void setParameters(Iparameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public Optional<Parameters> findRecordById(String id) {
       return  parameters.findById(id);
    }

    @Override
    public List<Parameters> findAll() {
        return parameters.findAll();
    }

    @Override
    public String save(Parameters entidad) {
        return parameters.save(entidad).getPk();
    }

    @Override
    public void delete(String id) {
        parameters.deleteById(id);
    }

    
}
