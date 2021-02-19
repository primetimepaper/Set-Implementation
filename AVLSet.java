package tool;
import java.util.Collection;

public class AVLSet<E extends Comparable<E>> implements ISet<E>{
    
    int size = 0; int maxdepth;
    Node root;

    class Node{
        E d;Node r;Node l;int lvl;

        public Node(Node left, E center, Node right){
            d = center;
            l = left;
            r = right;
        }
    }

    public AVLSet(Collection<E> collection){
        E[] array = (E[])collection.toArray();
        for(int i = 0; i < array.length; i++){this.add(array[i]);}
    }

    public AVLSet(){
        size = 0;
        root = null;
    }

    public boolean addTo(E element, Node branch){
        if(branch == null){
            Node newnode = new Node(null, element, null);
            newnode.lvl = this.maxdepth = iteration;
            iteration = 1;
            size += 1;
            return true;
        } else{
            iteration += 1;
            if(element.compareTo(branch.d) > 0){this.addTo(element, branch.r);}
            else if(element.compareTo(branch.d) < 0){this.addTo(element, branch.l);}
            return false;
        }
    }

    public boolean add(E element){
        int iteration = 1;
        if(root == null){
            Node newnode = new Node(null, element, null);
            newnode.lvl = this.maxdepth = iteration;
            root = newnode;
            size += 1;
            return true;
        }
        else{
            iteration += 1;
            if(element.compareTo(root.d) > 0){this.addTo(element, root.r);}
            else if(element.compareTo(root.d) < 0){this.addTo(element, root.l);}
            return false;
        }
    }
    
    public boolean addAll(Collection<E> collection){
        E[] array = collection.toArray();
        for(int i = 0; i < array.length; i++){if(this.add(array[i])){this.add(array[i]);} else{return false;}}
    }

    public void clear(){
        this.root = null;
    }

    public boolean contains(E element){
        if(!this.add(element)){return true;} else{return false;}
    }

    public boolean isEmpty(){
        if(root == null){return true;} else{return false;}
    }

    public boolean removeFrom(E element, Node branch) throws Exception{
        if(branch.d == element){
            if(previous == null){throw new Exception("-1 : previous == null : check this.removeFrom(...)");}
            else{
                if(branch.lvl == maxdepth){Node x = null;}
                else if(branch.l != null & branch.r == null){Node x = branch.l;}
                else if(branch.l == null & branch.r != null){Node x = branch.r;}
                else{Node x = Node(branch.l, this.leftmost(branch.r).d, branch.r); this.remove(this.leftmost(branch.r).d);}

                if(element.compareTo(previous.d) > 0){Node newnode = Node(previous.l, previous.d, x);}
                else if(element.compareTo(previous.d) < 0){Node newnode = Node(x, previous.d, previous.r);}
                else{return false;}
                if(!this.replacementtool(previous, newnode, root)){return false;}
                else{
                    maxdepth = iteration;
                    this.replacementtool(previous, newnode, root);
                    return true;
                }
            }
        } else{
            iteration -= 1;
            if(element.compareTo(branch.d) > 0){previous = branch; this.removeFrom(element, branch.r);}
            else if(element.compareTo(branch.d) < 0){previous = branch; this.removeFrom(element, branch.l);}
            else{return false;}
        }
    }

    public boolean replacementtool(Node target, Node replacement, Node branch){
        if(target == branch){branch = replacement; this.lvlreset(root, 1); return true;}
        else if(target.d.compareTo(branch.d) > 0){return this.replacementtool(target, replacement, branch.r);}
        else if(target.d.compareTo(branch.d) < 0){return this.replacementtool(target, replacement, branch.l);}
        else{return false;}
    }

    public boolean remove(E element){
        int iteration = maxdepth;
        if(!this.contains(element)){return false;}
        if(root == element){System.out.println("Cannot remove root");return false;}
        else{
            iteration -= 1;
            Node previous = null;
            return this.removeFrom(element, root);
        }
    }

    public boolean removeAll(Collection<E> collection){
        E[] array = collection.toArray();
        for(int i = 0; i < array.length; i++){
            if(this.remove(array[i])){this.remove(array[i]);}
            else{return false;}
        }
        return true;
    }

    public boolean lvlreset(Node branch, int LVL){
        int level = LVL + 1;
        if(branch != root){branch.lvl = level;} else{branch.lvl = 1;}
        if(branch.l == null & branch.r == null){return true;}
        else{
            if(this.lvlreset(branch.r, level) & this.lvlreset(branch.l, level)){
                if(branch.r != null){this.lvlreset(branch.r, level);}
                if(branch.l != null){this.lvlreset(branch.l, level);}
                return true;
            } else{return false;}
        }
    }

    public Node finder(E element, Node branch) throws Exception{
        if(!this.contains(element)){throw new Exception("Element not found in tree : this.finder(...) failed");}
        if(element == branch.d){return branch;}
        else if(element.compareTo(branch.d) > 0){return this.finder(element, branch.r);}
        else if(element.compareTo(branch.d) < 0){return this.finder(element, branch.l);}
        else{throw new Exception("Element comparison error");}
    }

    public Node leftmost(Node branch){
        if(branch.l == null){return branch;}
        else{return leftmost(branch.l);}
    }

    public int size(){
        return this.size;
    }
}