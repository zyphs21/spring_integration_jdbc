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
 * ��ʹ������ע��֮�󣬾Ϳ���ֱ����Test�н�������ע�룬ʡȥҪ����Beanfactory���bean���鷳
 */
//��JUnit������spring�Ĳ��Ի�����
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml") //����beans.xml�ļ�
public class TestJdbc
{
	@Resource(name="userJdbcDao")
	private IUserDao userJdbcDao; //�����userJdbcDaoҪ��UserDao�����������Repository����һ��
	
	@Resource(name="groupJdbcDao")
	private IGroupDao groupJdbcDao;
	
	@Test
	public void testAdd()
	{
//		Group g = new Group();
//		g.setName("����Ա");
//		groupJdbcDao.add(g);
		User u = new User("hanson","1234","zhang");
		userJdbcDao.add(u,1);
	}
	
	@Test
	public void testUpdate()
	{
		User u = new User("zhang","1234","Զ");
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
