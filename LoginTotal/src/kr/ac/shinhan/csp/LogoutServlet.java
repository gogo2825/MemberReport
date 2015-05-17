package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(UserLoginToken.class);
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		Cookie[] cookieList = req.getCookies();
		
		if(cookieList != null)
		{
			for(Cookie c : cookieList)
			{
				if(c.getName().equals("login_token"))
				{
					List<UserLoginToken> ult = (List<UserLoginToken>) qry.execute(c.getValue());
					for(UserLoginToken u : ult)
					{
						pm.deletePersistent(u);
					}
					c.setValue(null);
					c.setMaxAge(0);
					resp.addCookie(c);
					resp.sendRedirect("login.html");
				}
			}
		}
		
		else
		{
			resp.sendRedirect("login.html");
		}
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	
	}

}
