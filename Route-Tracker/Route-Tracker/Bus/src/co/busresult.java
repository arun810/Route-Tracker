package co;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findbus")
public class busresult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement pst=null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try{
			Connection con;
			con=Dbconnection.Getconnection();
			pst=con.prepareStatement("Select * from addbus where source=? and destination=?");
		}
		catch(Exception x){
			System.out.println(x.toString());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String so=request.getParameter("from");
		String des=request.getParameter("to");
		 PrintWriter out=response.getWriter();
         response.setContentType("text/html"); 
         out.println("<html><head><style>body{margin:0px;background: url(image/bus.gif);background-size: cover;}");
         out.println("#form{width:230px;margin:auto;margin-top:80px;background:linear-gradient(40deg,green,light green);text-align:center;font-size:25px;padding:30px;border-radius:30px;color:blue;box-shadow:0 0 20px white;");
         out.println("</style></head>");
         out.println("<body><form action='busresult.java' method='post'><div id='form'><table>");
		try{
			pst.setString(1,so);
			pst.setString(2,des);
			ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					String s = rs.getString("source");  
	                String de = rs.getString("destination");  
	                String bname= rs.getString("busname");
	                int fare = rs.getInt("farec");  
	                float dis = rs.getFloat("distance");  
	                String time= rs.getString("time");
	                out.println("<tr><td>"+s+" ====> "+de+"</td></tr>");
	                out.println("<tr><td>Bus Name ==> "+bname+"<td></tr>");
	                out.println("<tr><td>Fare Cost ==> "+fare+" Rs."+"</td></tr>");
	                out.println("<tr><td>Distance ==> "+dis+" KM"+"</td></tr>");
	                out.println("<tr><td>Time ==> "+time+"</td></tr>");
	                out.println("<tr><td><br/><input type='submit' value='Go for Booking' formaction='busDetail.html' class='btn_submit'></td>");
	                out.println("<td><input type='submit' value='Back' formaction='findbus.html' class='btn_submit'></td></tr>");
	                out.println("</table>");
	                out.println("</div></form></body></html>");
				}
			/*else{
				   out.println("<script type=\"text/javascript\">");
			       out.println("alert('Bus Not Available');");
			       out.println("</script>");
			      //RequestDispatcher rd=request.getRequestDispatcher("findbus.html");
				   //rd.forward(request,response);
			}*/
		}
		catch(Exception x){
			System.out.println(x.toString());
		}

	}
}
