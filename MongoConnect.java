import com.mongodb.*;
import com.mongodb.MongoClient;

public class MongoConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB db = null;
		MongoClient client = new MongoClient("localhost", 27017);
		db = client.getDB("users");
		String connectPoint = client.getConnectPoint();
		System.out.println(connectPoint);
		

		DBCollection coll = db.getCollection("Reviews");
		
		System.out.println("Count" + db.getCollection("Reviews").count());
		client.close();
		/*BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put("Text", new BasicDBObject("$regex", "Tree"));

		System.out.println(regexQuery.toString());

		DBCursor cursor = collection.find(regexQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());

		}*/
	}

}