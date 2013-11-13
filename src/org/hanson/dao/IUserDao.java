package org.hanson.dao;

import org.hanson.model.User;

public interface IUserDao
{
	public void add(User user,int groupId);
	public void update(User user);
	public void delete(int id);
	public void load(int id);
}
