package Unit_3.exercise;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by nirpis.hu on 2017/8/28.
 *
 * Question: 给定两个已排序的表L1,L2 只使用基本的表操作编写计算L1交L2的过程
 *
 */

public class Q3_4 {

    public static <AnyType extends Comparable<? super AnyType>> void
            intersection(List<AnyType> L1, List<AnyType> L2, List<AnyType> Intersect) {
        ListIterator<AnyType> iterL1 = L1.listIterator();
        ListIterator<AnyType> iterL2 = L2.listIterator();

        AnyType itemL1 = null;
        AnyType itemL2 = null;

        //get first item in each list
        if (iterL1.hasNext() &&  iterL2.hasNext()) {
            itemL1 = iterL1.next();
            itemL2 = iterL2.next();
        }

        while (itemL1 != null && itemL2 != null) {
            int compareResult = itemL1.compareTo(itemL2);
            if (compareResult == 0) {
                Intersect.add(itemL1);
                itemL1 = iterL1.hasNext()?iterL1.next():null;
                itemL2 = iterL2.hasNext()?iterL2.next():null;
            } else if (compareResult > 0) {
                itemL2 = iterL2.hasNext()?iterL1.next():null;
            } else {
                itemL1 = iterL1.hasNext()?iterL1.next():null;
            }
        }

    }
}
