package io.github.gozhuyinglong.datastructures.stack;

/**
 * 栈
 *
 * @author 码农StayUp
 * @date 2020/11/25 0025
 */
public class StackDemo {

    public static void main(String[] args) {

        System.out.println("-------------------入站");
        Stack<String> stack = new Stack<>(10);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.print();

        System.out.println("元素大小: " + stack.size());
        System.out.println("栈容量: " + stack.capacity());

        System.out.println("-------------------出站");
        System.out.println("出站元素: " + stack.pop());
        System.out.println("出站元素: " + stack.pop());
        stack.print();
        System.out.println("元素大小: " + stack.size());
        System.out.println("栈容量: " + stack.capacity());
    }

    private static class Stack<E> {
        private int size; // 元素大小
        private final int capacity; // 栈的容量
        transient Object[] elementData; // 元素数据

        public Stack(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + capacity);
            } else {
                this.capacity = capacity;
                elementData = new Object[capacity];
            }
        }

        /**
         * 获取栈的元素大小
         *
         * @return
         */
        public int size() {
            return size;
        }

        /**
         * 获取栈的容量
         *
         * @return
         */
        public int capacity() {
            return capacity;
        }

        /**
         * 入栈
         *
         * @param e
         * @return
         */
        public boolean push(E e) {
            if (size >= capacity) {
                return false;
            }
            elementData[size++] = e;
            return true;
        }

        /**
         * 出栈
         *
         * @return
         */
        public E pop() {
            if (size <= 0) {
                return null;
            }
            return (E) elementData[--size];
        }

        /**
         * 打印元素数据
         */
        public void print() {
            System.out.print("站内元素: ");
            for (int i = 0; i < size; i++) {
                System.out.printf("%s\t", elementData[i]);
            }
            System.out.println();
        }
    }
}
