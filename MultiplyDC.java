package assign2part1;

import java.util.Arrays;

public class MultiplyDC 
{
	int[] first;
	int[] second;
	int[] result;
	double time ;
	public MultiplyDC(int[] f, int[] s)
	{
		first = f;
		second = s;
		makeEqual(first, second); //make two integers equal
		time = System.nanoTime();
		result = multiply(first, second);
		time = (System.nanoTime() - time)/1000000;
		//System.out.println(Arrays.toString(result));
		//System.out.println(time);
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
	
	private int[] multiply(int[] u, int[] v)
	{
		int[] p, q, x, y, w, z, a, b, c, d, e, f, g, h;
		//if integer lengths are 1, 2, 3
		if(u.length <= 3)
		{
			return product(u, v);
		}
		else
		{
			//if integer lengths are odd
			if(u.length % 2 == 1)
			{
				p = preprocess(u);
				q = preprocess(v);
			}
			//if integer lengths are even
			else
			{
				p = u;
				q = v;
			}
			int n = p.length;
			
			x = left(p);			// p = xy
			y = right(p);
			w = left(q);			//q = wz
			z = right(q);
			
			//go through steps of algorithm
			a = add(x,y);
			b = add(w,z);
			c = multiply(a,b);
			d = multiply(x,w);
			e = multiply(y,z);
			f = subtract(c, add(d,e));
			g = power(d,n);
			h = power(f, n/2);
			return add(g,add(h,e));
		}	
	}
	
	//appends on zero at the beginning of odd length integer
	private int[] preprocess(int[] u)
	{
		int[] result = new int[u.length+1];
		result[0] = 0;
		for(int i =0;i<u.length;i++)
		{
			result[i+1] = u[i];
		}
		return result;
	}
	//multiplies two integers using usual multiplication
	private int[] product(int[] u, int[] v)
	{
		int a, b, c;
		//convert arrays of digits to integer values
		a = convert(u);
		b = convert(v);
		//find product of integer values 
		c = a*b;
		//convert product avlue to array of digits
		return convert(c);
	}
	
	//converts an array of integers to integer value
	private int convert(int[] u)
	{
		int n = u.length, sum = 0, power = 1;
		//go through digits , multiply by powers of 10, sum up products
		for(int i=n-1;i>=0;i--)
		{
			sum = sum + u[i]*power;
			power = power*10;
		}
		return sum;
	}
	
	//converts integer to an array, assumes integer has at most 6 digits
	private int[] convert(int n)
	{
		int i, remainder;
		int[] result = {0,0,0,0,0,0};
		//convert integer to array of digits from right to left
		i = 5;
		while(n>0)
		{
			remainder=n%10;
			result[i] = remainder;
			n = n/10;
			i--;
		}
		return result;
	}
	
	//returns the left half of an even length integer
	private int[] left(int[] u)
	{
		int n = u.length;
		//create array of half length
		int[] result = new int[n/2];
		//copy left half of integer
		for(int i=0;i<n/2;i++)
		{
			result[i] = u[i];
		}
		return result;
	}
	
	private int[] right(int[] u)
	{
		int n = u.length;
		//create right half of integer
		int[] result = new int[n/2];
		for(int i=0;i<n/2;i++)
		{
			result[i] = u[n/2+i];
		}
		return result;
	}
	
	//adds 2 integers, length of result will be one more than maximum 
	//of lengths of two integers
	private int[] add(int[] u, int[] v)
	{
		int i, j, k, size, sum, carry, remainder;
		
		//find maximum length of two integers
		if(u.length >= v.length)
		{
			size = u.length;
		}
		else
		{
			size = v.length;
		}
		
		//create array with length maximum+1
		int[] result = new int[size+1];
		//start adding from the right end
		i = u.length - 1;
		j = v.length - 1;
		k = result.length -1;
		carry = 0;
		//add digits from right to left
		while(k >= 0)
		{
			if(i>=0 && j>=0)
			{
				sum = u[i] + v[j] + carry;
			}
			else if(i >= 0)
			{
				sum = u[i] + carry;
			}
			else 
			{
				sum = carry;
			}
			remainder = sum % 10;
			carry = sum/10;
			result[k] = remainder;
			i--;j--;k--;
		}
		return result;
	}
	
	private int[] subtract(int[] u, int[] v)
	{
		int i, j, k, size;
		int[] w = new int[u.length];
		for(i=0;i<u.length;i++)
		{
			w[i] = u[i];
		}
		//find maximum of lengths of two integers
		if(w.length >= v.length)
		{
			size = w.length;
		}
		else
		{
			size = v.length;
		}
		//create array with length maximum
		int[] result = new int[size];
		//start subtracting from the right end
		i = w.length - 1;
		j = v.length - 1;
		k = result.length - 1;
		//subtract digits from rights to left
		while(k >= 0)
		{
			if(i >= 0 && j >= 0)
			{
				if(w[i] >= v[j])
				{
					result[k] = w[i] - v[j];
				}
				else
				{
					result[k] = 10 + w[i] - v[j];
					w[i-1] = w[i-1] - 1;
				}
			}
			else if (i >= 0)
			{
				result[k] = w[i];
			}
			else
			{
				result[k] = 0;
			}
			i--; j--; k--;
		}
		return result;
	}
	
	private int[] power(int[] u, int n)
	{
		int size = u.length;
		int[] result = new int[size+n];
		for(int i=0;i<size;i++)
		{
			result[i] = u[i];
		}
		for(int i=size;i<size+n;i++)
		{
			result[i] = 0;
		}
		return result;
	}
}
