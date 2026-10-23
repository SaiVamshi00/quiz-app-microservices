package com.telusko.question_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Response
 */
@Data 
@RequiredArgsConstructor 
public class Response {
    private Integer id;
    private String response;
}
