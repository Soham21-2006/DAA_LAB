// Dijkstra's Algorithm in Java
// Application: Airline or Train Route Planning
// Finds the shortest (minimum cost) route between stations or airports

import java.util.*;

public class AirlineRoutePlanner {


    int findMinDistance(int dist[], boolean visited[], int n) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < n; v++)
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        return minIndex;
    }

    void dijkstra(int graph[][], int src, int n) {
        int dist[] = new int[n];
        boolean visited[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = findMinDistance(dist, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++)
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        System.out.println("\nShortest Route (Minimum Distance) from Source Station:");
        for (int i = 0; i < n; i++) {
            System.out.println("To Station " + i + " --> Distance: " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of stations: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("\nEnter distance matrix (0 if no direct route):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("\nEnter source station (0 to " + (n - 1) + "): ");
        int src = sc.nextInt();

        AirlineRoutePlanner arp = new AirlineRoutePlanner();
        arp.dijkstra(graph, src, n);
    }
}

