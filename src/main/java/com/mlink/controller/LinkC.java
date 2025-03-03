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
import com.mlink.entities.Link;
import com.mlink.request.LinkReq;
import com.mlink.response.LinkRes;
import com.mlink.services.IlinkS;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestControllerV1
@RestController
public class LinkC {

        private ModelMapper mapper;
        private IlinkS linkS;
        private MessageSource messageSource;

        @Autowired
        public void setMapper(ModelMapper mapper) {
            this.mapper = mapper;
        }
        @Autowired
        public void setLinkS(IlinkS linkS) {
            this.linkS = linkS;
        }
        @Autowired
        public void setMessageSource(MessageSource messageSource) {
            this.messageSource = messageSource;
        }
        public Locale getLanguage(){
             return LocaleContextHolder.getLocale();
        }


    @PostMapping("/links")
	public ResponseEntity<Object> save(@Valid @RequestBody LinkReq request,BindingResult bindingResult, HttpServletRequest req) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		Link entidad = mapper.map(request, Link.class);
		linkS.save(entidad);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidad.getPk()).toUri();
		return ResponseEntity.created(location).build();
	}


    @GetMapping(value = "/links/{id}")
	public EntityModel<LinkRes> findRecordById(@PathVariable Long id) throws NotFoundException {
		Optional<Link> optional = linkS.findRecordById(id);
		
		if (optional.isEmpty()) {
			throw new NotFoundException(messageSource.getMessage("app.notFound ", null, getLanguage()) + id); 
		}
        Link entidad = optional.get();
        LinkRes response = mapper.map(entidad, LinkRes.class);

		 response.add(linkTo(methodOn(LinkC.class)
                 .findRecordById(id)).withSelfRel());
		 return EntityModel.of(response);
	}

	@GetMapping(value = "/links")
	public ResponseEntity<List<LinkRes>> findAll() throws NotFoundException {
		List<Link> findAll = linkS.findAll();
		Type listType = new TypeToken<List<LinkRes>>(){}.getType();
		List<LinkRes> toResponse = mapper.map(findAll,listType);
		toResponse.forEach(
				data ->  data.add(linkTo(methodOn(LinkC.class)
		                 .findRecordById(data.getPk())).withSelfRel())
				);
		
		return new ResponseEntity<>(toResponse, HttpStatus.OK);
	}

    @PutMapping("/links")
	public ResponseEntity<Object> update(@RequestBody LinkReq request) {
		Link entidad = mapper.map(request, Link.class);
		linkS.save(entidad);
		LinkRes response = mapper.map(entidad, LinkRes.class);
		
		 response.add(linkTo(methodOn(LinkC.class)
                 .findRecordById(response.getPk())).withSelfRel());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/links/{id}") 
	public ResponseEntity<?> delete(@PathVariable Long id) {
		linkS.delete(id);
		return ResponseEntity.noContent().build();
	}

    
}
