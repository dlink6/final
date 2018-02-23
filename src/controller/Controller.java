package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import approval.action.CommandResetA_num_d_seq;
import approval.db.ApprovalDAO;
import command.CommandAction;
public class Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	
	private Map commandHandlerMap = new java.util.HashMap();
	public void init(ServletConfig config) throws ServletException{
		String configFile = config.getInitParameter("configFile");
		configFile = config.getServletContext().getRealPath(configFile);
		Properties prop = new Properties();
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(configFile);
			prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null) {
					fis.close();
				}
			}catch(Exception e) {
		}
		Iterator keyIter = prop.keySet().iterator(); 
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName =prop.getProperty(command);
			try {
				Class handlerClass = Class.forName(handlerClassName);
				Object handlerInstance = handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		}
		
		//臾몄꽌踰덊샇 珥덇린�솕 & �궗吏곷맂�궗�엺 �궘�젣
		try {
			
	      final long timeInterval = 3600000;
	      SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	      Date date_ =new Date(System.currentTimeMillis());
	      final String date =df.format(date_);
	     
	      
	      Runnable runnable = new Runnable() {
	         public void run() {
	            while(true) {
	              
	               ApprovalDAO.getInstance().deleteEmp(date);
	               new CommandResetA_num_d_seq().deleteA_num_d();
	               try {
	                  Thread.sleep(timeInterval);
	               }catch(Exception e) {
	                  e.printStackTrace();
	               }
	            }
	      }
	      };
	      Thread thread = new Thread(runnable);
	      thread.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = null;
		CommandAction com =null;
		
		try {
			String command = request.getRequestURI();
			command = command.substring(request.getContextPath().length());
			com =(CommandAction) commandHandlerMap.get(command);
			
			view = com.execute(request, response);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher dispathcer = request.getRequestDispatcher(view);
		dispathcer.forward(request, response);
			
	}
}
