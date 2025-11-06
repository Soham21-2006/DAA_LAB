import java.util.*;

public class OptimalBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of keys: ");
        int n = sc.nextInt();

        int keys[] = new int[n];
        double p[] = new double[n];

        System.out.println("Enter keys (sorted): ");
        for (int i = 0; i < n; i++) {
            keys[i] = sc.nextInt();
        }

        System.out.println("Enter probabilities for each key: ");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextDouble();
        }

        System.out.println("\nMinimum cost of Optimal BST = " + optimalBST(p, n));
    }

    static double optimalBST(double p[], int n) {
        double[][] cost = new double[n][n];

        for (int i = 0; i < n; i++)
            cost[i][i] = p[i];

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                cost[i][j] = Double.MAX_VALUE;

                double sum = 0;
                for (int s = i; s <= j; s++)
                    sum += p[s];

                for (int r = i; r <= j; r++) {
                    double c = ((r > i) ? cost[i][r - 1] : 0) +
                               ((r < j) ? cost[r + 1][j] : 0) + sum;

                    if (c < cost[i][j])
                        cost[i][j] = c;
                }
            }
        }
        return cost[0][n - 1];
    }
}
