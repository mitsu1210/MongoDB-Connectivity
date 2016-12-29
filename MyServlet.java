import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int Ido = 0;
    /**
     * Default constructor. 
     */
    public MyServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DB db = null;
		MongoClient client = new MongoClient("localhost", 27017);
		db = client.getDB("users");
		String connectPoint = client.getConnectPoint();
	
		String Keyword=request.getParameter("search");	//gets the keyword from search bar
		
		DBCollection collection = db.getCollection("Reviews");
		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put("Text",							//regex search in the TEXT field of mongodb
		new BasicDBObject("$regex", Keyword));

		DBCursor cursor = collection.find(regexQuery).limit(20); 
		
		PrintWriter out = response.getWriter();					//prints the value from the variable
		
				
		while (cursor.hasNext()) {
		
		DBObject obj = cursor.next();								//stores the field in variable for each document
		Ido = (int)obj.get("Id");
        String ProductId = (String)obj.get("ProductId");
        String UserId = (String)obj.get("UserId");
        String ProfileName = (String)obj.get("ProfileName");
        int HelpfulnessNumerator = (int)obj.get("HelpfulnessNumerator");
        int HelpfulnessDenominator = (int)obj.get("HelpfulnessDenominator");
        int Score = (int)obj.get("Score");
        int Time = (int)obj.get("Time");
        String Summary = (String)obj.get("Summary");
        String Text = (String)obj.get("Text");
        String comment = (String)obj.get("comment");  
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
       
        out.println( docType + "<html>\n" 							
        		 +       "<head>"
        		 +        "<title>Amazon Reviews</title>"
        		 +       "<meta charset='utf-8'>"
        		 +      "<meta name='viewport' content='width=device-width, initial-scale=1'>"
        		 +       "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
        		 +       "<link rel='stylesheet' href='Cssfile.css' type='text/css'/>"
        		 +       "<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>"
        		 +       "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" 
        		 +     "</head>" 
        		 +"<body style='background-color: Gray'>"
     		   
        		 +"<div class='container'>"
        		   +"<div class='row'>"
        		     +"<div class='col-sm-8'>"
        		       +"<h3></h3>"
        		       +"<div class='well' id='holder'>"
        		           +"<table class='table table-hover'>"
        		           +"<tr>"
        		               +"<td><b> Product ID </b></td>"
        		               +"<td>"+ Ido + "</td>"
        		           +"</tr>"
        		           
        		           +"<tr>"
        		               +"<td><b> Name </b></td>"
        		               +"<td>" + ProfileName + "</td>"
        		           +"</tr>"
        		           
        		           +"<tr>"
        		               +"<td><b> Score: </b></td>"
        		               +"<td>" + Score + "</td>"
        		           +"</tr>"
        		           
        		           
        		           +"<tr>"
        		               +"<td><b> Summary: </b></td>"
        		               +"<td>" + Summary + "</td>"
        		           +"</tr>"

        		           +"<tr>"
        		               +"<td><b> Review </b></td>"
        		               +"<td>" + Text + "</td>"
        		           +"</tr>"
        		           
 							 + "<form action='AddComment' method='GET'>"
        					 + "<input type = 'hidden' name = 'fieldid' value =" + Ido + ">"
        					 + "<input type='text' name='cmnt'>\t \t "
        					 + "<input type='submit' name='cmntbt' value='Comment' />"
        					 +"</form>"
        				  
        		           
        		       +"</table>"
        		       +"</div>"
        		     +"</div>"
        		   +"</div>"
        		     
        		 +"</div>"
        		     
        		 +"</body>"
        		 +"</html>"
				 );
        
        if(comment != null)
        {
        out.println(docType + "<html>\n" 							//Add comment button that calls Addcomment servlet on button click
       		 +       "<head>"
       		  +       "<meta charset='utf-8'>"
        		 +      "<meta name='viewport' content='width=device-width, initial-scale=1'>"
        		 +       "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
        		 +       "<link rel='stylesheet' href='Cssfile.css' type='text/css'/>"
        		 +       "<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>"
        		 +       "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" 
       		 + " </head>"
       		 +		 "<body>"
       		+"<div class='container'>"
 		   +"<div class='row'>"
 		     +"<div class='col-sm-8'>"
       		+"<div class='well' id='holder'>"
		           +"<table class='table table-hover'>"
		  
       	  	 +		 "<tr>"
       		 +		 "<td><b> Comment </b></td>"
       		 +       "<td>" + comment + "</td>"
       		 +		 "</tr>"
       		
             +"</table>"
             + "</div>"
             +"</div>"
		     +"</div>"
		   +"</div>"
		 +	"</body>"
		 +"</html>"
       				);}
        
    }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	doGet(request, response);
	
	
			
	}

}
