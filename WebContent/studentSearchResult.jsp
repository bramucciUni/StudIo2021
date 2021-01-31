<%@ page import="logic.bean.LibraryBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="logic.bean.StudentBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stud.io</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<body>

	<%StudentBean studBean = (StudentBean)request.getSession().getAttribute("studentBean");
	request.getSession().setAttribute("BookingItemFailureMessage", "");
	%>

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
				<br>Results:
			</h1>
		</div>
	</div>

		<div class="row">
			<div class="container" id="listLib">

				<div class="table-wrapper-scroll-y my-custom-scrollbar">
				
				<% List<LibraryBean> librResults = (List<LibraryBean>)request.getSession().getAttribute("librResults");%>

					<table id="tableBookings"
						class="table table-striped table-bordered table-sm"
						style="text-align: center;">
						<thead>
							<tr>
								<th>Library:</th>
								<th>Address:</th>
							</tr>
						</thead>

						<tbody>

							<% for(int i=0; i<librResults.size(); i++){ %>
							
							<tr>
								<td>
								<form action="StudentSearchResultServlet" name="studentResultList" id="studentResultList" method="GET">
								<input type="hidden" name="index" value="<%= i%>"/>
								<input class="btn btn-success mx-auto"
									id="<%="lib".concat(librResults.get(i).getMailB())%>"
									name="<%="lib".concat(librResults.get(i).getMailB())%>"
									role="button" type="submit"
									value="<%out.println(librResults.get(i).getNameB());%>">
								</form>
								</td>
								<td>
									<%= librResults.get(i).getIndirizzoB() %>
								</td>
							</tr>
							<%  }
					   	%>

						</tbody>
					</table>
				</div>
			</div>
		</div>


	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>


