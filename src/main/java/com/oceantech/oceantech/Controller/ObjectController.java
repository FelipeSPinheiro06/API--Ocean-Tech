package com.oceantech.oceantech.Controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oceantech.oceantech.DTO.ObjectRequest;
import com.oceantech.oceantech.Model.Object;
import com.oceantech.oceantech.Service.ObjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping(path = "objects")
@Tag(name = "Object")
public class ObjectController {

    ObjectService objectService;

    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Objetos",
        description = "Retorna um array com todos os atributos do objeto"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Objeto retornado com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Objeto não autorizado. Realize a autenticação em /login")
    })
    public PagedModel<EntityModel<Object>> getMethod(
        @RequestParam(required = false) String user,
        @RequestParam(required = false) Integer mes,
        @PageableDefault(size = 5, sort = "recycleDate", direction = Direction.DESC) Pageable pageable
    ) {
        log.info("Pegando os objetos reciclados...");
        return objectService.getAllObjects(user, mes, pageable);
    }
        
    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(
        summary = "Cadastrar Objeto",
        description = "Cadastro de um objeto com o corpo de uma requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Objeto cadastrado com sucesso!"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login")
    })
    public ResponseEntity<EntityModel<Object>> postMethod(@RequestBody @Valid ObjectRequest object) {
        log.info("Cadastrando os objetos reciclados...");
        
        Object postedObject = objectService.postObject(object);
        
        return ResponseEntity
                    .created(postedObject.toEntityModel().getRequiredLink("self").toUri())
                    .body(postedObject.toEntityModel());
    }
            
    @PutMapping("{id}")
    @Operation(
        summary = "Atualizar objeto",
        description = "Atualiza os dados do objeto com o id informado na path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Objeto retornado com sucesso!"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login"),
        @ApiResponse(responseCode = "404", description = "Não existe objeto com o id informado")
    })
    public ResponseEntity<EntityModel<Object>> putMethod(@PathVariable Long id, @RequestBody @Valid ObjectRequest object) {
        log.info("Atualizando os objetos com o id " + id);
        return objectService.updateObject(id, object);
    }
                
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
        summary = "Apagar objeto",
        description = "Apaga o objeto com o id informado no parâmetro de path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Objeto apagado com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login")
    })
    public ResponseEntity<Object> deleteMethod(@PathVariable @Valid Long id) {
        log.info("Deletando o objeto com o id " + id);
        objectService.deleteObject(id);
        return ResponseEntity.noContent().build();
    }
                    
                    
    @GetMapping("{id}")
    @Operation(
        summary = "Pegar objeto pelo id",
        description = "Retorna os dados do objeto com o id informado no parâmetro de path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "objeto retornado com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login"),
        @ApiResponse(responseCode = "404", description = "Não existe objeto com o id informado")
    })
    public EntityModel<Object> getByID(@PathVariable Long id) {
        log.info("Pegando o object com o id " + id);
        var object = objectService.getObjectByID(id);

        return object.toEntityModel();
    }
                        
                        
    // @GetMapping("media-nota-empresa")
    // @Operation(
    //     summary = "Lista média das notas das empresas",
    //     description = "Calcula e retorna a média da nota de cada empresa pelos objetos que contem"
    // )
    // @ApiResponses({
    //     @ApiResponse(responseCode = "200", description = "objeto retornado com sucesso!"),
    //     @ApiResponse(responseCode = "401", description = "objeto não autorizado. Realize a autenticação em /login")
    // })
    // public Map<String, Double> getMediaNotaEmpresa() {
        
    //     var objetos = objectService.getAllobjetos();
        
    //     var collect = objetos.stream()
    //     .collect(
    //         Collectors.groupingBy(
    //             f -> f.getCompany().getName(),
    //             Collectors.averagingDouble(f -> f.getFeeling().getFeeling())
    //             )
    //         );
                
    //         return collect;
    //     }
}
                                
                                