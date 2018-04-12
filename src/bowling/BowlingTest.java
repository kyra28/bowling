package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class bowlingTest {
	
	@Test
	public void bowlingTestSum() {
		Booling b = new Booling();
		b.add(10,0);
		b.add(5,5);
		b.add(10,0);
		b.add(3,1);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		assertEquals(94, b.mySum());
	}
	
	@Test
	public void bowlingTestSize() throws Exception {
		Booling b = new Booling(15);
		if (b.values.length>10)
			throw new Exception("10 groupes de valeurs max !");
	}
	
	@Test
	public void bowlingTestPositive() throws Exception {
		Booling b = new Booling(10);
		for (int i = 0 ; i<b.values.length ; i++)
		{
			for (int j =0;j<b.values[i].length;j++)
			{
				if (b.values[i][j]<0)
					throw new Exception("Chaque valeur de chaque tuple est positive ou nulle!");
			}
		}
	}
	
	@Test
	public void bowlingTest10() throws Exception {
		Booling b = new Booling(10);
		for (int i =0; i<b.values.length; i++)
		{
			int sommeTuple = 0;
			for (int j =0; j < b.values[i].length; j++)
			{
				sommeTuple += b.values[i][j];
			}
			if (sommeTuple>10)
				throw new Exception("somme tuple doit être inférieur à 10!");
		}
	}

	@Test
	public void bowlingTestScore() throws Exception {
		Booling b = new Booling();
		b.add(10,0);
		b.add(5,5);
		b.add(10,0);
		b.add(3,1);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.add(10,0);
		b.bonusStrike[0][0]=10;
		b.bonusStrike[0][1]=0;
		b.bonusStrike[1][0]=10;
		b.bonusStrike[1][1]=10;
		int score = b.calculScore();
		assert(score == 238);
	}
	
}
