package lab1_bolkunov_java;

public enum PipeCount //ДопПеречисл
{
	Single (1), 
	Double (2), 
	Triple (3),
	Quadruple (4);
	
	private int count;
	
	PipeCount(int cnt)
	{
		this.count = cnt;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public static int getCount(PipeCount pc)
	{
		return pc.count;
	}
	
	public static PipeCount getPipeCount(int cnt)
	{
		for (PipeCount pc : PipeCount.values())
			if(pc.count == cnt)
				return pc;
		return null;
	}
	
	@Override
	public String toString()
	{
		switch(count)
		{
			case 1:
				return "Одна труба";
			case 2:
				return "Две трубы";
			case 3:
				return "Три трубы";
			case 4:
				return "Четыре трубы";
			default:
				return "Ничего";
		}
	}
}