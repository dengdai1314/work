package com.practice.customannotation;

/**
 * @author dengdai
 * @email 2900351160@qq.com
 * @date 2020/5/2717:16
 * @description
 */
public class Test {
    @Annotation_Test
    public void method1(){
        System.out.println("method1正常运行 = " + (1+1));
    }
    @Annotation_Test
    public void method2(){
        System.out.println("method2正常运行 = " + (2*3));
    }

    @Annotation_Test
    public void method3(){
        System.out.println("method3正常运行 = " + (2/0));
    }
}
