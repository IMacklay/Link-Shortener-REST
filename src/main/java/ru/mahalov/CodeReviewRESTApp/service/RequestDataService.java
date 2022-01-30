package ru.mahalov.CodeReviewRESTApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mahalov.CodeReviewRESTApp.entity.Link;
import ru.mahalov.CodeReviewRESTApp.entity.RequestData;
import ru.mahalov.CodeReviewRESTApp.repository.RequestDataRepository;

@Service
public class RequestDataService {

    RequestDataRepository requestDataRepository;

    @Autowired
    public RequestDataService(RequestDataRepository requestDataRepository) {
        this.requestDataRepository = requestDataRepository;
    }

    public void createVisit(String requestHost, Link link){
        requestDataRepository.save(new RequestData(requestHost, link));
    }
}
