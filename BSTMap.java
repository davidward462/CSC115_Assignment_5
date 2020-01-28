import java.util.*;

public class BSTMap<K extends Comparable<K>, V > implements  Map<K, V>  {
	
	BinarySearchTree<K, V> b;
	
	public BSTMap () {
		b = new BinarySearchTree<K, V>();
	}

	public boolean containsKey(K key) {
		try
		{
			b.find(key);
		}
		catch(KeyNotFoundException e)
		{
			return false;
		}
		return true;
	}

	public V get (K key) throws KeyNotFoundException {
		try
		{
			return b.find(key);
		}
		catch (KeyNotFoundException e)
		{
			throw new KeyNotFoundException();
		}
	}

	public List<Entry<K,V> >	entryList() {
		return null;
	}

	public void put (K key, V value) {
		b.insert(key, value);
	}

	public int size() {
		return b.size();
	}

	public void clear() {
		b.clear();
	}

	public int getGetLoopCount() {
		return b.getFindLoopCount();
	}

	public int getPutLoopCount() {
		return b.getInsertLoopCount();
	}

	public void resetGetLoops() {
		b.resetFindLoops();
	}
	public void resetPutLoops() {
		b.resetInsertLoops();
	}
}
