import java.util.ArrayList;
import java.util.List;

public class Queue<T> implements iQueue<T> {
	
		 private List<T> list;
		 private int head;
		 private int queueLen;

		 // Constructor
		 public Queue() {
		   list = new ArrayList<>();
		   head = 0;
		   queueLen = 0;
		 }

		 @Override
		 public void enqueue(T element) {
		   list.add(element);
		   queueLen++;
		 }
		 

		 @Override
		 public T dequeue() {
		   if (isEmpty()) {
		     throw new IllegalStateException("Queue is empty");
		   }
		   T removed = list.get(head);
		   head = (head + 1) % list.size();
		   if (head == 0) {
		     queueLen = 0;
		   }

		   return removed;
		 }

		 @Override
		 public T peek() {
		   if (isEmpty()) {
		     throw new IllegalStateException("Queue is empty");
		   }
		   return list.get(head);
		 }

		 @Override
		 public boolean isEmpty() {
		   return list.isEmpty();
		 }

		 @Override
		 public int size() {
		   return list.size();
		 }

		 @Override
		 public void clear() {
		   list.clear();
		   head = 0;
		 }

		 @Override
		// Printing only the active members in the queue 
		 public String toString() {
		   StringBuffer stringBuffer = new StringBuffer();
		   stringBuffer.append("These are the contents of the queue head : \n" + head);

		   for (int i = head; i < queueLen; i++) {
		   if (list.get(i) != null) {
		     stringBuffer.append(i);
		     stringBuffer.append(" : ");
		     stringBuffer.append(list.get(i));
		     stringBuffer.append("\n");

		 }
		 }
		 return stringBuffer.toString();
		}

}
