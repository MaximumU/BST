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

   

    public void insert(int key){
        insert(key, root);
        
    }

    private void insert(int key, Node start){
        if(start == null)
            root = new Node(key);
        else if(key < root.key){
            if(start.left == null)
                start.left = new Node(key);
            else 
                insert(key, start.left);
        }
        else if(key > start.key){
            if(start.right == null)
                start.right = new Node(key);
            else 
                insert(key, start.right);
        }
    }

    public boolean search(int key){
        return search(key, root);  
    }

    private boolean search(int key, Node start){
        if(start.key == key)
            return true;
        else if(key < root.key){
            if(root.left == null)
                return false;
            else
                return search(key, start.left);
        }
        else{
            if(root.right == null)
                return false;
            else
                return search(key, start.right);
        }
    }


    void remove(int key){

    }

    public String toString(){
       ArrayList<ArrayList<Integer>> map = new ArrayList();
       int height = 90;
       for (int i = 0; i < height; i++) {
        ArrayList<Integer> mapArray = new ArrayList();
        map.add(mapArray);
       }
       return "";
    }