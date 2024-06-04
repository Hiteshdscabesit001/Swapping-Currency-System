package com.swappingcurrency.controllers;

import com.swappingcurrency.dto.ResponseDto;
import com.swappingcurrency.models.StaticContent;
import com.swappingcurrency.services.impl.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("static")
public class StaticController {

    @Autowired
    private StaticService staticService;

    @PostMapping("createContent")
    public ResponseEntity<ResponseDto> createStaticContent(@RequestBody StaticContent staticContent){
        staticService.createContent(staticContent);
        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED, "Static content is created with content name :"+staticContent.getKeyName()), HttpStatus.CREATED);
    }

    @GetMapping("getByKeyName")
    public ResponseEntity<StaticContent> getByKeyName(@RequestHeader String keyName){
        StaticContent staticContent = staticService.getByKeyName(keyName);
        return new ResponseEntity<>(staticContent, HttpStatus.FOUND);
    }
}
