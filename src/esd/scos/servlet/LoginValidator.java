package esd.scos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginValidator
 */
@WebServlet("/LoginValidator")
public class LoginValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidator() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {	  	
    	doPost(req,resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
    		throws ServletException, IOException {
		username = req.getParameter("username");
		password = req.getParameter("password");
		String operation = req.getParameter("operation");  //login\register	
		try{  
            //中文乱码解决  
            resp.setContentType("text/html;charset=gbk");  
            PrintWriter pw = resp.getWriter();  
            JSONObject jsonObj = new JSONObject();
            
            if (operation.equals("login")){   //登录验证
            	 //返回Json数据
                if(authentication()) {
                	jsonObj.put("RESULTCODE" , 1);
                    pw.println(jsonObj.toString());
                    resp.getOutputStream().println("get");
                }
                else {
                	jsonObj.put("RESULTCODE" , 0);
                    pw.println(jsonObj.toString());
                    resp.getOutputStream().println("get");
                }
    		}
            else if (operation.equals("register")) {   //注册验证
            	if(!isOccupated()) {
                	jsonObj.put("RESULTCODE" , 1);
                    pw.println(jsonObj.toString());
                    resp.getOutputStream().println("get");
                }
                else {
                	jsonObj.put("RESULTCODE" , 0);
                    pw.println(jsonObj.toString());
                    resp.getOutputStream().println("get");
                }
            }             
        }  
        catch(Exception ex){  
            ex.printStackTrace();  
        }  		
		
	}
    
    private boolean authentication() {
    	
    	if(username.equals("SA18225475")&& password.equals("123456")) {
    		return true;
    	}
    	else return false;	
    }
    
private boolean isOccupated() {
    	
//    	if(username.equals("SA18225475")) {
//    		return true;
//    	}
//    	else return false;	
	return false;
    }
	
}
