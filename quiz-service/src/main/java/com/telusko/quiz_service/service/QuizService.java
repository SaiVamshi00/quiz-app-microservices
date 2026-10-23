package com.telusko.quiz_service.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quiz_service.dao.QuizDao;
import com.telusko.quiz_service.feing.QuizInterface;
import com.telusko.quiz_service.model.Question;
import com.telusko.quiz_service.model.QuestionWrapper;
import com.telusko.quiz_service.model.Quiz;
import com.telusko.quiz_service.model.Response;


/**
 * QuizService
 */
@Service 
public class QuizService {

    @Autowired 
    QuizDao quizDao;

    @Autowired 
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        
        List<Integer>questionIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        
        quizDao.save(quiz);  // from here I need to call the questions service to create. 
                     // That means from this service I need to call another service.
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz = quizDao.findById(id).get();
       List<Integer>questionIds = quiz.getQuestionIds();
       return quizInterface.getQuestionsFromId(questionIds);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }





}
