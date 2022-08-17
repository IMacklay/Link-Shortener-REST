package ru.mahalov.CodeReviewRESTApp.Model;

import lombok.Value;

@Value
public class ErrorInfo {

    String errorCode;
    String errorInfo;
}
