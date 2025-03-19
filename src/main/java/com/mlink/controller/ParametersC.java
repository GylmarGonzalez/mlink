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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import com.mlink.conf.api.RestControllerV1;
import com.mlink.conf.app.NotFoundException;
import com.mlink.entities.Parameters;
import com.mlink.request.ParametersReq;
import com.mlink.response.ParametersRes;
import com.mlink.services.IparametersS;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestControllerV1
@RestController
public class ParametersC {

    private ModelMapper mapper;
    private IparametersS parametersS;
    private MessageSource messageSource;

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setParametersS(IparametersS parametersS) {
        this.parametersS = parametersS;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Locale getLanguage() {
        return LocaleContextHolder.getLocale();
    }

    @PostMapping("/parameters")
    public ResponseEntity<Object> save(@Valid @RequestBody ParametersReq request, BindingResult bindingResult,
            HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        Parameters entidad = mapper.map(request, Parameters.class);
        parametersS.save(entidad);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidad.getPk())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/parameters/{id}")
	public EntityModel<ParametersRes> findRecordById(@PathVariable String id) throws NotFoundException {
		Optional<Parameters> optional = parametersS.findRecordById(id);
		
		if (optional.isEmpty()) {
			throw new NotFoundException(messageSource.getMessage("app.notFound ", null, getLanguage()) );
		}
        Parameters entidad = optional.get();
        ParametersRes response = mapper.map(entidad, ParametersRes.class);

		 response.add(linkTo(methodOn(ParametersC.class)
                 .findRecordById(id)).withSelfRel());
		 return EntityModel.of(response);
	}

    @GetMapping(value = "/parameters")
    public ResponseEntity<List<ParametersRes>> findAll() throws NotFoundException {
        List<Parameters> findAll = parametersS.findAll();
        Type listType = new TypeToken<List<ParametersRes>>() {
        }.getType();
        List<ParametersRes> toResponse = mapper.map(findAll, listType);
        toResponse.forEach(
                data -> data.add(linkTo(methodOn(ParametersC.class)
                        .findRecordById(data.getPk())).withSelfRel()));

        return new ResponseEntity<>(toResponse, HttpStatus.OK);
    }

    @PutMapping("/parameters")
    public ResponseEntity<Object> update(@RequestBody ParametersReq request) {
        Parameters entidad = mapper.map(request, Parameters.class);
        parametersS.save(entidad);
        ParametersRes response = mapper.map(entidad, ParametersRes.class);

        response.add(linkTo(methodOn(ParametersC.class)
                .findRecordById(response.getPk())).withSelfRel());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/parameters/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        parametersS.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
