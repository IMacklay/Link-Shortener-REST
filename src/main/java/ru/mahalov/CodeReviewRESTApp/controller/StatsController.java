package ru.mahalov.CodeReviewRESTApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mahalov.CodeReviewRESTApp.entity.LinkDetail;
import ru.mahalov.CodeReviewRESTApp.service.DetailLinkService;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final DetailLinkService detailLinkService;

    @Autowired
    public StatsController(DetailLinkService detailLinkService) {
        this.detailLinkService = detailLinkService;
    }

    @GetMapping(value = "/{shortLink}")
    public ResponseEntity<LinkDetail> returnStats(@PathVariable String shortLink){

        LinkDetail linkDetail = detailLinkService.getLinkDetailByShortLink(shortLink);

        if (linkDetail == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(linkDetail,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LinkDetail>> returnAllStats(@RequestParam(required = false) Integer page,
                                                           @RequestParam(required = false) Integer count){

        page = getValidIntParamValue(page, -1);
        count = getValidIntParamValue(count, 100);

        List<LinkDetail> linkDetailList = detailLinkService.getAllDetailLink(page, count);

        if (linkDetailList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(linkDetailList,HttpStatus.OK);
    }

    private Integer getValidIntParamValue(Integer value, int max){
        if (value==null || value>max && max!=-1) return max;
        if (value < 1) return 1;

        return value;
    }

}
