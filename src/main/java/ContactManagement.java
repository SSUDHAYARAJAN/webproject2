import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
@WebServlet(value="/contacts")
public class ContactManagement extends  HttpServlet
{

    static List <Contact> contacts=new ArrayList<>();
    PrintWriter pw;
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {

        res.setContentType("application/json;charset=UTF-8");
      //  res.setContentType("text/html;charset=UTF-8");
         int  id= Integer.parseInt(req.getParameter("id"));
         String firstname=req.getParameter("firstname");
         String lastname=req.getParameter("lastname");
         String email= req.getParameter("emailId");
         String password=req.getParameter("password");
         /*JSONObject jsonObj = new JSONObject(firstname);
         String op= jsonObj.getString("text");
         res.getWriter().println(op);*/
       // JSONObject allObj = jsonObj.getJSONObject("obj");
           Contact contact=new Contact(id,firstname,lastname,email,password);
           contacts.add(contact);
        res.getWriter().println("<font color='red'><b><i>Your Contact added successfully in server</i><b></font>");
    }
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
        pw=res.getWriter();
//            //res.setContentType("text/html;charset=UTF-8");
//            int id= Integer.parseInt(req.getParameter("id"));
//            boolean flag=true;
//            for(Contact contact:contacts)
//            {
//                if(contact.getId() ==id)
//                {
//                    String response=gson.toJson(contact);
//                     pw.println(contact);
//                    flag=false;
////                    pw.write("<font color='blue' ><h3 >");
//                    pw.println("<font color='red'><br><u><i>Details of given id </i></u></font>");
//                    pw.println("<br>FirstName: "+contact.getFirstname());
//                    pw.println("<br>LastName:  "+contact.getLastname());
//                    pw.println("<br>EmailId:   "+ contact.getEmail());
//                    pw.println("<br>Passwords: "+contact.getPassword()+"</h3>");
//                   pw.println("</h3></font>");
//                      pw.write(response);
//                    break;
//                }
//            }
//            if(flag)
//                pw.write("<h3>contact not found<h3>");
//            for( Contact contact: contacts)
//            {
//                pw.write("<font color='blue' ><h3 >");
//                pw.println("<font color='red'><br><u><i>contact details </i></u></font>");
//                pw.println("<br>FirstName: "+contact.getFirstname());
//                pw.println("<br>LastName:  "+contact.getLastname());
//                pw.println("<br>EmailId:   "+ contact.getEmail());
//                pw.println("<br>Passwords: "+contact.getPassword()+"</h3>");
//                pw.println("</h3></font>-----------------<br>")
//                String response=gson.toJson(contact);
//                pw.println(contact);
//            }
        Gson gson = new Gson();
        res.setContentType("application/json;charset=UTF-8");
        String response=gson.toJson(contacts);
        pw.println(response);
    }


    @Override
    public void doPut(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
        System.out.println("doPut called");
        boolean flag=false;
        int id= Integer.parseInt(req.getParameter("id"));
        String firstname= req.getParameter("firstname");
        for(Contact contact: contacts)
        {
            if(contact.getId()==id)
            {
                contact.setFirstname(firstname);
                res.getWriter().println("contact updated successfully");
                flag=false;
                break;
            }
        }
        if(flag)
            res.getWriter().println("<h3> contact not found <h3>");
    }
    @Override
    public void doDelete (HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
    {
        System.out.println("doDelete called");
        res.setContentType("text/html;charset=UTF-8");
        int id= Integer.parseInt(req.getParameter("id"));
        boolean flag=true;
        for(Contact contact: contacts)
        {
            if(contact.getId()==id)
            {
                contacts.remove(contact);
                res.getWriter().println("contact deleted successfully");
                flag=false;
                break;
            }
        }
        if(flag)
            pw.println("contact not found");

    }


}
