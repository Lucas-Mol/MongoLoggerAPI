package com.mongologgerapi.controller;

import com.mongologgerapi.domain.dto.input.PostInputDTO;
import com.mongologgerapi.domain.model.Post;
import com.mongologgerapi.services.PostLoggerService;
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
@RequestMapping("/posts")
@Validated
public class PostController {

    @Autowired
    private PostLoggerService postLoggerService;

    @Autowired
    private UriBuilderService uriBuilderService;

    @PostMapping
    public ResponseEntity log(@RequestBody PostInputDTO postDTO, UriComponentsBuilder uriBuilder) {
        Post post = (Post) postLoggerService.log(new Post(postDTO));
        URI uri = uriBuilderService.getURIToLog(uriBuilder, post);
        return ResponseEntity.created(uri).body(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        var post = postLoggerService.getById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping
    public ResponseEntity get(@PageableDefault(size = 10, sort = {"timestamp"}, direction = Sort.Direction.DESC)
                              Pageable pageable,
                              @RequestParam("user")
                              @Nullable
                              @Pattern(regexp = "^\\d+$", message = "the param shall be a digit")
                              String userId,
                              @RequestParam("post")
                              @Nullable
                              @Pattern(regexp = "^\\d+$", message = "the param shall be a digit")
                              String postId) {

        var posts = postLoggerService.get(pageable, userId, postId);

        return ResponseEntity.ok(posts);
    }

}
