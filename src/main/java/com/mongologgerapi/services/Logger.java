package com.mongologgerapi.services;

import com.mongologgerapi.domain.model.Log;


public interface Logger {

    public Log log(Log log);

    public Log getById(String id);

}
