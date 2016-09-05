/**
* Assignment 4                Heap12Tester.java             Due: 24 May 2013
* Login in: cs12sbd
**/

/**
* Class Heap12Tester - Class header comment
* This tester is to test if Heap12 works correctly as the stated instruction. 
* @author Pin Chu cs12sbd
*/
import junit.framework.*;
import java.lang.*;
import java.util.*;


public class Heap12Tester extends junit.framework.TestCase{
  
  /* Test if add() adds the specified element to this Heap12.
   * @ Throw NullPointerException - if the specified element is null.
   **/ 
  public void testAdd(){
    Heap12<Integer> heap = new Heap12<Integer>();
    heap.add(1);
    heap.add(2);
    // Check for the size and the element
    assertTrue(heap.size() == 2);
    assertTrue(heap.peek() == 2);
    heap.add(3);
    heap.add(4);
    heap.add(5);
    heap.add(6);
    assertTrue(heap.peek() == 6);
    // Try catch for an exception
    try
    {
      heap.add(null);
    }
    catch(NullPointerException e){}
  }
  
  /* Test if equals() correctly compares the specified object with Heap12 for
   * equality.
   */ 
  public void testEquals(){
    Heap12<Integer> heap = new Heap12<Integer>();
    Heap12<Integer> temp = new Heap12<Integer>();
    // First set
    heap.add(1);
    heap.add(2);
    heap.add(3);
    temp.add(1);
    temp.add(2);
    temp.add(3);
    assertTrue(heap.equals(temp));
    
    heap.remove();
    heap.remove();
    heap.remove();
    temp.remove();
    temp.remove();
    temp.remove();
    
    // Second set
    heap.add(4);
    heap.add(5);
    temp.add(6);
    temp.add(7);
    assertFalse(heap.equals(temp));
    
    heap.remove();
    heap.remove();
    temp.remove();
    temp.remove();
    
    // Third set
    heap.add(10);
    heap.add(8);
    temp.add(10);
    temp.add(8);
    assertTrue(heap.equals(temp));
  }
  
  /* Test if hashCode()'s return hashcode depeneds on all of the elements in 
   * Heap12, and if it is consistent with equals().
   */ 
  public void testHashCode(){
    Heap12<Integer> heap = new Heap12<Integer>();
    Heap12<Integer> temp = new Heap12<Integer>();
    
    // First set
    heap.add(1);
    heap.add(2);
    heap.add(3);
    temp.add(1);
    temp.add(2);
    temp.add(3);
    assertTrue(heap.equals(temp));
    assertTrue(heap.hashCode() == temp.hashCode());
    
    // Second set
    heap.add(4);
    assertFalse(heap.equals(temp));
    assertFalse(heap.hashCode() == temp.hashCode());
    
    // Third set
    temp.add(4);
    assertTrue(heap.equals(temp));
    assertTrue(heap.hashCode() == temp.hashCode());
  }
  
  
  /* Test if Heap12 contains any elements.
   **/ 
  public void testIsEmpty(){
    Heap12<Integer> heap = new Heap12<Integer>();
    // Beginning of the Heap12 is empty since nothing is added.
    assertTrue(heap.isEmpty());
    heap.add(1);
    heap.add(2);
    heap.add(3);
    assertFalse(heap.isEmpty());
    heap.remove();
    heap.remove();
    assertFalse(heap.isEmpty());
    heap.remove();
    assertTrue(heap.isEmpty());
  }
  
  
  /* Test if peek() in Heap12 returns the element that would be returned by 
   * remove()
   **/ 
  public void testPeek(){
    Heap12<Integer> heap = new Heap12<Integer>();
    // First set
    heap.add(1);
    assertEquals(new Integer(1), heap.peek());
    assertTrue(heap.peek() == 1);
    
    // Second set
    heap.add(2);
    heap.add(3);
    assertEquals(new Integer(3), heap.peek());
    assertTrue(heap.peek() == 3);
    
    // Third set
    heap.remove();
    assertEquals(new Integer(2), heap.peek());
    assertTrue(heap.peek() == 2);
    
    // Fourth set
    heap.remove();
    assertEquals(new Integer(1), heap.peek());
    assertTrue(heap.peek() == 1);
    
    // Fifth set
    heap.add(4);
    assertTrue(heap.peek() == 4);
    heap.remove();
    heap.remove();
    assertTrue(heap.peek() == null);
  }
  
  
  /* Test if remove() removes and returns the highest priority element in
   * Heap12. If more than one element has the same highest priority, one of them 
   * is removed and returned.
   **/ 
  public void testRemove(){
    Heap12<Integer> heap = new Heap12<Integer>();
    // First set
    heap.add(1);
    heap.add(2);
    heap.remove();
    assertTrue(heap.size() == 1);
    assertTrue(heap.peek() == 1);
    assertTrue(heap.remove() == 2);
    
    // Second set
    heap.add(3);
    heap.add(4);
    heap.add(5);
    assertTrue(heap.peek() == 5);
    heap.remove();
    heap.remove();
    heap.remove();
    heap.remove();
    assertTrue(heap.remove() == null);
    assertTrue(heap.size() == 0);
    
    // Third set
    heap.add(6);
    heap.add(6);
    heap.add(6);
    assertTrue(heap.peek() == 6);
    assertTrue(heap.size() == 3);
    heap.remove();
    assertTrue(heap.remove() == 6);
    heap.remove();
    heap.remove();
    
    // Fourth set
    heap.add(8);
    heap.add(10);
    heap.add(7);
    heap.add(9);
    assertTrue(heap.peek() == 10);
    heap.add(11);
    assertTrue(heap.size() == 5);
    heap.remove();
    heap.remove();
    heap.remove();
    assertTrue(heap.peek() == 8);
    
  }
  
  
  /* Test if size() in Heap12 returns the number of elements.
   **/ 
  public void testSize(){
    Heap12<Integer> heap = new Heap12<Integer>();
    assertTrue(heap.size() == 0);
    heap.add(1);
    heap.add(2);
    heap.add(3);
    assertTrue(heap.size() == 3);
    heap.remove();
    assertTrue(heap.size() == 2);
    heap.remove();
    assertTrue(heap.size() == 1);
    heap.remove();
    assertTrue(heap.size() == 0);    
  }
  
  // Test if sort() correctly sorted the elements in priority order.
  public void testSort(){
    // Assume an array with the size of 10.
    Integer[] array = new Integer[10];
    
    array[0] = 1;
    array[1] = 3;
    array[2] = 5;
    array[3] = 7;
    array[4] = 9;
    array[5] = 2;
    array[6] = 4;
    array[7] = 6;
    array[8] = 8;
    array[9] = 10;
    
    Heap12.sort(array);
    
    assertEquals(5, array[4].intValue());
      
  }
  
  public static void main(String[] args){
    junit.swingui.TestRunner.main(new String[]{"Heap12Tester"});
  }
}
  
  
  
  
  
  
  
  
  
  
  
  