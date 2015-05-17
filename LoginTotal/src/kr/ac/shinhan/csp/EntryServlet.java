package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EntryServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		
		Cookie[] cookies = req.getCookies();
		String token = "";
		
		if(cookies != null)
		{
		for(Cookie c : cookies)
		{
			if(c.getName().equals("login_token"))
			{
				token = c.getValue();
			}
		}
		}	
		
		PersistenceManager manager = MyPersistentManager.getManager();
		Query qry = manager.newQuery(UserLoginToken.class);
		
		qry.setFilter("token == tokenParam");
		qry.declareParameters("String tokenParam");
		
		List<UserLoginToken> tokenList = (List<UserLoginToken>) qry.execute(token);
		
		
		if(token.equals("") || tokenList.size() == 0)
		{
			resp.sendRedirect("login.html");
		}
		
		else
		{
			UserLoginToken ult = tokenList.get(0);
			String expireDate = ult.getExpireDate();
			String now = new Date().toString();
			
			if(expireDate.compareTo(now) > 0)
			{
				String newToken = UUID.randomUUID().toString();
				ult.setToken(newToken);
				Cookie cookie = new Cookie("login_token", newToken);
				resp.addCookie(cookie);
				
				MyPersistentManager.getManager();
			
				resp.sendRedirect("index.html");
			}
			else
				resp.sendRedirect("index.html");
		}
		}
	}

