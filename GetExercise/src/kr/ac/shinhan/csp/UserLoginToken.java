package kr.ac.shinhan.csp;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserLoginToken { //사용기한을 체크했을때 이 클래스에 정보를 넣어줌
	@PrimaryKey 
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key; //프라이머리 
	 	 
	@Persistent 
	private String token; 
	 	 
	@Persistent 
	private String Account;
	 	 
	@Persistent 
	private String expireDate;
	
	public UserLoginToken(String token, String Account, String expireDate) {
		super();
		this.token = token;
		this.Account = Account;
		this.expireDate = expireDate;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAccount() {
		return Account;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

}