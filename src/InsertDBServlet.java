import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertDBServlet
 */
@WebServlet("/InsertDBServlet")
public class InsertDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String JDBCUrl = "jdbc:mysql://ee417.crxkzf89o3fh.eu-west-1.rds.amazonaws.com:3306/testdb";
        String username = "ee417";
        String password = "ee417";
        PrintWriter out = response.getWriter();
        try {
            System.out.println("\nConnecting to the SSD Database......");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(JDBCUrl, username, password);
        }
        catch (Exception e) {
            System.out.println("\nAn error has occurred during the connection phase!  This is most likely due to your CLASSPATH being set wrong and the"
                    + "  classes unable to be found.  Otherwise the database itself may be down.  Try telneting to port 3306 and see if it is up!");
            e.printStackTrace();
            System.exit(0);
        }   

	 try {
		  PreparedStatement pstmt = con.prepareStatement("INSERT INTO testdb.customers (id,surname,firstname,email,country,phone) VALUES (?,?,?,?,?,?)");
				  pstmt.clearParameters();       // Clears any previous parameters
				  pstmt.setInt(1, 3063);
				  pstmt.setString(2, "Darcy");
				  pstmt.setString(3, "Simon");
				  pstmt.setString(4, "darcys@marconi.com");
				  pstmt.setString(5, "United States");
				  pstmt.setString(6, "+44 213 5553343");
				  pstmt.executeUpdate();

		 System.out.println("\nConnection Successful..... creating statement....");
     	     stmt = con.createStatement();
	     rs = stmt.executeQuery("SELECT * FROM testdb.customers");
	     response.setContentType("text/html");
	     out.println("\n <html><body><table border='1'>");
	   out.println("<tr>\n"
	   		+ "            <th>ID</th>\n"
	   		+ "            <th>Surname</th>\n"
	   		+ "            <th>First Name</th>\n"
	   		+ "            <th>Email</th>\n"
	   		+ "            <th>Country</th>\n"
	   		+ "            <th>Phone</th>\n"
	   		+ "         </tr>");
	     while (rs.next())
	     {   out.println("\n <tr>");
	     	out.println("<td>" + rs.getInt("id") + "<td/>");
	     	out.println("<td>" + rs.getString("surname") + "<td/>");
	     	out.println("<td>" + rs.getString("firstname") + "<td/>");
	     	out.println("<td>" + rs.getString("email") + "<td/>");
	     	out.println("<td>" + rs.getString("country") + "<td/>");
	     	out.println("<td>" + rs.getString("phone") + "<td/>");
	     	out.println("</tr>");
	     }
	     out.println("</table></body></html>");
	 }
        catch (SQLException e) {
	     System.out.println("\nAn error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
            while (e != null) {
	         System.out.println(e.getMessage());
                e = e.getNextException();
	     }
            System.exit(0);
        }

        finally {
	     try {    
	         if (rs != null) rs.close();
		 if (stmt != null) stmt.close();
		 if (con != null) con.close();
	     }
	     catch (Exception ex) {
	         System.out.println("An error occurred while closing down connection/statement"); 
            }
        }
		
	}

}
