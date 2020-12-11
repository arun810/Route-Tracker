package co;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			Connection con;
			con=Dbconnection.Getconnection();
			pst=con.prepareStatement("select * from admin_login where name=? and pass=?");
		}
		catch(Exception x){
			System.out.println(x.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String pwd=request.getParameter("upass");
		
		try{
			pst.setString(1,name);
			pst.setString(2,pwd);
			
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				PrintWriter out=response.getWriter();
				out.println("<script type=\"text/javascript\">");
			       out.println("alert('Entered');");
			       out.println("</script>");
			       Thread.sleep(10000);
				RequestDispatcher rd=request.getRequestDispatcher("addbus.html");
				rd.forward(request,response);
			}
			else{
				PrintWriter out=response.getWriter();
				out.println("<script type=\"text/javascript\">");
			       out.println("alert('Not Entered');");
			       out.println("</script>");
			       RequestDispatcher rd=request.getRequestDispatcher("admin.html");
				rd.include(request, response);
			}
		}
		catch(Exception x){
			System.out.println(x.toString());
		}

	}

}
