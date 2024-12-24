
<nav
	class="navbar navbar-dark bg-dark sticky top-0 z-3 navbar-expand-lg">
	<div class="container-fluid">
		<a class="navbar-brand" href="home"> <svg width="25px"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
				fill="currentColor" class="size-6">
				  <path
					d="M11.644 1.59a.75.75 0 0 1 .712 0l9.75 5.25a.75.75 0 0 1 0 1.32l-9.75 5.25a.75.75 0 0 1-.712 0l-9.75-5.25a.75.75 0 0 1 0-1.32l9.75-5.25Z" />
				  <path
					d="m3.265 10.602 7.668 4.129a2.25 2.25 0 0 0 2.134 0l7.668-4.13 1.37.739a.75.75 0 0 1 0 1.32l-9.75 5.25a.75.75 0 0 1-.71 0l-9.75-5.25a.75.75 0 0 1 0-1.32l1.37-.738Z" />
				  <path
					d="m10.933 19.231-7.668-4.13-1.37.739a.75.75 0 0 0 0 1.32l9.75 5.25c.221.12.489.12.71 0l9.75-5.25a.75.75 0 0 0 0-1.32l-1.37-.738-7.668 4.13a2.25 2.25 0 0 1-2.134-.001Z" />
				</svg> Quiz App
		</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active" href="home"
					aria-current="page">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="quizHistory">Quiz
						History</a></li>

			</ul>


			<div>
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary d-flex gap-1 align-items-center" data-bs-toggle="modal"
					data-bs-target="#logoutModel">
					<svg style="width: 25px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
						fill="currentColor" class="size-6">
  						<path fill-rule="evenodd"
							d="M12 2.25a.75.75 0 0 1 .75.75v9a.75.75 0 0 1-1.5 0V3a.75.75 0 0 1 .75-.75ZM6.166 5.106a.75.75 0 0 1 0 1.06 8.25 8.25 0 1 0 11.668 0 .75.75 0 1 1 1.06-1.06c3.808 3.807 3.808 9.98 0 13.788-3.807 3.808-9.98 3.808-13.788 0-3.808-3.807-3.808-9.98 0-13.788a.75.75 0 0 1 1.06 0Z"
							clip-rule="evenodd" />
					</svg>
					<span>Logout</span>
				</button>
			</div>

		</div>
	</div>
</nav>

<!-- Modal -->
<div class="modal fade" id="logoutModel" tabindex="-1"
	aria-labelledby="logoutModel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">Logout</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to logout</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Cancel</button>
				<a href="logout" class="btn btn-primary">Yes</a>
			</div>
		</div>
	</div>
</div>