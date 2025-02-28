package com.mlink.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlink.entities.Link;
import com.mlink.repository.IlinkRepo;
import com.mlink.services.IlinkS;

@Service
public class LinkS implements IlinkS{

    private IlinkRepo linkRepo;

    @Autowired
    public void setLinkRepo(IlinkRepo linkRepo) {
        this.linkRepo = linkRepo;
    }

    @Override
    public Optional<Link> findRecordById(Long id) {
        return linkRepo.findById(id);
    }

    @Override
    public List<Link> findAll() {
        return linkRepo.findAll();
    }

    @Override
    public Long save(Link entidad) {
        return linkRepo.save(entidad).getPk();
    }

    @Override
    public void delete(Long id) {
        linkRepo.deleteById(id);
    }

}
