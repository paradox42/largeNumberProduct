package assign2part1;

import java.util.Arrays;

public class MultiplyReg 
{
	int[] first;
	int[] second;
	int[] result;
	double time ;
	public MultiplyReg(int[] f, int[] s)
	{
		first = f;
		second = s;
		//makeEqual(first, second);
		time = System.nanoTime();
		result = multiply(first, second);
		time = (System.nanoTime() - time)/1000000;
	}
	private void makeEqual(int[] first, int[] second)			//make 2 arrays equal length
	{
		int len1 = first.length;
		int len2 = second.length;
		if(len1 > len2)
		{
			this.second = fillUp(second, len1-len2);
		}
		else if(len1 < len2)
		{
			this.first = fillUp(first, len2-len1);
		}
	}
	
	private int[] fillUp(int[] arr, int dif)					//fill up the array with 0s at begining of array 
	{
		int[] newArr = new int[arr.length+dif];
		for(int i=0;i<newArr.length;i++)
		{
			if(i < dif)
			{
				newArr[i] = 0;
			}
			else
			{
				newArr[i] = arr[i-dif];
			}
		}
		return newArr;
	}
	
	private int[] multiply(int[] a, int[] b)
	{
		int[] result = null;
		int factorMaxLen = a.length + b.length;
		int[][] factors = new int[b.length][factorMaxLen];
		//calculate all the factors
		for(int i=b.length-1;i>=0;i--)
		{
			int counter = factorMaxLen-1-(b.length-1-i);				//counter is the position of the factor
			for(int j=a.length-1;j>=0;j--)
			{
				int num = a[j]*b[i];
				factors[i][counter] = factors[i][counter]+num;
				if(factors[i][counter] >= 10)
				{
					factors[i][counter-1] = factors[i][counter-1] + factors[i][counter]/10;
					factors[i][counter] = factors[i][counter]%10;
				}
				counter--;
			}
			//System.out.println(Arrays.toString(factors[i]));
		}
		//adding factors together
		result = addFactors(factors);
		return result;
	}
	
	private int[] addFactors(int[][] factors)
	{
		int[] result = new int[factors[0].length];
		for(int i=factors[0].length-1;i>=0;i--)
		{
			for(int j=0;j<factors.length;j++)
			{
				result[i] += factors[j][i];
				if(result[i]>=10)
				{
					result[i-1] += result[i]/10;
					result[i] = result[i]%10;
				}
			}
		}
		//System.out.println(Arrays.toString(result));
		return result;
	}
}
