package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadTeamMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Long longKey = Long.parseLong(req.getParameter("Key"));
		
		PersistenceManager pm = MyPersistentManager.getManager();
		TeamMember tm = pm.getObjectById(TeamMember.class,longKey);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>��������</h1>");
		resp.getWriter().println("<table border='1'");
		resp.getWriter().println("<tr>");
		resp.getWriter().println("<form method ='POST' action = '/updateMember?Key="+ tm.getKey()+"'>");
		resp.getWriter().println("<tr><td>�̸� : <input type = 'text' name= 'name' value='"+tm.getName()+"'></td></tr>");
		resp.getWriter().println("<tr><td>�й� : <input type = 'text' name= 'hb' value='"+tm.gethb()+"'></td></tr>");
		resp.getWriter().println("<tr><td>��ȭ��ȣ : <input type = 'text' name= 'pn' value='"+tm.getpn()+"'></td></tr>");
		resp.getWriter().println("<tr><td>�����ּ� : <input type = 'text' name= 'mail' value='"+tm.getMail()+"'></td></tr>");
		resp.getWriter().println("<tr><td>īī���� ���̵� : <input type = 'text' name= 'katok' value='"+tm.getKatok()+"'></td></tr>");
		resp.getWriter().println("<tr><td>���忩�� : <input type = 'checkbox' name= 'check' value='ok'></td></tr>");
		resp.getWriter().println("<tr><td>Github id : <input type = 'text' name= 'gitid' value='"+tm.getGitid()+"'></td></tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("<input type='submit' value='��������'>");
		resp.getWriter().println("</form>");
		
		
		
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
				
	}
}
