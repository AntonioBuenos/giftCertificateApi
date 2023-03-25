package by.smirnov.giftcertapi.controller;

import by.smirnov.giftcertapi.domain.Tag;
import by.smirnov.giftcertapi.dto.TagConverter;
import by.smirnov.giftcertapi.dto.TagRequest;
import by.smirnov.giftcertapi.dto.TagResponse;
import by.smirnov.giftcertapi.exception.BadRequestException;
import by.smirnov.giftcertapi.exception.NoSuchEntityException;
import by.smirnov.giftcertapi.exception.ValidationErrorConverter;
import by.smirnov.giftcertapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.smirnov.giftcertapi.controller.ControllerConstants.DELETED;
import static by.smirnov.giftcertapi.controller.ControllerConstants.ID;
import static by.smirnov.giftcertapi.controller.ControllerConstants.MAPPING_ID;
import static by.smirnov.giftcertapi.controller.ControllerConstants.MAPPING_TAGS;
import static by.smirnov.giftcertapi.controller.ControllerConstants.TAGS;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAPPING_TAGS)
public class TagController {

    private final TagService service;

    private final TagConverter converter;

    @GetMapping
    public ResponseEntity<Map<String, List<TagResponse>>> index() {
        List<TagResponse> responses = service.findAll()
                .stream()
                .map(converter::convert)
                .toList();
        return new ResponseEntity<>(Collections.singletonMap(TAGS, responses), HttpStatus.OK);
    }

    @GetMapping(MAPPING_ID)
    public ResponseEntity<TagResponse> show(@PathVariable(ID) long id) {

        Tag tag = service.findById(id);
        TagResponse response = converter.convert(tag);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagResponse> create(@RequestBody @Valid TagRequest request,
                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        Tag tag = converter.convert(request);
        Tag created = service.create(tag);
        TagResponse response = converter.convert(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(MAPPING_ID)
    public ResponseEntity<TagResponse> update(@PathVariable(name = ID) Long id,
                                                  @RequestBody @Valid TagRequest request,
                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        Tag tag = converter.convert(request);
        Tag changed = service.update(tag);
        TagResponse response = converter.convert(changed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(MAPPING_ID)
    public ResponseEntity<Map<String, Long>> delete(@PathVariable(ID) long id) {

        if (Objects.isNull(service.findById(id))) throw new NoSuchEntityException();
        service.delete(id);
        return new ResponseEntity<>(Map.of(DELETED, id), HttpStatus.OK);
    }
}
