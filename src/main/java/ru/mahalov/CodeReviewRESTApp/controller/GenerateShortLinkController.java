package ru.mahalov.CodeReviewRESTApp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mahalov.CodeReviewRESTApp.entity.Link;
import ru.mahalov.CodeReviewRESTApp.entity.jsonview.JSON_Views;
import ru.mahalov.CodeReviewRESTApp.service.LinkGeneratorService;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

@RestController
@RequestMapping("/generate")
public class GenerateShortLinkController {

    private final LinkGeneratorService linkGeneratorService;

    private final InetAddress localHost = InetAddress.getLoopbackAddress();

    private final Logger logger = LoggerFactory.getLogger(ShortLinkController.class);

    @Autowired
    public GenerateShortLinkController(LinkGeneratorService linkGeneratorService) {
        this.linkGeneratorService = linkGeneratorService;
    }

    @PostMapping
    @JsonView(JSON_Views.PutShortLink.class)
    public ResponseEntity<?> generateEasyLink(@RequestParam(required = false) String[] original){

        String errors = validLink(original);
        if (errors!=null)
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

        String originalUrl = original[0];

        Link link = linkGeneratorService.findAndGetShortLink(originalUrl);
        link.setShortLink("/l/"+link.getShortLink());

        return new ResponseEntity<>(link,HttpStatus.OK);
    }

    private String validLink(String[] original){
        if (original==null)
            return "Empty parameter <original>";

        if (original.length!=1)
            return "More one parameter <original>";

        String originalLinkHost = getHostName(original[0]).toLowerCase(Locale.ROOT);
        if ( originalLinkHost.equals(localHost.getHostName().toLowerCase(Locale.ROOT)) ||
                originalLinkHost.equals(localHost.getHostAddress().toLowerCase(Locale.ROOT)) )
            return "We can't save this link";

        return null;
    }

    private String getHostName(String url){
        URL aURL = null;

        try {
            aURL = new URL(url);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }
        return aURL.getHost();
    }
}
