package io.github.gozhuyinglong.datastructures.array;

/**
 * 稀疏数组
 *
 * @author 码农StayUp
 * @date 2020/11/16
 */
public class SparseArrayDemo {

    public static void main(String[] args) {
        System.out.println("-----------------------普通数组");
        int[][] initialArray = initArray();
        printArray(initialArray);
        System.out.println("-----------------------普通数组 --> 稀疏数组");
        int[][] sparseArray = arrayConvertSparseArray(initialArray);
        printArray(sparseArray);
        System.out.println("-----------------------稀疏数组 --> 普通数组");
        int[][] array = sparseArrayConvertArray(sparseArray);
        printArray(array);
    }

    /**
     * 初始化五子棋数组
     *
     * @return
     */
    static int[][] initArray() {
        // 0为空，1为白子，2为黑子
        int[][] array = new int[15][15];
        array[3][11] = 1;
        array[4][10] = 2;
        array[5][9] = 2;
        array[6][8] = 2;
        array[6][7] = 1;
        array[7][8] = 1;
        array[7][7] = 2;
        array[8][6] = 1;
        return array;
    }

    /**
     * 打印二维数组
     *
     * @param array
     */
    static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%s\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 统计非零值数量
     *
     * @param array
     * @return
     */
    static int valueCount(int[][] array) {
        int count = 0;
        for (int[] row : array) {
            for (int data : row) {
                if (data != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 普通数组转为稀疏数组
     *
     * @param array
     * @return
     */
    static int[][] arrayConvertSparseArray(int[][] array) {
        int rowNum = array.length;
        int colNum = array[0].length;
        int valueNum = valueCount(array);

        int[][] sparseArray = new int[valueNum + 1][3];
        sparseArray[0][0] = rowNum;
        sparseArray[0][1] = colNum;
        sparseArray[0][2] = valueNum;

        int rowCount = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int value = array[i][j];
                if (value != 0) {
                    sparseArray[rowCount][0] = i;
                    sparseArray[rowCount][1] = j;
                    sparseArray[rowCount][2] = value;
                    rowCount++;
                }
            }
        }
        return sparseArray;
    }

    /**
     * 稀疏数组转为普通数组
     *
     * @param sparseArray
     * @return
     */
    static int[][] sparseArrayConvertArray(int[][] sparseArray) {
        int rowNum = sparseArray[0][0];
        int colNum = sparseArray[0][1];
        int valueNum = sparseArray[0][2];

        int[][] array = new int[rowNum][colNum];

        for (int i = 1; i < valueNum + 1; i++) {
            int row = sparseArray[i][0];
            int col = sparseArray[i][1];
            int value = sparseArray[i][2];
            array[row][col] = value;
        }

        return array;
    }
}
