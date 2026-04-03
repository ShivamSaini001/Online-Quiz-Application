<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>
<body>

	<%@include file="navbar.jsp" %>

	<!-- Hero Section -->
	 <section class="hero-section text-white">
	    <div class="container py-5">
	      <!-- Welcome Header -->
	      <div class="mb-4">
	        <h1 class="fs-1 fw-bold mb-2">
	          Welcome back, Admin! 👋
	        </h1>
	        <p class="text-white-90 fs-5">
	          Here's what's happening with your quiz platform today
	        </p>
	        <div class="d-flex align-items-center gap-2 mt-3 text-white-80">
	          <i class="bi bi-calendar"></i>
	          <span class="small">Friday, April 3, 2026</span>
	        </div>
	      </div>
	
	      <!-- Stats Grid -->
	      <div class="row g-4">
	        <!-- Stat Card 1 -->
	        <div class="col-12 col-sm-6 col-lg-3">
	          <div class="stat-card p-4">
	            <div class="d-flex align-items-start justify-content-between mb-4">
	              <div class="icon-box icon-box-blue">
	                <i class="bi bi-people-fill fs-4"></i>
	              </div>
	              <div class="badge-trend badge-trend-up">
	                <i class="bi bi-arrow-up-right"></i>
	                12%
	              </div>
	            </div>
	            <h3 class="fs-2 fw-bold mb-1">2,543</h3>
	            <p class="text-white-80 small mb-0">Total Users</p>
	          </div>
	        </div>
	
	        <!-- Stat Card 2 -->
	        <div class="col-12 col-sm-6 col-lg-3">
	          <div class="stat-card p-4">
	            <div class="d-flex align-items-start justify-content-between mb-4">
	              <div class="icon-box icon-box-green">
	                <i class="bi bi-patch-check-fill fs-4"></i>
	              </div>
	              <div class="badge-trend badge-trend-up">
	                <i class="bi bi-arrow-up-right"></i>
	                8%
	              </div>
	            </div>
	            <h3 class="fs-2 fw-bold mb-1">1,234</h3>
	            <p class="text-white-80 small mb-0">Completed Quizzes</p>
	          </div>
	        </div>
	
	        <!-- Stat Card 3 -->
	        <div class="col-12 col-sm-6 col-lg-3">
	          <div class="stat-card p-4">
	            <div class="d-flex align-items-start justify-content-between mb-4">
	              <div class="icon-box icon-box-yellow">
	                <i class="bi bi-trophy-fill fs-4"></i>
	              </div>
	              <div class="badge-trend badge-trend-up">
	                <i class="bi bi-arrow-up-right"></i>
	                15%
	              </div>
	            </div>
	            <h3 class="fs-2 fw-bold mb-1">87.5%</h3>
	            <p class="text-white-80 small mb-0">Average Score</p>
	          </div>
	        </div>
	
	        <!-- Stat Card 4 -->
	        <div class="col-12 col-sm-6 col-lg-3">
	          <div class="stat-card p-4">
	            <div class="d-flex align-items-start justify-content-between mb-4">
	              <div class="icon-box icon-box-red">
	                <i class="bi bi-clock-fill fs-4"></i>
	              </div>
	              <div class="badge-trend badge-trend-down">
	                <i class="bi bi-arrow-down-right"></i>
	                3%
	              </div>
	            </div>
	            <h3 class="fs-2 fw-bold mb-1">24 min</h3>
	            <p class="text-white-80 small mb-0">Avg. Time Spent</p>
	          </div>
	        </div>
	      </div>
	    </div>
	  </section>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>