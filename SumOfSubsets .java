import java.util.*;

public class SumOfSubsets {
    
    static void findSubsets(int[] set, int n, int index, List<Integer> current, int sum, int target) {

        if (sum == target) {
            System.out.println(current);
            return;
        }

        if (index == n || sum > target) {
            return;
        }

        current.add(set[index]);
        findSubsets(set, n, index + 1, current, sum + set[index], target);

        current.remove(current.size() - 1);
        findSubsets(set, n, index + 1, current, sum, target);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] set = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        System.out.println("\nSubsets that sum to " + target + ":");
        findSubsets(set, n, 0, new ArrayList<>(), 0, target);

        sc.close();
    }
}
