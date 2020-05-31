package com.endava.drinks;

import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DrinkResourceAssembler implements RepresentationModelAssembler<DrinkDto, EntityModel<DrinkDto>> {
    @Override
    public EntityModel<DrinkDto> toModel(DrinkDto entity) {
        return EntityModel.of(entity, linkTo(methodOn(DrinkController.class).findById(Integer.parseInt(entity.getId()))).withSelfRel(),
                linkTo(methodOn(DrinkController.class).findAll(PageRequest.of(0, 10))).withRel("all"));
    }
}
