package org.hanson.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hanson.model.Group;
import org.hanson.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	public User load(int id)
	{
		String sql = "select t1.id groupId,t1.*,t2.* from user t1 left join group t2(t1.groupId=t2.groupId) where t1.id=?";
		/**
		 * ��һ��������sql���
		 * �ڶ���������sql����еĲ���ֵ����Ҫ����һ����������
		 * ������������һ��RowMapper,���rowMapper�������һ����������ݿ��ֶεĶ�Ӧ
		 * ʵ�����rowMapper��Ҫʵ��mapRow��������rs���������ͨ��rs�����еĻ�ȡ���ݿ���ֶ�
		 * �����������ڸ�dao�лᱻ�ظ�ʹ�ã�����ͨ���ڲ��������������Ҫ�������ڲ���
		 */
		User u = (User)jdbcTemplate.queryForObject(sql,new Object[]{id},new UserMapper());
		return u;
	}

	@Override
	public List<User> list(String sql,Object[] args)
	{
		String sqlCount = "Select count(*) from user";
		
		//��ȡ����ֵ
		int count = jdbcTemplate.queryForInt(sqlCount);
		System.out.println(count);
		String nCount = "select nickname from user";
		
		//��ȡString���͵��б�
		List<String> ns = jdbcTemplate.queryForList(nCount,String.class);
		for(String n:ns)
		{
			System.out.println("---->"+n);
		}
		
		String tSql = "select username,nickname from user";
		
		//�޷�ȡ��user
		/*
		List<User> us = jdbcTemplate.queryForList(tSql, User.class);
		for(User u :us)
		{
			System.out.println(u);
		}*/
		
		//��������Ҳ�޷�����
		/*
		List<Object[]> os = jdbcTemplate.queryForList(tSql,Object[].class);
		for(Object[] oo:os)
		{
			System.out.println(oo[0]+"," + oo[1]);
		}*/
		
		List<User> us = jdbcTemplate.query(tSql,new RowMapper<User>()
				{
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException
					{
						User u = new User();
						u.setNickname(rs.getString("nickname"));
						u.setUsername(rs.getString("username"));
						return null;
					}
			
				});
		return jdbcTemplate.query(sql, args, new UserMapper());
	}
	
	private class UserMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException
		{
				Group g = new Group();
				g.setName(rs.getString("name"));
				g.setId(rs.getInt("groupId"));
				User u = new User();
				u.setGroup(g);
				u.setId(rs.getInt("groupId"));
				u.setNickname(rs.getString("nickname"));
				u.setPassword(rs.getString("password"));
				u.setUsername(rs.getString("username"));
				return u;
		}
		
	}

}
