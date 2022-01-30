package ru.mahalov.CodeReviewRESTApp.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mahalov.CodeReviewRESTApp.entity.Link;
import ru.mahalov.CodeReviewRESTApp.entity.jsonview.JSON_Views;
import ru.mahalov.CodeReviewRESTApp.service.LinkGeneratorService;
import ru.mahalov.CodeReviewRESTApp.service.RequestDataService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/l")
public class ShortLinkController {


    private final RequestDataService requestDataService;
    private final LinkGeneratorService linkGeneratorService;


    @Autowired
    public ShortLinkController(RequestDataService requestDataService,
                               LinkGeneratorService linkGeneratorService) {
        this.requestDataService = requestDataService;
        this.linkGeneratorService = linkGeneratorService;
    }

    @GetMapping(value = "/{shortLink}")
    @JsonView(JSON_Views.PutOriginal.class)
    public ResponseEntity<?> redirectToOriginal(HttpServletRequest request,
                                             @PathVariable String shortLink){
        Link link = linkGeneratorService.read(shortLink);

        if (link == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("This short link not found in our database..");

        requestDataService.createVisit(request.getRemoteHost(), link);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(link.getOriginal())).build();
    }








}
