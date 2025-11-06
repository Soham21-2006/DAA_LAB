// Application: Building minimum-cost road or network connections between cities
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}
class Subset {
    int parent, rank;
}
public class KruskalAlgorithm {
    int vertices, edges;
    Edge[] edgeList;

    KruskalAlgorithm(int v, int e) {
        vertices = v;
        edges = e;
        edgeList = new Edge[e];
        for (int i = 0; i < e; i++)
            edgeList[i] = new Edge();
    }
    int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    void kruskalMST() {
        Edge result[] = new Edge[vertices];
        int e = 0;
        int i = 0;

        for (i = 0; i < vertices; i++)
            result[i] = new Edge();

        Arrays.sort(edgeList);

        Subset subsets[] = new Subset[vertices];
        for (i = 0; i < vertices; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        i = 0;
        while (e < vertices - 1) {
            Edge nextEdge = edgeList[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }
        System.out.println("Edges in the Minimum Cost Spanning Tree (Kruskal):");
        int minCost = 0;
        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + " - " + result[i].dest + " : " + result[i].weight);
            minCost += result[i].weight;
        }
        System.out.println("Minimum Total Cost: " + minCost);
    }
    public static void main(String[] args) {
        int V = 4; 
        int E = 5; 
        KruskalAlgorithm graph = new KruskalAlgorithm(V, E);

        graph.edgeList[0].src = 0; graph.edgeList[0].dest = 1; graph.edgeList[0].weight = 10;
        graph.edgeList[1].src = 0; graph.edgeList[1].dest = 2; graph.edgeList[1].weight = 6;
        graph.edgeList[2].src = 0; graph.edgeList[2].dest = 3; graph.edgeList[2].weight = 5;
        graph.edgeList[3].src = 1; graph.edgeList[3].dest = 3; graph.edgeList[3].weight = 15;
        graph.edgeList[4].src = 2; graph.edgeList[4].dest = 3; graph.edgeList[4].weight = 4;
        graph.kruskalMST();
    }
}


