package assign2part1;
import java.io.*;
import java.util.Arrays;

public class Part1
{	
	public static void main(String[] args)
	{
		//The name of the file to open
		String fileName = "Test/file1";
		int[][] fileResult = readFile(fileName);
		
		MultiplyDC multdc = new MultiplyDC(fileResult[0], fileResult[1]);
		MultiplyReg multrg = new MultiplyReg(fileResult[0],fileResult[1]);
		System.out.println("input parameters of file 1: "+convert(fileResult[0]) + " " + convert(fileResult[1]));
		System.out.println("multiplied by divide and conquer result: " + convert(multdc.result));
		System.out.println("it takes " + multdc.time + " milliseconds");
		System.out.println("multiplied by regular algorithm result: " + convert(multrg.result));
		System.out.println("it takes " + multrg.time + " milliseconds");
		System.out.println();
		
		fileName = "Test/file2";
		fileResult = readFile(fileName);
		
		multdc = new MultiplyDC(fileResult[0],fileResult[1]);
		multrg = new MultiplyReg(fileResult[0],fileResult[1]);
		System.out.println("input parameters of file 2: "+convert(fileResult[0]) + " " + convert(fileResult[1]));
		System.out.println("multiplied by divide and conquer result: " + convert(multdc.result));
		System.out.println("it takes " + multdc.time + " milliseconds");
		System.out.println("multiplied by regular algorithm result: " + convert(multrg.result));
		System.out.println("it takes " + multrg.time + " milliseconds");
		
		System.out.println();
		SpeedTest st = new SpeedTest();
	}
	
	public static int[][] readFile(String fileName)
	{
		int[][] result = new int[2][];
		int index = 0;
		// The name of the file to open
		// This will reference one line at a time
		String line = null;
		try 
		{
			// FileReader reads text files in the default encoding
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null)
			{
				if(notEmpty(line))
				{
					result[index] = new int[line.length()];
					getResult(result[index], line);
					index ++;
				}
			}
			br.close();
		} 
		catch (FileNotFoundException ex) 
		{
			System.out.println("Unable to open file " + fileName);
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		return result;
	}
	
	public static boolean notEmpty(String line)		//check if line is empty
	{
		if(line.length() == 0 || line.charAt(0) == ' ')
		{
			return false;
		}
		return true;
	}
	
	public static void getResult(int[] target, String str)
	{
		for(int i=0;i<str.length();i++)
		{
			target[i] = Character.getNumericValue(str.charAt(i));
		}
	}
	public static String convert(int[] u)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<u.length;i++)
		{

			sb.append(u[i]);
		}
		return sb.toString();
	}
}
