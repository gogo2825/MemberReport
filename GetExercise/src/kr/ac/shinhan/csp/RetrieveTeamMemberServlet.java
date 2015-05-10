package kr.ac.shinhan.csp;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RetrieveTeamMemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		List<TeamMember> memberList = MyPersistentManager.getAllMembers();	
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
		
		String UserID = "";
		HttpSession session = req.getSession(false);
		
		if(session == null)
		{
			resp.getWriter().println("login�� �ʿ��մϴ�.");
		}
		
		else
		{
			UserID = (String) session.getAttribute("userloginID");
			resp.getWriter().println(UserID + "�� ȯ���մϴ�.");
		}
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>��� ����Ʈ</h1>");
		resp.getWriter().println("<table>");		
		
		resp.getWriter().println("<table border='1'>");
		resp.getWriter().println("<tr>");
		resp.getWriter().println("<td>�̸�</td>");
		resp.getWriter().println("<td>�й�</td>");
		resp.getWriter().println("<td>��ȭ��ȣ</td>");
		resp.getWriter().println("<td>�����ּ�</td>");
		resp.getWriter().println("<td>īī���� ���̵�</td>");
		resp.getWriter().println("<td>���忩��</td>");
		resp.getWriter().println("<td>GitHub���̵�</td>");
		resp.getWriter().println("<td>...</td>");
		resp.getWriter().println("</tr>");
		
		for(TeamMember tm : memberList)
		{
			resp.getWriter().println("<tr>");
			resp.getWriter().println("<td>" +"<a href = '/readTeamMember?Key="+tm.getKey()+"'>"+ tm.getName() +"</a></td>");
			resp.getWriter().println("<td>" + tm.gethb() +"</td>");
			resp.getWriter().println("<td>" + tm.getpn() +"</td>");
			resp.getWriter().println("<td>" + tm.getMail() +"</td>");
			resp.getWriter().println("<td>" + tm.getKatok() +"</td>");
			if(tm.getCheck() == null)
				resp.getWriter().println("<td>����ƴ� </td>");
			else
				resp.getWriter().println("<td>����</td>");
			resp.getWriter().println("<td>" + tm.getGitid() +"</td>");
			resp.getWriter().println("<td>"+ "<a href = '/deleteMember?Key=" + tm.getKey() + "'>����</a></td>");
			resp.getWriter().println("</tr>");
		}		
		resp.getWriter().println("</table>");
		resp.getWriter().println("<a href = 'index.html'>ó������</a>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
		
	}
}

