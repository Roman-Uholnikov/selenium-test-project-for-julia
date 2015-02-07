<%@ page import="servlets.*" %>
<html>
<body>
<div align="left">
    <h2>Registration</h2>
    <a href="form.jsp"> fill in form again</a>
    <br>current time: <%= new java.util.Date() %>
    <br>
    <% servlets.Registration.validate(request); %>
    
    <div class="tab-pane">
       <% if(!request.getAttribute("error").equals("")){ %>
            <div class="error">
                <p><b><%=request.getAttribute("error")%></b></p>
            </div>
        <% }else{ %>
            <div class="success">
                <h3>Congratulation!</h3>
                <p>you are registered</p>
                <p class="name-success">you name is:<i><b><%=request.getAttribute("name")%></i></b></p>
            <% if(request.getAttribute("keepposted")!=null){ %>
                    <p calss="subscription-success">you are also subscribed by email:<i><b><%=request.getAttribute("email")%></b></i></p>
                <% } %>
            </div>
        <% } %>
    </div>
</div>
</body>
</html>
