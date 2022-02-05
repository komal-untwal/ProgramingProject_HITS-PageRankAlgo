package prp2_DSA;

import java.io.FileInputStream;
import java.util.Scanner;

public class Hits {

	static int iterations;
	static int initialval;
	static double errorrate = 0.00001; 
	static int n; // No of vertices
	static int m;
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
		
		iterations = Integer.parseInt(args[0]);
		initialval = Integer.parseInt(args[1]);
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
			A = new int[n][m];
			hub0 = new double[n];
			auth0 = new double[m];
			
			}
			catch(Exception e1)
			{
				//System.out.println("In exception e1");//*****check
				System.out.println(e1);
			}	
			
			try {
				String[] nodes = new String[2];
				while(reader.hasNext())
				{
					//2nd line
					nodes = reader.nextLine().split(" ");
					int i = Integer.parseInt(nodes[0]);
					int j = Integer.parseInt(nodes[1]);
					A[i][j] = 1;
				}
			}catch(Exception e2)
			{
				//System.out.println("In exception e2");//*****check
				System.out.println(e2);
				
			}
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
				hub0[k]= auth0[k] =  1.0 / Math.sqrt(n);
			}
		}
		else if(initialval == -1)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k]= auth0[k] =  1.0 / n;
			}
		}
		else if(initialval == 0)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k]= auth0[k] =  0;
			}
		}
		else if(initialval == 1)
		{
			for(int k=0; k<n; k++)
			{
				hub0[k]= auth0[k] =  1;
			}
		}
	
		//Iterations
		
		hub_t = hub_prev = auth_t= auth_prev = new double[n];
		
		//if number of vertices > 10
		if(n > 10)
		{
			//set iteration value default to 0
			iterations = 0;
			//set initial value default to -1
			for(int k=0; k<n; k++)
			{
				hub_t[k]= auth_t[k] =  1.0 / n;
				hub_prev[k]=  hub_t[k];
				auth_prev[k] = auth_t[k];
			}
			
			int iter_counter = 0;
			do
			{
				for(int i=0; i<n; i++)
				{
					hub_prev[i]=  hub_t[i];
					auth_prev[i] = auth_t[i];
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
		System.out.print("Base:    0 :");
		for (int i = 0; i < n; i++) {
			System.out.printf(" A/H[%d]=%.7f/%.7f", i,auth0[i], hub0[i]);
		}
		
		if(iterations>0)
		{
			for(int i=0; i<n; i++)
			{
				vectorScaling(auth_t,hub_t);
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
					hub_prev[i]=  hub_t[i];
					auth_prev[i] = auth_t[i];
				}
				
				vectorScaling(auth_t,hub_t);
				iter_counter++;
				
				//System.out.println();
				System.out.println("Iter: " + iter_counter + " :");
				for (int i=0; i<n; i++)
					System.out.printf(" A/H[%d]=%.7f/%.7f\n",i,Math.round(auth_t[i]*10000000.0)/10000000.0,Math.round(hub_t[i]*10000000.0)/10000000.0);
				
			}
			while(!check_error_rate(auth_prev,auth_t) || !check_error_rate(hub_prev,hub_t));
			
		}
		
}
	
public static void vectorScaling(double[] auth,double[] hub)
{
	double  auth_sqr_sum = 0.0, hub_sqr_sum= 0.0;

	//Authority vector
	for(int i=0; i<n; i++) auth[i]=0.0;
	
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<n; j++)
		{
			if (A[j][i] == 1) { auth[i] += hub[j]; }
		}
	}
	
	//hub vector
	for(int i=0; i<n; i++) hub[i]=0.0;
		
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<n; j++)
		{
			if (A[i][j] == 1) { hub[i] += auth[j]; }
		}
	}
	
	//scaling Authority vector
	for(int i =0; i<n; i++) {
		auth_sqr_sum = auth[i] * auth[i];
	}
	auth_sqr_sum =  Math.sqrt(auth_sqr_sum);
	
	for(int i =0; i<n; i++) {
		auth[i] = auth[i] / auth_sqr_sum;
	}	
	
	//scaling Hub vector
	for(int i =0; i<n; i++) {
		hub_sqr_sum = hub[i] * hub[i];
	}
	hub_sqr_sum =  Math.sqrt(hub_sqr_sum);
		
	for(int i =0; i<n; i++) {
		hub[i] = hub[i] / hub_sqr_sum;
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


