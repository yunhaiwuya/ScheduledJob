package com.scheduled.JCronTab;

import java.util.Date;

/**
 * @author cjm 
 * @desc
 * @createDate 2019年7月22日下午8:20:54
 *
 */
public class JCronTask2 implements Runnable{
	private static int count = 0;
	 
    private static String[] args;
 
    public JCronTask2(String[] args) {
        System.out.println("--------------Task2-----------------");
        System.out.println("Current Time = " + new Date() + ", Count = "
                + count++);
        JCronTask2.args = args;
    }
 
    @Override
    public void run() {
        System.out.println("enter into run method");
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
         System.out.print("This is arg " + i + " " + args[i] + "\n");
            }
        }
    }
}
