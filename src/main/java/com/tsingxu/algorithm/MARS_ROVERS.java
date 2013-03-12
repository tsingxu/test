package com.tsingxu.algorithm;

/**
 * <b>the solution for MARS ROVERS problem</b>
 * 
 * <ol>
 * <li>Generally, it is a simulation related problem</li>
 * </ol>
 * 
 * @since Mar 12, 2013 8:08:19 PM
 * @author xuhuiqing
 */
public class MARS_ROVERS
{
	private static final int[][] step = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static class Rover
	{
		private int x;
		private int y;
		private int direction;

		public Rover(int x, int y, int direction)
		{
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		public void spin(char cmd)
		{
			if (cmd != 'R' && cmd != 'L')
			{
				return;
			}

			if (cmd == 'R')
			{
				this.direction = (this.direction + 1) % 4;
			}
			else
			{
				this.direction = (this.direction + 3) % 4;
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

	}

}
