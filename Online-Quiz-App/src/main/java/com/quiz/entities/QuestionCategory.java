package com.quiz.entities;

public class QuestionCategory {

	private int categoryId = -1;
	private String categoryName;

	public QuestionCategory() {

	}

	public QuestionCategory(int categoryId) {
		this.categoryId = categoryId;
	}

	public QuestionCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public QuestionCategory(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	
	
	@Override
	public String toString() {
		return "QuestionCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
