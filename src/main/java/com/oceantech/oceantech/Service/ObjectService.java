package com.oceantech.oceantech.Service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.oceantech.oceantech.DTO.ObjectRequest;
import com.oceantech.oceantech.Model.Object;
import com.oceantech.oceantech.Repository.ObjectRepository;

@Service
public class ObjectService {
    
    @Autowired
    ObjectRepository objectRepository;

    @Autowired
    PagedResourcesAssembler<Object> pageAssembler;

    public ObjectService(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    public PagedModel<EntityModel<Object>> getAllObjects(
        @RequestParam(required = false) String user,
        @RequestParam(required = false) Integer mes,
        @PageableDefault(size = 5, sort = "date", direction = Direction.DESC) Pageable pageable
    ) {

        Page<Object> page = null;

        if (mes != null && user != null) {
            page = objectRepository.findByUserNameAndMes(user, mes, pageable);
        }

        if (mes != null) {
            page = objectRepository.findByMes(mes, pageable);
        }

        if (user != null) {
            page = objectRepository.findByUserName(user, pageable);
        }

        if (page == null) {
            page = objectRepository.findAll(pageable);
        }

        return pageAssembler.toModel(page, Object::toEntityModel);
    }

    public List<Object> getAllObjects() {
        return objectRepository.findAll();
    }


    public Object postObject(ObjectRequest objectRequest) {
        Object object = constructObject(objectRequest);
        return objectRepository.save(object);
    }

    public Object updateObject(Long id, ObjectRequest objectRequest) {
        Object object = constructObject(objectRequest);
        checkExistence(id);
        object.setId(id);
        return objectRepository.save(object);
    }

    public void deleteObject(Long id) {
        objectRepository.deleteById(id);
    }

    public Object getObjectByID(Long id) {
        return checkExistence(id);
    }

    public Object checkExistence(Long id) {
        return objectRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe um usuário com este ID"));
    }

    public Object constructObject(ObjectRequest object) {
        return new Object(object.id(),
                          object.name(),
                          object.type(),
                          object.recycleDate(),
                          object.user());
    }
}
