package org.hanson.template;

/**
 * ���ڼ̳�ʵ��ģ�����ģʽ
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
	 * ��ģ�淽������һ�ֺ����������Ӻ��������Ӻ�������������ʵ�������ͨ��һЩ����������ģ���е�����
	 * @return
	 */
	public abstract boolean isLog();
	
	public void execute()
	{
		beginConnection();
		
		if(isLog())
		{
			System.out.println("��������־");
		}
		
		run();
		
		closeConnection();
	}
}
