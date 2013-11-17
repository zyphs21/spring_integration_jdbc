package org.hanson.test;

import static org.junit.Assert.*;

import org.aspectj.bridge.Message;
import org.hanson.template.MessageDao;
import org.hanson.template.MyJdbcTemplateByIn;
import org.hanson.template.RoleDao;
import org.junit.Test;

public class TestTemplate
{
	@Test
	public void test01()
	{
		MyJdbcTemplateByIn mt = new RoleDao();
		mt.execute();
		MyJdbcTemplateByIn msgt = new MessageDao();
		msgt.execute();
	}
	
	@Test
	public void test02()
	{
		RoleDao rd = new RoleDao();
		rd.add(2);
	}
}
