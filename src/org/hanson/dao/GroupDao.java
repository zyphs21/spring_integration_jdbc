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
		 * ͨ�����·����������һ�����󣬲��һ�ȡ���������Զ�����id
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
