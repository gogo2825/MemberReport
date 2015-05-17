package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class DeleteMember extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		
		Long longKey = Long.parseLong(req.getParameter("Key"));

		PersistenceManager pm =MyPersistentManager.getManager();
		TeamMember tm = pm.getObjectById(TeamMember.class,longKey);
		pm.deletePersistent(tm);
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<h1>멤버가 삭제되었습니다.!</h1>");
		resp.getWriter().println("<a href='index.html'>처음으로</a>");
		resp.getWriter().println("</table>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	}
}