package io.github.gozhuyinglong.datastructures.array;

import java.util.Arrays;

/**
 * 数组
 *
 * @author 码农StayUp
 * @date 2020/11/15 0015
 */
public class ArrayDemo {

    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c", "d"};

        // 在指定下标处插入数据
        String[] insertArray = insert(array, 2, "m");
        for (String arr : insertArray) {
            System.out.print(arr);
        }
        System.out.println();

        // 删除指定下标的数据
        String[] removeArray = remove(array, 1);
        for (String arr : removeArray) {
            System.out.print(arr);
        }

    }

    /**
     * 在指定下标处插入数据
     *
     * @param array
     * @param index
     * @param data
     * @return
     */
    public static String[] insert(String[] array, int index, String data) {
        // 判断参数是否合法
        if (array == null || array.length == 0 || index < 0 || index > array.length) {
            throw new RuntimeException("参数不合法！");
        }
        // 复制出一个长度+1的数组
        String[] newArray = Arrays.copyOf(array, array.length + 1);
        // 将数据从index开始往后移1位；若index为最后一位，则无需移动
        if (array.length > index) {
            System.arraycopy(newArray, index, newArray, index + 1, newArray.length - index - 1);
        }
        // 将值放到index下标上
        newArray[index] = data;
        return newArray;
    }

    /**
     * 删除指定下标的数据
     *
     * @param array
     * @param index
     * @return
     */
    public static String[] remove(String[] array, int index) {
        // 判断参数是否合法
        if (array == null || index < 0 || index >= array.length) {
            throw new RuntimeException("参数不合法！");
        }
        // 将数据从index+1处往前移一位
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        // 复制出长度-1的数组
        return Arrays.copyOf(array, array.length - 1);
    }
}
