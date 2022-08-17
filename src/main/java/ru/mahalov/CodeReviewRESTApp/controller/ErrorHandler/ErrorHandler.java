package ru.mahalov.CodeReviewRESTApp.controller.ErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.mahalov.CodeReviewRESTApp.Model.ErrorInfo;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorInfo> processException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorInfo(e.getClass().getName(), e.getMessage()));
    }

}
