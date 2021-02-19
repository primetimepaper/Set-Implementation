package tool;
import java.util.Collection;

public class LinkedListSet<E extends Comparable<E>> implements ISet<E>{

    Node[] NodeArray;

    class Node{
        E data;E next;E previous;

        public Node(E left, E center, E right){
            data = center;
            previous = left;
            next = right;
        }
    }
    
    public LinkedListSet(Collection<E> collection){
        E none = null;
        E[] array = (E[])collection.toArray();
        NodeArray = (Node[])new Object[array.length];

        if(array.length == 1){
            Node n = new Node(none, array[0], none);
            NodeArray[0] = n;
        }
        else{
            for(int i = 0; i < array.length; i++){
                if(i == 0){
                    Node n = new Node(none, array[i], array[i+1]);
                    NodeArray[i] = n;
                }
                else if(i == array.length - 1){
                    Node n = new Node(array[i-1], array[i], none);
                    NodeArray[i] = n;
                }
                else{
                    Node n = new Node(array[i-1], array[i], array[i+1]);
                    NodeArray[i] = n;
                }
            }
        }
    }

    public boolean contains(E element){
        for(int i = 0; i < NodeArray.length; i++){
            if(NodeArray[i].data == element){return true;}
        }
        return false;
    }

    public void clear(){
        NodeArray = (Node[])new Object[NodeArray.length];
    }

    public boolean isEmpty(){
        if(NodeArray.length == 0){return true;}
        else{
            for(int i = 0; i < NodeArray.length; i++){
                if(NodeArray[i] != null){return true;}
            }
            return false;
        }
    }

    public int size(){
        int size = 0;
        for(int i = 0; i < NodeArray.length; i++){
            if(NodeArray[i] != null){size += 1;}
        }
        return size;
    }

    public boolean add(E element){
        boolean flag = false;
        int EmptyIndex = -1;
        for(int i = 0; i < NodeArray.length; i++){
            if(NodeArray[i].data == element){return false;}
            if(NodeArray[i].data == null){flag = true; EmptyIndex = i; break;}
        }
        if(!flag){
            Node[] newNodeArray = (Node[])new Object[NodeArray.length * 2];
            for(int j = 0; j < NodeArray.length; j++){newNodeArray[j] = NodeArray[j];}
            NodeArray = newNodeArray;
            return this.add(element);
        }
        NodeArray[NodeArray.length - 1].next = element;
        Node newNode = new Node(NodeArray[NodeArray.length - 1].data, element, null);
        NodeArray[EmptyIndex] = newNode;
        return true;
    }

    public boolean addAll(Collection <E> collection){
        E[] collectionArray = collection.toArray();
        for(int i = 0; i < collectionArray.length; i++){
            if(this.add(collectionArray[i]) == false){return false;}
            else{this.add(collectionArray[i]);}
        }
        return true;
    }

    public boolean remove(E element){
        E none = null;
        if(this.contains(element)){
            int index = this.FindIndexOf(element);
            if(NodeArray[index].next != null){
                int nextindex = this.FindIndexOf(NodeArray[index].next);
                NodeArray[nextindex].previous = NodeArray[index].previous;
            }
            if(NodeArray[index].previous != null){
                int previndex = this.FindIndexOf(NodeArray[index].previous);
                NodeArray[previndex].next = NodeArray[index].next;
            }
            NodeArray[index] = none;
            return true;
        } else{return false;}
    }

    public boolean removeAll(Collection <E> collection){
        E[] collectionArray = collection.toArray();
        for(int i = 0; i < collectionArray.length; i++){
            if(this.remove(collectionArray[i]) == false){return false;}
            else{this.remove(collectionArray[i]);}
        }
        return true;
    }

    // FOR YOUR CONVENIENCE.
    public int FindIndexOf(E element) throws Exception{
        if(this.contains(element)){
            for(int i = 0; i < NodeArray.length; i++){
                if(NodeArray[i].data == element){return i;}
            }
            return -1;
        } else{throw Exception("Element not found");}
    }
}