package bowling;

public class Bowling {
	
	public int[][] values; 
	int ind;
	public int[] bonusSpare;
	public int[][] bonusStrike;

	public Bowling()
	{
		values = new int[10][2];
		bonusSpare = new int[2];
		bonusStrike = new int[2][2];
		ind = 0;
	}
	
	public Bowling(int size)
	{
		values = new int[10][2];
		bonusSpare = new int[2];
		bonusStrike = new int[2][2];
		ind = 0;
		for(int i=0; i<10; i++)
		{
			int[] tuple = new int[2];
			
			tuple[0] = (int)Math.random()*10;
			int v = (int)Math.random()*10;
			while(tuple[0] + v > 10)
			{
				v = (int)Math.random()*10;
			}
			tuple[1] = v;
			//add(tuple);
			values[i] = tuple;
		}
		
		int sommeTupleFinal = values[9][0] + values[9][1];
		if(sommeTupleFinal == 10) // dernier coup = strike ou spare ?
		{
			if(values[9][0] == 10) // dernier coup = strike  ?
			{
				bonusStrike[0][0] = (int)Math.random()*10;
				int v = (int)Math.random()*10;
				while(bonusStrike[0][0] + v > 10)
				{
					v = (int)Math.random()*10;
				}
				bonusStrike[0][1] = v;
				
				if(bonusStrike[0][0]==10) // premier coup bonus = strike ?
				{
					bonusStrike[1][0] = (int)Math.random()*10;
					v = (int)Math.random()*10;
					while(bonusStrike[1][0] + v > 10)
					{
						v = (int)Math.random()*10;
					}
					bonusStrike[1][1] = v;
				}
			}
			
			else { // dernier coup = spare
				bonusSpare[0] = (int)Math.random()*10;
				int v = (int)Math.random()*10;
				while(bonusSpare[0] + v > 10)
				{
					v = (int)Math.random()*10;
				}
				bonusSpare[1] = v;
			}
				
		}
	}
	
	public void add(int value1, int value2)
	{
		int [] tuple = new int[2];
		tuple[0] = value1;
		tuple[1] = value2;
		if(ind<10 && value1 >= 0 && value2 >= 0)
			values[ind] = tuple;
		ind++;
	}
	
	public int mySum()
	{		
		int result=0;
		for (int i =0 ; i<values.length ; i++)
		{
			int sommeTuple=0;
			for (int j =0;j<values[i].length;j++)
			{
				sommeTuple+=values[i][j];
			}
			result+=sommeTuple;
		}
		return result;
	}
	
	public int calculScore()
	{
		int score = 0;
		int sommeTuple;
		for(int i = 0; i < values.length; i++)
		{
			sommeTuple = values[i][0] + values[i][1];
			if(sommeTuple == 10) // {10,-} ou {4,6} Strike ou spare ?
			{
				if(values[i][0]==10 && i != 9) // {10,-} Strike ! et pas le dernier coup
				{
					if(values[i+1][0]==10 && i<8) // ...{10,-},{10,-},{4,6}... Le prochain est un strike ?
						score += 10 + values[i+1][0] + values[i+2][0]; // 10 + 10 + 4 OUI
					if(values[i+1][0]==10 && i==8) // ...{10,-},{10,-}] Le prochain est un strike et c'est le dernier coup ?
						score += 10 + values[i+1][0] + bonusStrike[0][0]; // ...{10,-},{10,-}] Bonus[{5,0}... -> 10 + 10 + 5
  					else // ...{10,-},{4,1}...
						score += 10 + values[i+1][0] + values[i+1][1]; // 10 + 4 + 1 NON
				}
				else if(values[i][0]==10 && i == 9) // ...{10,-}] Strike ! et dernier coup
				{
					if(bonusStrike[0][0]==10) // ... {10,-}] + bonus[{10,-}{2,3}] ou ... {10,-}] + bonus[{10,-}{10,-}] 
					{
						score += 20 + bonusStrike[1][0]; // 10 + 10 + 2 ou 10 + 10 + 10
					}
				}
				else if(i != 9) // {4,6} spare ! et pas le dernier coup
					// ...{4,6},{10,-}... ou ...{4,6},{5,2}...
					score += sommeTuple + values[i+1][0]; // 4 + 6 + 10 ou  4 + 6 + 5
				else // {4,6} spare ! et dernier coup
					score += sommeTuple + bonusSpare[0];
			}
			else // ...{4,2},{5,1}...
				score += sommeTuple;
		}
			
		return score;
	}
}
