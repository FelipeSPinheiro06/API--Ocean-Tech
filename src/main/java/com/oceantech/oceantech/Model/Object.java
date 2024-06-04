package com.oceantech.oceantech.Model;

import java.time.LocalDate;

import org.springframework.hateoas.EntityModel;

import com.oceantech.oceantech.Controller.ObjectController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Object extends EntityModel<Object> {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "{object.name.notblank}")
    @Size(min = 3, max = 64)
    public String name;
    
    @NotNull(message = "{object.type.notnull}")
    @Enumerated(EnumType.STRING)
    public ObjectDictionary type;
    
    @NotNull(message = "{object.recycleDate.notnull}")
    @PastOrPresent
    public LocalDate recycleDate;
    
    @NotNull(message = "{object.user.notnull}")
    @ManyToOne
    public Users user;

    public EntityModel<Object> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(ObjectController.class).getByID(id)).withSelfRel(),
            linkTo(methodOn(ObjectController.class).deleteMethod(id)).withRel("delete"),
            linkTo(methodOn(ObjectController.class).getMethod(null, null, null)).withRel("contents")
        );
    }
}
