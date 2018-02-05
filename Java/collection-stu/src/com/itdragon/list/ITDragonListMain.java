package com.itdragon.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class ITDragonListMain {
	
	public static void main(String[] args) {
		List<Integer> arraylist = new ArrayList<>();
		// 添加元素
		for (int i = 0; i < 100; i++) {
			arraylist.add(i);
		}
		// 删除元素
		for (Iterator<Integer> iterator = arraylist.iterator(); iterator.hasNext();) {
			Integer index = (Integer) iterator.next();
			if (0 == index % 2) {
				iterator.remove();
			}
		}
		// 线程安全化
		List<Integer> synchronizedList = Collections.synchronizedList(arraylist);
		// 数组转集合
		Integer[] intArgs = { 1, 2, 3, 4, 5, 6 };
		Arrays.asList(intArgs);
		List<Integer> linkedlist = new LinkedList<>();
	}

}
