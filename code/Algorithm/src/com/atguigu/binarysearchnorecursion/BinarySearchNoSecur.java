package com.atguigu.binarysearchnorecursion;

/**
 * ClassName:  <br/>
 * Description:  <br/>
 * Date: 2021-03-04 15:30 <br/>
 * @project data_algorithm
 * @package com.atguigu.binarysearchnorecursion
 */
public class BinarySearchNoSecur {
    public static void main(String[] args) {
//        测试
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        // 把数组放进去
        int index = binarySearch(arr, 1);
        System.out.println("index=" + index);
    }

    /**
     * 二分查找的非递归实现
     * @param arr 待查找的数组,arr是升序排序的数组
     * @param target 需要查找的数
     * @return 返回对应下标 , -1 表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            // 说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                // 说明找到了
                return mid;
            } else if (arr[mid] > target) {
                // 大于 目标
                // 进行下一次的
                right = mid - 1; // 需要向左边查找
            } else {
                // 小于目标
                left = mid + 1;// 需要向右边查找
            }
        }
        // 找不到了
        return -1;
    }
}
