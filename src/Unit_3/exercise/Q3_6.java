package Unit_3.exercise;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by nirpis.hu on 2017/8/28.
 *
 * Question: N个人编号从1到N，围坐成一个圆圈。从1号传递一个热土豆，经过M次传递后拿着热土豆的人被清除
 *           出局，围坐的圆圈缩紧由坐在被清除的人后面的人拿起热土豆继续进行游戏。最后剩下的两个人取胜
 *           因此，如果M=0和N=5，则游戏人依序被清除，5号游戏人获胜。如果M=1和N=5，那么被清除的人的
 *           顺序是2，4，1，5。
 *           a.编写一个程序解决M和N在一般值下的Josephus问题，应使程序尽可能的提高效率，要确保能够
 *           清除各个单元
 *           b.你的程序运行时间是多少
 *
 */
public class Q3_6 {
    public static void pass(int m, int n) {
        //numLeft 每轮人数总和
        //mPrime 每轮m % numLeft的值
        int i, j, mPrime, numLeft;

        ArrayList<Integer> L = new ArrayList<>();

        for (i = 1; i <= n; i++)
            L.add(i);

        ListIterator<Integer> iter = L.listIterator();
        Integer item = 0;

        numLeft = n;
        mPrime = m % n;

        for (i = 0; i < n; i++) {
            mPrime = m % numLeft;
            if (mPrime <= numLeft/2) {
                if (iter.hasNext())
                    item = iter.next();
                for (j = 0; j < mPrime; j++) {
                    if (!iter.hasNext())
                        iter = L.listIterator();
                    item = iter.next();
                }
            } else {
                for (j = 0; j < numLeft - mPrime; j++) {
                    if (!iter.hasPrevious())
                        iter = L.listIterator();
                    item = iter.previous();
                }
            }
            System.out.println("Removed " + item + " ");
            iter.remove();
            if (!iter.hasNext())
                iter = L.listIterator();
            System.out.println();
            for (Integer x : L)
                System.out.println(x + " ");
            System.out.println();
            numLeft--;
        }
        System.out.println();
    }
}
