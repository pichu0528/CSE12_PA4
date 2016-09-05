/**
* Assignment 4                Heap12.java             Due: 24 May 2013
* Login in: cs12sbd
**/

/**
* Class Heap12 - Class header comment
* Heap12 is a class that uses a heap-structured array to implement PQueue
* interface. It not only implements the methods from PQueue interface but also 
* needs to write equals(), hashCode(), and sort(). Additional, bubbleUp() and
* trickleDown() are methods that will help our code to work more efficently.
* @author Pin Chu cs12sbd
*/
import java.util.*;
import java.lang.*;


public class Heap12<E extends java.lang.Comparable<? super E>> extends
  java.lang.Object {
  
  private int size;
  private Object[] backStore;
  
  // Public default constructor(No arg) - default capacity = 5.
  public Heap12(){
    backStore = new Object[5];
  }
  
  /* Public copy constructor(Arg of type Heap12) - create copy of the array, but
  not the elements(deep copy)*/
  public Heap12(Heap12<E> heap)
  {
    for(int i = 0; i < heap.backStore.length; i++){
      this.backStore[i] = heap.backStore[i];
      size++;
    }
  }
  
  /* add() adds the specified element to Heap12.
   * Postcondition: the element is added to Heap12, and the size is increased
   * by 1.
   * @Param e - element to add.
   */ 
  public void add(E e){
    if(e == null)
      throw new NullPointerException();
    if(size == backStore.length){
      Object[] temp = new Object[backStore.length*2];
      for(int i = 0; i < backStore.length; i++){
        temp[i] = backStore[i];
      }
      backStore = temp;
    }
    backStore[size] = e;
    size++;
    bubbleUp(size - 1);
  }
  
  /* equals() compares the specified object with Heap12 for equality.
   * @ Param o - object that is being compared.
   * @ Return true - if the specified object, o, is equal to Heap12.
   */ 
  public boolean equals(java.lang.Object o){
    int numSize = this.size();

    
    //****** you didn't do a test to check if o is 
    //instanceof Heap12 ****************
    if(o instanceof Heap12){
      // Return true when the specified objects have the same size
      if(((Heap12<E>) o).size() == numSize){
        
        // Check whether the elements in the backing heaps are equal or not
        for(int i = 0; i < numSize; i++){
          
          if(((Heap12<E>)o).backStore[i] == this.backStore[i])
            return true;
        }
      }
    }
    return false;
  }
  
  // Determine if Heap12 contains any element.
  public boolean isEmpty(){
    // Return true if the size is equal to 0.
    if(size == 0)
      return true;
    else
      return false; 
  }
  
  /* Compute and return a hashcode for Heap12. The hashcode must depend on all
   * of the elements in Heap12.
   * @ Return hashcode - in Heap12.
   */ 
  public int hashCode(){
    final int value = 31;
    int hash = 1;
    /* Loop through the whole thing to make sure the hashcode depeneds on all
     * the elements in Heap12.
     */
    for(int i = 0; i < this.size; i++){
      hash = value*hash + this.backStore[i].hashCode();
    }
    return hash;
  }
  
  /* Returns the element in Heap12 that would be returned by remove().
   * @ Return the highest priority element in Heap12.
   * @ Return null if Heap12 is empty.
   */ 
  public E peek(){
    // Return null when size is equal to 0.
    if(size == 0)
      return null;
    // Else return the highest priority in Heap12.
    return (E)backStore[0];
  }
  
  // Removes and Return the highest priority in this Heap12.
  /* If one or more element has the same highest priority. Remore and 
   return just one.*/
  public E remove(){
    // Return null when size is equal to 0.
    if(size == 0)
      return null;
    
    // Declare the highest priority element.
    E highestPriority = (E)backStore[0];
    backStore[0] = backStore[size - 1];
    
    // Remove the highest priority element.
    backStore[size - 1] =  null;
    size--;
    
    // Trickle down
    this.trickleDown(0);
    return highestPriority;
  }
  
  // Returns the number of elements contain 
  public int size(){
    return size;
  }
      
  // Private constructor for sort().(very shallow copy) 
  private Heap12(Object[] o){
    backStore = o;
    size = o.length;
  }
  
  /* Sort an array of Comparable<? super T> objects into nondecreasing order
   * according to the natural ordering of its elements.
   * @Param a - array to sort.
   * @Exception NullPointerException - if the argument is null.
   */ 
  public static <T extends java.lang.Comparable<? super T>> void sort(T[] a){
    if(a == null)
      throw new NullPointerException();
    Heap12<T> sort = new Heap12<T>(a);
    int length = sort.backStore.length;
    int index = length;
    
    for(int i = 0; i < length; i++){
      sort.bubbleUp(i);
    }
    while(index != 0){
      sort.backStore[index - 1] = sort.remove();
      index--;
    }
  }
  
  /* TrickleDown() organizes the heap structure after an element is removed
   * from Heap12. This is a recursive method.
   * @Param index - where to start trickleDown(). 
   */
  private void trickleDown(int index){
    int left = 2*index + 1;
    int right = 2*index + 2;
    if(left < size && right < size 
         && ((E)backStore[index]).compareTo(((E)backStore[left])) < 0
         && ((E)backStore[left]).compareTo((E)backStore[right]) > 0)
    {
      swap(backStore, index, left);
      trickleDown(left);
    }
    else
    {
     if(right < size && ((E)backStore[index]).compareTo(((E)backStore[right]))<0)
     {
       swap(backStore, index, right);
       trickleDown(right);
     }
    }
    
  }
  
  
  /* bubbleUp() organizes the array everytime when something is added to Heap12.
   * @param index - where to start comparison.
   */ 
  private void bubbleUp(int index){
    int parent = (index - 1)/2;
    if(((E)backStore[index]).compareTo(((E)backStore[parent])) > 0)
    {
      swap(backStore,index,parent);
      //************* you put the bubble up outside the if loop, so it will bubbleup
      //all the time, and even it's over the limit, still bubbles up
      //so put the bubble up inside, and only bubble up when the elements
      //needs to be bubble up***************
      bubbleUp(parent);
    }
    
  }
  
  /* swap() takes values and swap them accoring to their sizes or volumes.
   */ 
  public void swap(Object[] array, int i, int j)
  {
    Object temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
    
    