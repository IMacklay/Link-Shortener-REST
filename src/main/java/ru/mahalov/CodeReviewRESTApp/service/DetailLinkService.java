package ru.mahalov.CodeReviewRESTApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mahalov.CodeReviewRESTApp.entity.LinkDetail;
import ru.mahalov.CodeReviewRESTApp.repository.DetailLinkRepository;
import java.util.List;

@Service
public class DetailLinkService {

    DetailLinkRepository detailLinkRepository;

    @Autowired
    public DetailLinkService(DetailLinkRepository detailLinkRepository) {
        this.detailLinkRepository = detailLinkRepository;
    }

    public LinkDetail getLinkDetailByShortLink(String shortLink){
        return detailLinkRepository.getLinkDetailsByShortName(shortLink);
    }

    public List<LinkDetail> getAllDetailLink(int numPage, int countInPage){
        int offsetRowCount = (numPage-1)*countInPage;
        return detailLinkRepository.getLinkAllLinkDetails(offsetRowCount, countInPage);
    }
}
