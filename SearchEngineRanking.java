import java.util.Scanner;

class SearchResult {
    String website;
    double relevanceScore;

    SearchResult(String website, double score) {
        this.website = website;
        this.relevanceScore = score;
    }

    void display() {
        System.out.println(website + "  |  Score: " + relevanceScore);
    }
}

public class SearchEngineRanking{

    static int partition(SearchResult arr[], int low, int high) {
        double pivot = arr[high].relevanceScore;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].relevanceScore > pivot) { 
                i++;
                SearchResult temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        SearchResult temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(SearchResult arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of websites: ");
        int n = sc.nextInt();
        sc.nextLine();

        SearchResult results[] = new SearchResult[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter website name: ");
            String name = sc.nextLine();
            System.out.print("Enter relevance score (0–100): ");
            double score = sc.nextDouble();
            sc.nextLine();
            results[i] = new SearchResult(name, score);
        }

        quickSort(results, 0, n - 1);

        System.out.println("\n--- Search Results (Ranked by Relevance) ---");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". ");
            results[i].display();
        }

        sc.close();
    }
}

