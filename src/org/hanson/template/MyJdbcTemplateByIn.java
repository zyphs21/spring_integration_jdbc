package org.hanson.template;

/**
 * 基于继承实现模版设计模式
 * @author handsome
 *
 */
public abstract class MyJdbcTemplateByIn
{
	private void beginConnection()
	{
		System.out.println("begin connection");
	}
	
	public void closeConnection()
	{
		System.out.println("close connection");
	}
	
	public abstract void run();
	
	/**
	 * 在模版方法中有一种函数叫做钩子函数，钩子函数的作用是让实现类可以通过一些方法来控制模版中的流程
	 * @return
	 */
	public abstract boolean isLog();
	
	public void execute()
	{
		beginConnection();
		
		if(isLog())
		{
			System.out.println("加入了日志");
		}
		
		run();
		
		closeConnection();
	}
}
