package ru.mahalov.CodeReviewRESTApp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mahalov.CodeReviewRESTApp.entity.RequestData;

@Repository
public interface RequestDataRepository extends CrudRepository<RequestData,Long> {

}
