package com.clemson.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clemson.mappers.QuestionMapper;
import com.clemson.model.Question;

@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionMapper questionMapper;

	public ArrayList<Question> getAllQuestion(){
		return questionMapper.getAllQuestion();
	}
	
	public void insertQuestion(Question question){
		questionMapper.insertQuestion(question);
	}
	
	public ArrayList<Question> getAllQuestionType(){
		return questionMapper.getAllQuestionType();
	}
	
	public void deleteQuestion(Question question){
		questionMapper.deleteQuestion(question);
	}
	
	public void editQuestion(Question question){
		questionMapper.editQuestion(question);
	}
}