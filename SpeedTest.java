package assign2part1;

import java.util.Arrays;
import java.io.*;

public class SpeedTest 
{
	StringBuilder first = new StringBuilder("9");
	StringBuilder second = new StringBuilder("9");
	int size = first.length();
	MultiplyDC multdc;
	MultiplyReg multreg;
	
	public SpeedTest()
	{
		int[] firstArr = constructArr(first);
		int[] secondArr = constructArr(second);
		multdc = new MultiplyDC(firstArr, secondArr);
		multreg = new MultiplyReg(firstArr, secondArr);
		findSize();
	}
	
	private void findSize() 
	{
		for (int i=0; i < 300; i++)
		{
			this.first.append("9");
			this.second.append("9");
			int[] firstArr = constructArr(this.first);
			int[] secondArr = constructArr(this.second);
			double sumdc = 0;
			double sumreg = 0;
			for(int j=0;j<10;j++)
			{
				this.multdc = new MultiplyDC(firstArr, secondArr);
				this.multreg = new MultiplyReg(firstArr, secondArr);
				sumdc += multdc.time;
				sumreg += multreg.time;
			}
			try 
			{
				writeToFile(i, sumdc, sumreg);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private int[] constructArr(StringBuilder sb)
	{
		int[] result = new int[sb.length()];
		for(int i=0;i<sb.length();i++)
		{
			result[i] = Character.getNumericValue(sb.charAt(i));
		}
		return result;
	}
	
	private void writeToFile(int i, double sumdc, double sumreg) throws IOException
	{
		File outFile = new File("src/assign2part1/data.txt");
		FileWriter fWriter = new FileWriter(outFile, true);
		PrintWriter pWriter = new PrintWriter(fWriter);
		pWriter.println(i + ","+sumdc/10+","+sumreg/10);
		pWriter.close();
		fWriter.close();
	}
}
