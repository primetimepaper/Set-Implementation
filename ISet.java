package tool;
import java.util.Collection;

public interface ISet<E extends Comparable<E>> {
    public boolean add(E element);
    public boolean addAll(Collection<E> collection);
    public void clear();
    public boolean contains(E element);
    public boolean isEmpty();
    public boolean remove(E member);
    public boolean removeAll(Collection<E> collection);
    public int size();
}