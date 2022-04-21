package com.atguigu.kmp;

/**
 * ClassName:  <br/>
 * Description:  <br/>
 * Date: 2021-03-08 9:49 <br/>
 * @project data_algorithm
 * @package com.atguigu.kmp
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        // 测试暴力匹配算法
        String str1 = "硅谷 啥改改改 桑格个你 是哪个更尚硅谷你好素尚硅谷你昂硅谷阿森的";
        String str2 = "尚硅谷你";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    // 暴力匹配算法实现
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;// i 索引指向s1
        int j = 0;// j 索引指向s2

        while (i < s1Len && j < s2Len) {// 保证匹配时,不越界
            //
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                // 没有匹配成功
                // 如果适配(即str
                i = i - (j - 1);
                j = 0;

            }
        }

        // 判断是否有匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
