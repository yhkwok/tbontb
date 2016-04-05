<%-- 
    Document   : Home
    Created on : 2016年4月4日, 下午06:23:38
    Author     : YH Jonathan Kwok
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

  <head>

    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <meta name="description" content="">

    <meta name="author" content="">

    <link rel="icon" href="../../favicon.ico">



    <title>2 Buy / Not 2 Buy</title>



    <!-- Bootstrap core CSS -->

    <link href="css/bootstrap.min.css" rel="stylesheet">



    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">



    <!-- Custom styles for this template -->

    <link href="css/jumbotron.css" rel="stylesheet">



    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->

    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>



    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>

      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>

      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->

  </head>

  <body>

    <div class="container">

      <div class="header clearfix">

        <nav>

			<a href="SearchWalmart.jsp">Create a Poll</a></li>

        </nav>

        <h3 class="text-muted">2 Buy / Not 2 Buy</h3>

      </div>

      <div class="jumbotron">

        <h1>2 Buy / Not 2 Buy</h1>

      </div>



      <div class="row marketing">

        <div class="col-lg-6">

			

			<c:forEach items="${Polls}" var="Poll">

        <form action="ViewPoll" method="POST">

       <div class="jumbotron" style="width: 650px; height: 100px; padding-bottom: 10px; padding-top: 2px;">

          <h5> Poll Title:</h5>

				  <h4> ${Poll.getName()}</h4>

				<button type="submit" name="pollId" value="${Poll.getId()}"> View Poll </button>

        </hr>



      </div>

        </form>

			</c:forEach>

        </div>

      </div>



      <footer class="footer">



      </footer>



    </div> <!-- /container -->





    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

  </body>

</html>
