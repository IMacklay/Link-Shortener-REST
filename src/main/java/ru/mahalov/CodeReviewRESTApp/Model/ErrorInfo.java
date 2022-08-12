package ru.mahalov.CodeReviewRESTApp.Model;

import lombok.Data;
import lombok.Value;

@Value
public class ErrorInfo {

    private String errorCode;
    private String errorInfo;

}
