# ProgramingProject_HITS-PageRankAlgo
The World Wide Web is expanding day by day and so is the amount of data available on web. In such a situation in order to trace relevant data, users mainly rely on varied search engines for finding suitable answers for their queries. This common trend has resulted in a rise in the number as well as use of different search engines. The present state of affairs necessitates comparison and analysis of different link analysis algorithms employed by search engines for ranking web pages against user queries. In this project, I intend to compare two popular web page ranking algorithms namely: HITS algorithm and PageRank algorithm. 

Implementation of Kleinberg’s HITS Algorithm, and Google’s PageRank algorithm.
•	HITS(Hyperlink-Induced Topic Search)also known as hubs and authorities is a link analysis algorithm that rates Web pages.
•	PageRank (PR) is an algorithm used by Google Search to rank web pages in their search engine results.

Problem Statement:
•	The two algorithms are iterative. At any iteration ‘i’ all pagerank values are computed using results from iteration i-1. 
•	The initialvalue helps us to set-up the initial values of iteration 0 as needed.
•	In PageRank, parameter d is set to 0.85. 
•	The PageRank PR(A) of vertex A depends on the PageRanks of vertices T1,….,Tm incident to A, i.e. pointing to A. 
•	The pageranks at iteration i use the pageranks of iteration t-1 (synchronous update). Thus PR(A)  on the left in the PageRank equation is for iteration i, but all PR (Ti) values are from the previous iteration t -1. 
•	We run the ’algorithm’ either for a fixed number of iterations and iterations determines that, or for a fixed errorrate (an alias for iterations); an iterations equal to 0 corresponds to a default errorrate of 10^-5. A -1, -2, etc , -6 for iterations becomes an errorrate of 10^ -1;10^-2,….,10^-6 respectively. 
•	At iteration t when all authority/hub/PageRank values have been completed, we compare for every vertex the current and the previous iteration values. If the difference is less than errorrate for EVERY VERTEX, then and only then can we stop at iteration t.
•	Argument initialvalue sets the initial vector values. If it is 0 they are initialized to 0, if it is 1 they are initialized to 1. If it is -1 they are initialized to 1/N, where N is the number of web-pages (vertices of the graph). If it is -2 they are initialized to 1/N^(1/2).
•	Argument filename describes the input (directed) graph and it has the following form. The first line contains two numbers: the number of vertices followed by the number of edges which is also the number of remaining lines.

Sample Input:

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
