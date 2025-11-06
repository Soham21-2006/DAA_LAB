import java.util.*;

class Job {
    String id;      
    int deadline;   
    int profit;     

    Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
public class JobSequencingInput {

    static void jobScheduling(Job jobs[], int n) {

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline)
                maxDeadline = job.deadline;
        }

        String result[] = new String[maxDeadline];
        boolean slot[] = new boolean[maxDeadline];

        int totalProfit = 0;

        for (int i = 0; i < n; i++) {

            for (int j = Math.min(maxDeadline, jobs[i].deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = jobs[i].id;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }

        System.out.print("\nJob sequence for maximum profit: ");
        for (String jobId : result) {
            if (jobId != null)
                System.out.print(jobId + " ");
        }

        System.out.println("\nTotal Profit: " + totalProfit);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        System.out.println("Enter job details (ID Deadline Profit):");
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            int deadline = sc.nextInt();
            int profit = sc.nextInt();
            jobs[i] = new Job(id, deadline, profit);
        }

        jobScheduling(jobs, n);
    }
}

