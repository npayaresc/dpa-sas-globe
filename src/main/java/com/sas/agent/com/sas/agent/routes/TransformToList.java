package com.sas.agent.com.sas.agent.routes;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.*;

/**
 * Created by eurnpa on 18.06.2017.
 */
@Log
@Component("transformer")
public class TransformToList {





    public Map<String, String>  transformJson(String body) throws IOException{

        Map<String, String> record = new HashMap<String, String>();

        byte[] mapData = body.getBytes();


        ObjectMapper objectMapper = new ObjectMapper();
        record = objectMapper.readValue(mapData, HashMap.class);
        record.put("eventID", "");

        log.info("Map is: "+record);

        return record;
    }
}
