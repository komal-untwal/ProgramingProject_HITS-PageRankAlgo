
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.*;

public class Hits {

	static int iterations, initialval;
	static double errorrate = 0.00001; 
	static int n, m; // No of vertices & edges
	static double[] hub0,hub_t,hub_prev;
	static double[] auth0,auth_t,auth_prev;
	static int[][] A;
		
	
public static void main(String args[])
{
	//Arguments Check
	
		if(args.length != 3)
		{
			System.out.println("Invalid arument List! Arguments should be in format : <iterations> <initialvalue> <filename>");
			System.exit(0);
		}
		
		try{
		iterations = Integer.parseInt(args[0]);
		initialval = Integer.parseInt(args[1]);
		}catch(NumberFormatException nm){
			System.out.println("Please Enter Valid Integer value for Iterations & Initial Values!");
			System.exit(0);
		}

		String file_ = args[2];
		
		
		if (!(initialval >= -2 && initialval <= 1)) {
			System.out.println("Initial value must be -2, -1, 0, or 1!");
			System.exit(0);
		}
		Scanner reader;
		try {
			reader = new Scanner(new FileInputStream(file_));
			
			try {
			if(reader.hasNextLine()) {    
				 String[] dimentions = reader.nextLine().split(" ");
				 n = Integer.parseInt(dimentions[0]);
				 m = Integer.parseInt(dimentions[1]);
			 }
			//initialize matrix, hub & auth vectors.
			A = new int[n][n];
			hub0 = new double[n];
			auth0 = new double[n];
			
			
			
			String[] nodes = new String[2];
			while(reader.hasNext())
				{
					//2nd line
					nodes = reader.nextLine().split(" ");
					int i = Integer.parseInt(nodes[0]);
					int j = Integer.parseInt(nodes[1]);
					A[i][j] = 1;
				}
				//print matrix
				// for(int i=0;i<n;i++)
				// {
				// 	for(int j=0;j<n;j++)
				// 		{System.out.print(A[i][j] + " ");}
				// 	System.out.println();
				// }
				// System.out.println();

			}catch(NumberFormatException e1)
			{
				
				System.out.println(e1);
				System.out.println("Please input valid Integer number for Vertices & edges of graph!");//
				System.exit(0);
			}	
		}
		catch(FileNotFoundException fs)
			{
				System.out.println(fs);
				System.exit(0);
			}
		catch(Exception e3)
		{
			//System.out.println("In exception e1");
			System.out.println(e3);
		}
		
		//Initial Values of Hub & Auth
		
		if(initialval == -2)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k] = 1.0 / Math.sqrt(n);
				auth0[k] = 1.0 / Math.sqrt(n);
			}
		}
		else if(initialval == -1)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k] = 1.0 / n;
				auth0[k] = 1.0 / n;
			}
		}
		else if(initialval == 0)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k]= 0;
				auth0[k]= 0;
			}
		}
		else if(initialval == 1)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k] = 1;
				auth0[k] =  1;
			}
		}
	
		//Iterations
		
		hub_t = new double[n];
		hub_prev = new double[n];
		auth_t= new double[n];
		auth_prev = new double[n];
		
		//if number of vertices > 10
		if(n > 10)
		{
			//set iteration value default to 0
			iterations = 0;
			//set initial value default to -1
			for(int k=0; k<n; k++)
			{
				hub_t[k]= 1.0 / n;
				auth_t[k] =  1.0 / n;
				hub_prev[k]=  hub_t[k];
				auth_prev[k] = auth_t[k];
			}
			
			int iter_counter = 0;
			do
			{
				for(int i=0; i<n; i++)
				{
					auth_prev[i] = auth_t[i];
					hub_prev[i]=  hub_t[i];
				}
				
				vectorScaling(auth_t,hub_t);
				iter_counter++;
			}
			while(!check_error_rate(auth_prev,auth_t) || !check_error_rate(hub_prev,hub_t));
			System.out.println("Iter: " + iter_counter);
			for (int i=0; i<n; i++)
				System.out.printf(" A/H[%d]=%.7f/%.7f\n",i,Math.round(auth_t[i]*10000000.0)/10000000.0,Math.round(hub_t[i]*10000000.0)/10000000.0);
			
			return;
			
			
		}
		
		//else if n<10
		for(int k=0; k<n; k++)
		{
			hub_t[k]= hub0[k];
			auth_t[k] =  auth0[k];
			hub_prev[k]=  hub_t[k];
			auth_prev[k] = auth_t[k];
		}
		
		//Base iteration
		System.out.print("Base: 0 :");
		for (int i = 0; i < n; i++) {
			System.out.printf(" A/H[%d]=%.7f/%.7f", i,auth0[i], hub0[i]);
		}
		
		if(iterations>0)
		{
			for(int i=0; i<iterations; i++)
			{
				vectorScaling(auth_t,hub_t);
				System.out.println();
				System.out.print("Iter: " + (i+1) + " :");
				for(int k=0; k<n; k++)
				{
					System.out.printf(" A/H[%d]=%.7f/%.7f",k,Math.round(auth_t[k]*10000000.0)/10000000.0,Math.round(hub_t[k]*10000000.0)/10000000.0);	
				}
			}
		}
		
		else
		{
			if(iterations<0) errorrate = Math.pow(10, iterations);

			int iter_counter = 0;
			do
			{
				for(int i=0; i<n; i++)
				{
					auth_prev[i] = auth_t[i];
					hub_prev[i]=  hub_t[i];
				}
				
				vectorScaling(auth_t,hub_t);
				iter_counter++;
				
				System.out.println();
				System.out.print("Iter: " + iter_counter + " :");
				for (int i=0; i<n; i++)
					System.out.printf(" A/H[%d]=%.7f/%.7f",i,Math.round(auth_t[i]*10000000.0)/10000000.0,Math.round(hub_t[i]*10000000.0)/10000000.0);
				
			}
			while(!check_error_rate(auth_prev,auth_t) || !check_error_rate(hub_prev,hub_t));
			
		}
		System.out.println();
				
		
}
	
public static void vectorScaling(double[] auth_t,double[] hub_t)
{
	double  auth_sum = 0.0, hub_sum= 0.0;
	double  auth_sqr_sum = 0.0, hub_sqr_sum= 0.0;

	//Authority vector
	for(int i=0; i<n; i++) auth_t[i]=0.0;
	
	for(int j=0; j<n; j++)
	{
		for(int i=0; i<n; i++)
		{
			if (A[i][j] == 1) { auth_t[j] += hub_t[i]; }
		}
	}
	
	//hub vector
	for(int i=0; i<n; i++) hub_t[i]=0.0;
		
	for(int j=0; j<n; j++)
	{
		for(int i=0; i<n; i++)
		{
			if (A[j][i] == 1) { hub_t[j] += auth_t[i]; }
		}
	}
	
	//scaling Authority vector
	for(int i =0; i<n; i++) {
		auth_sum += auth_t[i] * auth_t[i];
	}
	auth_sqr_sum =  Math.sqrt(auth_sum);
	
	for(int i =0; i<n; i++) {
		auth_t[i] = auth_t[i] / auth_sqr_sum;
	}	
	
	//scaling Hub vector
	for(int i =0; i<n; i++) {
		hub_sum += hub_t[i] * hub_t[i];
	}
	hub_sqr_sum =  Math.sqrt(hub_sum);
		
	for(int i =0; i<n; i++) {
		hub_t[i] = hub_t[i] / hub_sqr_sum;
	}	

}

public static boolean check_error_rate(double[] previous,double[] current)
{

for(int i =0; i<n; i++)
{
	if(Math.abs(current[i]-previous[i]) > errorrate)
		return false;
}
return true;
}
	

}


