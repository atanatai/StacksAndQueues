//Kai Rahm
//CS3240

public class Linked<E> {
	private Linked<E> first;
	private Linked<E> last;
	private E data;
	private Linked<E> nextNode;
	
	
	
	public Linked(){
		super();
	}
	
	public Linked(E data1){
		super();
		this.data = data1;
	}
	
	public void insertLast(E data1){
		Linked<E> newLink = new Linked<E>(data1);
		newLink.nextNode = null;
		if(first != null){
			last.nextNode = newLink;
			last = newLink;
		}else{
			first = newLink;
			last = newLink;
		}
		//System.out.println(data1);
	}
	
	
	public void insertAt(int position, E newData){
		Linked<E> newLink = new Linked<E>(newData);
		int listCount = this.listCount();
		if (listCount == 0){
			System.out.println("List is empty, inserting at position 0 (first value).");
			first = newLink;
			newLink.nextNode = null;
			last = newLink;
		}
		else{
			if (position >= listCount){
				System.out.println("Linked List range is (0-"+(listCount-1)+"), the requested index number for inserting is "+position+".");
				System.out.println("Therefore element will be appended to the end of the Linked List.");
				System.out.println("Inserting element data: "+newLink.data+" at index position "+listCount+" (last value in list)");
				last.nextNode = newLink;
				last = newLink;
			}
			else if (position <= 0){
				System.out.println("Inserting element data:  "+newLink.data+" at index position 0 (first value in list).");
				newLink.nextNode = first;
				first = newLink;
			}
			else{
				int counter = 0;
				Linked<E> temp = first;
				while(temp != null){
				if(counter == position){
					Linked<E> beforeInsertedNode = first;
					Linked<E> afterInsertedNode = first;
					for(int i=0; i<(position-1); i++){
						beforeInsertedNode = beforeInsertedNode.nextNode;
					}
					for(int i=0; i<position; i++){
						afterInsertedNode = afterInsertedNode.nextNode;
					}
					System.out.println("Inserting element data:  "+newLink.data+" at index position "+position+".");
						//sets the next link of the link being inserted to the link that would have come next (before inserting)
					newLink.nextNode = afterInsertedNode; 
						//sets the link to come next as the new element, which will point to the link that was supposed to be at position index.
					beforeInsertedNode.nextNode = newLink;
				}
				temp = temp.nextNode;
				counter++;
				}
			}
		}
	}

	public void insertFirst(E data2){
		if(this.listCount() == 0){
			this.insertLast(data2);
		}
		else{
			this.insertAt(0, data2);
		}
	}
	
	public int listCount(){
		int counter = 0;
		Linked<E> tempLink = first;
 		while (tempLink != null){
			counter++;
			tempLink = tempLink.nextNode;
		}
		return (counter);
	}
	
	public void printList(){
		Linked<E> temp = first;
		System.out.println("The elements of the linked list are: ");
		while(temp != null){
			System.out.println(temp.data);
			temp = temp.nextNode;
		}
	}
	
	public void printRange(int startIndex, int endIndex){
		if (this.listCount() == 0){
			System.out.println("The list is empty. No elements to print.");
		}
		else{
			if(endIndex >= this.listCount()){
				System.out.println("Ending index("+endIndex+") extends out of Linked List range (0,"+(this.listCount()-1)+").");
				System.out.println("Range will conclude at end of list.");
				endIndex = this.listCount() - 1;
			}
			if (startIndex < 0){
				System.out.println("Starting index("+startIndex+") is out of Linked List range (0,"+(this.listCount()-1)+").");
				System.out.println("Range will begin from the start of the list.");
				startIndex = 0;
			}
			System.out.println("Printing range ("+startIndex+","+endIndex+") of the Linked List:");
			while(startIndex <= endIndex){
				System.out.println(this.returnData(startIndex));
				startIndex++;
			}
		}
	}
	
	public void deleteFirst(){
		if(null != first){
			//System.out.println("Deleting value: "+first.data+" from index position 0 (first value).");
			first = first.nextNode;
		}
		else{
			System.out.println("Linked list is already empty.");
		}
	}
	
	public void deleteItem(int indexNum){
		int listCount = this.listCount();
		if (listCount == 0){
			System.out.println("Linked list is already empty");
		}
		else{
			if (indexNum >= listCount){
				System.out.println("The index number you want to delete("+indexNum+") is out of Linked List range (0-"+(listCount-1)+")");
				System.out.println("The last value will be deleted instead");
				indexNum = listCount - 1;
			}
			if (indexNum <= 0){
				this.deleteFirst();
			}
			else{
				Linked<E> beforeDeletedNode = first;
				Linked<E> afterDeletedNode = first;
				for(int i=0; i<(indexNum-1); i++){
					beforeDeletedNode = beforeDeletedNode.nextNode;
				}
				for(int i=0; i<=indexNum; i++){
					afterDeletedNode = afterDeletedNode.nextNode;
				}
				//System.out.println("Deleting value: "+beforeDeletedNode.nextNode.data+" from index position "+indexNum+".");
				beforeDeletedNode.nextNode = afterDeletedNode;
			}
		}
	}
	
	public E returnData(int indexPosition){
		Linked<E> temp = first;
		int listCount = this.listCount();
		if (listCount == 0){
			System.out.println("Can't search an empty list.");
		}
		else{
			if (indexPosition >= listCount){
				System.out.println("The index number you want to find("+indexPosition+") is out of Linked List range (0-"+(listCount-1)+")");
				System.out.println("The last value in list will be returned instead");
				indexPosition = listCount - 1;
			}
			if (indexPosition <= 0){
				return(temp.data);
			}
			else{
				int counter = 0;
				while(temp != null){
					if(counter == indexPosition){
						return (temp.data);
					}
					temp = temp.nextNode;
					counter++;
				}
			}
		}
		return(null);
	}

}
