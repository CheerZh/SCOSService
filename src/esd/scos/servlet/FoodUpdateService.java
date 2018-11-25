package esd.scos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FoodUpdateService
 */
@WebServlet("/FoodUpdateService")
public class FoodUpdateService extends HttpServlet {

	public FoodUpdateService() {
		super();
	}
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//中文乱码解决  
        resp.setContentType("text/html;charset=gbk");
		String [][] foods = getNewFoods();
		long t1=0,t2=0;

		try{  
			PrintWriter pw = resp.getWriter();
            pw.println(BulidJson(foods));	//用Json封装消息
//            pw.println("\n\n");
//            pw.println(BulidXml(foods));      //用Xml封装消息  
//          pw.println("\n\n");
//          
//          //测试Json封装时间
//          t1 = System.nanoTime() ;
//          for(int i=0;i<100;i++){
//             BulidJson(getNewFoods());
//          }
//          t2 = System.nanoTime() ;
//          double t = t2 - t1 ;
//          pw.println("封装Json时间:"+ t +"ns");
//          pw.println("\n\n");
//          
//          //测试xml封装时间
//          t1 = System.nanoTime() ;
//          for(int i=0;i<100;i++){
//             BulidXml(getNewFoods());
//          }
//          t2 = System.nanoTime() ;
//          t = t2 - t1 ;
//          pw.println("封装xml时间:"+ t +"ns");
//          pw.println("\n\n");
//          
//          //Json封装后数据长度与xml封装后数据长度
//          pw.println("Json长度："+BulidJson(getNewFoods()).length());
//          pw.println("\n\n");
//          pw.println("xml长度："+BulidXml(getNewFoods()).length());
            resp.getOutputStream().println("get");
            
        }  
        catch(Exception ex){  
            ex.printStackTrace();  
        }  			
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	private static String[][] getNewFoods(){
		
		String [][] newFoods = {{"黑山羊火锅","90.00","HOT_FOODS"},{"豌豆凉粉","20.00","COLD_FOODS"},
				{"玲珑醉蟹","60.00","SEA_FOODS"},{"荷花酿","40.00","DRINGS"}};
		return newFoods;
		
	}
	
	private static String BulidXml(String [][] foods) {
		StringBuilder sb= new StringBuilder();
		int count = foods.length;
		int i = 0;
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<count>").append(count+"").append("</count>");
		sb.append("<foods>");
		while(i<count) {
			for (String [] food : foods) {
				sb.append("<name>").append(food[0]).append("</name>");
				sb.append("<price>").append(food[1]).append("</price>");
				sb.append("<category>").append(food[2]).append("</category>");
			}
		i++;
		}
		sb.append("</foods>");
		return sb.toString();	
	}
	
	private static String BulidJson(String [][] foods) {  
        JSONObject foodsJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        foodsJson.element("count", foods.length);
        int i = 0;
        for(String [] food : foods) {
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("name",food[0]);
        	jsonObj.put("price", food[1]);
        	jsonObj.put("category", food[2]);
    		jsonArray.add(i++,jsonObj);
        }      
        foodsJson.element("jsonArray", jsonArray);
        return foodsJson.toString();
	}

}
