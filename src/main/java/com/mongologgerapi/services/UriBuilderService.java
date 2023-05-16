package com.mongologgerapi.services;

import com.mongologgerapi.domain.model.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class UriBuilderService {

    public URI getURIToLog(UriComponentsBuilder uriComponentsBuilder, Log log) {
        return uriComponentsBuilder.path("/posts/{id}").buildAndExpand(log.getId()).toUri();
    }
}
