import java.util.Scanner;
class Student {
    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks);
    }
}
class Node {
    Student data;
    Node left, right;

    Node(Student data) {
        this.data = data;
        left = right = null;
    }
}
class StudentBST {
    Node root;

    void insert(Student s) {
        root = insertRec(root, s);
    }
    Node insertRec(Node root, Student s) {
        if (root == null) {
            root = new Node(s);
            return root;
        }

        if (s.id < root.data.id)
            root.left = insertRec(root.left, s);
        else if (s.id > root.data.id)
            root.right = insertRec(root.right, s);

        return root;
    }
    Student search(int id) {
        Node result = searchRec(root, id);
        if (result != null)
            return result.data;
        else
            return null;
    }
    Node searchRec(Node root, int id) {
        if (root == null || root.data.id == id)
            return root;

        if (id < root.data.id)
            return searchRec(root.left, id);

        return searchRec(root.right, id);
    }
    void displayAll() {
        inorder(root);
    }
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            root.data.display();
            inorder(root.right);
        }
    }
}
public class SchoolDatabase{
    public static void main(String[] args) {
        StudentBST tree = new StudentBST();
        Scanner sc=new Scanner(System.in);
        tree.insert(new Student(102, "Ravi", 85.5));
        tree.insert(new Student(105, "Asha", 90.2));
        tree.insert(new Student(101, "Soham", 88.7));
        tree.insert(new Student(110, "Neha", 79.4));
        System.out.println("All Student Records (sorted by ID):");
        tree.displayAll();
        System.out.print("Enter ID :");
        int searchID = sc.nextInt();
        System.out.println("\nSearching for student with ID " + searchID + "...");
        Student found = tree.search(searchID);
        if (found != null)
            found.display();
        else
            System.out.println("Student not found!");
    }
}

