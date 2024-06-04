package com.swappingcurrency.services.impl;

import com.swappingcurrency.exceptions.ResourceNotFoundException;
import com.swappingcurrency.models.StaticContent;
import com.swappingcurrency.repositories.StaticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaticService {

    @Autowired
    private StaticRepository staticRepository;

    public void createContent(StaticContent staticContent) {
        staticRepository.save(staticContent);
    }

    public StaticContent getByKeyName(String keyName) {
        StaticContent staticContent = staticRepository.findByKeyName(keyName);
        if(staticContent == null) throw new ResourceNotFoundException("Static content is not available.");
        return staticContent;
    }
}
