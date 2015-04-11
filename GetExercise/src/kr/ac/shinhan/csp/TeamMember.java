package kr.ac.shinhan.csp;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;




@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TeamMember {

	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long Key;
	
	@Persistent
	private String name;		
	@Persistent
	private String hb;	
	@Persistent
	private String pn;
	@Persistent
	private String mail;
	@Persistent
	private String katok;	
	@Persistent
	private String check;
	@Persistent
	private String gitid;	
	public TeamMember(String name, String hb,String pn,String mail,String katok,String check,String gitid)
	{
		this.name= name;
		this.hb = hb;
		this.pn = pn;
		this.mail = mail;
		this.katok = katok;
		this.check = check;
		this.gitid = gitid;		
	}
	
	public String getpn() {
		return pn;
	}

	public void setpn(String pn) {
		this.pn = pn;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getKatok() {
		return katok;
	}

	public void setKatok(String katok) {
		this.katok = katok;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getGitid() {
		return gitid;
	}

	public void setGitid(String gitid) {
		this.gitid = gitid;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String gethb() {
		return hb;
	}

	public void sethb(String hb) {
		this.hb = hb;
	}

	public Long getKey() {
		return Key;
	}
	
	public void setKey(Long Key){
		this.Key = Key;
	}
	
}
