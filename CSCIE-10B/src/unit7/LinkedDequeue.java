/*
 * LinkedDequeue.java
 *
 * PROBLEM 2
 *
 * This program demonstrates singly linked lists by adding
 * and removing them from a queue. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

public class LinkedDequeue {

    //Class variables
    private QueueNode front;
    private QueueNode rear;
    private int count;

    // Inner class 
    class QueueNode {
         private Object item;
         private QueueNode link;
    }
    
    // Constructor
    public LinkedDequeue( ) {
        rear = front = null;
        count = 0;
    }

    /**
     *  This method will add an item to the front of the queue.  
     *  The object is set to the string passed and the head
     *  location set as a new head. The counter is updated. 
     */
    public void headAdd ( Object o  ) {
       
	    QueueNode temp = new QueueNode();
	    temp.item = o;
	    temp.link = front;
	    
            
	    if (front == null) {
                front = rear = temp;
            }
	    else {
	       front = temp;
	    }
	    count++ ;  
    }

    /**
     *  This method will return the object at the queues head. 
     *
     *  @return     Object which represents the queues head.
     */
    public Object headPeek( ) {
        try {
            if ( isEmpty( ) ) {
                throw new DequeueUnderFlowException();
            }
        } catch ( DequeueUnderFlowException e ) {
            System.out.println("I'm sorry the queue is empty.");
        }
        return front.item;
    }

    /**
     *  This method will remove an item from the front of the queue.  
     *  In doing so, the queue variables are reset to detach the node,
     *  and the Object which it contains is then returned.  The queue
     *  counter is also updated to reflect the removal.
     *
     *  @return     The Object which was just removed from the queue.
     */
    public Object headRemove( ) {
        try {
            if ( isEmpty( ) ) {
                throw new DequeueUnderFlowException();
            }
        } catch ( DequeueUnderFlowException e ) {
            System.out.println("I'm sorry the queue is empty.");
            return null;
        }
            // Set head to second link in list
            Object tempItem = front.item;
            front = front.link;
            if (front == null) {
                rear = null;
            }
            count -- ;
            return tempItem;
    }

    /**
     *  This method will test for an empty queue and return a boolean result.
     *
     *  @return  true for an empty list; false if the queue contains QueueNodes.
     */
    boolean isEmpty( ) {
	    return ( count == 0 );
    }

   /**
     *  This method will evaluate and return the current size of the queue.
     *
     *  @return     An int describing the current number of nodes in the queue
     */
    public int size( ) {
        return count;
    }
    
    /**
     *  This method will construct a new QueueNode and add it onto the rear
     *  of the queue (standard FIFO behavior). If it is the first node added into
     *  the queue, both front and rear will reference it, otherwise it is added
     *  using the rear variable.  The node counter is also updated.
     *
     *  @param   o     The Object to be added as part of a new QueueNode
     */
    void tailAdd( Object o ) {
	    QueueNode temp = new QueueNode();
	    temp.item = o;
	    temp.link = null;
	
            // If list is empty, set positions to null. 
	    if (rear == null) {
                front = rear = temp;
            }
            // set the rear position to the object
	    else {
	       rear.link = temp;
	       rear = temp;
	    }
	    count++ ;
    }

   /**
     *  This method will return the current value of the tail.
     *
     *  @return     An Object containing the tail of the list.
     */
    public Object tailPeek( ) {
        try {
            if ( isEmpty( ) ) {
                throw new DequeueUnderFlowException();
            }
        } catch ( DequeueUnderFlowException e ) {
            System.out.println( "I'm sorry the queue is empty" );
            System.exit(0);
        }
        return rear.item;
    }
    
    /**
     *  This method will remove an item from the tail of the queue.  
     *  In doing so, the queue variables are reset to detach the node,
     *  and the Object which it contains is then returned.  The queue
     *  counter is also updated to reflect the removal.
     *
     *  @return     The Object which was just removed from the queue.
     */
    public Object tailRemove( ) {
        try {
            if ( isEmpty( ) ) {
                throw new DequeueUnderFlowException();
            }
        } catch ( DequeueUnderFlowException e ) {
            System.out.println("I'm sorry the queue is empty.");
        }
        Object tempItem = rear.item;            
             QueueNode next = front;
             QueueNode newRear = null;
             // track two pointers while traversing the linked list 
             while ( next.link != null ) {
                 // lag one pointer behind the other
                 newRear = next;
                 next = next.link;
             }
             // when the next.link is null, we know to remove
             // the final value by setting the new tail.
             rear = newRear;
             rear.link = new QueueNode( );
             if (rear == null) {
                 front = null;
             }
             count--;
        return tempItem;
    }
    
    /**
     *  This method will move through the linked list and append
     *  a string adding each element to a new line.
     *
     *  @return     The complete linked list.
     */
    public String toString( ) {

        StringBuilder dq = new StringBuilder( );
        // traverse through the list using a while loop and
        // append string builder with each element. 
        if (!isEmpty() ) {
            QueueNode temp = front;
            while ( temp != null ) {
                dq.append(temp.item).append( "\n");
                temp = temp.link;
            }
        }
        return dq.toString();
   }
    
     public static void main ( String [] args ) {
        LinkedDequeue list = new LinkedDequeue ();
        
        System.out.println( "Lets add some values to the head & tail of our queue." );
        list.headAdd("First");
        list.tailAdd ("Second");
        list.tailAdd ("Third");
        list.tailAdd ("Fourth");
        System.out.println( list.toString( ) );
        
        System.out.println( "Now let's just remove the head and tail." );
        list.headRemove();
        list.tailRemove();
        
        System.out.println( "Now peek at the head and tail.. ");
        System.out.println ("The head is now: " + list.headPeek() );
        System.out.println ("The tail is now: " + list.tailPeek() );
        
        System.out.println("Great now lets delete it all!");
        list.tailRemove();
        list.tailRemove();
        list.headRemove();
        
        System.out.println( "Hmmm, lets take a peek at the tail to make sure..." );
        System.out.println( list.tailPeek( ) );



    }
}  
    // Custom exception
    class DequeueUnderFlowException extends Exception {
        //Paramterless Constructor 
        public DequeueUnderFlowException( ){
        }
        public DequeueUnderFlowException ( String message ) {
            super( message );
        }
    } 
    
