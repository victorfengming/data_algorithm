package com.atguigu.search;

/**
 * ClassName:  <br/>
 * Description:  <br/>
 * Date: 2021-02-23 15:01 <br/>
 * @project data_algorithm
 * @package com.atguigu.search
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89}; // 没有顺序的数组
        int index = seqSearch(arr, -11);
        if (index == -1) {
            System.out.println("就离谱!,没有找到");
        } else {
            System.out.println("找到了 下标为="+index);
        }
    }

    /**
     * 这里我们实现的线性查找,是找到一个满足条件的就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        // 线性查找是逐一比对,发现有相同的值,就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                // 如果找到了
                return i;
            }
        }
        // 如果没有找到
        return -1;
    }

}
