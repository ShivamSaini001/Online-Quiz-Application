package com.quiz.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.quiz.entities.QuestionCategory;

public class QuestionCategoryRowMapper implements RowMapper<QuestionCategory> {

	@Override
	public QuestionCategory mapRow(ResultSet rs, int rowNum) throws SQLException {

		QuestionCategory questionCategory = new QuestionCategory();
		questionCategory.setCategoryId(rs.getInt(1));
		questionCategory.setCategoryName(rs.getString(2));

		return questionCategory;
	}

}
