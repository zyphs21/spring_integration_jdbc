package org.hanson.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.hanson.dao.IGroupDao;
import org.hanson.dao.IUserDao;
import org.hanson.model.Group;
import org.hanson.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 当使用以下注释之后，就可以直接在Test中进行依赖注入，省去要创建Beanfactory获得bean的麻烦
 */
//让JUnit运行在spring的测试环境中
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml") //加载beans.xml文件
public class TestJdbc
{
	@Resource(name="userJdbcDao")
	private IUserDao userJdbcDao; //这里的userJdbcDao要与UserDao这个类声明的Repository名字一样
	
	@Resource(name="groupJdbcDao")
	private IGroupDao groupJdbcDao;
	
	@Test
	public void testAdd()
	{
//		Group g = new Group();
//		g.setName("管理员");
//		groupJdbcDao.add(g);
		User u = new User("hanson","1234","zhang");
		userJdbcDao.add(u,1);
	}
	
	@Test
	public void testUpdate()
	{
		User u = new User("zhang","1234","远");
		u.setId(1);
		userJdbcDao.update(u);
	}
	
	@Test
	public void testDelete()
	{
		userJdbcDao.delete(1);
	}
	
	@Test
	public void testLoad()
	{
		User u = userJdbcDao.load(2);
		System.out.println(u.getNickname() + "u.getGroup().getName()");
	}
	
	@Test
	public void testList()
	{
		List<User> us = userJdbcDao.list("select t1.id groupId,t1.*,t2.* from user t1 left join group t2(t1.groupId=t2.groupId)", null);
		for(User u:us)
		{
			System.out.println(u);
		}
	}

}
