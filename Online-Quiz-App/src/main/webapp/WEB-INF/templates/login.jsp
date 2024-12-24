<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>
<body>

	<div
		class="container my-5 p-2 px-4 mx-auto col-8 col-md-6 col-lg-4 bg-dark text-white border rounded">
		<h1 align="center" class="mb-3">Login here</h1>

		<form action="login" method="post" class="d-flex flex-column">

			<div class="mb-3">
				<label for="email" >Email</label> <input name="username"
					type="email" class="form-control" id="email"
					placeholder="Enter here" required>
			</div>

			<div class="mb-3">
				<label for="password" >Password</label>
				<input type="password" name="password" class="form-control" id="password"
					placeholder="Enter here" required>
			</div>

			<div class="d-flex gap-3 justify-content-end me-2 my-3">
				<button type="reset" class="btn btn-secondary">Reset</button>
				<button type="submit" class="btn btn-success">Login</button>
			</div>
			
			<div class="ms-2">
				<p>
				Don't have an account? 
				<a href="register" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
				Register here.</a>
				</p>
			</div>
		</form>

	</div>








	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>