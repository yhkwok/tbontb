<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Pratt - Free Bootstrap 3 Theme">
    <meta name="author" content="Alvarez.is - BlackTie.co">
    <link rel="shortcut icon" href="assets/ico/favicon.png">

    <title>Team Project</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">
    
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,300,700' rel='stylesheet' type='text/css'>
    
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/smoothscroll.js"></script>
  </head>
  <body>
	<!-- INTRO WRAP -->
		<div class="container">
			<div class="row">
				<h1>Create a Poll</h1>
				<br>	
				<form action="write_poll.php" method="POST">
					<label for="title">Poll Title</label><br />
					<input type="text" class="form-control" id="title" name="title" required="required"/>
					<label for="content">Content</label><br />
					<textarea type="text" class="form-control" id="content" name="content" required="required"/></textarea>
			<div class="row">
					<div class="col-md-6">
						<label for="optionOneTitle">Option 1 Title</label><br />
						<input type="text" class="form-control" id="optionOneTitle" name="optionOneTitle" required="required" />
					</div>
					<div class="col-md-6">
					<br/>
						<label>Add New Item &nbsp;
							<input type="checkbox" aria-label="...">
						</label>
					</div>
			</div>
			<div class="row">
					<div id="pic1" class="col-md-6">
					</div>
					<div id="pic2" class="col-md-6">
					</div>
			</div>
			<div class="row">
					<div class="col-md-6">
						<label for="optionOneURL">Amazon Item ID</label><br />
						<input type="text" class="form-control" id="optionOneURL" onchange="option1()" name="optionOneURL" required="required" />
					</div>
			</div>
			<div class="row">
			<div class="col-md-6">
					<label for="optionOneContent">Additional Information</label><br />
					<textarea type="text" class="form-control" id="optionOneContent" name="optionOneContent" required="required" ></textarea>
					</div>
			</div>	
				<br />
				<input type="submit" value="Create a Poll" class="form-control btn btn-primary"/>
				</form>
			</div>		
		</div>			
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/bootstrap.js"></script>
	<script>
	$('.carousel').carousel({
	  interval: 3500
	})
	</script>
  </body>
</html>
