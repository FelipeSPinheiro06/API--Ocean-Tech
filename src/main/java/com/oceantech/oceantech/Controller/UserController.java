package com.oceantech.oceantech.Controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oceantech.oceantech.DTO.UserRequest;
import com.oceantech.oceantech.Model.Users;
import com.oceantech.oceantech.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping(path = "users")
@CacheConfig(cacheNames = "users")
@Tag(name = "User")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(
        summary = "Listar usuários",
        description = "Retorna um array com todos os atributos do user"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Usuário não autorizado. Realize a autenticação em /login")
    })
    public List<Users> getMethod() {
        log.info("Pegando os usuários...");
        return userService.GrabAllUsers();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar usuário",
        description = "Cadastro de um usuário com o corpo de uma requisição"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição"),
        @ApiResponse(responseCode = "401", description = "Usuário não autorizado. Realize a autenticação em /login")
    })
    public Users postMethod(@RequestBody @Valid UserRequest user) {
        log.info("Cadastrando o usuário...");
        return userService.createUser(user);
    }
    
    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar usuário",
        description = "Atualiza os dados do usuário com o id informado na path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique os dados enviados no corpo da requisição"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login"),
        @ApiResponse(responseCode = "404", description = "Não existe usuário com o `id` informado")
    })
    public Users putMethod(@PathVariable Long id, @RequestBody @Valid UserRequest user) {
        return userService.updateUser(id, user);
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Apagar usuário",
        description = "Apaga o usuário com o id informado no parâmetro de path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuário apagado com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login")
    })
    public void deleteMethod(@PathVariable @Valid Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Pegar usuário pelo id",
        description = "Retorna os dados do usuário com o id informado no parâmetro de path"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso!"),
        @ApiResponse(responseCode = "401", description = "Não autorizado. Realize a autenticação em /login"),
        @ApiResponse(responseCode = "404", description = "Não existe usuário com o `id` informado")
    })
    public Users getByID(@PathVariable Long id) {
        return userService.GrabUserByID(id);
    }
}
