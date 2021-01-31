<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.LibrarianBean" %>
<%@ page import="logic.bean.BookingBean" %>
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
 
	<%LibrarianBean librarianBean = (LibrarianBean)request.getSession().getAttribute("librarianBean");
	String message = "";
	message = (String)request.getSession().getAttribute("outcomeValidating");
	BookingBean bookingBean = (BookingBean)request.getSession().getAttribute("selectedBooking");
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


    <div class="row" align="center">
			<div class="container" id="listAttr" align="center">

				<h1 align=center>
					<br>
					<br>BOOKING
				</h1>
				
				<h3 align=center><font color=red><%=message%></font></h3>

				<div class="table-wrapper-scroll-y my-custom-scrollbar">

					<table id="tableLib"
						class="table table-striped table-bordered table-sm"
						style="content-align: center;">
						<tbody>
							<tr>
								<td><p class="text-justify" align="center">
										Date:
										<%out.println(bookingBean.getDate());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Hour:
										<%
								out.println(bookingBean.getHour());
								%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Type:
										<% out.println(bookingBean.getTipo());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Student:
										<%out.println(bookingBean.getStudentId());%>
									</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<form action="LibrarianManageBookingServlet" name="fff" method="GET">
			<div class="row">
				<div class="container" id="aaa">
					<p class="text-center">
						<input class="btn btn-success mx-auto" role="button" id="btnAccept"
							name="btnAccept" type="submit" value="Accept"> 
						<input class="btn btn-success mx-auto" role="button" id="bookSeat"
							name="bookSeat" type="submit" value="Book seat"> 
					</p>
				</div>
			</div>
		</form>


    <!-- jQuery e plugin JavaScript  -->
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="js/bootstrap.min.js"></script>

  </body>
</html>

