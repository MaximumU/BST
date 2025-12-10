import java.util.*;

class BST {
    private Node root;
    private boolean rightChild;
    
    public BST()
    {
         root = null;
    }

   public int height(Node root) {
        if (root == null) {
            return -1;
        } else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return 1 + Math.max(leftHeight, rightHeight);
        }
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


    public void remove(int key){
        Node rem = findNode(key, root);
        if(rightChild)
            findParent(rem).right = findReplacement(rem);
        else
            findParent(rem).left = findReplacement(rem);
    }

    private Node findNode(int key, Node start){
        if(start.key == key)
            return start;
        else if(key < root.key){
            if(root.left != null)
                return findNode(key, start.left);
        }
        else{
            if(root.right != null)
                return findNode(key, start.right);
        }
        return null;
    }

    private Node findParent(Node start){
        if(start == root)
            return null;
        Node current = root;
        Node parent = null;
        while(current != null && current != start){
            parent = current;
            if(start.key < current.key){
                current = current.left;
                rightChild = false;
            }
            else{
                current = current.right;
                rightChild = true;
            }
        }
        return parent;
    }

    private Node findReplacement(Node start){
        Node current = start.right;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    @Override
    public String toString(){
        String fin = "";
       ArrayList<ArrayList<Integer>> map = new ArrayList();
       int height = height(root);
       for (int i = 0; i < height; i++) {
        ArrayList<Integer> mapArray = new ArrayList();
        map.add(mapArray);
       }
       toString(0, root, map);
       for (int i = 0; i < height; i ++){
        for (int j = 0; j < map.get(i).size(); j++){
            fin = fin + ", " + map.get(i).get(j);
        }
        fin = fin + System.lineSeparator();
       }
       System.out.println(fin);
       return fin;
    }

    private void toString(int i, Node start, ArrayList<ArrayList<Integer>> map){
        if(start != null){
            map.get(i).add(start.key);
            toString(i + 1, start.left, map);
            toString(i + 1, start.right, map);
        }
    }
}