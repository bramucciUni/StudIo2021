<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.LibrarianBean" %>
<%@ page import="logic.bean.LibraryBean" %>
<%@ page import="logic.dao.LibraryDao" %>
<%@ page import="logic.dao.BookingDao" %>
<%@ page import="logic.dao.BookableItemDao" %>
<%@ page import="logic.constants.BookingConstants" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Stud.io</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
</head>
 <body>
 
	<%LibrarianBean librarianBean = (LibrarianBean)request.getSession().getAttribute("librarianBean");%>
	<%
	LibraryDao libraryDao = new LibraryDao();
	LibraryBean libraryBean = libraryDao.selectLibraryFromId(librarianBean.getBiblioteca());
	request.getSession().setAttribute("releaseSeatOutcome", "");
	request.getSession().setAttribute("releasePcOutcome", "");
	BookingDao bookingDao = new BookingDao();
	BookableItemDao bookableItemDao = new BookableItemDao();
	int freeSeats = bookableItemDao.selectCountFreeItems(librarianBean.getBiblioteca(), BookingConstants.TYPESEAT);
	int freePC = bookableItemDao.selectCountFreeItems(librarianBean.getBiblioteca(), BookingConstants.TYPEPC);
	int seatBookings = bookingDao.selectCountBookingsFromLibrary(librarianBean.getBiblioteca(), BookingConstants.TYPESEAT);
	int pcBookings = bookingDao.selectCountBookingsFromLibrary(librarianBean.getBiblioteca(), BookingConstants.TYPEPC);
	
	%>
    <!-- navbar -->
    <div class="container">
      <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand">Librarian: <%=librarianBean.getMatricola() %></a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
  
          <ul class="navbar-nav ml-auto"> <!-- "ml-auto" consente di allineare item a destra-->
         
            <li class="nav-link active">
            </li>
            <li class="nav-link">
             <a class="btn btn-secondary" href="librarianHome.jsp">Home</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="bookingsListLibrarian.jsp">Bookings</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Noticeboard</a>
            </li>
            <li class="nav-link">
             <a class="btn btn-secondary" href="ReportListLibrary.jsp">Reports</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Statistics</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Settings</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="index.html">Log out</a>
            </li>
           </ul>
          </div>
        </nav>
     </div>
    <!-- END navbar -->


    <!-- CONTENT -->
		<div class="row" align="center">
			<div class="container" id="listAttr" align="center">

				<h1 align=center>
					<br>
					<br><%out.println(libraryBean.getNameB());%>
				</h1>

				<div class="table-wrapper-scroll-y my-custom-scrollbar">

					<table id="tableLib"
						class="table table-striped table-bordered table-sm"
						style="content-align: center;">
						<tbody>
							<tr>
								<td><p class="text-justify" align="center">
										Address:
										<%out.println(libraryBean.getIndirizzoB());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Mail:
										<%
								out.println(libraryBean.getMailB());
								%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Phone:
										<% out.println(libraryBean.getTelefonoB());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Free seats:
										<%out.println(String.valueOf(freeSeats));%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Booked seats:
										<%out.println(String.valueOf(seatBookings));%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Free PC:
										<%out.println(String.valueOf(freePC));%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Booked PC:
										<%out.println(String.valueOf(pcBookings));%>
									</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<form action="UpdateSeatsServlet" name="bookBtn" method="GET">
			<div class="row">
				<div class="container" id="librPageButtons">
					<p class="text-center">
						<input class="btn btn-success mx-auto" role="button" id="addVisitor"
							name="addVisitor" type="submit" value="Add visitor"> 
						<input class="btn btn-success mx-auto" role="button" id="addPcUser"
							name="addPcUser" type="submit" value="Add PC user"> 
						<input class="btn btn-success mx-auto" role="button" id="releaseSeat"
							name="releaseSeat" type="submit" value="Release seat"> 
						<input class="btn btn-success mx-auto" role="button" id="releasePc"
							name="releasePc" type="submit" value="Release PC"> 
					</p>
				</div>
			</div>
		</form>


    <!-- jQuery e plugin JavaScript  -->
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="js/bootstrap.min.js"></script>

  </body>
</html>

