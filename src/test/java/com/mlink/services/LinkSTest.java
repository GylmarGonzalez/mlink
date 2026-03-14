package com.mlink.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mlink.entities.Link;
import com.mlink.repository.IlinkRepo;

import com.mlink.services.impl.LinkS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LinkSTest {

    @Mock
    private IlinkRepo linkRepo;

    @InjectMocks
    private LinkS linkS;

    @BeforeEach
    void setup() {
        linkS.setLinkRepo(linkRepo);
    }

    @Test
    void testFindRecordById() {

        Link link = new Link();
        link.setPk(1L);

        when(linkRepo.findById(1L)).thenReturn(Optional.of(link));

        Optional<Link> result = linkS.findRecordById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getPk());
    }

    @Test
    void testFindAll() {

        List<Link> links = Arrays.asList(new Link(), new Link());

        when(linkRepo.findAll()).thenReturn(links);

        List<Link> result = linkS.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testSave() {

        Link link = new Link();
        link.setPk(1L);

        when(linkRepo.save(link)).thenReturn(link);

        Long result = linkS.save(link);

        assertEquals(1L, result);
    }

    @Test
    void testDelete() {

        linkS.delete(1L);

        verify(linkRepo, times(1)).deleteById(1L);
    }
}