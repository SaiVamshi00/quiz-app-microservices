package com.telusko.quiz_service.model;

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
