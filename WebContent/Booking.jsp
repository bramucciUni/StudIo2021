<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="logic.entity.Booking"
import="logic.bean.StudentBean"
import="logic.bean.LibraryBean"
import="logic.constants.BookingConstants"
import="java.util.List"
import="logic.dao.LibraryDao"
import="logic.dao.BookingDao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stud.io</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<body>

<% StudentBean studBean = (StudentBean)request.getSession().getAttribute("studentBean");%>

	<!-- navbar -->
	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#"><%=studBean.getUtenteUsername() %></a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<!-- "ml-auto" consente di allineare item a destra-->
					<li class="nav-link active"><a class="btn btn-secondary"
						href="studentHome.jsp">Search<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Bookmarks</a></li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Messages</a></li>
					<li>
					<a class="btn btn-success mx-auto" href="Booking.jsp">Bookings</a>
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

	<%
	String warningMsg = "";
	String nameLibrary = "";
	String addressLibrary = "";
	String contactsLibrary = "";
	String bookingTime = "";
	String bookingItem = "";
	boolean booked = true;
	Booking booking = null;
	BookingDao bookingDao = new BookingDao();
	List<Booking> bookings = null;
	bookings = bookingDao.selectActiveBookingFromStudentId(studBean.getEmailUtente(), BookingConstants.HANGING);
	if(bookings.size() == 0) {
		booked = false;
		warningMsg = "Student have no hanging bookings";
	} else {
		booking = bookings.get(0);
		request.getSession().setAttribute("book", booking);
		LibraryDao libraryDao = new LibraryDao();
		LibraryBean libraryBean = libraryDao.selectLibraryFromId(booking.getMailBiblioteca());
		nameLibrary = libraryBean.getNameB();
		addressLibrary = libraryBean.getIndirizzoB();
		contactsLibrary = libraryBean.getMailB() + " - Phone: " + libraryBean.getTelefonoB();
		bookingTime = booking.getDateTimeBooking();
		bookingItem = booking.getItemInformation();
	}
	%>

	<form action="BookingItemServlet" name="bookForm" method="GET">
		<div class="row" align="center">
			<div class="container" id="listAttr" align="center">

				<h1 align=center>
					<br>
					<br>Your reservation:
				</h1>
				
				<div>
					<h4 id=reservationWarning align=center>
						<br>
						<%=warningMsg %>
					</h4>
				</div>

				<div class="table-wrapper-scroll-y my-custom-scrollbar">

					<table id="tableLib"
						class="table table-striped table-bordered table-sm"
						style="content-align: center;">
						<tbody>
							<tr>
								<td><p class="text-justify" align="center">
										Library:
										<%=nameLibrary%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Address:
										<%=addressLibrary%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Contacts:
										<%=contactsLibrary%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Booking time:
										<%=bookingTime%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Booked item:
										<%=bookingItem%>
									</td>
							</tr>
					</table>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="container" id="librPageButtons">
				<p class="text-center">
					<input class="btn btn-success mx-auto" role="button" id="deleteBook"
						name="deleteBook" type="submit" value="Delete reservation"> 
				</p>
			</div>
		</div>

	</form>


	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>