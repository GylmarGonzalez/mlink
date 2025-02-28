package com.mlink.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlink.entities.Info;
import com.mlink.repository.IinfoRepo;
import com.mlink.services.IinfoS;

@Service
public class InfoS implements IinfoS{

    private IinfoRepo infoRepo;

    @Autowired
    public void setInfoRepo(IinfoRepo infoRepo) {
        this.infoRepo = infoRepo;
    }

    @Override
    public Optional<Info> findRecordById(Long id) {
        return infoRepo.findById(id);
    }

    @Override
    public List<Info> findAll() {
        return infoRepo.findAll();
    }

    @Override
    public Long save(Info entidad) {
        return infoRepo.save(entidad).getPk();
    }

    @Override
    public void delete(Long id) {
        infoRepo.deleteById(id);
    }
  
}
