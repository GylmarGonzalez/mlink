package com.mlink.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mlink.entities.Info;
import com.mlink.repository.IinfoRepo;

import com.mlink.services.impl.InfoS;
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
class InfoSTest {

    @Mock
    private IinfoRepo infoRepo;

    @InjectMocks
    private InfoS infoS;

    @BeforeEach
    void setup() {
        infoS.setInfoRepo(infoRepo);
    }

    @Test
    void testFindRecordById() {

        Info info = new Info();
        info.setPk(1L);

        when(infoRepo.findById(1L)).thenReturn(Optional.of(info));

        Optional<Info> result = infoS.findRecordById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getPk());
    }

    @Test
    void testFindAll() {

        List<Info> list = Arrays.asList(new Info(), new Info());

        when(infoRepo.findAll()).thenReturn(list);

        List<Info> result = infoS.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testSave() {

        Info info = new Info();
        info.setPk(1L);

        when(infoRepo.save(info)).thenReturn(info);

        Long result = infoS.save(info);

        assertEquals(1L, result);
    }

    @Test
    void testDelete() {

        infoS.delete(1L);

        verify(infoRepo, times(1)).deleteById(1L);
    }
}