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
        boolean keepposted ;
        boolean validationSuccess;

        System.out.println(new java.util.Date() + " registration attempt");
        System.out.println("name: "+ request.getParameter("name"));
        System.out.println("email: "+ request.getParameter("email"));
        System.out.println("keep posted: "+ request.getParameter("keepposted"));

        keepposted =  request.getParameter("keepposted")!=null;

        validationSuccess = isNameValide(name, error);

        if(keepposted && ((email==null) || (email.equals("")))){
            error += "if you want to be posted, please, enter your email";
        }

        if(error.equals("")){
            System.out.println("registered successfully ");
        }else{
            System.out.print("registration failed because: " + error);
        }

        request.setAttribute("name",name);
        request.setAttribute("error",error);
        request.setAttribute("vadidationSuccess",validationSuccess);
        request.setAttribute("email",email);
        request.setAttribute("keepposted ",keepposted);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate(req);

        resp.sendError(403);
    }

    private static Boolean isNameValide(String name, String error){
        if(name == null || name.trim().equals("")){
            error.concat("name cannot be empty; ");
            return false;
        }
        if(name.length()<3){
            error.concat("we hope that your name should be more than 2 symbols; ");
            return false;
        }
        return true;
    }
}
