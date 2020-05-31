package com.endava.drinks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final DrinkResourceAssembler drinkResourceAssembler;
    private final PagedResourcesAssembler<DrinkDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<DrinkDto>> findAll(Pageable pageable) {
        Page<DrinkDto> all = drinkService.findAll(pageable);
        return pagedResourcesAssembler.toModel(all, drinkResourceAssembler);
    }

    @GetMapping("{id}")
    public EntityModel<DrinkDto> findById(@PathVariable long id) {
        DrinkDto drink = drinkService.findById(id);
        return drinkResourceAssembler.toModel(drink);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated DrinkDto drink) {
        DrinkDto createdDrink = drinkService.create(drink);
        UriComponents components = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .build();
        URI uri = components.expand(Map.of("id", createdDrink.getId())).toUri();
        return ResponseEntity.created(uri)
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        drinkService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }
}
