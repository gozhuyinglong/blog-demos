package com.github.gozhuyinglong.datastructures.queue;

/**
 * 队列 - 数组实现
 *
 * @author ZhuYinglong
 * @date 2020/11/26 0026
 */
public class ArrayQueueDemo {


    private static class ArrayQueue<T> {

        private final int queue[]; // 存储队列数据元素
        private int capacity; // 容量
        private int head = -1; // 头部指针
        private int tail = -1; // 尾部指针

        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            this.queue = new int[capacity];
        }

        /**
         * 向队列添加一个元素
         *
         * @param data
         * @return
         */
        public boolean add(int data) {
            // 队列满，添加失败
            if (size() == capacity) {
                throw new IndexOutOfBoundsException("Queue full");
            }
            // 因为是环形数组，所以要求模
            tail = (tail + 1) % capacity;
            queue[tail] = data;
            return true;
        }

        /**
         * 从队列中获取一个元素
         *
         * @return
         */
        public int get() {
            if (size() <= 0) {
                throw new IndexOutOfBoundsException("Queue empty");
            }
            head = (head + 1) % capacity;
            return queue[head];
        }

        /**
         * 当前队列大小
         *
         * @return
         */
        public int size() {
            int size = tail - head + 1;
            if (size < 0) {
                size += capacity;
            }
            return size;
        }

    }
}
