package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UpdateMember extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");


		Long longKey = Long.parseLong(req.getParameter("Key"));

		PersistenceManager pm = MyPersistentManager.getManager();
		TeamMember tm = pm.getObjectById(TeamMember.class,longKey);
		
		String name = req.getParameter("name");
		String hb = req.getParameter("hb");
		String pn = req.getParameter("pn");
		String mail = req.getParameter("mail");
		String katok = req.getParameter("katok");
		String check = req.getParameter("check");
		String gitid = req.getParameter("gitid");

		tm = new TeamMember(name, hb, pn, mail, katok, check, gitid);
		
		tm.setName(name);
		tm.sethb(hb);
		tm.setpn(pn);
		tm.setMail(mail);
		tm.setKatok(katok);
		tm.setCheck(check);
		tm.setGitid(gitid);
		pm.close();
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>�����Ϸ�!!</h1>");
		resp.getWriter().println("<table border = 1>");
		resp.getWriter().println("<tr>");
		resp.getWriter().println("<form method='Get' action='/retrieveTeamMember'");
		resp.getWriter().println("<tr><th> �̸�</th><th>" + name + "</th></tr>");
		resp.getWriter().println("<tr><th> �й�</th><th>" + hb + "</th></tr>");
		resp.getWriter().println("<tr><th> ��ȭ��ȣ</th><th>" + pn + "</th></tr>");
		resp.getWriter().println("<tr><th> �̸���</th><th>" + mail + "</th></tr>");
		resp.getWriter().println("<tr><th> īī����</th><th>" + katok + "</th></tr>");
		if(check == null)
			resp.getWriter().println("<tr><th>���忩��</th><th>����</th></tr>");
			else
			resp.getWriter().println("<tr><th>���忩��</th><th>����</th></tr>");
		
		resp.getWriter().println("<tr><th> GitHub ID</th><th>" + gitid + "</th></tr>");
		resp.getWriter().println("<tr><td> <input type='submit' value='�������Ʈ'></td></tr>");
		resp.getWriter().println("<td> <a href ='index.html'>ó������ </a></td>");
		resp.getWriter().println("</form>");
		resp.getWriter().println("</tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
