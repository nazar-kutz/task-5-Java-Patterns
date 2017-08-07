package behavioral.iterator;

/**
 * Created by Nazar on 06.08.2017.
 */
public class IteratorApp {
    public static void main(String[] args) {
        Tasks c = new Tasks();
        Iterator it = c.getIterator();
        while (it.hasNext()){
            System.out.println((String)it.next());
        }
    }
}

interface Iterator{
    boolean hasNext();
    Object next();
}

interface Container {
    Iterator getIterator();
}

class Tasks implements Container {
    String[] tasks = {"Построить дом", "Родить сына", "Посадить дерево"};

    @Override
    public Iterator getIterator() {
        return new TaskIterator();
    }

    private class TaskIterator implements Iterator{
        int index = 0;

        @Override
        public boolean hasNext() {
            if(index < tasks.length){
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public Object next() {
            return tasks[index++];
        }
    }
}