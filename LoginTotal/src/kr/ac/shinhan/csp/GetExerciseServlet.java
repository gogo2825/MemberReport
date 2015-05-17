package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GetExerciseServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	
		String name = req.getParameter("name");
		String hb = req.getParameter("hb");
		String pn = req.getParameter("pn");
		String mail = req.getParameter("mail");
		String katok = req.getParameter("katok");
		String check = req.getParameter("check");
		String gitid = req.getParameter("gitid");		
		
		TeamMember tm = new TeamMember(name,hb,pn,mail,katok,check,gitid);
		PersistenceManager pm = MyPersistentManager.getManager();
		pm.makePersistent(tm);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain");
	
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>등록완료</h1>");
		resp.getWriter().println("<table border = 1>");
		resp.getWriter().println("<tr><th>이름</th> <th>" + name +"</th></tr> ");
		resp.getWriter().println("<tr><th>학번</th> <th>" + hb +"</th></tr>");
		resp.getWriter().println("<tr><th>전화번호</th><th>" + pn +"</th></tr>");
		resp.getWriter().println("<tr><th>메일주소</th><th>" + mail +"</th></tr>");
		resp.getWriter().println("<tr><th>카카오톡 아이디</th><th>" + katok +"</th></tr>");
		if(check == null)
			resp.getWriter().println("<tr><th>팀장여부</th><th>팀원</hd></tr>");
			else
			resp.getWriter().println("<tr><th>팀장여부</th><th>팀장</td></tr>");
		resp.getWriter().println("<tr><th>GitHub ID</th><th>" + gitid +"</th></tr>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("등록되었습니다.<br>");
		resp.getWriter().println("<a href='index.html'>처음으로</a>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}
