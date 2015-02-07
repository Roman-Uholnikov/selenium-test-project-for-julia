package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by roman on 07.02.15.
 */
public class Registration extends HttpServlet {

    public static void validate(HttpServletRequest request){
        String error = "";
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Boolean keepposted  = null;

        System.out.println(new java.util.Date() + " registration attempt");
        System.out.println("name: "+ request.getParameter("name"));
        System.out.println("email: "+ request.getParameter("email"));
        System.out.println("keep posted: "+ request.getParameter("keepposted"));

        if(request.getParameter("keepposted")!=null){
            keepposted = true;
        }else{
            keepposted = false;
        }

        if(name == null || name.trim().equals("")){
            error += "name cannot be empty; ";
        }
        if(keepposted && ((email==null) | (email.equals("")))){
            error += "if you want to be posted, please, enter your email";
        }

        if(error.equals("")){
            System.out.println("registered successfully ");
        }else{
            System.out.print("registration failed because: " + error);
        }

        request.setAttribute("name",name);
        request.setAttribute("error",error);
        request.setAttribute("email",email);
        request.setAttribute("keepposted ",keepposted);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate(req);

    }
}
