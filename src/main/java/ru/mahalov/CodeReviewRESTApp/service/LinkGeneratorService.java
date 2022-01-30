package ru.mahalov.CodeReviewRESTApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mahalov.CodeReviewRESTApp.entity.Link;
import ru.mahalov.CodeReviewRESTApp.repository.LinkRepository;

@Service
public class LinkGeneratorService {

    LinkRepository linkRepository;

    @Autowired
    public LinkGeneratorService(LinkRepository linkRepository){
        this.linkRepository = linkRepository;
    }

    public Link findAndGetShortLink(String url){

        Link savedLink = linkRepository.findByOriginal(url);
        if (savedLink == null )
            savedLink = add(url);

        return savedLink;

    }

    public Link add(String url){
        Link newLink = new Link(url);
        Link savedLink = linkRepository.save(newLink);
        savedLink.setShortLink(generateShortLink(savedLink.getId()));

        return linkRepository.save(savedLink);
    }

    public Link read(String shortLink){
        return linkRepository.findByShortLink(shortLink);
    }

    // TODO: улучшить механизм вычисления короткой ссылки
    private String generateShortLink(long id){

        long quot = id/26;
        long rem = id%26;
        char letter = (char)((int)'A' + rem);

        return quot == 0 ? ""+letter : generateShortLink(quot-1) + letter;
    }



}
