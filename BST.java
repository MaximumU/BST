import java.util.*;

class BST {
    private Node root;
    private boolean rightChild;
    
    public BST()
    {
         root = null;
    }

    public Node getRoot(){
        return root;
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
         if(root == null){
            root = new Node(key);
            return;
        }
         else{
        insert(key, root);
         }
    }

    private void insert(int key, Node start){
       
        if(key < start.key){
            if(start.left == null){
                start.left = new Node(key);
                return;
            }
            else 
                insert(key, start.left);
        }
        else if(key > start.key){
            if(start.right == null){
                start.right = new Node(key);
                return;
            }
            else 
                insert(key, start.right);
        }
    }

    public boolean search(int key){
        return search(key, root);  
    }

    private boolean search(int key, Node start){
        if(start == null)
            return false;
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

    public Node findNode(int key, Node start){
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
            fin = fin + map.get(i).get(j) + ", ";
        }
        fin = fin + System.lineSeparator();
       }
       return fin;
    }

    private void toString(int i, Node start, ArrayList<ArrayList<Integer>> map){
        if(map.size() <= i){
            ArrayList<Integer> mapArray = new ArrayList();
            map.add(mapArray);
        }
        if(start != null){
            map.get(i).add(start.key);
            toString(i + 1, start.left, map);
            toString(i + 1, start.right, map);
        }
    }

    public int getBalanceFactor(Node start){
        return height(start.left) - height(start.right);
    }

    public void rotateRight(Node node1, Node node2){
        if(node1 == root)
            root = node2;
        else{
            Node parent = findParent(node1);
            if(parent.left == node1)
                parent.left = node2;
            else
                parent.right = node2;
        }
        node1.left = node2.right;
        node2.right = node1;
    }


    //Add the following functions to your BST
 //Please use this code to verify your tree integrity
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree(){
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }

//this goes into it's own file
    class Trunk
   {
    Trunk prev;
    String str;
 
    Trunk(Trunk prev, String str)
    {
        this.prev = prev;
        this.str = str;
    }
   };
 

}