package me.donnior.fava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FArrayList<E> extends ArrayList<E> implements FList<E> {

    private static final long serialVersionUID = 1L;

    public FArrayList() {}

    public FArrayList(Collection<E> list) {
        super(list);
    }

    public int indexOf(Predicate<E> function) {
        ListIterator<E> it = this.listIterator();
        while (it.hasNext()) {
            int index = it.nextIndex();
            E e = it.next();
            if (function.apply(e)) {
                return index;
            }
        }
        return -1;
    }
    
    public E find(Predicate<E> function) {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (function.apply(e)) {
                return e;
            }
        }
        return null;
    }

    public FList<E> findAll(Predicate<E> function) {
        FArrayList<E> result = new FArrayList<E>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (function.apply(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public <T> FList<T> collect(Function<E, T> function) {
        FArrayList<T> result = new FArrayList<T>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.add(function.apply(it.next()));
        }
        return result;
    }

    public <T> FList<T> map(Function<E, T> function) {
        return this.collect(function);
    }

    public void each(Consumer<E> function) {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            function.apply(it.next());
        }
    }
    
    
    public void eachIndex(Consumer<Integer> function) {
        Iterator<E> it = this.iterator();
        int index = 0;
        while (it.hasNext()) {
            it.next();
            function.apply(index++);
        }
    }
    
    public E at(int index) {
        if (index >= 0) {
            return this.get(index);
        } else {
            return this.get(this.size() + index);
        }
    }

    public FList<E> compact() {
        FArrayList<E> result = new FArrayList<E>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (e != null) {
                result.add(e);
            }
        }
        return result;
    }

    public E first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(0);
    }

    public E last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(this.size() - 1);
    }

    public FList<E> select(Predicate<E> predict) {
        return this.findAll(predict);
    }

    @StateModified
    public FList<E> deleteIf(Predicate<E> predict) {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (predict.apply(e)) {
                it.remove();
            }
        }
        return this;
    }

    public FList<E> reject(Predicate<E> predict) {
        FArrayList<E> result = new FArrayList<E>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (!predict.apply(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean any(Predicate<E> function) {
        return this.find(function) != null;
    }

    public boolean all(Predicate<E> function) {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (!function.apply(e)) {
                return false;
            }
        }
        return true;
    }

    public int count(Predicate<E> function) {
        int count = 0;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (function.apply(it.next())) {
                count += 1;
            }
        }
        return count;
    }

    public FList<E> top(int n) {
        FArrayList<E> result = new FArrayList<E>();
        int count = 0;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            if (count < n) {
                result.add(e);
                count += 1;
            } else {
                break;
            }
        }
        return result;
    }

    @StateModified
    public FList<E> push(E... elements) {
        if (elements != null) {
            for (E e : elements) {
                this.add(e);
            }
        }
        return this;
    }
    
    @StateModified
    public FList<E> push(List<E> elements) {
        if (elements != null) {
            for (E e : elements) {
                this.add(e);
            }
        }
        return this;
    }
    
    public FList<E> plus(E... elements) {
        FArrayList<E> result = new FArrayList<E>(this);
        result.push(elements);
        return result;
    }
    
    public FList<E> plus(List<E> elements) {
        FArrayList<E> result = new FArrayList<E>(this);
        result.push(elements);
        return result;
    }

    public <T> T fold(T init, FoldFunction<E, T> function) {
        Iterator<E> it = this.iterator();
        T result = init;
        while (it.hasNext()) {
            E e = it.next();
            result = function.apply(e, result);
        }
        return result;
    }
    
    public E reduce(FoldFunction<E, E> function) {
        Iterator<E> it = this.iterator();
        if(!it.hasNext()){
            throw new RuntimeException("reduce operation can'b be called with empty array");
        }
        E result = it.next();
        while (it.hasNext()) {
            E e = it.next();
            result = function.apply(e, result);
        }
        return result;
    }
    
    @StateModified
    public FList<E> sort(Comparator<? super E> comparator) {
        Collections.sort(this, comparator);
        return this;
    }
    
    @StateModified
    public <T extends Comparable<T>> FList<E> sortBy(final Function<E, T> function) {
        Comparator<E> c = new Comparator<E>() {
            public int compare(E e1, E e2) {
                return function.apply(e1).compareTo(function.apply(e2));
            }
        };
        Collections.sort(this, c);
        return this;
    }

    @Override
    public <T> FMap<T, FList<E>> groupBy(final Function<E, T> function) {
        final FMap<T, FList<E>> map = new FHashMap<T, FList<E>>();
        this.each(new Consumer<E>() {
            public void apply(E e) {
                T t = function.apply(e);
                FList<E> list = map.get(t);
                if(list == null){
                   map.put(t, new FArrayList<E>());
                   list = map.get(t);
                }
                list.add(e);
            }
        });
        return map;
    }
}
