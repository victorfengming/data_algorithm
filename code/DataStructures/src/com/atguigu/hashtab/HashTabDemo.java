package com.atguigu.hashtab;

import java.util.Scanner;

/**
 * ClassName:  <br/>
 * Description:  <br/>
 * Date: 2021-02-24 13:10 <br/>
 * <br/>
 * @project data_algorithm
 * @package com.atguigu.hashtab
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单来测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出雇员");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    int findId = scanner.nextInt();
                    hashTab.findEmpById(findId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

///创建hashTab 管理多条链表

class HashTab{
    // 数组里面放的是链表
    private EmpLinkedList[] empLinkedListArray;
    //
    private Integer size = 7;


    // 构造器
    public HashTab(int size) {
        // 初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // ? 留一个坑
        // 这里能直接用么
        /*
        * add:添加雇员
        list:显示雇员
        exit:退出雇员
        add
        输入id
        tom
        Exception in thread "main" java.util.InputMismatchException
            at java.util.Scanner.throwFor(Scanner.java:864)
            at java.util.Scanner.next(Scanner.java:1485)
            at java.util.Scanner.nextInt(Scanner.java:2117)
            at java.util.Scanner.nextInt(Scanner.java:2076)
            at com.atguigu.hashtab.HashTabDemo.main(HashTabDemo.java:30)

        Process finished with exit code 1

                * */
        // 这个时候不要忘了, 分别初始化 每个链表
        for (int i = 0; i < size; i++) {
            // 数组中的每一个元素都需要创建一把
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员

    public void add(Emp emp) {
        // 根据员工的id,得到该员工应当加入到,哪条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);

    }

    // 不管你是什么操作,总是要找链表
    // 遍历所有的链表
    public void list() {
        // 遍历Hash表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    // 编写一个散列函数
    // 散列函数有很多种写法
    // 这里使用简单的方法-取模法
    public int hashFun(int id) {
        return id % size;
    }

    /**
     * 更根据输入的id,查找雇员
     * @param id
     */
    public void findEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            // 说明找到了
            System.out.println("找到了该雇员");
            System.out.printf("在第%d条链表中找到了该雇员,id = %d",id,empLinkedListNO+1);
        } else {
            System.out.println("在哈希表中,没有找到该雇员~");
        }
    }
}


/**
 * 表示一个雇员
 */
class Emp{
    public int id;
    public String name;
    public Emp next;//next 默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    // 头指针, 执行第一个Emp,因此我们这个链表的head,是直接 指向第一个Emp
    private Emp head;

    //添加雇员到链表
    // 说明.
    // 1. 假定,当添加雇员的时候,id是自增长的,即id 的分配总是从小到大

    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是添加第一个雇员,则使用一个辅助的指针,帮助定位到最后
        Emp currEmp = head;
        while (true) {
            if (currEmp.next == null) {
                // 说明到链表最后
                break;
            }
            // 后移
            currEmp = currEmp.next;
        }
        // 退出时,直接将emp 加入链表
        currEmp.next = emp;

    }

    // 遍历链表的雇员信息
    public void list(int no) {
        // 判断是否为空
        if (head == null) {
            // 说明链表为空
            System.out.println("当前链"+no+"表为空!");
            return;
        }
        // 没有空
//        打印信息
        System.out.println("当前链"+no+"表的信息为");
        // 辅助指针
        Emp currEmp = head;
        while (true) {
            System.out.printf("=> id =%d name = %s\t",currEmp.id,currEmp.name);
            if (currEmp.next == null) {
                // 说明,currEmp 已经是最后节点
                break;
            }
            // 后移 遍历
            currEmp = currEmp.next;
        }
        System.out.println();
    }


    /**
     *     // 根据id 查找雇员
     *    // 如果查找到 ,就返回Emp,如果没有找打到,就返回null
     * @param id
     * @return
     */
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        //辅助指针
        Emp curEmp = head;
        while (true) {
            //
            if (curEmp.id == id) {
                // 找到
                break;
            //  这个时候,currEmp就指向了要查找的雇员
            }
            // 退出
            if (curEmp.next == null) {
                // 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            // 后移
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
