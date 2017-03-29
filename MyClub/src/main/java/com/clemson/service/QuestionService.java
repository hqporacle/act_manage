package com.clemson.service;

import java.util.ArrayList;

import com.clemson.model.Question;

public interface QuestionService {	
	public ArrayList<Question> getAllQuestion();
	
	public void insertQuestion(Question question);
	
	public ArrayList<Question> getAllQuestionType();
	
	public void deleteQuestion(Question question);
	
	public void editQuestion(Question question); 
}
