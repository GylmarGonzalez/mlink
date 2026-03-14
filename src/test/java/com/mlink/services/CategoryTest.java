package com.mlink.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mlink.entities.Category;
import com.mlink.repository.IcategoryRepo;

import com.mlink.services.impl.CategoryS;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategorySTest {

    @Mock
    private IcategoryRepo categoryRepo;

    @InjectMocks
    private CategoryS categoryS;

    @Test
    void testFindRecordById() {

        Category category = new Category();
        category.setPk(1L);
        category.setName("Test Category");

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryS.findRecordById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Category", result.get().getName());
    }

    @Test
    void testFindAll() {

        List<Category> categories = Arrays.asList(new Category(), new Category());

        when(categoryRepo.findAll()).thenReturn(categories);

        List<Category> result = categoryS.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testSave() {

        Category category = new Category();
        category.setPk(1L);

        when(categoryRepo.save(category)).thenReturn(category);

        Long result = categoryS.save(category);

        assertEquals(1L, result);
    }

    @Test
    void testDelete() {

        categoryS.delete(1L);

        verify(categoryRepo, times(1)).deleteById(1L);
    }
}