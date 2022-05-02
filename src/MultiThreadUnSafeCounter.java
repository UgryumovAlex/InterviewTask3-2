public class MultiThreadUnSafeCounter implements ICounter{

    private int value = 0;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void incCounter() {
        value++;
    }

    @Override
    public void decCounter() {
        value--;
    }
}
