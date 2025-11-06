import java.util.*;

public class FloydWarshall {
    final static int INF = 99999;  // Representing infinity (no edge)

    public static void floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        // Initialize distance and next matrices
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
                if (i != j && graph[i][j] != INF)
                    next[i][j] = j;
                else
                    next[i][j] = -1;
            }
        }

        // Core Floyd–Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Print results
        printSolution(dist, V);
        printPaths(next, dist, V);
    }

    // Print the distance matrix
    static void printSolution(int[][] dist, int V) {
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Print paths between pairs
    static void printPaths(int[][] next, int[][] dist, int V) {
        System.out.println("\nShortest paths between vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && next[i][j] != -1) {
                    System.out.print("Path from " + i + " to " + j + " (cost " + dist[i][j] + "): ");
                    printPath(i, j, next);
                    System.out.println();
                }
            }
        }
    }

    // Helper to reconstruct and print path
    static void printPath(int i, int j, int[][] next) {
        if (next[i][j] == -1) {
            System.out.print("No path");
            return;
        }
        System.out.print(i);
        while (i != j) {
            i = next[i][j];
            System.out.print(" -> " + i);
        }
    }

    // Main method (driver)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix (use 99999 for INF):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        floydWarshall(graph, V);
        sc.close();
    }
}


