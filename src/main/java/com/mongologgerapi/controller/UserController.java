package com.mongologgerapi.controller;

import com.mongologgerapi.domain.dto.input.PostInputDTO;
import com.mongologgerapi.domain.dto.input.UserInputDTO;
import com.mongologgerapi.domain.model.Post;
import com.mongologgerapi.domain.model.User;
import com.mongologgerapi.services.PostLoggerService;
import com.mongologgerapi.services.UriBuilderService;
import com.mongologgerapi.services.UserLoggerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/users")
@Validated
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserLoggerService userLoggerService;

    @Autowired
    private UriBuilderService uriBuilderService;

    @PostMapping
    public ResponseEntity log(@RequestBody UserInputDTO userDTO, UriComponentsBuilder uriBuilder) {
        User user = (User) userLoggerService.log(new User(userDTO));
        URI uri = uriBuilderService.getURIToLog(uriBuilder, user);
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        var user = userLoggerService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity get(@PageableDefault(size = 10, sort = {"timestamp"}, direction = Sort.Direction.DESC)
                              Pageable pageable,
                              @RequestParam("user")
                              @Nullable
                              @Pattern(regexp = "^\\d+$", message = "the param shall be a digit")
                              String userId,
                              @RequestParam("ipAddress")
                              @Nullable
                              String ipAddress) {

        var users = userLoggerService.get(pageable, userId, ipAddress);

        return ResponseEntity.ok(users);
    }

}
