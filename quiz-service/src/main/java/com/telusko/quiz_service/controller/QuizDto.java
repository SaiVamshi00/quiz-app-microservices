package com.telusko.quiz_service.controller;

import lombok.Data;

/**
 * QuizDto
 */
@Data 
public class QuizDto {
private String category;
private int numQ;
private String title;
}
