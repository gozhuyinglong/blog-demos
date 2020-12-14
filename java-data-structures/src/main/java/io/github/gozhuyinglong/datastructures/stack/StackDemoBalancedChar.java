package io.github.gozhuyinglong.datastructures.stack;

import java.util.Stack;

/**
 * 栈的应用 - 平衡符
 *
 * @author 码农StayUp
 * @date 2020/11/25 0025
 */
public class StackDemoBalancedChar {

    public static void main(String[] args) {

        BalancedChar balancedChar = new BalancedChar();
        String str = "[()][{}][][((()))]";
        boolean ok = balancedChar.isOk(str);
        System.out.printf("字符串：%s\t----> %s", str, ok);
    }

    private static class BalancedChar {
        private final char[] openArray = {'(', '[', '{'};  // 左括号
        private final char[] closeArray = {')', ']', '}'}; // 右括号

        /**
         * 判断字符串是否正确
         *
         * @param str
         * @return
         */
        public boolean isOk(String str) {
            // 使用 Java 自带的 Stack 类
            Stack<Character> stack = new Stack<>();

            boolean ok = true; // 判断字符串是否正确

            for (char c : str.toCharArray()) {

                // 若不是平衡符则忽略
                if (!isBalancedChar(c)) {
                    continue;
                }

                // 如果是左括号，则入栈
                if (isOpen(c)) {
                    stack.push(c);
                    continue;
                }

                // 如果是右括号，而栈为空则报错
                if (stack.empty()) {
                    ok = false;
                    break;
                }
                // 如果是右括号，从栈中取出一个元素，并与当前元素判断是否是一对，若不是一对则报错
                Character open = stack.pop();
                if (!isTwain(open, c)) {
                    ok = false;
                }
            }

            return ok && stack.empty();
        }

        /**
         * 是否为左括号
         *
         * @param c
         * @return
         */
        public boolean isOpen(char c) {
            return inArray(openArray, c);
        }

        /**
         * 是否为右括号
         *
         * @param c
         * @return
         */
        public boolean isClose(char c) {
            return inArray(closeArray, c);
        }

        /**
         * 是否是平衡符
         */
        public boolean isBalancedChar(char c) {
            return isOpen(c) || isClose(c);
        }

        /**
         * 是否在数组中
         *
         * @param charArray
         * @param c
         * @return
         */
        public boolean inArray(char[] charArray, char c) {
            for (char c1 : charArray) {
                if (c1 == c) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 是否一对平衡符
         *
         * @param open
         * @param close
         * @return
         */
        public boolean isTwain(char open, char close) {
            switch (open) {
                case '(':
                    if (close == ')') {
                        return true;
                    }
                case '[':
                    if (close == ']') {
                        return true;
                    }
                case '{':
                    if (close == '}') {
                        return true;
                    }
                default:
                    return false;
            }
        }

    }
}
