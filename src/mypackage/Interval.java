package mypackage;

public class Interval {
    public int begin = 0;
    public int end = 0;

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    Interval()
    {
        setBegin(0);
        setEnd(0);
    }

    Interval(int begin, int end)
    {
        this.setBegin(begin);
        this.setEnd(end);
    }

    public Interval copy_Interval()
    {
        Interval copy1 = new Interval();
        copy1.setBegin(this.begin);
        copy1.setEnd(this.end);
        return copy1;
    }
}
