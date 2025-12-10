public class Main{
    public static void main (String[] args) {
      BST tree = new BST();

      System.out.println(System.lineSeparator() + "Creating tree..." + System.lineSeparator());

      tree.insert(12);
      tree.insert(5);
      tree.insert(15);
      tree.insert(2);
      tree.insert(9);
      tree.insert(20);
      tree.insert(7);
      tree.insert(10);
      tree.insert(17);
      tree.insert(30);

      System.out.println(tree.toString() + System.lineSeparator() + "Searching for 9...");
      if(tree.search(9))
        System.out.println("Found 9!");
        else 
        System.out.println("9 not found.");

    System.out.println(System.lineSeparator() + "Searching for 25...");
      if(tree.search(25))
        System.out.println("Found 25!");
        else 
        System.out.println("25 not found.");

      tree.insert(25);
      tree.insert(35);
      tree.insert(27);
      
      System.out.println(System.lineSeparator() + tree.toString() + System.lineSeparator() + "Removing 20..." + System.lineSeparator());

      tree.remove(20);

      System.out.println(System.lineSeparator() + tree.toString());
   }
}
