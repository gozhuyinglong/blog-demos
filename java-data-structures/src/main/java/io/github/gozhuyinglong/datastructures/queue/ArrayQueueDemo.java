package io.github.gozhuyinglong.datastructures.queue;

/**
 * 队列 - 数组实现
 *
 * @author 码农StayUp
 * @date 2020/11/26 0026
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
        System.out.println("出队： --> " + queue.get());
        System.out.println("入队：1 --> " + queue.add(1));
        System.out.println("入队：2 --> " + queue.add(2));
        System.out.println("入队：3 --> " + queue.add(3));
        System.out.println("入队：4 --> " + queue.add(4));
        System.out.println("入队：5 --> " + queue.add(5));

        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
        System.out.println("出队： --> " + queue.get());
        System.out.println("入队：6 --> " + queue.add(6));
        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
        System.out.println("入队：7 --> " + queue.add(7));
        System.out.println("出队： --> " + queue.get());
        System.out.println("出队： --> " + queue.get());
        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
        System.out.println("入队：8 --> " + queue.add(8));
        System.out.println("入队：9 --> " + queue.add(9));
        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
        System.out.println("出队： --> " + queue.get());
        System.out.println("出队： --> " + queue.get());
        System.out.println("出队： --> " + queue.get());
        System.out.println("出队： --> " + queue.get());
        System.out.println("出队： --> " + queue.get());
        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
        System.out.println("入队：10 --> " + queue.add(10));
        System.out.printf("头指针: %s\t尾指针: %s\t队列大小: %s\t容量: %s\n", queue.head, queue.tail, queue.size(), queue.capacity);
    }


    private static class ArrayQueue<T> {

        private final T[] queue; // 存储队列数据元素
        private final int capacity; // 容量
        private int head = 0; // 头部指针，指向队头元素
        private int tail = 0; // 尾部指针，指向下一个待入队元素的存储位置

        public ArrayQueue(int capacity) {
            this.capacity = capacity + 1; // 环形队列需要空出一个位置，来满足队列满时head与tail不重合
            this.queue = (T[]) new Object[this.capacity];
        }

        /**
         * 向队列添加一个元素
         *
         * @param data
         * @return
         */
        public boolean add(T data) {
            // 队列满，添加失败
            if (isFull()) {
                return false;
            }
            // tail指向下一个待入队元素的存储位置，所以先赋值再让指针加1
            queue[tail] = data;
            // 环形数组需要取模运算
            tail = (tail + 1) % capacity;
            return true;
        }

        /**
         * 从队列中获取一个元素
         *
         * @return
         */
        public T get() {
            if (isEmpty()) {
                return null;
            }
            // head指向头元素位置，所以先取出再让指针加1
            T data = queue[head];
            // 环形数组需要取模运算
            head = (head + 1) % capacity;
            return data;
        }

        /**
         * 当前队列大小
         *
         * @return
         */
        public int size() {
            int size = tail - head;
            if (size < 0) {
                size += capacity;
            }
            return size;
        }

        /**
         * 队列是否为空：当tail与head指向同一位置时，表示队列为空
         *
         * @return
         */
        public boolean isEmpty() {
            return tail == head;
        }

        /**
         * 队列是否已满：因为预留了一个位置，所以tail需要加1；环形队列需要取模运算
         *
         * @return
         */
        public boolean isFull() {
            return head == (tail + 1) % capacity;
        }

    }
}
