package mzs.libtools.bean;

/**
 * Created by 24275 on 2016/8/8.
 */
public class Counter {

    private long sum;
    private long count;

    public Counter() {
    }

    public Counter(long sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "sum=" + sum +
                ", count=" + count +
                '}';
    }
}
