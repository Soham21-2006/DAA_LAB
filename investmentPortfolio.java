import java.util.*;

public class InvestmentPortfolio {

    static int maximizeReturn(int budget, int cost[], int returns[], int n) {
        int dp[][] = new int[n + 1][budget + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= budget; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (cost[i - 1] <= w)
                    dp[i][w] = Math.max(returns[i - 1] + dp[i - 1][w - cost[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][budget];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of investment options: ");
        int n = sc.nextInt();

        int[] cost = new int[n];
        int[] returns = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter cost of investment " + (i + 1) + ": ");
            cost[i] = sc.nextInt();
            System.out.print("Enter expected return for investment " + (i + 1) + ": ");
            returns[i] = sc.nextInt();
        }

        System.out.print("Enter total budget: ");
        int budget = sc.nextInt();

        int maxReturn = maximizeReturn(budget, cost, returns, n);
        System.out.println("\n💰 Maximum Expected Return = " + maxReturn);

        sc.close();
    }
}

