package com.mongologgerapi.controller;

import com.mongologgerapi.domain.dto.input.UserInputDTO;
import com.mongologgerapi.domain.model.User;
import com.mongologgerapi.services.LoggerService;
import com.mongologgerapi.services.UriBuilderService;
import com.mongologgerapi.services.UserLoggerService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
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
import java.time.LocalDateTime;

@RestController
@RequestMapping("/logs")
@Validated
public class LogController {

    @Autowired
    private LoggerService loggerService;


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        var log = loggerService.getById(id);
        return ResponseEntity.ok(log);
    }

    @GetMapping
    public ResponseEntity get(@PageableDefault(size = 10, sort = {"timestamp"}, direction = Sort.Direction.DESC)
                              Pageable pageable,
                              @RequestParam("user")
                              @Nullable
                              @Pattern(regexp = "^\\d+$", message = "the param shall be a digit")
                              String userId,
                              @RequestParam("method")
                              @Nullable
                              String method,
                              @RequestParam("from")
                              @Nullable
                              @PastOrPresent
                              LocalDateTime from,
                              @RequestParam("to")
                              @Nullable
                              @PastOrPresent
                              LocalDateTime to) {

        var logs = loggerService.get(pageable, userId, method, from, to);

        return ResponseEntity.ok(logs);
    }

}
