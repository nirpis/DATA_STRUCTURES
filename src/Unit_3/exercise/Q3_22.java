package Unit_3.exercise;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by nirpis.hu on 2017/8/29.
 *
 * Question: 编写一个程序计算后缀表达式的值
 *
 */
public class Q3_22 {

    public static double evalPostFix() {
        Stack<Double> s = new Stack<>();
        String token;
        Double a, b, result = 0.0;
        boolean isNumber;

        Scanner sc = new Scanner(System.in);
        token = sc.next();
        while (token.charAt(0) != '=') {
            try {
                isNumber = true;
                result = Double.parseDouble(token);
            } catch (Exception e) {
                isNumber = false;
            }
            if (isNumber)
                s.push(result);
            else {
                switch (token.charAt(0)) {
                    case '+' : a = s.pop(); b = s.pop();
                        s.push(a + b); break;
                    case '-' : a = s.pop(); b = s.pop();
                        s.push(a - b); break;
                    case '*' : a = s.pop(); b = s.pop();
                        s.push(a * b); break;
                    case '/' : a = s.pop(); b = s.pop();
                        s.push(a / b); break;
                    case '^' : a = s.pop(); b = s.pop();
                        s.push(Math.exp(a * Math.log(b)));
                        break;
                }
            }
            token = sc.next();
        }
        return s.peek();
    }

}
