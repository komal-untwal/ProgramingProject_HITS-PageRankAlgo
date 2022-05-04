
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.*;

public class Pgrk{

	static int iterations, initialval;
	static double errorrate = 0.00001; //default errorrate
	static double d = 0.85;
	static int n, m; // No of vertices & edges
	static double[] pr_prev, page_rank;
	static int[] c;
	static int[][] A;
	static boolean flag = true;
	
	public static void main(String args[])
	{
		//Arguments Check
		
			if(args.length != 3)
			{
				System.out.println("Invalid argument List! Arguments should be in format : <iterations> <initialvalue> <filename>");
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

				}catch(Exception e1)
				{
					
					System.out.println(e1);
					System.out.println("Please input valid Integer number for Vertices & edges of graph!");//
					System.exit(0);
				}

				c = new int[n];
				for (int i = 0; i < n; i++) {
					c[i] = 0;
					for (int j = 0; j < n; j++) {
						c[i] += A[i][j];
					}
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
			
			pr_prev = new double[n];
			page_rank = new double[n];
			//large graph
			if(n > 10)
			{
				//set iteration value default to 0
				iterations = 0;
				//set initial value default to -1
				for(int k=0; k<n; k++)
				{
					pr_prev[k]= 1.0 / n;
				}
				
				int iter_counter = 0;
				do
				{
					if(flag) flag = false;
					else{
						 for(int i=0;i<n;i++)
							{ pr_prev[i] = page_rank[i];}
					}
					for(int i=0; i<n; i++)
					{ page_rank[i]= 0.0; }
					
					for(int i=0;i<n;i++)
					{
						for(int j=0;j<n;j++)
						{
							if(A[j][i] == 1)
							  page_rank[i] += pr_prev[j]/c[j];
						}
					}
 
					
					for(int i=0;i<n;i++)
					{
						page_rank[i] = ((1-d)/n) + (d * page_rank[i]);
					}
					
					//for(int i=0;i<n;i++)
					//{ pr_prev[i] = page_rank[i];}
					
					iter_counter++;
				}
				while(!check_error_rate(pr_prev,page_rank));
				System.out.println("Iter : " + iter_counter);
				for (int i=0; i<n; i++)
					System.out.printf("P[" + i + "] = %.7f\n", Math.round(page_rank[i] * 10000000.0) / 10000000.0);
				
				return;
				
			}	
			
//Initial Values
			
			if(initialval == -2)
			{
				for(int k=0; k<n; k++)
				{
					pr_prev[k] = 1.0 / Math.sqrt(n);
				}
			}
			else if(initialval == -1)
			{
				for(int k=0; k<n; k++)
				{
					pr_prev[k] = 1.0 / n;
				}
			}
			else if(initialval == 0)
			{
				for(int k=0; k<n; k++)
				{
					pr_prev[k]= 0;
				}
			}
			else if(initialval == 1)
			{
				for(int k=0; k<n; k++)
				{
					pr_prev[k] = 1;
				}
			}
			
		//base value
		    System.out.print("Base : 0 : ");
			for (int i = 0; i < n; i++) {
				System.out.printf(" P[ " + i + "]=%.7f", Math.round(pr_prev[i] * 10000000.0) / 10000000.0);
			}	
			
		//iterations
		 if(iterations>0)
		 {
			 for(int i=0; i<iterations; i++)
					 {
				 
				 for(int j=0;j<n; j++) page_rank[j]=0.0;
				 
				 for(int j=0;j<n; j++)
				 { for(int k=0; k<n; k++)
					 { if(A[k][j] == 1) 
					 	{page_rank[j] += pr_prev[k]/c[k];}
					 }	
				 }
				 
				 for(int j=0;j<n;j++)
				 {  page_rank[j] = ((1-d)/n) + (d * page_rank[j]); }
					
				 System.out.println();
				 System.out.print("Iter : " + (i + 1) + " : ");
					for (int j = 0; j < n; j++) {
						System.out.printf(" P[ " + j + "]=%.7f", Math.round(pr_prev[j] * 10000000.0) / 10000000.0);
					}
					
			    for(int j=0; j<n; j++)
					{ pr_prev[j] = page_rank[j];}	
					 
					 }
			 System.out.println();
		 }else
		 {
			 if(iterations<0) errorrate = Math.pow(10, iterations);
			 int iter_counter = 0;
				do
				{
					if(flag) flag = false;
					else{
						 for(int i=0;i<n;i++)
							{ pr_prev[i] = page_rank[i];}
					}

					for(int i=0; i<n; i++)
					{ page_rank[i]= 0.0; }
					
					for(int i=0;i<n;i++)
					{
						for(int j=0;j<n;j++)
						{
							if(A[j][i] == 1)
							  page_rank[i] += pr_prev[j]/c[j];
						}
					}
					
					for(int i=0;i<n;i++)
					{
						page_rank[i] = ((1-d)/n) + (d * page_rank[i]);
					}
					
					//for(int i=0;i<n;i++)
					//{ pr_prev[i] = page_rank[i];}
					
				System.out.println();	
				System.out.print("Iter : " + (iter_counter+1) + " : ");
				for (int i=0; i<n; i++)
					System.out.printf(" P[" + i + "] = %.7f", Math.round(page_rank[i] * 10000000.0) / 10000000.0);
				iter_counter++;
				}
				while(!check_error_rate(pr_prev,page_rank));
				System.out.println();
				
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
