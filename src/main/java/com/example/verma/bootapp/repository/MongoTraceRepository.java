package com.example.verma.bootapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SANJIT on 10/11/17.
 */

@Service
public class MongoTraceRepository {/*implements TraceRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Trace> findAll() {
        return mongoOperations.findAll(Trace.class);
    }

    @Override
    public void add(Map<String, Object> traceinfo) {
        mongoOperations.save(new Trace(new Date(), traceinfo));
    }*/
}
