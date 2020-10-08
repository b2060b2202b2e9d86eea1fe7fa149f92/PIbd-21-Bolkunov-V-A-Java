package lab1_bolkunov_java;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Pipes //КлассДоп
{	
	private Random rnd;
	private PipeCount pipeCount;
	private int pipeRadius;
	
	private Pipes()
	{
		rnd = new Random();
		pipeRadius = rnd.nextInt(4) + 12; 
	}
	
	public Pipes(int count)
	{
		this();
		setPipeCount(count);
	}
	
	public Pipes(PipeCount count)
	{
		this();
		setPipeCount(count);
	}
	
	public int getPipeCount()
	{
		return pipeCount.getCount();
	}
	
	public void setPipeCount(int count)
	{
		pipeCount = PipeCount.getPipeCount(count);
	}
	
	public void setPipeCount(PipeCount pipeCnt)
	{
		pipeCount = pipeCnt;
	}
	
	public void Draw(Graphics g, MotorShip transport)
	{
		if(pipeCount != null)
			for (int i = 0; i < getPipeCount()+1; i++)
			{
				//PIPES
				if (transport.getHelicopterPad() & i == (int)getPipeCount() / 2) continue;
	
				int pipePointX, pipePointY;
				if (transport.getLastDirection() == Direction.Left || transport.getLastDirection()  == Direction.Right)
				{
					pipePointX = transport.getPosX() + (-getPipeCount() / 2 + i) * pipeRadius * 3; 
					pipePointY = transport.getPosY();
				}
				else
				{
					pipePointX = transport.getPosX(); 
					pipePointY = transport.getPosY() + (-getPipeCount() / 2 + i) * pipeRadius * 3;
				}
	
				g.setColor(transport.getAdditionalColor());
				g.fillOval(pipePointX - pipeRadius, pipePointY - pipeRadius, pipeRadius * 2, pipeRadius * 2);
				g.setColor(Color.black);
				g.drawOval(pipePointX - pipeRadius, pipePointY - pipeRadius, pipeRadius * 2, pipeRadius * 2);
	
				//SMOKE
				if (transport.getSmoke())
				{
					int despertionRadius = 15;
					for (int j = 0; j < 5 + rnd.nextInt(10); j++)
					{
						int rad = 2 + rnd.nextInt(10);
						g.setColor(Color.gray);
						g.fillOval(pipePointX - despertionRadius + rnd.nextInt(despertionRadius + 1),
							pipePointY - despertionRadius + rnd.nextInt(despertionRadius + 1), rad, rad);
					}
				}
			}
	}
}
