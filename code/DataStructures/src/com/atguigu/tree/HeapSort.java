package com.atguigu.tree;

import java.util.Arrays;

/**
 * ClassName:  <br/>
 * Description:  <br/>
 * Date: 2021-02-26 14:31 <br/>
 *
 * @project data_algorithm
 * @package com.atguigu.tree
 */
public class HeapSort {
    public static void main(String[] args) {
        /**
         * 要求,吧一个
         *
         * ??? 如何将一个给定的数组,
         * 以一个大顶堆的方式来搞出来
         */
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
        /*
        * 堆排序!
        第一次[4, 9, 8, 5, 6]

        Process finished with exit code 0
        * */
    }

    //

    /**
     * 编写一个堆排序的方法
     *
     * @param arr
     */
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序!");

//        4, 6, 8, 5, 9
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
            System.out.println("第" + (arr.length / 2 - 1 - i) + "次" + Arrays.toString(arr));
        }

        /*
        * 1. 将无序序列构建成一个堆,根据升序降序需求选择大顶堆或者小顶堆;
        * 1.已经完成了
        2. 将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端;
        3. 重新调整结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素,反复执行调整+交换步骤,直到这个整个序列有序.
        *
        * */
//        5个数你调整4个数就行了arr.length - 1;
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 再次调用
            adjustHeap(arr, 0, j);
        }
    }


    /**
     * 将一个数组(二叉树) 调整成一个大顶堆
     * <p>
     * 方法的功能: 完成,当我们传递了,
     * "      将以i对应的非叶子节点的树调整成大顶堆
     * 举例: int arr[] = {4,6,8,5,9} => i =1 ==>
     * adjustHeap => 得到 {4,9,8,5,6}
     * 如果我们再次调用 adjustHeap 传入的是i =0 ==>
     * adjustHeap 得到{4,9,8,5,6}=> {9,6,8,5,4}
     *
     * @param arr
     * @param i      表示非叶子节点在 数组中的索引
     * @param lenght 表示对多少个元素进行调整, length 是在逐渐减少
     */
    public static void adjustHeap(int arr[], int i, int lenght) {
        int temp = arr[i];  // 先取出当前元素的值,保存在临时变量
        // 开始调整
        //
        /**
         * 说明:
         * 1.k代表的是以i为非叶子节点的左子节点
         * k = i * 2 + 1 中k是i节点的左子节点
         */
        for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            // 找i的柚子节点大还是左子节点大
            if (arr[k] < arr[k + 1] && k + 1 < lenght) {
                // 说明左子节点的值 小于 右子节点的值
                k++;    //让k指向右子节点
                // k 指向i这个下面的 较大的 子节点
            }
            // 现在这个k已经指向了,右子节点后者左子节点最大的值
            if (arr[k] > temp) {
                // 如果子节点大于父节点temp
                arr[i] = arr[k];    // 把较大的值赋给当前的节点
                i = k;      // 让i指向k,继续循环比较
            } else {
                // 这里!!!!
                // 因为这个地方我是 从左至右 从上至下 调整的
                break;
            }
        }
        // 当for循环结束后,我们己经将以i为父节点的树的最大值,放在了最顶上的位置
        // 局部(以i为父节点的)吧这个树调整成了大顶堆
        // 调整完后i不是原来的i了,i是调整中的当前的i
        arr[i] = temp;  // 将temp值放到调整后的位置
        // 这里因为前面 是将子节点中大数字和父节点的数字换了顺序,现在
        // 父节点原来的数字他不能丢了,所以用temp又给到arr[i]

    }

}
