package org.hanson.template;

public class MyJdbcTemplate
{
	private void beginConnection()
	{
		System.out.println("begin connection");
	}
	
	private void closeConnection()
	{
		System.out.println("close connection");
	}
	
	/**
	 * ���÷���,����һ�����Ӻ����Ľ��
	 */
	public void execute(MyCallback call)
	{
		beginConnection();
		closeConnection();
	}
	
	/**
	 * ������Ҫʵ�ֵķ�����������ģ����
	 */
	public void add(final int id)
	{
		execute(new MyCallback()
		{
			@Override
			public void doInTemplate()
			{
				System.out.println("add " + id);
			}
			
		});
	}
	public void delete(final int id)
	{
		execute(new MyCallback()
		{
			@Override
			public void doInTemplate()
			{
				System.out.println("delete "+ id);
			}
		});
	}
}
