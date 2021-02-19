package tool;
import java.util.Collection;

public class ArraySet<E extends Comparable<E>> implements ISet<E>{
    
    E[] _array;

    public ArraySet(){
        _array = (E[])new Object[1000];
        //
    }

    public ArraySet(Collection<E> collection){
        _array = (E[])collection.toArray();
    }

    public ArraySet(int size){
        _array = (E[])new Object[size];
    }

    @Override
    public int size(){
        return _array.length;
    }

    @Override
    public boolean isEmpty(){
        if(_array.length == 0){return true;}
        else{
            for(int i = 0; i < _array.length; i++){
                if(_array[i] != null){return true;}
            }
            return false;
        }
    }

    @Override
    public void clear(){
        int size = _array.length;
        _array = (E[])new Object[size];
    }

    @Override
    public boolean contains(E element) {
        for(int i = 0; i < _array.length; i++){
            if(_array[i] == element){return true;}
        }
        return false;
    }

    @Override
    public boolean add(E element) {

        int emptyIndex = 0;
        boolean flag = false;

        for(int i = 0; i < _array.length; i++) {if(_array[i] == null){emptyIndex = i;flag = true;}}
        flag = false;

        if(this.contains(element)){return false;}
        else{

            if(flag){_array[emptyIndex] = element; return true;}
            else{
                E[] bigger = (E[])new Object[_array.length * 2];
                for(int j = 0; j < _array.length; j++){
                    bigger[j] = _array[j];
                }
                _array = bigger;
                return this.add(element);
            }
        }
    }

    @Override
    public boolean remove(E element){
        for(int i = 0; i < _array.length; i++){
            if(_array[i] == element){_array[i] = null;}
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<E> collection){
        E[] _a = (E[])collection.toArray();
        int count = 0;
        for(int i = 0; i < _a.length; i++){
            if(this.add(_a[i]) == false){count = count + 1;}
            else{this.add(_a[i]);}
        }
        if(count == 0){return true;}
        else{System.out.println(Integer.toString(count) + " additions failed."); return false;}
    }
    @Override
    public boolean removeAll(Collection<E> collection){
        E[] _a = (E[])collection.toArray();
        int count = 0;
        for(int i = 0; i < _a.length; i++){
            if(this.remove(_a[i]) == false){count = count + 1;}
            else{this.remove(_a[i]);}
        }
        if(count == 0){return true;}
        else{System.out.println(Integer.toString(count) + " removals failed."); return false;}
    }
}