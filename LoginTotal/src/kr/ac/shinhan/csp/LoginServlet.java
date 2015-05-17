package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			 throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		boolean termcheck = req.getParameter("remember") != null;
		boolean success = false;
		
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(UserAccount.class);
		
		List<UserAccount> UserList = (List<UserAccount>) qry.execute();
		
		if(UserList.size() ==0)
		{
			success = false;
		}
		
		for(UserAccount ul:UserList)
		{
			if(ul.getUserID().equals(id))
			{
				if(ul.getPassword().equals(password))
				{
					success = true;
				}
			}
		}
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		if(success==false)
		{	
			resp.getWriter().println("Fail to Login");
			resp.getWriter().println("<a href='login.html'>Login Again</a>");
		}
		
		else
		{
			if(termcheck==true)
			{
				String token = UUID.randomUUID().toString();
				Cookie cookie = new Cookie("login_token", token);
				cookie.setMaxAge(60*60*24*30);
				resp.addCookie(cookie);
				
				//save token to Database
				Calendar now = Calendar.getInstance();
				now.add(Calendar.DATE, 30);
				String expireDate = now.getTime().toString();
				UserLoginToken ult = new UserLoginToken(id, token, expireDate.toString());
			
				pm.makePersistent(ult);
				
				HttpSession session = req.getSession(true);
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("userloginID", id);
				
				resp.sendRedirect("/index.html");
			}
			
			else
			{
				HttpSession session = req.getSession(true);
				session.setMaxInactiveInterval(30*60);
				session.setAttribute("userloginID", id);
				
				resp.sendRedirect("/index.html");
			}
		}
			resp.getWriter().println("</html>");
			resp.getWriter().println("</body>");
		}
	}