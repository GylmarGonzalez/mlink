package com.mlink.controller;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mlink.conf.api.RestControllerV1;
import com.mlink.conf.app.NotFoundException;
import com.mlink.entities.Category;
import com.mlink.request.CategoryReq;
import com.mlink.response.CategoryRes;
import com.mlink.services.IcategoryS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestControllerV1
@RestController
public class CategoryC {

    private ModelMapper mapper;
    private IcategoryS categoryS;
    private MessageSource messageSource;

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setCategoryS(IcategoryS categoryS) {
        this.categoryS = categoryS;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Locale getLanguage() {
        return LocaleContextHolder.getLocale();
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> save(@Valid @RequestBody CategoryReq request, BindingResult bindingResult,
            HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        Category entidad = mapper.map(request, Category.class);
        categoryS.save(entidad);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidad.getPk())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/categories/{id}")
	public EntityModel<CategoryRes> findRecordById(@PathVariable Long id) throws NotFoundException {
		Optional<Category> optional = categoryS.findRecordById(id);
		
		if (optional.isEmpty()) {
			throw new NotFoundException(messageSource.getMessage("app.notFound ", null, getLanguage()) );
		}
        Category entidad = optional.get();
        CategoryRes response = mapper.map(entidad, CategoryRes.class);

		 response.add(linkTo(methodOn(CategoryC.class)
                 .findRecordById(id)).withSelfRel());
		 return EntityModel.of(response);
	}

    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryRes>> findAll() throws NotFoundException {
        List<Category> findAll = categoryS.findAll();
        Type listType = new TypeToken<List<CategoryRes>>() {
        }.getType();
        List<CategoryRes> toResponse = mapper.map(findAll, listType);
        toResponse.forEach(
                data -> data.add(linkTo(methodOn(CategoryC.class)
                        .findRecordById(data.getPk())).withSelfRel()));

        return new ResponseEntity<>(toResponse, HttpStatus.OK);
    }

    @PutMapping("/categories")
    public ResponseEntity<Object> update(@RequestBody CategoryRes request) {
        Category entidad = mapper.map(request, Category.class);
        categoryS.save(entidad);
        CategoryRes response = mapper.map(entidad, CategoryRes.class);

        response.add(linkTo(methodOn(CategoryC.class)
                .findRecordById(response.getPk())).withSelfRel());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryS.delete(id);
        return ResponseEntity.noContent().build();
    }

}
