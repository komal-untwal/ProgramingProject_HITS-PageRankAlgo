# Kleinberg’s HITS Algorithm, and Google’s PageRank algorithm

The World Wide Web is expanding day by day and so is the amount of data available on web. In such a situation in order to trace relevant data, users mainly rely on varied search engines for finding suitable answers for their queries. This common trend has resulted in a rise in the number as well as use of different search engines. The present state of affairs necessitates comparison and analysis of different link analysis algorithms employed by search engines for ranking web pages against user queries. In this project, I intend to compare two popular web page ranking algorithms namely: HITS algorithm and PageRank algorithm. 

Implementation of Kleinberg’s HITS Algorithm, and Google’s PageRank algorithm.  
•	HITS(Hyperlink-Induced Topic Search)also known as hubs and authorities is a link analysis algorithm that rates Web pages.  
•	PageRank (PR) is an algorithm used by Google Search to rank web pages in their search engine results.  

# Problem Statement:  
•	Compute K iterations of the HITS & pagerank algorithm to rank web pages.
•	The two algorithms are iterative. At any iteration ‘i’ all pagerank values are computed using results from iteration i-1.   
•	The initialvalue helps us to set-up the initial values of iteration 0 as needed.   
•	We run both the ’algorithm’ either for a fixed number of iterations and iterations determines that, or for a fixed errorrate (an alias for iterations); an iterations equal to 0 corresponds to a default errorrate of 10^-5. A -1, -2, etc , -6 for iterations becomes an errorrate of 10^ -1;10^-2,….,10^-6 respectively.   
•	At iteration t when all authority/hub/PageRank values have been completed, we compare for every vertex the current and the previous iteration values. If the difference is less than errorrate for EVERY VERTEX, then and only then can we stop at iteration t.  
  
# HITS Algorithm: 
•	HITS is based on two basic terms: hubs and authorities to define a recursive relationship between webpages.   
•	HITS assigns two scores for each page: its authority, which estimates the value of the content of the page, and its hub value, which estimates the value of its links to other pages.

# Pagerank Algorithm:   
•	PageRank works by counting the number and quality of links to a page to determine a rough estimate of how important the website is.   
•	PageRank algorithm is based on idea of Random Surfing & given by the below equation.  
  PR(A) = (1-d)/n + d * {PR(T1)/C(T1) + PR(T2)/C(T2)+ ---- PR(Ti)/C(Ti)}   
•	Parameter d known as dumping factor is set to 0.85.   
•	The PageRank PR(A) of vertex A depends on the PageRanks of vertices T1,….,Tm incident to A, i.e. pointing to A.   
•	C(Ti) denotes the outdegree of page Ti.

# Input file & command-line arguments:
The input for both of the algorithm problems would be a file a containing a graph represented through an adjacency list representation   

* The command-line interface is as follows:  
% java program_name iterations initialvalue filename  
 
•	Argument iterations denotes the number of iterations if it is a positive integer or an errorrate for a negative or zero integer value.  
•	Argument initialvalue sets the initial vector values. If it is 0 they are initialized to 0, if it is 1 they are initialized to 1. If it is -1 they are initialized to 1/N, if it is -2 they are initialized to 1/N^(1/2),where N is the number of web-pages (vertices of the graph).    
•	Argument filename describes the input (directed) graph and it has the following form. The first line contains two numbers: the number of vertices followed by the number of edges which is also the number of remaining lines.   

Sample Input scenarios:

4 4
0 2
0 3
1 0
2 1
 
Execution command for Hits algorithm implementation:
java Hits 7 1 samplegraph2.txt
 
Output: 
Base: 0 : A/H[0]=1.0000000/1.0000000 A/H[1]=1.0000000/1.0000000 A/H[2]=1.0000000/1.0000000 A/H[3]=1.0000000/1.0000000
Iter: 1 : A/H[0]=0.5000000/0.8164966 A/H[1]=0.5000000/0.4082483 A/H[2]=0.5000000/0.4082483 A/H[3]=0.5000000/0.0000000
Iter: 2 : A/H[0]=0.3162278/0.9428090 A/H[1]=0.3162278/0.2357023 A/H[2]=0.6324555/0.2357023 A/H[3]=0.6324555/0.0000000
Iter: 3 : A/H[0]=0.1714986/0.9847319 A/H[1]=0.1714986/0.1230915 A/H[2]=0.6859943/0.1230915 A/H[3]=0.6859943/0.0000000
Iter: 4 : A/H[0]=0.0877058/0.9961165 A/H[1]=0.0877058/0.0622573 A/H[2]=0.7016464/0.0622573 A/H[3]=0.7016464/0.0000000
Iter: 5 : A/H[0]=0.0441081/0.9990249 A/H[1]=0.0441081/0.0312195 A/H[2]=0.7057297/0.0312195 A/H[3]=0.7057297/0.0000000
Iter: 6 : A/H[0]=0.0220863/0.9997559 A/H[1]=0.0220863/0.0156212 A/H[2]=0.7067618/0.0156212 A/H[3]=0.7067618/0.0000000
Iter: 7 : A/H[0]=0.0110472/0.9999390 A/H[1]=0.0110472/0.0078120 A/H[2]=0.7070205/0.0078120 A/H[3]=0.7070205/0.0000000
 
with errorate = -2: java Hits -2 1 samplegraph2.txt
output:
 
Base: 0 : A/H[0]=1.0000000/1.0000000 A/H[1]=1.0000000/1.0000000 A/H[2]=1.0000000/1.0000000 A/H[3]=1.0000000/1.0000000
Iter: 1 : A/H[0]=0.5000000/0.8164966 A/H[1]=0.5000000/0.4082483 A/H[2]=0.5000000/0.4082483 A/H[3]=0.5000000/0.0000000
Iter: 2 : A/H[0]=0.3162278/0.9428090 A/H[1]=0.3162278/0.2357023 A/H[2]=0.6324555/0.2357023 A/H[3]=0.6324555/0.0000000
Iter: 3 : A/H[0]=0.1714986/0.9847319 A/H[1]=0.1714986/0.1230915 A/H[2]=0.6859943/0.1230915 A/H[3]=0.6859943/0.0000000
Iter: 4 : A/H[0]=0.0877058/0.9961165 A/H[1]=0.0877058/0.0622573 A/H[2]=0.7016464/0.0622573 A/H[3]=0.7016464/0.0000000
Iter: 5 : A/H[0]=0.0441081/0.9990249 A/H[1]=0.0441081/0.0312195 A/H[2]=0.7057297/0.0312195 A/H[3]=0.7057297/0.0000000
Iter: 6 : A/H[0]=0.0220863/0.9997559 A/H[1]=0.0220863/0.0156212 A/H[2]=0.7067618/0.0156212 A/H[3]=0.7067618/0.0000000
Iter: 7 : A/H[0]=0.0110472/0.9999390 A/H[1]=0.0110472/0.0078120 A/H[2]=0.7070205/0.0078120 A/H[3]=0.7070205/0.0000000
Iter: 8 : A/H[0]=0.0055241/0.9999847 A/H[1]=0.0055241/0.0039062 A/H[2]=0.7070852/0.0039062 A/H[3]=0.7070852/0.0000000 
 
For n(number of nodes >10) : 
largegraph.txt:  

20 8
2 3
3 4
2 5
5 8
6 7
7 8
8 9
9 10

Command to implement HITS implementation: 
java Hits 15 -1 largegraph.txt

Output:

Iter: 17  
 A/H[0]=0.0000000/0.0000000  
 A/H[1]=0.0000000/0.0000000  
 A/H[2]=0.0000000/0.5773503   
 A/H[3]=0.4082483/0.0000044  
 A/H[4]=0.0000062/0.0000000    
 A/H[5]=0.4082483/0.5773503    
 A/H[6]=0.0000000/0.0000044   
 A/H[7]=0.0000062/0.5773503   
 A/H[8]=0.8164966/0.0000044  
 A/H[9]=0.0000062/0.0000044  
 A/H[10]=0.0000062/0.0000000  
 A/H[11]=0.0000000/0.0000000  
 A/H[12]=0.0000000/0.0000000  
 A/H[13]=0.0000000/0.0000000  
 A/H[14]=0.0000000/0.0000000  
 A/H[15]=0.0000000/0.0000000  
 A/H[16]=0.0000000/0.0000000  
 A/H[17]=0.0000000/0.0000000  
 A/H[18]=0.0000000/0.0000000  
 A/H[19]=0.0000000/0.0000000 
 
---------------------------------------------

Command to implement pagerank implementation: 

java Pgrk_8304 7 1 samplegraph2.txt.    
Base : 0 :  P[ 0]=1.0000000 P[ 1]=1.0000000 P[ 2]=1.0000000 P[ 3]=1.0000000  
Iter : 1 :  P[ 0]=1.0000000 P[ 1]=1.0000000 P[ 2]=1.0000000 P[ 3]=1.0000000  
Iter : 2 :  P[ 0]=0.8875000 P[ 1]=0.8875000 P[ 2]=0.4625000 P[ 3]=0.4625000  
Iter : 3 :  P[ 0]=0.7918750 P[ 1]=0.4306250 P[ 2]=0.4146875 P[ 3]=0.4146875  
Iter : 4 :  P[ 0]=0.4035313 P[ 1]=0.3899844 P[ 2]=0.3740469 P[ 3]=0.3740469  
Iter : 5 :  P[ 0]=0.3689867 P[ 1]=0.3554398 P[ 2]=0.2090008 P[ 3]=0.2090008  
Iter : 6 :  P[ 0]=0.3396239 P[ 1]=0.2151507 P[ 2]=0.1943194 P[ 3]=0.1943194  
Iter : 7 :  P[ 0]=0.2203781 P[ 1]=0.2026715 P[ 2]=0.1818401 P[ 3]=0.1818401  

java Pgrk 15 -1 largegraph.txt

Output:  
Iter : 6  
P[0] = 0.0075000  
P[1] = 0.0075000  
P[2] = 0.0075000  
P[3] = 0.0106875  
P[4] = 0.0165844  
P[5] = 0.0106875  
P[6] = 0.0075000  
P[7] = 0.0138750  
P[8] = 0.0283781  
P[9] = 0.0316214  
P[10] = 0.0343782  
P[11] = 0.0075000  
P[12] = 0.0075000  
P[13] = 0.0075000  
P[14] = 0.0075000  
P[15] = 0.0075000  
P[16] = 0.0075000  
P[17] = 0.0075000  
P[18] = 0.0075000  
P[19] = 0.0075000   

---------------------------------------------
