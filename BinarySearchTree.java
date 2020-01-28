import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V>	root;
	int		count;

	int		findLoops;
	int		insertLoops;

	public BinarySearchTree () {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	public int getFindLoopCount() {
		return findLoops;
	}

	public int getInsertLoopCount() {
		return insertLoops;
	}

	public void resetFindLoops() {
		findLoops = 0;
	}
	public void resetInsertLoops() {
		insertLoops = 0;
	}

	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree.  If the Key
	// already exists in the tree, update the value stored at
	// that node with the new value.
	//
	// Pre-Conditions:
	// 	the tree is a valid binary search tree
	//
	public void insert (K k, V v) {
		BSTNode<K, V> n = new BSTNode<K, V>(k ,v);//new node
		if (root == null)//if empty
		{
			root = n;
			count++;
		}
		else//not empty
		{
			BSTNode<K, V> p = root;//pointer
			while (true)//loop through tree
			{
				insertLoops++;
				if(n.key.compareTo(p.key) == 0)//if key is in tree
				{
					p.value = v;//update value in tree
					break; //end loop
				}
				else//if keys not equal to eachother
				{
					if(k.compareTo(p.key) > 0) //
					{
						if(p.right == null)//if no right child
						{
							p.right = n;//add
							count++;
							break;//end loop
						}
						p = p.right;
					}
					else if (k.compareTo(p.key) < 0)//
					{
						if(p.left == null)//if no left child
						{
							p.left = n;//add
							count++;
							break;//end loop
						}
						p = p.left;
					}
				}
			}
		}
	}
	
	//
	// Purpose:
	//
	// Return the value stored at key.  Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	//	the tree is a valid binary search tree
	//
	// Returns:
	//	the value stored at key
	//
	// Throws:
	//	KeyNotFoundException if key isn't in the tree
	//
	public V find (K key) throws KeyNotFoundException {
		if (size() == 0)//tree is empty
		{
			throw new KeyNotFoundException();
		}
		else//tree not empty
		{
			BSTNode<K, V> p = root;//pointer
			
			while (p != null)//while p pointing to valid node
			{
				findLoops++;
				if (key.equals(p.key))//if key found
				{
					return p.value; //"error: incompatible types: Object cannot be converted to V"
				}
				else//key not equal to p.key
				{
					if(key.compareTo(key) < key.compareTo(p.key))
					{
						p = p.right;
					}
					else
					{
						p = p.left;
					}
				}
			}
			throw new KeyNotFoundException();//key not in tree
		}
	}

	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	//	the number of nodes in the tree.
	public int size() {
		//System.out.println("count: " + count);
		return count;
	}

	//
	// Purpose:
	//	Remove all nodes from the tree.
	//
	public void clear() {
		root = null;
	}

	//
	// Purpose:
	//
	// Return the height of the tree.  We define height
	// as being the number of nodes on the path from the root
	// to the deepest node.
	//
	// This means that a tree with one node has height 1.
	//
	// Examples:
	//	See the assignment PDF and the test program for
	//	examples of height.
	//
	public int height() {
		if (size() == 0)
		{
			return 0;
		}
		return recursiveHeight(root);//call recursion
	}
	
	//recursive helper method
	public int recursiveHeight(BSTNode<K, V> node)//sub-root
	{
		//base case
		if (node == null)//if no node
		{
			return 0;
		}
		return (1 + (Math.max(recursiveHeight(node.right), recursiveHeight(node.left))));
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	//
	// Level order is most commonly implemented using a queue of nodes.
	//
	//  From wikipedia (they call it breadth-first), the algorithm for level order is:
	//
	//	levelorder()
	//		q = empty queue
	//		q.enqueue(root)
	//		while not q.empty do
	//			node := q.dequeue()
	//			visit(node)
	//			if node.left != null then
	//			      q.enqueue(node.left)
	//			if node.right != null then
	//			      q.enqueue(node.right)
	//
	// Note that we will use the Java LinkedList as a Queue by using
	// only the removeFirst() and addLast() methods.
	//
	public List<Entry<K,V>> entryList() {
		List<Entry<K, V>> 			l = new LinkedList<Entry<K,V> >();//final list called l
		LinkedList<BSTNode<K,V>> q = new LinkedList<BSTNode<K,V> >();//queue called q
		
		q.addLast(root);//enqueue root
		while (q.isEmpty() == false)//while queue not empty
		{
			BSTNode<K, V> n = q.removeFirst();//dequeue
			l.add(new Entry<K,V>(n.key, n.value));//add to final list
			if (n.left != null)
			{
				q.addLast(n.left);//enqueue
			}
			if (n.right != null)
			{
				q.addLast(n.right);//enqueue
			}
		}
		return l;
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal 
	// specified by the parameter which.
	//
	// If which is:
	//	BST_PREORDER	perform a pre-order traversal
	//	BST_POSTORDER	perform a post-order traversal
	//	BST_INORDER	perform an in-order traversal
	//
	public List<Entry<K,V> > entryList (int which) {
		List<Entry<K,V> > l = new LinkedList<Entry<K,V> >();
		if(which == BST_INORDER)//check which
		{
			doInOrder(root, l);
		}
		else if(which == BST_PREORDER)
		{
			doPreOrder(root, l);
		}
		else if(which == BST_POSTORDER)
		{
			doPostOrder(root, l);
		}
		return l;
	}
	
	private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l)
	{
		if (n == null)
		{
			return;
		}
		doInOrder(n.left, l);
		l.add(new Entry<K,V>(n.key, n.value));//add node to list
		doInOrder(n.right, l);
	}
	
	private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l)
	{
		if (n == null)
		{
			return;
		}
		l.add(new Entry<K,V>(n.key, n.value));//add node to list
		doPreOrder(n.left, l);
		doPreOrder(n.right, l);
	}
	
	private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l)
	{
		if (n == null)
		{
			return;
		}
		doPostOrder(n.left, l);
		doPostOrder(n.right, l);
		l.add(new Entry<K,V>(n.key, n.value));//add node to list
	}

	// Your instructor had the following private methods in his solution:
	// private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l);
	// private int doHeight (BSTNode<K,V> t)
}
