package com.itdragon.list;

import java.util.Arrays;

/**
 * ArrayList 顾名思义是基于数组实现的一个集合
 * ArrayList 查询元素快，中间插入，删除元素慢。
 * 查询快是因为ArrayList是通过下标直接定位到数据。
 * 插入，删除慢是因为ArrayList底层需要将数组移位。
 * 允许添加重复的值，允许添加空值，输出和输入的顺序一致，非线程安全。
 * ArrayList的底层是基于动态数组
 * 
 * @author itdragon
 * https://www.cnblogs.com/nullllun/p/8390675.html
 * http://www.coolblog.xyz/
 */
public class ITDragonArrayList {
	
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10; // Default initial capacity.
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	transient Object[] elementData; // non-private to simplify nested class access
	private int size;
	
	public ITDragonArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
	
	public boolean add(Object e) { // 集合尾部插入元素
        ensureCapacityInternal(size + 1); 
        elementData[size++] = e;
        return true;
    }
	
	public void add(int index, Object element) {// 集合指定位置插入元素
	    rangeCheckForAdd(index);
	    ensureCapacityInternal(size + 1);
	    // 将 index 及其之后的所有元素都向后移一位
	    System.arraycopy(elementData, index, elementData, index + 1, size - index);
	    elementData[index] = element;
	    size++;
	}
	
	public int size() {
        return size;
    }
	
	public Object get(int index) {
        rangeCheck(index); // 判断下标是否越界
        return elementData[index];
    }
	
	public Object set(int index, Object element) {
        rangeCheck(index);

        Object oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }
	
	public boolean remove(Object o) {
        if (null == o) {
            for (int index = 0; index < size; index++) {
            	if (elementData[index] == null) {
            		fastRemove(index);
            		return true;
            	}
            }
        } else {
            for (int index = 0; index < size; index++) {
            	if (o.equals(elementData[index])) {
            		fastRemove(index);
            		return true;
            	}
            }
        }
        return false;
    }
	
	public void clear() {
        for (int i = 0; i < size; i++) {
        	elementData[i] = null;
        }
        size = 0;
    }
	
	private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (0 < numMoved) { 
        	// 如果删除的元素不是最后一个，则将从index后面的数组往前移。五个参数分别代表，原数组，原数组开始下标，目标数组，目标数组开始下标，长度。
        	System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null; // 清理工作让垃圾回收器去做。
    }
	
	/**
	 * 检查下标是否越界
	 * @param index
	 */
	private void rangeCheck(int index) {
        if (index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
	
	private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
	
	/**
	 * 扩容方法
	 * 如果集合为空，则扩容的长度是最小为10
	 * @param minCapacity 扩容的长度
	 */
	private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }
	
	/**
	 * 自定义的扩容规则，源码中的方法远比这个复杂，扩容后一定要负责数组
	 * @param minCapacity
	 */
	private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
        	minCapacity = minCapacity - MAX_ARRAY_SIZE > 0 ? MAX_ARRAY_SIZE : minCapacity;
            elementData = Arrays.copyOf(elementData, minCapacity);// 第一个参数是需要拷贝的数组，第二个参数是拷贝的长度
        }
    }
	
	public static void main(String[] args) {
		ITDragonArrayList itdragonList = new ITDragonArrayList();
		for (int i = 0; i < 5; i++) {
			itdragonList.add("itdragon-" + i);
		}
		itdragonList.remove("itdragon-3");
		itdragonList.add(2, "add-index");
//		itdragonList.clear();
//		itdragonList.set(3, "itdragon-33");
		for (int i = 0; i < itdragonList.size(); i++) {
			System.out.println(itdragonList.get(i));
		}
	}

}
