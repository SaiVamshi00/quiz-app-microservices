package com.telusko.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.question_service.dao.QuestionDao;
import com.telusko.question_service.model.Question;
import com.telusko.question_service.model.QuestionWrapper;
import com.telusko.question_service.model.Response;



@Service 
public class QuestionService {

    @Autowired 
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
       try {
           return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
       } catch (Exception e) {
          e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer noOfQuestions) {
        return new ResponseEntity<>(questionDao.findRandomQuestionsByCategory(categoryName, noOfQuestions), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper>list = new ArrayList<>();
        for(Integer id : questionIds)
        {
            Question q = questionDao.findById(id).get();
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            list.add(qw);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
         
        int rightAnswer=0;
        for(Response r : responses)
        {
            Question q = questionDao.findById(r.getId()).get();
            if(q.getRightAnswer().equals(r.getResponse()))
                rightAnswer++;
            
        }

        return  new ResponseEntity<>(rightAnswer,HttpStatus.OK);

    }

    
    
}
