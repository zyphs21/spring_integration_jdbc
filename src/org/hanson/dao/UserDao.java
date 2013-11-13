package org.hanson.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hanson.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userJdbcDao")
public class UserDao implements IUserDao
{
	private JdbcTemplate jdbcTemplate;
	
	@Resource   //���beans.xml�ļ���ע����Ҫ��DataSource����
	public void setDataSource(DataSource dataSource)
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void add(User user,int groupId)
	{
		jdbcTemplate.update("insert into user(username,password,nickname,groupId) value(?,?,?,?)",
				user.getUsername(),user.getPassword(),user.getNickname(),groupId);
	}

	@Override
	public void update(User user)
	{
		jdbcTemplate.update("update user set username=?,password=?,nickname=? where id=?",
				user.getUsername(),user.getPassword(),user.getNickname(),user.getId());
	}

	@Override
	public void delete(int id)
	{
		jdbcTemplate.update("delete from user where id=?",id);

	}

	@Override
	public void load(int id)
	{
		jdbcTemplate.update("");
	}

}
