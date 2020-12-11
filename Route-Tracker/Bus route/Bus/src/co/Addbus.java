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

@WebServlet("/addbus")
public class Addbus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			Connection con;
			con=Dbconnection.Getconnection();
			pst=con.prepareStatement("insert into addbus values(?,?,?,?,?)");
		}
		catch(Exception x){
			System.out.println(x.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from=request.getParameter("from");
		String to=request.getParameter("to");
		String na=request.getParameter("na");
		int fc=Integer.parseInt(request.getParameter("fc"));
		float dis=Float.parseFloat(request.getParameter("dis"));
		try{
			pst.setString(1,from);
			pst.setString(2,to);
			pst.setString(3,na);
			pst.setInt(4,fc);
			pst.setFloat(5,dis);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				PrintWriter out=response.getWriter();
				out.println("<script type=\"text/javascript\">");
			       out.println("alert('Bus Added');");
			       out.println("</script>");
			       Thread.sleep(50000);
				RequestDispatcher rd=request.getRequestDispatcher("admin.html");
				rd.forward(request,response);
			}
			else{
				PrintWriter out=response.getWriter();
				out.println("<script type=\"text/javascript\">");
			       out.println("alert('Not Added');");
			       out.println("</script>");
			       RequestDispatcher rd=request.getRequestDispatcher("addbus.html");
				rd.include(request, response);
			}
		}
		catch(Exception x){
			System.out.println(x.toString());
		}

	}

}
