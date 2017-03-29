package com.clemson.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.clemson.model.Question;

public interface QuestionMapper {
	
	@Select("SELECT s_question.question_id as questionId, s_question.question_type_id as questionTypeId, s_question.question_content as questionContent, s_question.sub_type_id as subTypeId, s_sub_type.sub_type_name as subTypeName, s_question_type.question_type_name as questionTypeName FROM s_question, s_sub_type, s_question_type WHERE s_question.sub_type_id = s_sub_type.sub_type_id AND s_question.question_type_id = s_question_type.question_type_id order by question_id desc")
	public ArrayList<Question> getAllQuestion();
	
	@Insert("INSERT INTO s_question(question_content, question_type_id, sub_type_id)VALUES(#{questionContent},#{questionTypeId},#{subTypeId})")
	@Options(useGeneratedKeys = true, keyProperty = "question_id", flushCache = true, keyColumn = "question_id")
	public void insertQuestion(Question question);
	
	@Select("SELECT question_type_id as questionTypeId, question_type_name as questionTypeName FROM s_question_type")
	public ArrayList<Question> getAllQuestionType();
	
	@Delete("DELETE FROM s_question WHERE s_question.question_id = #{questionId}")
	public void deleteQuestion(Question question);
	
	@Update("UPDATE s_question set question_content=#{questionContent}, sub_type_id=#{subTypeId}, question_type_id=#{questionTypeId} WHERE question_id = #{questionId}")
	public void editQuestion(Question question); 
	
}