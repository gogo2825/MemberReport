package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		boolean check = false;
		
		PersistenceManager pm = MyPersistentManager.getManager();
		Query qry = pm.newQuery(UserAccount.class);
		
		List<UserAccount> UserList = (List<UserAccount>) qry.execute();
		
		for(UserAccount ul:UserList)
		{	
			if(id.equals(ul.getUserID()))
			{
				check=true;			
			}
		}
			
		
		if(check==true)
		{
			resp.getWriter().println("<h1>" + "���̵� �ߺ�!" + "</h1>");
			resp.getWriter().println("<a href='login.html'>�α��� ȭ��</a>");
		}
		
		else
		{	
			UserAccount ua = new UserAccount(id, password, name);
			pm.makePersistent(ua);
	
			resp.getWriter().println("<h1>" + "ȸ�������� �Ϸ�Ǿ����ϴ�" + "</h1>");
			resp.getWriter().println("<a href='login.html'>�α��� ȭ��</a>");
		}
		resp.getWriter().println("</html>");
		resp.getWriter().println("</body>");
	}
}