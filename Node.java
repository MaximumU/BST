import java.util.*;

public class Node{
    int key;
    Node left, right;

    public Node(int item)
    {
        key = item;
        left = right = null;
    }
    
    public static void main (String[] args) {
      System.out.println("Abababa");
   }
}

class BST {
    private Node root;
    
    public BST()
    {
         root = null;
    }

   

    void insert(int key){
        Node start = root;
        if(start == null)
            root = new Node(key);
        else if(key < root.key){
            if(start.left == null){
                start.left = new Node(key);
            }
            else {
                start = start.left;

                insert(key);
            }
        }
        else if(key > start.key){
            if(start.right == null){
                start.right = new Node(key);
            }
            else {
                start = start.right;
                insert(key);
            }
        }
    }

    boolean search(int key){
        Node start = root;
        if(start.key == key)
            return true;
        else if(key < root.key){
            if(root.left == null)
                return false;
            else{
                start = root.left;
                return search(key);
            }
        }
        else if(key > start.key){
            if(root.right == null)
                return false;
            else{
                start = root.right;
                return search(key);
            }
        }
    }

    void remove(int key){

    }

    public String toString(){
       ArrayList<ArrayList<Integer>> map = new ArrayList();
        Node start = root;

    }