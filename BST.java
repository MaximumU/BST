public class Node{
    int key;
    Node left, right;

    public Node(int item)
    {
        key = item;
        left = right = null;
    }
    
}

class BST {

    public BST()
    {
         root = null;
    }

   

    void insert(int key){
        if(root == null)
            root = new Node(key);
        else if(key < root){
            if(root.left == null)
                root.left = new Node(key);
            else {
                root = root.left;
                return insert( key);
            }
            
        }
        else if(key > root){
            
        }
    }

    boolean search(int key){

    }

    int remove(int key){

    }

    public String toString(){
        
    }