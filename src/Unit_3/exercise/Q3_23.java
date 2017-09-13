package Unit_3.exercise;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by nirpis.hu on 2017/8/30.
 *
 * Question : a.写出一个程序，将包含(,), +, -, *和/等符号的中缀表达式转换成后缀表达式
 *            b.将取幂运算符添加到指令系统中
 *            c.编写一个程序将后缀表达式转换成中缀表达式
 *
 */
public class Q3_23 {
    public static void InFixToPostFix() {
        Stack<Character> s = new Stack<>();
        String expression;
        Character token;
        int i = 0;

        Scanner sc = new Scanner(System.in);
        expression = sc.next();

        while ((token = expression.charAt(i++)) != '=') {
            if (token >= 'a' && token <= 'z')
                System.out.print(token + " ");
            else {
                switch (token) {
                    case '(' : s.push(token);
                        break;
                    case ')' :
//                             while ((token = expression.charAt(expression.length())) != ')') {
//                                 s.push(token);
//                             }
//                             break;
                               while (!s.empty() && s.peek() != ')') {
                                   System.out.println(s.pop() + " ");
                               }
                               s.pop();    //remove ')'
                               break;
                    case '*' :
                    case '/' : while (!s.isEmpty() && s.peek() != '+'
                                   && s.peek() != '-' && s.peek() != '(') {
                                   System.out.println(s.pop());
                               }
                               s.push(token);
                               break;
                    case '+' :
                    case '-' : while (!s.isEmpty() && s.peek() != '(') {
                                   System.out.println(s.pop() + " ");
                               }
                               s.push(token);
                               break;
                    case '^' : while (!s.isEmpty() && s.peek() != '(') {
                                   System.out.println(s.pop() + " ");
                               }
                               s.push(token);
                               break;

                }
            }
        }

        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
        System.out.println();
    }
}
