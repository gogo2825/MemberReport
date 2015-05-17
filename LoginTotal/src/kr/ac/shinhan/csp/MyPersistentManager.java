package kr.ac.shinhan.csp;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class MyPersistentManager {	

	public static TeamMember addMember(String name, String hb,String pn,String mail,String katok,String check,String gitid)
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		TeamMember m = new TeamMember(name, hb, pn, mail, katok, check, gitid);
		pm.makePersistent(m);
		
		return m;
	}
	
	public static TeamMember getMember(Long Key)
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		TeamMember m = pm.getObjectById(TeamMember.class,Key);
		
		return m;
	}
	
	public static TeamMember updateMember(TeamMember newMember) {
		PersistenceManager pm = MyPersistentManager.getManager();
		TeamMember tm = MyPersistentManager.getMember(newMember.getKey());
		
		tm.setName(newMember.getName());
		tm.sethb(newMember.gethb());
		tm.setpn(newMember.getpn());
		tm.setMail(newMember.getMail());
		tm.setKatok(newMember.getKatok());
		tm.setCheck(newMember.getCheck());
		tm.setGitid(newMember.getGitid());
		pm.makePersistent(tm);
		
		return tm;
	}
	
	public static void deleteMember(Long Key)
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		TeamMember tm = MyPersistentManager.getMember(Key);
		pm.deletePersistent(tm);
	}
	
	public static List<TeamMember> getMemberByName(String name)
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		Query qry = pm.newQuery(TeamMember.class);
		qry.setFilter("name == nameParam");
		qry.declareParameters("String nameParam");
		
		List<TeamMember> memberList = (List<TeamMember>) qry.execute(name);
		
		return memberList;
	}
	
	
	public static List<TeamMember> getAllMembers()
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		Query qry = pm.newQuery(TeamMember.class);
		List<TeamMember> memberList = (List<TeamMember>) qry.execute();

		return memberList;
	}
	
	public static PersistenceManager getManager()
	{
		PersistenceManager pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
		return pm;
	}
}
