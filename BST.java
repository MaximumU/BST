import java.util.*;

class BST {
    private Node root;
    
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
        root = insert(key, root);
    }

    private Node insert(int key, Node node){
        // Standard BST insertion
        if(node == null){
            return new Node(key);
        }
        
        if(key < node.key){
            node.left = insert(key, node.left);
        }
        else if(key > node.key){
            node.right = insert(key, node.right);
        }
        else{
            return node; // Duplicate keys not allowed
        }
        
        // Get balance factor and perform rotations
        int balanceFactor = getBalanceFactor(node);
        
        // Left heavy cases
        if(balanceFactor > 1){
            // Left-Right case
            if(key > node.left.key){
                node.left = rotateLeft(node.left);
            }
            // Left-Left case
            node = rotateRight(node);
        }
        
        // Right heavy cases
        if(balanceFactor < -1){
            // Right-Left case
            if(key < node.right.key){
                node.right = rotateRight(node.right);
            }
            // Right-Right case
            node = rotateLeft(node);
        }
        
        return node;
    }

    public boolean search(int key){
        return search(key, root);  
    }

    private boolean search(int key, Node start){
        if(start == null)
            return false;
        if(start.key == key)
            return true;
        else if(key < start.key){
            return search(key, start.left);
        }
        else{
            return search(key, start.right);
        }
    }

    public void remove(int key){
        root = remove(key, root);
    }

    private Node remove(int key, Node node){
        if(node == null){
            return null;
        }
        
        if(key < node.key){
            node.left = remove(key, node.left);
        }
        else if(key > node.key){
            node.right = remove(key, node.right);
        }
        else{
            // Node found - handle deletion
            // Case 1: No children (leaf node)
            if(node.left == null && node.right == null){
                return null;
            }
            // Case 2: One child
            if(node.left == null){
                return node.right;
            }
            if(node.right == null){
                return node.left;
            }
            // Case 3: Two children
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.right = remove(successor.key, node.right);
        }
        
        if(node == null){
            return null;
        }
        
        int balanceFactor = getBalanceFactor(node);
        
        // Left heavy case
        if(balanceFactor > 1){
            if(getBalanceFactor(node.left) < 0){
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        
        // Right heavy case
        if(balanceFactor < -1){
            if(getBalanceFactor(node.right) > 0){
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        
        return node;
    }

    private Node findMin(Node node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public Node findNode(int key, Node start){
        if(start == null)
            return null;
        if(start.key == key)
            return start;
        else if(key < start.key){
            return findNode(key, start.left);
        }
        else{
            return findNode(key, start.right);
        }
    }

    @Override
    public String toString(){
        String fin = "";
       ArrayList<ArrayList<Integer>> map = new ArrayList();
       int height = height(root);
       for (int i = 0; i <= height; i++) {
        ArrayList<Integer> mapArray = new ArrayList();
        map.add(mapArray);
       }
       toString(0, root, map);
       for (int i = 0; i <= height; i ++){
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

    public int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    private Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
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