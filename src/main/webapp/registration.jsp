<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

  </head>
<body>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div class="jumbotron">
		    <h2>Registration</h2>
		    <a href="form.jsp" class="btn btn-primary" role="button"> fill in form again</a>
		    <br>current time: <%= new java.util.Date() %>
		    <br>
		    <% servlets.Registration.validate(request); %>
		    
		    <div class="tab-pane">
		       <% if(!request.getAttribute("error").equals("")){ %>
		            <div class="alert alert-danger"  role="alert">
		                <%=request.getAttribute("error")%> Please fill in <a href="form.jsp" class="alert-link">form</a> again!
		            </div>
		        <% }else{ %>
		            <div class="alert alert-success"  role="alert">
		                <h3>Congratulation!</h3>
		                <p>you are registered</p>
		                <p class="name-success">you name is:<b><i><%=request.getAttribute("name")%></i></b></p>
		            <% if(request.getParameter("keepposted")!=null){ %>
		                    <p class="subscription-success">you are also subscribed by email:<i><b><%=request.getAttribute("email")%></b></i></p>
		                <% } %>
		            </div>
		        <% } %>
    		</div>
    	</div>
    </div>
</div>
</body>
</html>
