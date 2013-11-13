package org.hanson.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hanson.model.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("groupJdbcDao")
public class GroupDao implements IGroupDao
{
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setDateSource(DataSource dataSource)
	{
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void add(final Group group)
	{
		/**
		 * 通过以下方法可以添加一个对象，并且获取这个对象的自动递增id
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException
			{
				String sql = "insert into group (name) value(?)";
				PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
				ps.setString(1, group.getName());
				return ps;
			}
		},keyHolder);
		group.setId(keyHolder.getKey().intValue());
	}

}
