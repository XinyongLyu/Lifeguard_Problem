package mypackage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lifeguard_Problem {
    public static List<Interval> readFile(File fileName) {
        List<Interval> list = new ArrayList<Interval>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                if(tempString.contains(" ")){
                    String[] str = tempString.split(" ");
                    int s = Integer.parseInt(str[0]);
                    int e = Integer.parseInt(str[1]);
                    Interval interval = new Interval(s, e);
                    list.add(interval);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    public static void output_result(int result, File output) {
        String myresult = Integer.toString(result);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output)))
        {
            bw.write(myresult);
        }
        catch(IOException e)
        {
            System.out.println("Sorry...");
        }
    }
    public static int max_time(List<Interval> list) {
        int max = 0;
        for(Interval a:list)
        {
            max += a.getEnd()-a.getBegin();
        }
        return max;
    }
    public static List<Interval> merge_interval1(List<Interval> list)
    {
        Collections.sort(list, (a, b) -> a.getBegin() - b.getBegin());
        List<Interval> my_merge = new ArrayList<Interval>();
        int x =0;
        Interval first = list.get(x);
        Interval second = list.get(x+1);
        int a = first.getBegin();
        int b = 0;
        if(first.getEnd()<=second.getBegin()) {
            b = first.getEnd();
        }
        else {
            b = second.getBegin();
        }
        my_merge.add(new Interval(a,b));
        for(int i =1; i<list.size();i++)
        {
            Interval current_interval = list.get(i);

            if(current_interval.getBegin()<=b)
            {
                if((current_interval.getBegin()==a)&&(current_interval.getEnd()==b))
                {
                    continue;
                }
                else if(current_interval.getEnd()>=b)
                {
                    my_merge.add(new Interval(b,current_interval.getEnd()));
                    a = b;
                    b = current_interval.getEnd();
                }
                else
                {
                    my_merge.add(new Interval(current_interval.getEnd(),b));
                    a = current_interval.getEnd();
                }
            }
            else
            {
                a = current_interval.getBegin();
                b = current_interval.getEnd();
                if(i+1<=list.size())
                {
                    Interval next_interval = list.get(i+1);
                    if(current_interval.getEnd()>next_interval.getBegin()) {
                        b = next_interval.getBegin();
                    }
                }
                my_merge.add(new Interval(a,b));
            }
        }
        return my_merge;
    }
    public static List<Interval> merge_interval2(List<Interval> list) {

        Collections.sort(list, (a, b) -> a.getBegin() - b.getBegin());
        Interval base = list.get(0);
        int beg = base.getBegin();
        int end = base.getEnd();

        List<Interval> my_merge = new ArrayList<Interval>();

        for (int i = 1; i < list.size(); i++) {
            Interval current = list.get(i);

            if (current.getBegin() <= end) {
                end = Math.max(current.getEnd(), end);

            } else {
                my_merge.add(new Interval(beg, end));
                beg = current.getBegin();
                end = current.getEnd();
            }
        }

        my_merge.add(new Interval(beg, end));
        return my_merge;
    }
    public static int FindBestTime(File file) {

        List<Interval> list = new ArrayList<Interval>();
        list = readFile(file);
        Collections.sort(list, (a, b) -> a.getBegin() - b.getBegin());

        List<Interval> list2 = new ArrayList<Interval>();

        for (Interval a : list) {
            list2.add(a.copy_Interval());
        }
        Collections.sort(list2, (a, b) -> a.getBegin() - b.getBegin());
        list2 = merge_interval2(list2);
        int time = max_time(list2);
        
        list = merge_interval1(list);
        int myTime = max_time(list);
        int former=0;
        int latter=0;
        int t = 0;
        int[] distinct = new int[list.size()];

        for (int i = 0; i < list.size()-1; i++) {
            former = list.get(i).getEnd()-list.get(i).getBegin();
            latter = list.get(i+1).getEnd()-list.get(i+1).getBegin();
            if(latter<=former)
            {
                t=latter;
            }
        }
        int maxtime = time -t;
        return maxtime;
    }
    public static void main(String[] args) {
        File input1 = new File("10.in");
        File output1 = new File("10.out");
        int result1 = FindBestTime(input1);
        output_result(result1,output1);
        
    }
}

