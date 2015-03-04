public class Recursion
{
	public static long factorialI(long n)
	{
		long result = (n <= 0) ? 1 : n;
		for (long i = n - 1; i > 0; i--)
		{
			result *= i;
		}
		return result;
	}
	
	public static long factorialR(long n)
	{
		if (n == 0)
		{
			return 1;
		}
		else
		{
			return n * factorialR(n - 1);
		}
	}
	
	public static long fibI(long n)
	{
		if (n <= 0)
		{
			throw new java.lang.IllegalArgumentException("Invalid Argument; n must be greater than 0");
		}
		if (n == 1 || n == 2)
		{
			return 1;
		}
		else
		{
			long first = 1;
			long second = 1;
			long result = 1;
			for (long i = 3; i <= n; i++)
			{
				result = first + second;
				second = first;
				first = result;
			}
			return result;
		}
	}

	public static long fibR(long n)
	{
		if (n <= 0)
		{
			throw new java.lang.IllegalArgumentException("Invalid Argument; n must be greater than 0");
		}
		if(n == 1 || n == 2)
		{
			return 1;
		}
		else
		{
			return fibR(n - 1) + fibR(n - 2);
		}
	}
	
	public static int findMax(int[] array, int start, int end)
	{
		if (end - start == 0)
		{
			return 0;
		}
		
		if (end - start == 1) //base case
		{
			return array[start];
		}
		
		//divide-and-conquer
		int mid = (start + end) / 2;
		int maxLeft = findMax(array, start, mid);
		int maxRight = findMax(array, mid, end);

		//combine
		if (maxLeft > maxRight)
		{
			return maxLeft;
		}
		else
		{
			return maxRight;
		}
		
	}
	
	public static void main(String[] args)
	{
		java.util.Scanner scan = new java.util.Scanner(System.in);
		System.out.println("Please enter the number n : ");
		int n = scan.nextInt();
		System.out.println("Iterative factorial (n factorial) : " + factorialI(n));
		System.out.println("Recursive factorial (n factorial) : " + factorialR(n));
		System.out.println("Please enter the number n : (n value smaller than 45 recommended)");
		n = scan.nextInt();
		
		double time1 = (double) System.currentTimeMillis() / 1000.0;
		System.out.println("Iterative Fibonacci (n-th term) : " + fibI(n));
		double time2 = (double) System.currentTimeMillis() / 1000.0 - time1;
		System.out.println("Computation time : " + time2 + " seconds");
		
		time1 = (double) System.currentTimeMillis() / 1000.0;
		System.out.println("Recursive Fibonacci (n-th term) : " + fibR(n));
		time2 = (double) System.currentTimeMillis() / 1000.0 - time1;
		System.out.println("Computation time : " + time2 + " seconds");
		
		
		int[] array = {1, 5, 3, 8, 9, 7, 2};
		if (args.length > 0)
		{
			int[] temp = new int[args.length];
			try
			{
				for(int i = 0; i < args.length; ++i)
				{
					temp[i] = Integer.parseInt(args[i]);
				}
				array = temp; // exception safety
			} catch(NumberFormatException e)
			{
				System.err.println("Invalid command line array; using the default array...");
			}
		}
		System.out.println("Given array : " + java.util.Arrays.toString(array));
		System.out.println("The maximum is ");
		System.out.println(findMax(array, 0, array.length-1));
		
	}
}
