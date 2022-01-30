package ru.mahalov.CodeReviewRESTApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mahalov.CodeReviewRESTApp.entity.Link;

import java.util.List;

@Repository
public interface LinkRepository extends CrudRepository<Link,Long> {

    Link findByShortLink(String shortLink);
    Link findByOriginal(String original);

}
