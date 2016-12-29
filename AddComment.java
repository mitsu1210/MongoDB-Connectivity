

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       MyServlet mysrv = new MyServlet();

    public AddComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comment=request.getParameter("cmnt");		//takes comment from text-box
		String prodid=request.getParameter("fieldid");		//takes fieldid which is a hidden field to keep track of the productid
		
		int result = Integer.parseInt(prodid);
		System.out.println(result);
		
        MongoClient client = new MongoClient("localhost", 27017);  	
        BasicDBObject setNewFieldQuery = new BasicDBObject().append("$set", new BasicDBObject().append("comment", comment));
  	 	client.getDB("users").getCollection("Reviews").update(new BasicDBObject().append("Id", result), setNewFieldQuery);
		response.getWriter().append("Comment added!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
