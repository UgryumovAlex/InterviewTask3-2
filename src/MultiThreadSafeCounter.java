import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThreadSafeCounter implements ICounter{

    private int value = 0;
    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public int getValue() {
        int returnValue;

        readWriteLock.readLock().lock();
        try {
            returnValue = value;
        } finally {
            readWriteLock.readLock().unlock();
        }
        return returnValue;
    }

    @Override
    public void incCounter() {
        readWriteLock.writeLock().lock();
        try {
            value++;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void decCounter() {
        readWriteLock.writeLock().lock();
        try {
            value--;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
