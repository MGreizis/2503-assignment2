public class SLL<T extends Comparable<T>> {
   public Node<T> head;

   public SLL() {
      head = null;
   }

   /**
    * Add the input data in order to the linked list.
    *
    * @param data the data to be added to the linked list
    * @return void
    */
   public void addInOrder(T data) {
      Node<T> newNode = new Node<>(data);

      if (head == null || data.compareTo(head.getData()) <= 0) {
         newNode.setNext(head);
         head = newNode;
         return;
      }

      Node<T> current = head;
      while (current.getNext() != null && data.compareTo(current.getNext().getData()) > 0) {
         current = current.getNext();
      }

      newNode.setNext(current.getNext());
      current.setNext(newNode);
   }

   /**
    * Sorts the elements in the linked list in ascending order.
    *
    */
   public void sort() {
      Node<T> current = head;
      while (current != null) {
         Node<T> next = current.getNext();
         while (next != null) {
            if (current.getData().compareTo(next.getData()) > 0) {
               T temp = current.getData();
               current.setData(next.getData());
               next.setData(temp);
            }
            next = next.getNext();
         }
         current = current.getNext();
      }
   }

   /**
    * Returns the size of the list.
    *
    * @return the number of elements in the list
    */
   public int size() {
      int count = 0;
      Node<T> current = head;
      while (current != null) {
         count++;
         current = current.getNext();
      }
      return count;
   }

   public Node<T> getHead() {
      return head;
   }

   public Node<T> setHead(Node<T> newHead) {
      Node<T> oldHead = head;
      head = newHead;
      return oldHead;
   }

   /**
    * Retrieves the node at the specified index.
    *
    * @param index the index of the node to retrieve
    * @return the node at the specified index, or null if the index is out of
    *         bounds
    */
   public Node<T> get(int index) {
      if (index < 0 || index >= size()) {
         return null;
      }

      Node<T> current = head;
      for (int i = 0; i < index; i++) {
         current = current.getNext();
      }

      return current;
   }

   /**
    * Finds the index of the specified data in the linked list.
    *
    * @param data the data to search for
    * @return the index of the specified data, or -1 if the data is not found
    */
   public int indexOf(T data) {
      Node<T> current = head;
      int index = 0;

      while (current != null) {
         if (current.getData().equals(data)) {
            return index;
         }
         current = current.getNext();
         index++;
      }

      return -1;
   }

}
