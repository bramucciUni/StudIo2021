<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.LibrarianBean" %>
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
	message = (String)request.getSession().getAttribute("releasePcOutcome");
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


    <div class="container">
      <h1 align=center><br><br>RELEASE PC</h1>
      <h3 align=center>Please insert the name of PC:</h3>

      <!-- form -->
      <div class="container">
      
      <h3 align=center><font color=red><%=message%></font></h3>
      
      <form action="ReleasePcServlet" name="loginForm" method="GET">
        <div class="form-group">
          <label for="exampleInputEmail1">PC name</label>
          <input class="form-control" id="pcNameField" name="pcNameField" type="text" 
               value="" 
               aria-describedby="pcNameField">
        </div>
        <h2 align=center><input class="btn btn-success mx-auto" role="button" name="releasePcBtn" type="submit"
               id="releasePcBtn" value="Release PC"></h2>
      </form>
      
      </div>
    </div>
      <!-- END  form -->


    <!-- jQuery e plugin JavaScript  -->
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="js/bootstrap.min.js"></script>

  </body>
</html>

