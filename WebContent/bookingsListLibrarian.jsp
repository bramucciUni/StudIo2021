<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.LibrarianBean" %>
<%@ page import="logic.dao.BookableItemDao" %>
<%@ page import="logic.constants.BookingConstants" %>
<%@ page import="logic.dao.BookingDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="logic.bean.BookingBean" %>
   
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
	request.getSession().setAttribute("outcomeValidating", "");
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


    <div class="row">
		<div class="container" id="search">
			<h1 align=center>
				<br>
				<br>BOOKINGS:
			</h1>
		</div>
	</div>
	
	<div class="row">
			<div class="container" id="listLib">

				<div class="table-wrapper-scroll-y my-custom-scrollbar">
				
				<% 
				List<BookingBean> bookings = new ArrayList<>();
				BookingDao bookingDao = new BookingDao();
				bookings = bookingDao.selectBookingsForLibrary(librarianBean.getBiblioteca());
				request.getSession().setAttribute("listBookingsResults", bookings);
				%>

					<table id="tableBookings"
						class="table table-striped table-bordered table-sm"
						style="text-align: center;">
						<thead>
							<tr>
								<th>Student:</th>
								<th>Date:</th>
								<th>Hour:</th>
								<th>Type:</th>
							</tr>
						</thead>

						<tbody>

							<% for(int i=0; i<bookings.size(); i++){ %>
							
							<tr>
								<td>
								<form action="LibrarianBookingsServlet" name="studentResultList" id="studentResultList" method="GET">
								<input type="hidden" name="index" value="<%= i%>"/>
								<input class="btn btn-success mx-auto"
									id="<%="lib".concat(bookings.get(i).getStudentId())%>"
									name="<%="lib".concat(bookings.get(i).getStudentId())%>"
									role="button" type="submit"
									value="<%out.println(bookings.get(i).getStudentId());%>">
								</form>
								</td>
								<td>
									<%= bookings.get(i).getDate() %>
								</td>
								<td>
									<%= bookings.get(i).getHour() %>
								</td>
								<td>
									<%= bookings.get(i).getTipo() %>
								</td>
							</tr>
							<%  }
					   	%>

						</tbody>
					</table>
				</div>
			</div>
		</div>


    <!-- jQuery e plugin JavaScript  -->
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="js/bootstrap.min.js"></script>

  </body>
</html>

