package org.hanson.dao;

import java.util.List;

import org.hanson.model.User;

public interface IUserDao
{
	public void add(User user,int groupId);
	public void update(User user);
	public void delete(int id);
	public User load(int id);
	public List<User> list(String sql,Object[] args);
}
