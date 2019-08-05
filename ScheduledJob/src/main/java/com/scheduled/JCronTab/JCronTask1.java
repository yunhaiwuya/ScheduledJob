package com.scheduled.JCronTab;

import java.util.Date;

/**
 * @author cjm 
 * @desc
 * @createDate 2019年7月22日下午8:20:54
 *
 */
public class JCronTask1 {
	private static int count = 0;
    public static void main(String[] args) {
        System.out.println("--------------Task1-----------------");
        System.out.println("Current Time = " + new Date() + ", Count = "
                + count++);
    }
}
