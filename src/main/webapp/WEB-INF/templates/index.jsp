<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">

<link rel="stylesheet" href="static/css/app.css">

</head>
<body class="bg-body-tertiary">
	
	<!-- Navbar Start -->
		<%@include file="AppNavbar.jsp" %>
	<!-- Navbar End -->
	
	<!-- Hero Section -->
    <section class="hero-section">
        <div class="hero-background"></div>

        <div class="hero-container">
            <div class="hero-content">
                <!-- Badge -->
                <div class="badge">
                    <!-- Sparkles Icon SVG -->
                    <svg class="icon-sparkles" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="m12 3-1.912 5.813a2 2 0 0 1-1.275 1.275L3 12l5.813 1.912a2 2 0 0 1 1.275 1.275L12 21l1.912-5.813a2 2 0 0 1 1.275-1.275L21 12l-5.813-1.912a2 2 0 0 1-1.275-1.275Z"/>
                        <path d="M5 3v4"/>
                        <path d="M19 17v4"/>
                        <path d="M3 5h4"/>
                        <path d="M17 19h4"/>
                    </svg>
                    <span class="badge-text">Test Your Knowledge Today</span>
                </div>

                <!-- Main Heading -->
                <h1 class="hero-heading">
                    Challenge Your Mind with Interactive Quizzes
                </h1>

                <!-- Description -->
                <p class="hero-description">
                    Join thousands of learners exploring a world of knowledge through engaging quizzes.
                    Learn, compete, and track your progress across multiple topics.
                </p>

                <!-- CTA Button -->
                <div class="cta-container">
                    <a href="login" class="btn custom-btn-primary">
                        Start Quiz Now
                        <!-- Arrow Right Icon SVG -->
                        <svg class="icon-arrow" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M5 12h14"/>
                            <path d="m12 5 7 7-7 7"/>
                        </svg>
                    </a>
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