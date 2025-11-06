import java.util.*;

class DijkstraNavigationInput {
    private static final int INF = Integer.MAX_VALUE;

    static class Graph {
        int vertices;
        int[][] matrix;

        Graph(int vertices) {
            this.vertices = vertices;
            matrix = new int[vertices][vertices];
        }

        void addEdge(int src, int dest, int weight) {
            matrix[src][dest] = weight;
            matrix[dest][src] = weight; 
        }

        void dijkstra(int startVertex, String[] cityNames) {
            int[] distance = new int[vertices];
            boolean[] visited = new boolean[vertices];

            Arrays.fill(distance, INF);
            distance[startVertex] = 0;

            for (int i = 0; i < vertices - 1; i++) {
                int u = selectMinVertex(distance, visited);
                visited[u] = true;

                for (int v = 0; v < vertices; v++) {
                    if (!visited[v] && matrix[u][v] != 0 && distance[u] != INF
                            && distance[u] + matrix[u][v] < distance[v]) {
                        distance[v] = distance[u] + matrix[u][v];
                    }
                }
            }

            printSolution(distance, startVertex, cityNames);
        }

        int selectMinVertex(int[] distance, boolean[] visited) {
            int min = INF, minIndex = -1;
            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && distance[v] < min) {
                    min = distance[v];
                    minIndex = v;
                }
            }
            return minIndex;
        }

        void printSolution(int[] distance, int startVertex, String[] cityNames) {
            System.out.println("\nShortest Distances from " + cityNames[startVertex] + ":");
            for (int i = 0; i < vertices; i++) {
                if (distance[i] == INF)
                    System.out.println(cityNames[startVertex] + " ➜ " + cityNames[i] + " = No Path");
                else
                    System.out.println(cityNames[startVertex] + " ➜ " + cityNames[i] + " = " + distance[i] + " km");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        String[] cities = new String[n];
        System.out.println("Enter city names:");
        for (int i = 0; i < n; i++) {
            System.out.print("City " + (i + 1) + ": ");
            cities[i] = sc.nextLine();
        }

        Graph g = new Graph(n);

        System.out.print("\nEnter number of roads (edges): ");
        int e = sc.nextInt();

        System.out.println("Enter road details (source_index destination_index distance):");
        System.out.println("(Example: 0 1 150 means City0 ↔ City1 = 150 km)");

        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int dist = sc.nextInt();
            g.addEdge(src, dest, dist);
        }

        System.out.println("\nList of Cities:");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ". " + cities[i]);
        }

        System.out.print("\nEnter source city index: ");
        int source = sc.nextInt();

        g.dijkstra(source, cities);

        sc.close();
    }
}

