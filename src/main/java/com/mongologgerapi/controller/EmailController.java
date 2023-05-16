package com.mongologgerapi.controller;

import com.mongologgerapi.domain.dto.input.EmailInputDTO;
import com.mongologgerapi.domain.model.Email;
import com.mongologgerapi.services.EmailLoggerService;
import com.mongologgerapi.services.UriBuilderService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/emails")
@Validated
public class EmailController {

    @Autowired
    private EmailLoggerService emailLoggerService;

    @Autowired
    private UriBuilderService uriBuilderService;

    @PostMapping
    public ResponseEntity log(@RequestBody EmailInputDTO emailDTO, UriComponentsBuilder uriBuilder) {
        Email email = (Email) emailLoggerService.log(new Email(emailDTO));
        URI uri = uriBuilderService.getURIToLog(uriBuilder, email);
        return ResponseEntity.created(uri).body(email);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        var email = (Email) emailLoggerService.getById(id);
        return ResponseEntity.ok(email);
    }

    @GetMapping
    public ResponseEntity get(@PageableDefault(size = 10, sort = {"timestamp"}, direction = Sort.Direction.DESC)
                              Pageable pageable,
                              @RequestParam("user")
                              @Nullable
                              @Pattern(regexp = "^\\d+$", message = "the param shall be a digit")
                              String userId,
                              @RequestParam("sender")
                              @Nullable
                              @jakarta.validation.constraints.Email
                              String sender,
                              @RequestParam("recipient")
                              @Nullable
                              @jakarta.validation.constraints.Email
                              String recipient) {

        var emails = emailLoggerService.get(pageable, userId, sender, recipient);

        return ResponseEntity.ok(emails);
    }

}
