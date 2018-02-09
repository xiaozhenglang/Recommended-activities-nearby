package rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entity.Item;
import algorithm.GeoRecommendation;

/**
 * Servlet implementation class RecommendationItem
 */
@WebServlet("/recommendation")
public class RecommendationItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendationItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		GeoRecommendation recommendation = new GeoRecommendation();
		List<Item> items = recommendation.recommendItems(userId, lat, lon);

		JSONArray result = new JSONArray();
		try {
			for (Item item : items) {
				result.put(item.toJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, result); 
//		response.addHeader("Acess-Control-Orgin","*");
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		
//		JSONArray array = new JSONArray();
//		try {
//			JSONObject obj1 = new JSONObject();
//			JSONObject obj2 = new JSONObject();
//			obj1.put("name", "abcd");
//			obj1.put("address", "San Franscico");
//			obj1.put("time", "01/01/2017");
//			array.put(obj1);
//			
//			
//			obj2.put("name", "1234");
//			obj2.put("address", "San Joes");
//			obj2.put("time", "01/02/2017");
//			array.put(obj2);
//		} catch(JSONException e) {
//			e.printStackTrace();
//		}
//		
//		out.print(array);
//		out.flush();
//		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
