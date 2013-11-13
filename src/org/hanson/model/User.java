package org.hanson.model;

public class User
{
	private int id;
	private String username;
	private String password;
	private String nickname;
	private Group group;
	
	//两个构造方法，一个带参数一个不带参数，注意不要加入id因为这个在数据库中是主键，自动生成
	public User()
	{
		
	}
	public User(String username, String password, String nickname)
	{
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	
	public Group getGroup()
	{
		return group;
	}
	public void setGroup(Group group)
	{
		this.group = group;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	
}
