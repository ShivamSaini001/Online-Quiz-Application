<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Questions</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>

<body>
	<!-- Navbar start -->
	<%@include file="navbar.jsp"%>
	<!-- Navbar end -->

	<main>
		<div class="container">
			<div
				class="container-fluid d-flex mt-4 mb-2 py-3 px-6 bg-dark rounded justify-content-end gap-5">

				<p class="fs-5 fw-bold text-white my-auto me-auto ms-4">All
					Questions:</p>

				<!-- START Add Question Modal -->
				<div>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#addQuestionModal">
						Add New Question</button>

					<!-- Modal -->
					<div class="modal fade" id="addQuestionModal"
						data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="staticBackdropLabel">Add
										Question</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>

								<button type="button" class="btn btn-primary m-3 mb-0"
									data-bs-toggle="modal" data-bs-target="#addCategoryModal">
									Add New Category</button>

								<!-- ADD New Question Form -->

								<form action="question/add" method="post"
									class="question-management-form">

									<div class="modal-body d-flex flex-column gap-3">

										<!-- Question Category -->
										<select name="questionCategoryId" class="form-select" required>
											<option class="d-none" value="">Please Select
												Question Category...</option>

											<!-- Show all categories -->
											<c:forEach items="${categoryList }" var="category">

												<option value="${category.getCategoryId() }">
													${category.getCategoryName() }</option>

											</c:forEach>
										</select>

										<!-- Question text -->
										<div class="form-floating">
											<textarea name="questionText" required class="form-control"
												placeholder="Enter here" id="questionText"
												style="height: 100px"></textarea>
											<label for="questionText">Question</label>
										</div>

										<!-- All Options -->
										<div id="allOptions">

											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default1">(A)</span> <input
													type="text" name="optionA" required
													placeholder="Enter here"
													oninput="setOptionsToChooseCorrect('correctOption', 'allOptions')"
													class="form-control" aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default1">
											</div>

											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default2">(B)</span> <input
													name="optionB" required placeholder="Enter here"
													oninput="setOptionsToChooseCorrect('correctOption', 'allOptions')"
													class="form-control" aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default2">
											</div>

											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default3">(C)</span> <input
													name="optionC" required placeholder="Enter here"
													oninput="setOptionsToChooseCorrect('correctOption', 'allOptions')"
													class="form-control question-options"
													aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default3">
											</div>

											<div class="input-group mb-3">
												<span class="input-group-text"
													id="inputGroup-sizing-default4">(D)</span> <input
													name="optionD" required placeholder="Enter here"
													oninput="setOptionsToChooseCorrect('correctOption', 'allOptions')"
													class="form-control" aria-label="Sizing example input"
													aria-describedby="inputGroup-sizing-default4">
											</div>

										</div>


										<!-- Correct option -->
										<div class="input-group mb-3">
											<label class="input-group-text" for="inputGroupSelect01">Correct
												Options</label> <select name="correctOption" required disabled
												id="correctOption" class="form-select"
												id="inputGroupSelect01">
												<option selected class="d-none" value="">Please
													Select....</option>
												<option></option>
												<option></option>
												<option></option>
												<option></option>
											</select>
										</div>

									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-danger"
											data-bs-dismiss="modal">Close</button>
										<button type="reset" class="btn btn-secondary">Reset</button>
										<button type="submit" class="btn btn-success">Add</button>
									</div>
								</form>

							</div>
						</div>
					</div>
				</div>
				<!-- END Add Question Modal -->



				<!-- START Add Category Modal -->
				<div>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#addCategoryModal">
						Add New Category</button>

					<!-- Modal -->
					<div class="modal fade modal-lg" id="addCategoryModal"
						data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="staticBackdropLabel">Add
										New Category</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>

								<div class="modal-body container d-flex flex-column gap-3">
									<!-- Add category form -->
									<form action="category/add" method="post"
										class="category-management-form">
										<div class="input-group mb-3">
											<input name="categoryName" class="form-control" required
												placeholder="Enter Category Name">

											<button class="btn btn-outline-success" type="submit">Add</button>
										</div>
									</form>

									<div>All Categories:</div>

									<div style="height: 350px; overflow-y: auto;">
										<table class="table table-hover">
											<thead>
												<tr>
													<th scope="col" class="text-center">Id</th>
													<th scope="col" class="text-start">Category Name</th>
													<th></th>
												</tr>
											</thead>
											<tbody>

												<%
												int count = 1;
												%>
												<c:forEach items="${categoryList }" var="category">

													<tr>
														<th class="text-center"><%=count++%></th>
														<td class="text-start">${category.getCategoryName() }</td>
														<td class="text-center">
															<button class="btn btn-sm btn-outline-success"
																data-bs-toggle="modal"
																data-bs-target="#updateCategoryModal${category.getCategoryId() }">Edit</button>
															<button class="btn btn-sm btn-outline-danger"
																data-bs-toggle="modal"
																data-bs-target="#deleteCategoryModal${category.getCategoryId() }">Delete</button>
														</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>

								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-bs-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- ********** Edit and Delete Model for each category ********** -->
				<c:forEach items="${categoryList }" var="category">

					<!-- START Edit Category model -->
					<div class="modal fade"
						id="updateCategoryModal${category.getCategoryId() }"
						data-bs-keyboard="false" tabindex="-50"
						aria-labelledby="deleteCategoryModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="staticBackdropLabel">Update
										Category</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body d-flex flex-column gap-3">

									<!-- Edit Category Form -->
									<form action="category/update" method="post"
										class="category-management-form">
										<div class="input-group mb-3">
											<input name="categoryId" type="hidden"
												value="${category.getCategoryId() }"> <input
												name="categoryName" class="form-control" required
												value="${category.getCategoryName() }"
												placeholder="Enter Category Name">

											<button class="btn btn-outline-success" type="submit">Update</button>
										</div>
									</form>

								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-bs-dismiss="modal">Close</button>
								</div>

							</div>
						</div>
					</div>

					<!-- END Edit Category model -->

					<!-- START Delete Category model -->
					<div class="modal fade"
						id="deleteCategoryModal${category.getCategoryId() }"
						data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="deleteCategoryModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="staticBackdropLabel">Delete
										Category</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body d-flex flex-column gap-3">

									<p class="fw-bold fs-5">Are you sure you want to delete
										category?</p>
									<p class="fs-5 fw-bold text-success">${category.getCategoryName() }</p>
									<p>
										<span class="text-danger fw-bold">Note: </span>All questions
										belongs to this category also will be deleted...
									</p>

								</div>

								<div class="modal-footer">

									<!-- Delete Category Form -->
									<form action="category/deleteById" method="post">
										<div class="gap-3">
											<input name="categoryId" value="${category.getCategoryId() }"
												class="form-control d-none">

											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Cancel</button>
											<button class="btn btn-danger" type="submit"
												id="button-addon23">Delete</button>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>
					<!-- END Delete Category btn model -->
				</c:forEach>

				<!-- END add Category Modal -->


			</div>

			<!-- Alert Start -->
			<c:if test="${responseMessage != null }">
				<div
					class="container alert alert-success d-flex align-items-center alert-dismissible fade show"
					role="alert">
					<svg class="pt-1" style="height: 23px; width: 25px;" role="img"
						aria-label="Success:">
			 		<path
							d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" /></svg>
					<div>${responseMessage }</div>
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>

			<!-- Alert End -->

			<!-- *********** START Show all Questions ************-->
			<div class="container">


				<ol type="1" class="list-bold">

					<c:forEach items="${questionList }" var="question">
						<!-- First Question -->
						<li>
							<div class="d-flex justify-content-between gap-2">
								<div>
									<p class="fw-bold mb-0">${question.getQuestionText() }</p>
									<ol type="A">
										<li>${question.getOptionA() }</li>
										<li>${question.getOptionB() }</li>
										<li>${question.getOptionC() }</li>
										<li>${question.getOptionD() }</li>
									</ol>
									<p class="text-success">
										Correct Answer: <span class="fw-bold">
											${question.getCorrectOption() }</span>
									</p>
								</div>
								<div class="d-flex gap-2 flex-column">
									<button class="btn btn-sm btn-outline-success" type="button"
										data-bs-toggle="modal"
										data-bs-target="#updateQuestionModal${question.getQuestionId() }">Edit</button>
									<button class="btn btn-sm btn-outline-danger" type="button"
										data-bs-toggle="modal"
										data-bs-target="#deleteQuestionModal${question.getQuestionId() }">Delete</button>
								</div>
							</div>
						</li>


						<!-- START Update Question Modal -->

						<!-- Modal -->
						<div class="modal fade"
							id="updateQuestionModal${question.getQuestionId() }"
							data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
							aria-labelledby="staticBackdropLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="staticBackdropLabel">Update
											Question</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>

									<form action="question/update" method="post"
										class="question-management-form">
										<div class="modal-body d-flex flex-column gap-3">

											<!-- Question Category -->
											<select name="questionCategoryId" class="form-select"
												required>
												<option class="d-none" value="">Please Select
													Question Category...</option>

												<!-- Show all categories -->
												<c:forEach items="${categoryList }" var="category">

													<option value="${category.getCategoryId() }"
														${category.getCategoryId() == question.getQuestionCategoryId()? 'selected' : '' }>
														${category.getCategoryName() }</option>

												</c:forEach>
											</select>

											<!-- Question Id -->
											<input type="hidden" name="questionId"
												value="${question.getQuestionId() }" />

											<!-- Question text -->
											<div class="form-floating">
												<textarea name="questionText" required class="form-control"
													placeholder="Enter here"
													id="questionText${question.getQuestionId() }"
													style="height: 100px">${question.getQuestionText() }</textarea>
												<label for="questionText${question.getQuestionId() }">Question</label>
											</div>

											<!-- All Options -->
											<div id="allOptions${question.getQuestionId() }">
												<div class="input-group mb-3">
													<span class="input-group-text"
														id="inputGroup-sizing-default1">(A)</span> <input
														name="optionA" value="${question.getOptionA() }" required
														placeholder="Enter here"
														oninput="setOptionsToChooseCorrect('correctOption${question.getQuestionId() }', 'allOptions${question.getQuestionId() }')"
														class="form-control question-options"
														aria-label="Sizing example input"
														aria-describedby="inputGroup-sizing-default1">
												</div>

												<div class="input-group mb-3">
													<span class="input-group-text"
														id="inputGroup-sizing-default2">(B)</span> <input
														name="optionB" value="${question.getOptionB() }" required
														placeholder="Enter here"
														oninput="setOptionsToChooseCorrect('correctOption${question.getQuestionId() }', 'allOptions${question.getQuestionId() }')"
														class="form-control question-options"
														aria-label="Sizing example input"
														aria-describedby="inputGroup-sizing-default2">
												</div>

												<div class="input-group mb-3">
													<span class="input-group-text"
														id="inputGroup-sizing-default3">(C)</span> <input
														name="optionC" value="${question.getOptionC() }" required
														placeholder="Enter here"
														oninput="setOptionsToChooseCorrect('correctOption${question.getQuestionId() }', 'allOptions${question.getQuestionId() }')"
														class="form-control question-options"
														aria-label="Sizing example input"
														aria-describedby="inputGroup-sizing-default3">
												</div>

												<div class="input-group mb-3">
													<span class="input-group-text"
														id="inputGroup-sizing-default4">(D)</span> <input
														name="optionD" value="${question.getOptionD() }" required
														placeholder="Enter here"
														oninput="setOptionsToChooseCorrect('correctOption${question.getQuestionId() }', 'allOptions${question.getQuestionId() }')"
														class="form-control question-options"
														aria-label="Sizing example input"
														aria-describedby="inputGroup-sizing-default4">
												</div>

											</div>


											<!-- Correct option -->
											<div class="input-group mb-3">
												<label class="input-group-text" for="inputGroupSelect01">Correct
													Options</label> <select name="correctOption" required
													id="correctOption${question.getQuestionId() }"
													class="form-select">
													<option selected class="d-none" value="">Please
														Select....</option>
													<option value="${question.getOptionA() }"
														${question.getCorrectOption() == question.getOptionA() ? 'selected' : ''}>${question.getOptionA() }</option>
													<option value="${question.getOptionB() }"
														${question.getCorrectOption() == question.getOptionB() ? 'selected' : ''}>${question.getOptionB() }</option>
													<option value="${question.getOptionC() }"
														${question.getCorrectOption() == question.getOptionC() ? 'selected' : ''}>${question.getOptionC() }</option>
													<option value="${question.getOptionD() }"
														${question.getCorrectOption() == question.getOptionD() ? 'selected' : ''}>${question.getOptionD() }</option>
												</select>
											</div>

										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-danger"
												data-bs-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-success">Update</button>
										</div>
									</form>

								</div>
							</div>
						</div>

						<!-- END Update Question Modal -->

						<!-- START Delete Question Modal -->

						<!-- Modal -->
						<div class="modal fade"
							id="deleteQuestionModal${question.getQuestionId() }"
							data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
							aria-labelledby="staticBackdropLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="staticBackdropLabel">Delete
											Question</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>

									<form action="question/delete" method="post">

										<div class="modal-body d-flex flex-column gap-3">

											<!-- Question Id -->
											<input type="hidden" name="questionId"
												value="${question.getQuestionId() }" />

											<!-- Question -->
											<div>
												<p class="fs-5 fw-bold">Are you sure you want to delete
													question?</p>
												<p class="fw-bold mb-0">${question.getQuestionText() }</p>
												<ol type="A">
													<li>${question.getOptionA() }</li>
													<li>${question.getOptionB() }</li>
													<li>${question.getOptionC() }</li>
													<li>${question.getOptionD() }</li>
												</ol>
											</div>

										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-danger"
												data-bs-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-success">Delete</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- End Delete Question Model -->
					</c:forEach>
				</ol>

			</div>

			<!-- ************ END Show all Questions *********** -->

		</div>

	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

	<script src="static/js/script.js"></script>

</body>
</html>