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
		
		//����������  
        resp.setContentType("text/html;charset=gbk");
		String [][] foods = getNewFoods();
		long t1=0,t2=0;

		try{  
			PrintWriter pw = resp.getWriter();
            pw.println(BulidJson(foods));	//��Json��װ��Ϣ
//            pw.println("\n\n");
//            pw.println(BulidXml(foods));      //��Xml��װ��Ϣ  
//          pw.println("\n\n");
//          
//          //����Json��װʱ��
//          t1 = System.nanoTime() ;
//          for(int i=0;i<100;i++){
//             BulidJson(getNewFoods());
//          }
//          t2 = System.nanoTime() ;
//          double t = t2 - t1 ;
//          pw.println("��װJsonʱ��:"+ t +"ns");
//          pw.println("\n\n");
//          
//          //����xml��װʱ��
//          t1 = System.nanoTime() ;
//          for(int i=0;i<100;i++){
//             BulidXml(getNewFoods());
//          }
//          t2 = System.nanoTime() ;
//          t = t2 - t1 ;
//          pw.println("��װxmlʱ��:"+ t +"ns");
//          pw.println("\n\n");
//          
//          //Json��װ�����ݳ�����xml��װ�����ݳ���
//          pw.println("Json���ȣ�"+BulidJson(getNewFoods()).length());
//          pw.println("\n\n");
//          pw.println("xml���ȣ�"+BulidXml(getNewFoods()).length());
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
		
		String [][] newFoods = {{"��ɽ����","90.00","HOT_FOODS"},{"�㶹����","20.00","COLD_FOODS"},
				{"������з","60.00","SEA_FOODS"},{"�ɻ���","40.00","DRINGS"}};
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
