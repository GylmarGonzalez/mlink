package com.mlink.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mlink.entities.Parameters;
import com.mlink.repository.Iparameters;

import com.mlink.services.impl.ParametersS;
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
class ParametersSTest {

    @Mock
    private Iparameters parameters;

    @InjectMocks
    private ParametersS parametersS;

    @BeforeEach
    void setup() {
        parametersS.setParameters(parameters);
    }

    @Test
    void testFindRecordById() {

        Parameters param = new Parameters();
        param.setPk("TEST");

        when(parameters.findById("TEST")).thenReturn(Optional.of(param));

        Optional<Parameters> result = parametersS.findRecordById("TEST");

        assertTrue(result.isPresent());
        assertEquals("TEST", result.get().getPk());
    }

    @Test
    void testFindAll() {

        List<Parameters> list = Arrays.asList(new Parameters(), new Parameters());

        when(parameters.findAll()).thenReturn(list);

        List<Parameters> result = parametersS.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testSave() {

        Parameters param = new Parameters();
        param.setPk("PARAM1");

        when(parameters.save(param)).thenReturn(param);

        String result = parametersS.save(param);

        assertEquals("PARAM1", result);
    }

    @Test
    void testDelete() {

        parametersS.delete("PARAM1");

        verify(parameters, times(1)).deleteById("PARAM1");
    }
}