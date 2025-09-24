import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Practice 
{
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) 
    {
        // Set up sum variable
        int odds = 0;

        // Null handling
        if (nums == null) return odds;

        // Loop through each item in the array
        for (int i = 0; i < nums.length; i++) 
        {
            // If odd, add amount to the variable
            if (nums[i] % 2 != 0) odds += nums[i];
        }

        // Return the sum of the odds in the array
        return odds;

    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) 
    {
        // Error handling
        if (words.isEmpty()) throw new IllegalArgumentException();
        if (words == null) throw new NullPointerException();

        // Set up shortest word variable
        String shortWord = "";

        // Go through each word in the set
        for (String word : words) 
        {
            // If empty, initialize first word into variable
            if (shortWord.equals("")) shortWord = word;
            // If shorter, change variable to current word
            else if (word.length() < shortWord.length()) shortWord = word;
            // If same length, compare individual letters lexicographically
            else if (word.length() == shortWord.length())
            {
                // Cycle through each letter in the word
                for (int i = 0; i < word.length(); i++)
                {
                    // If larger, keep old word and stop checking
                    if (word.charAt(i) > shortWord.charAt(i)) break;
                    // If smaller, replace with new word and stop checking
                    if (word.charAt(i) < shortWord.charAt(i))
                    {
                        shortWord = word;
                        break;
                    }
                    // Otherwise, keep checking
                }
            }
        }

        // Return shortest word
        return shortWord;

    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) 
    {
        // Set up a variable to hold a set of names
        Set<String> adultList = new HashSet<String>();
        
        // For each name in the map
        for (String name : ages.keySet())
        {
            // If the age is null throw an exception
            if (ages.get(name) == null) throw new NullPointerException();
            // Otherwise, if 18 or over, add name to the adultList
            else if (ages.get(name) >= 18) adultList.add(name);
        }

        // Return the set
        return adultList;

    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) 
    {
        // Throw exception, if head is null
        if (head == null) throw new IllegalArgumentException();

        // Set up a new node that can cycle through the list
        ListNode<Integer> current = head;
        // Initialize the biggest number variable with the current number
        int bigNum = current.data;
        
        // Cycle through
        while (current.next != null)
        {
            // Set new node spot
            current = current.next;
            // If the current number is bigger than the value in bigNum, use the current number
            if (current.data > bigNum) bigNum = current.data;
        }

        // Return the biggest number
        return bigNum;

    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) 
    {
        // Create Map variable to hold the frequencies
        Map<T, Integer> freqMap = new HashMap<T, Integer>();

        // Null error handling
        if (head == null) return freqMap;

        // Set up temp node to cycle through the list
        ListNode<T> current = head;

        // Add the first node data to the map
        freqMap.put(current.data, 1);

        // Cycle through the node list
        while (current.next != null)
        {
            // Go to next node
            current = current.next;

            //If the key doesn't exist, add key with a count of 1
            if (!freqMap.containsKey(current.data)) freqMap.put(current.data, 1);
            // If key already exists, increment the value of said key
            else freqMap.put(current.data, freqMap.get(current.data) + 1);
        }

        // Return the map of frequencies
        return freqMap;

    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) 
    {
        // If null return 0
        if (root == null) return 0;

        // Check left, incrementing each level
        int levelLeft = levelCount(root.left) + 1;
        // Check right, incrementing each level
        int levelRight = levelCount(root.right) + 1;

        // Compare left and right directions, and return the largest level
        if (levelLeft > levelRight) return levelLeft;
        else return levelRight;

    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) 
    {
        // Call helper method and return result
        return sumAtLevel(root, level, 0);
    }

    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level, int sum)
    {
        // If null increment the level back up and return the sum
        if (root == null)
        {
            level++;
            return sum;
        }

        // Decrement the level
        level--;
        // When the level is zero, add the data to the sum
        if (level == 0) sum += root.data;

        // Traverse left, then right
        int sumLeft = sumAtLevel(root.left, level, sum);
        int sumRight = sumAtLevel(root.right, level, sumLeft);

        // Return the overall sum
        return sumRight;

    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) 
    {
        // Call a method to calculate and return the sum of the ListNode
        int listTotal = listSum(head);
        // Call a method to calculate and return the sum of the tree
        int treeTotal = treeSum(root, 0);
        // Compare the totals, if matching return true, else return false
        if (listTotal == treeTotal) return true;
        else return false;
    }

    public static int listSum(ListNode<Integer> head)
    {
        // Set up the variable to hold the sum within the list
        int listTotal = 0;
        // If null return current total
        if (head == null) return listTotal;
        // Set up a node to cycle through the list
        ListNode<Integer> current = head;
        // Add the current data to the total
        listTotal += current.data;
        
        // Cycle through the Listnode
        while (current.next != null)
        {
            // Go to next node
            current = current.next;
            // Add the current data to the total
            listTotal += current.data;
        }

        // Return the overall total
        return listTotal;

    }

    public static int treeSum(BinaryTreeNode<Integer> root, int total)
    {
        // If null, return total
        if (root == null) return total;
        // Add the current data to the total
        total += root.data;

        // Cycle through the tree left, then right
        int leftTotal = treeSum(root.left, total);
        int rightTotal = treeSum(root.right, leftTotal);

        // Return the overall total
        return rightTotal;

    }


    /**
     * Returns the sum of all the vertices in a graph that are reachable from a given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {
        return 0;
    }

    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        return 0;
    }
}