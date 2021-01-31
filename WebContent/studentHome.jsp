<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.StudentBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stud.io</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<body>

	<%StudentBean studBean = (StudentBean)request.getSession().getAttribute("studentBean");%>

	<!-- navbar -->
	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#"><%=studBean.getUtenteUsername()%></a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<!-- "ml-auto" consente di allineare item a destra-->
					<li class="nav-link"><a class="btn btn-success mx-auto"
						href="studentHome.jsp">Search<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Bookmarks</a></li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Messages</a></li>
					<li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="Booking.jsp">Bookings</a></li>
					<li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Settings</a></li>
					<li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="index.html">Log out</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!-- END navbar -->

	<div class="row">
		<div class="container" id="search">
			<h1 align=center>
				<br>
				<br>SEARCH A SEAT
			</h1>
			<h3 align=center>Enter your city and look for the place to
				study closest to you:</h3>


			<form class="form-inline my-2 my-lg-0" action="SearchSeatServlet"
				name="studSearch" id="studSearch" method="GET">
				<input class="form-control ml-auto mr-1" id="searchField"
					name="searchField" type="text" value=""
					placeholder="Insert your position..."> <input
					class="btn btn-success mr-auto" id="searchBtn" name="searchBtn"
					type="submit" value="Search">
			</form>


		</div>
	</div>

	<div class="row">
		<div class="container" id="logo">
			<p>
				<img src="img/logo3.jpg" >
			</p>
		</div>
	</div>

	<!-- jQuery e plugin JavaScript  -->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>


