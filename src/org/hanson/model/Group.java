package org.hanson.model;

public class Group
{
	private String name;
	private int id;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Override
	public String toString()
	{
		return "Group [name=" + name + ", id=" + id + "]";
	}
	
	
}
