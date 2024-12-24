package com.quiz.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.entities.Option;
import com.quiz.entities.Question;
import com.quiz.entities.QuestionCategory;
import com.quiz.repositories.QuestionRepo;
import com.quiz.repositories.mappers.QuestionCategoryRowMapper;

@Repository
public class QuestionRepoImpl implements QuestionRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public Question addQuestion(Question question) {

		String questionText = question.getQuestionText();

		int questionCategoryId = question.getQuestionCategory().getCategoryId();
		// fetch category info (categoryName) from database and set it into question
		// object.
		QuestionCategory questionCategory = this.getCategoryById(questionCategoryId);
		question.setQuestionCategory(questionCategory);

		List<Option> allOptions = question.getOptions();
		String correctOptionText = question.getCorrectOption().getOptionText();

		// Query to Insert Question into DB
		String addQuestionQuery = "INSERT INTO questions (question_text, category_id) values (?, ?)";
		jdbcTemplate.update(addQuestionQuery, questionText, questionCategoryId);

		// Query to get QuestionId from Database by question_text
		String queryForQuestionId = "SELECT question_id FROM questions WHERE question_text = ?";
		Integer questionId = jdbcTemplate.queryForObject(queryForQuestionId, (rs, rowNum) -> rs.getInt("question_id"),
				questionText);

		// Set Question Id into question object.
		question.setQuestionId(questionId.intValue());

		// Query to Insert all Options into Database
		String addOptionsQuery = "INSERT INTO options (option_text, question_id) values (?, ?)";

		List<Object[]> queryOptions = allOptions.stream()
				.map((option) -> new Object[] { option.getOptionText(), questionId }).toList();

		jdbcTemplate.batchUpdate(addOptionsQuery, queryOptions);

		// Query to get id of correct option from Database
		String queryForCorrectOptionId = "SELECT option_id FROM options WHERE option_text = ? AND question_id=?";

		Integer correctOptionId = jdbcTemplate.queryForObject(queryForCorrectOptionId,
				(rs, rowNum) -> rs.getInt("option_id"), correctOptionText, questionId);

		// Query to set correctOptionId to Question (i.e., Set Correct Answer)
		String updateQueryForQuestion = "UPDATE questions SET correct_option_id = ? WHERE question_id = ?";

		jdbcTemplate.update(updateQueryForQuestion, correctOptionId, questionId);

		// Set Correct Option Id into correctOption object into question object.
		question.getCorrectOption().setOptionId(correctOptionId);

		return question;
	}

	@Override
	@Transactional
	public Question updateQuestion(Question question) {

		/*
		 * What you can update into question. 1. Question text 2. Question category id
		 * 3. Options text 4. Correct option id
		 */

		int questionId = question.getQuestionId();
		int categoryId = question.getQuestionCategory().getCategoryId();

		String questionText = question.getQuestionText();
		String correctOptionText = question.getCorrectOption().getOptionText();
		List<Option> allOptions = question.getOptions();

		// Get all Options from db
		String getAllOptionFromDBQuery = "SELECT * FROM options WHERE question_id=?";

		List<Option> optionsInDb = jdbcTemplate.query(getAllOptionFromDBQuery, (rs, rowNum) -> {
			Option option = new Option();
			option.setOptionId(rs.getInt("option_id"));
			option.setOptionText(rs.getString("option_text"));
			option.setQuestionId(rs.getInt("question_id"));

			return option;
		}, questionId);

		// Query to Update all Options into Database
		String updateQueryforOptions = "UPDATE options SET option_text=? WHERE option_id=? AND question_id=?";

		// setting option id
		for (int index = 0; index < optionsInDb.size(); index++) {
			int optionId = optionsInDb.get(index).getOptionId();
			allOptions.get(index).setOptionId(optionId);

			allOptions.get(index).setQuestionId(questionId);
		}

		List<Object[]> queryOptions = allOptions.stream()
				.map((option) -> new Object[] { option.getOptionText(), option.getOptionId(), questionId }).toList();

		jdbcTemplate.batchUpdate(updateQueryforOptions, queryOptions);

		// Update correct option or find correct option
		for (int index = 0; index < optionsInDb.size(); index++) {
			if (optionsInDb.get(index).getOptionText().equals(correctOptionText)) {
				question.setCorrectOption(optionsInDb.get(index));
				break;
			}
		}

		// Update Question in DB
		String updateQueryForQuestion = "UPDATE questions SET question_text=?, category_id=?, correct_option_id = ? WHERE question_id = ?";

		jdbcTemplate.update(updateQueryForQuestion, questionText, categoryId, question.getCorrectOption().getOptionId(),
				questionId);

		return question;
	}

	@Override
	public boolean deleteQuestionById(int questionId) {
		String query = "DELETE FROM questions WHERE question_id = ?";

		int deleteRow = jdbcTemplate.update(query, questionId);
		return deleteRow > 0;
	}

	@Override
	public List<Question> getAllQuestion() {

		Map<Integer, Question> questionMap = new LinkedHashMap<Integer, Question>();

		String query = "SELECT * FROM questions AS q " + "INNER JOIN options AS o "
				+ "ON q.question_id = o.question_id " + "INNER JOIN question_categories AS qc "
				+ "ON q.category_id = qc.category_id " + "ORDER BY q.question_id DESC, o.option_id ; ";

		List<Question> questionList = jdbcTemplate.query(query, rs -> {

			while (rs.next()) {
				int questionId = rs.getInt("question_id");
				int correctOptionId = rs.getInt("correct_option_id");

				// create question object if question id is not present in Map as key.
				Question question = questionMap.computeIfAbsent(questionId, id -> {

					// Creating Question object
					Question q = new Question();
					try {
						// Setting Question Id and Question Text
						q.setQuestionId(id);
						q.setQuestionText(rs.getString("question_text"));

						// Setting Question Category
						QuestionCategory questionCategory = new QuestionCategory();
						questionCategory.setCategoryId(rs.getInt("category_id"));
						questionCategory.setCategoryName(rs.getString("category_name"));

						q.setQuestionCategory(questionCategory);

						q.setOptions(new ArrayList<Option>());

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Message from getAllQuestion: " + e.getMessage());
					}

					return q;
				});

				// After creating object Add option into the question.
				Option option = new Option();

				option.setOptionId(rs.getInt("option_id"));
				option.setOptionText(rs.getString("option_text"));
				option.setQuestionId(questionId);

				question.getOptions().add(option);

				// Setting Question Correct Option
				if (correctOptionId == option.getOptionId()) {
					question.setCorrectOption(option);
				}

			}

			return new ArrayList<Question>(questionMap.values());
		});

		return questionList;
	}

	@Override
	public Question getQuestionById(int questionId) {

		String query = "SELECT * FROM questions AS q " + "INNER JOIN options AS o "
				+ "ON q.question_id = o.question_id " + "INNER JOIN question_categories AS qc "
				+ "ON q.category_id = qc.category_id " + "where q.question_id = ?; ";

		Question ques = jdbcTemplate.queryForObject(query, new RowMapper<Question>() {

			@Override
			public Question mapRow(ResultSet rs, int rowNum) throws SQLException {

				// Creating Question Object
				Question question = new Question();

				int questionId = rs.getInt("question_id");
				int correctOptionId = rs.getInt("correct_option_id");

				// Setting Question Id and Question Text
				question.setQuestionId(questionId);
				question.setQuestionText(rs.getString("question_text"));

				// Setting Question Category
				QuestionCategory questionCategory = new QuestionCategory();
				questionCategory.setCategoryId(rs.getInt("category_id"));
				questionCategory.setCategoryName(rs.getString("category_name"));

				question.setQuestionCategory(questionCategory);

				// Setting Question Options
				question.setOptions(new ArrayList<Option>());

				do {
					Option option = new Option();
					option.setOptionId(rs.getInt("option_id"));
					option.setOptionText(rs.getString("option_text"));
					option.setQuestionId(questionId);

					question.getOptions().add(option);

					// Setting Question Correct Option
					if (option.getOptionId() == correctOptionId) {
						question.setCorrectOption(option);
					}
				} while (rs.next());

				return question;
			}

		}, questionId);

		return ques;
	}

	@Override
	public List<Question> getAllQuestionByCategoryId(int categoryId) {

		Map<Integer, Question> questionMap = new LinkedHashMap<Integer, Question>();

		String query = "SELECT * FROM questions AS q " + "INNER JOIN options AS o "
				+ "ON q.question_id = o.question_id " + "INNER JOIN question_categories AS qc "
				+ "ON q.category_id = qc.category_id " + "where qc.category_id = ? ";

		List<Question> questionList = jdbcTemplate.query(query, rs -> {

			while (rs.next()) {
				int questionId = rs.getInt("question_id");
				int correctOptionId = rs.getInt("correct_option_id");

				// create question object if question id is not present in Map as key.
				Question question = questionMap.computeIfAbsent(questionId, id -> {

					// Creating Question object
					Question q = new Question();
					try {
						// Setting Question Id and Question Text
						q.setQuestionId(id);
						q.setQuestionText(rs.getString("question_text"));

						// Setting Question Category
						QuestionCategory questionCategory = new QuestionCategory();
						questionCategory.setCategoryId(rs.getInt("category_id"));
						questionCategory.setCategoryName(rs.getString("category_name"));

						q.setQuestionCategory(questionCategory);

						q.setOptions(new ArrayList<Option>());

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Message from getAllQuestion: " + e.getMessage());
					}

					return q;
				});

				// After creating object Add option into the question.
				Option option = new Option();

				option.setOptionId(rs.getInt("option_id"));
				option.setOptionText(rs.getString("option_text"));
				option.setQuestionId(questionId);

				question.getOptions().add(option);

				// Setting Question Correct Option
				if (correctOptionId == option.getOptionId()) {
					question.setCorrectOption(option);
				}

			}

			return new ArrayList<Question>(questionMap.values());
		}, categoryId);

		return questionList;
	}

	@Override
	public List<Question> getRandomQuestionByCategoryId(int categoryId, int size) {

		Map<Integer, Question> questionMap = new LinkedHashMap<Integer, Question>();

		String query = "SELECT * FROM (" + " SELECT * FROM questions AS ques " + "WHERE ques.category_id = ? "
				+ "ORDER BY RAND() " + "LIMIT ? " + ") as q " + "INNER JOIN options AS o "
				+ "ON q.question_id = o.question_id " + "INNER JOIN question_categories AS qc "
				+ "ON q.category_id = qc.category_id ";

		List<Question> questionList = jdbcTemplate.query(query, rs -> {

			while (rs.next()) {
				int questionId = rs.getInt("question_id");
				int correctOptionId = rs.getInt("correct_option_id");

				// create question object if question id is not present in Map as key.
				Question question = questionMap.computeIfAbsent(questionId, id -> {

					// Creating Question object
					Question q = new Question();
					try {
						// Setting Question Id and Question Text
						q.setQuestionId(id);
						q.setQuestionText(rs.getString("question_text"));

						// Setting Question Category
						QuestionCategory questionCategory = new QuestionCategory();
						questionCategory.setCategoryId(rs.getInt("category_id"));
						questionCategory.setCategoryName(rs.getString("category_name"));

						q.setQuestionCategory(questionCategory);

						q.setOptions(new ArrayList<Option>());

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Message from getAllQuestion: " + e.getMessage());
					}

					return q;
				});

				// After creating object Add option into the question.
				Option option = new Option();

				option.setOptionId(rs.getInt("option_id"));
				option.setOptionText(rs.getString("option_text"));
				option.setQuestionId(questionId);

				question.getOptions().add(option);

				// Setting Question Correct Option
				if (correctOptionId == option.getOptionId()) {
					question.setCorrectOption(option);
				}

			}

			return new ArrayList<Question>(questionMap.values());
		}, categoryId, size);

		return questionList;
	}

//	********** Question Category ************

	@Override
	public boolean addCategory(String categoryName) {

		String query = "INSERT INTO question_categories (category_name) values (?)";

		int insertedRow = jdbcTemplate.update(query, categoryName);

		return insertedRow > 0;
	}

	@Override
	public boolean deleteCategoryById(int id) {
		String query = "DELETE FROM question_categories WHERE category_id = ?";

		int deleteRow = jdbcTemplate.update(query, id);
		return deleteRow > 0;
	}

	@Override
	public void updateCategory(QuestionCategory category) {
		int id = category.getCategoryId();
		String name = category.getCategoryName();

		String query = "UPDATE question_categories SET category_name = ? WHERE category_id = ?";

		jdbcTemplate.update(query, name, id);
	}

	@Override
	public List<QuestionCategory> getAllCategories() {

		String query = "SELECT * FROM question_categories ORDER BY category_id DESC";
		List<QuestionCategory> questionCategories = jdbcTemplate.query(query, new QuestionCategoryRowMapper());

		return questionCategories;
	}

	@Override
	public QuestionCategory getCategoryById(int id) {

		String query = "SELECT * FROM question_categories WHERE category_id = ?";

		QuestionCategory questionCategory = jdbcTemplate.queryForObject(query, new QuestionCategoryRowMapper(), id);

		return questionCategory;
	}
}
