package com.mlink.controller;

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
import java.lang.reflect.Type;
import com.mlink.conf.api.RestControllerV1;
import com.mlink.conf.app.NotFoundException;
import com.mlink.entities.Info;
import com.mlink.request.InfoReq;
import com.mlink.response.InfoRes;
import com.mlink.services.IinfoS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestControllerV1
@RestController
public class InfoC {

        private ModelMapper mapper;
        private IinfoS infoS;
        private MessageSource messageSource;

        @Autowired
        public void setMapper(ModelMapper mapper) {
            this.mapper = mapper;
        }
        @Autowired
        public void setInfoS(IinfoS infoS) {
            this.infoS = infoS;
        }
        @Autowired
        public void setMessageSource(MessageSource messageSource) {
            this.messageSource = messageSource;
        }

        public Locale getLanguage(){
             return LocaleContextHolder.getLocale();
        }

    @PostMapping("/infos")
	public ResponseEntity<Object> save(@Valid @RequestBody InfoReq request,BindingResult bindingResult, HttpServletRequest req) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		Info entidad = mapper.map(request, Info.class);
		infoS.save(entidad);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidad.getPk()).toUri();
		return ResponseEntity.created(location).build();
	}


    @GetMapping(value = "/infos/{id}")
	public EntityModel<InfoRes> findRecordById(@PathVariable Long id) throws NotFoundException {
		Optional<Info> optional = infoS.findRecordById(id);
		
		if (optional.isEmpty()) {
			throw new NotFoundException(messageSource.getMessage("app.notFound", null, getLanguage()) );
		}
        Info entidad = optional.get();
        InfoRes response = mapper.map(entidad, InfoRes.class);

		 response.add(linkTo(methodOn(InfoC.class)
                 .findRecordById(id)).withSelfRel());
		 return EntityModel.of(response);
	}

	@GetMapping(value = "/infos")
	public ResponseEntity<List<InfoRes>> findAll() throws NotFoundException {
		List<Info> findAll = infoS.findAll();
		Type listType = new TypeToken<List<InfoRes>>(){}.getType();
		List<InfoRes> toResponse = mapper.map(findAll,listType);
		toResponse.forEach(
				data ->  data.add(linkTo(methodOn(InfoC.class)
		                 .findRecordById(data.getPk())).withSelfRel())
				);
		
		return new ResponseEntity<>(toResponse, HttpStatus.OK);
	}

    @PutMapping("/infos")
	public ResponseEntity<Object> update(@RequestBody InfoReq request) {
		Info entidad = mapper.map(request, Info.class);
		infoS.save(entidad);
		InfoRes response = mapper.map(entidad, InfoRes.class);
		
		 response.add(linkTo(methodOn(InfoC.class)
                 .findRecordById(response.getPk())).withSelfRel());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/infos/{id}") 
	public ResponseEntity<?> delete(@PathVariable Long id) {
		infoS.delete(id);
		return ResponseEntity.noContent().build();
	}

}
