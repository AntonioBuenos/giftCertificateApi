package by.smirnov.giftcertapi.controller;

import by.smirnov.giftcertapi.domain.GiftCertificate;
import by.smirnov.giftcertapi.dto.GiftCertificateConverter;
import by.smirnov.giftcertapi.dto.GiftCertificateRequest;
import by.smirnov.giftcertapi.dto.GiftCertificateResponse;
import by.smirnov.giftcertapi.exception.BadRequestException;
import by.smirnov.giftcertapi.exception.NoSuchEntityException;
import by.smirnov.giftcertapi.exception.ValidationErrorConverter;
import by.smirnov.giftcertapi.service.GiftCertificateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.smirnov.giftcertapi.controller.ControllerConstants.CERTIFICATES;
import static by.smirnov.giftcertapi.controller.ControllerConstants.DELETED;
import static by.smirnov.giftcertapi.controller.ControllerConstants.ID;
import static by.smirnov.giftcertapi.controller.ControllerConstants.MAPPING_CERTIFICATES;
import static by.smirnov.giftcertapi.controller.ControllerConstants.MAPPING_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAPPING_CERTIFICATES)
@Tag(
        name = "Gift certificate controller",
        description = "This controller is responsible for the CRUD operations with gift certificates"
)
public class GiftCertificateController {

    private final GiftCertificateService service;

    private final GiftCertificateConverter converter;

    @GetMapping
    public ResponseEntity<Map<String, List<GiftCertificateResponse>>> index() {
        List<GiftCertificateResponse> responses = service.findAll()
                .stream()
                .map(converter::convert)
                .toList();
        return new ResponseEntity<>(Collections.singletonMap(CERTIFICATES, responses), HttpStatus.OK);
    }

    @GetMapping(MAPPING_ID)
    public ResponseEntity<GiftCertificateResponse> show(@PathVariable(ID) long id) {

        GiftCertificate certificate = service.findById(id);
        GiftCertificateResponse response = converter.convert(certificate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GiftCertificateResponse> create(@RequestBody @Valid GiftCertificateRequest request,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        GiftCertificate certificate = converter.convert(request);
        GiftCertificate created = service.create(certificate);
        GiftCertificateResponse response = converter.convert(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(MAPPING_ID)
    public ResponseEntity<GiftCertificateResponse> update(@PathVariable(name = ID) Long id,
                                              @RequestBody @Valid GiftCertificateRequest request,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ValidationErrorConverter.getErrors(bindingResult).toString());
        }

        GiftCertificate certificate = converter.convert(request);
        GiftCertificate changed = service.update(certificate);
        GiftCertificateResponse response = converter.convert(changed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(MAPPING_ID)
    public ResponseEntity<Map<String, Long>> delete(@PathVariable(ID) long 
