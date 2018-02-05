package com.itdragon.list;

public class ITDragonLinkedList {
	
	transient int size = 0;
	transient ITDragonNode first;
	transient ITDragonNode last;

	public ITDragonLinkedList() {
	}

	public boolean add(Object e) { // 在集合尾部添加元素
		final ITDragonNode lastNode = last;
		final ITDragonNode newNode = new ITDragonNode(lastNode, e, null);
		last = newNode;
		if (lastNode == null) {
			first = newNode;
		} else {
			lastNode.next = newNode;
		}
		size++;
		return true;
	}
	
	public int size() {
        return size;
    }
	
	public Object get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }
	
	ITDragonNode node(int index) {
        if (index < (size >> 1)) { // size向右移一位，相当于除以2
        	ITDragonNode x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
        	ITDragonNode x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }
	
	public void add(int index, Object element) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }
	
	public boolean remove(Object o) {
        if (o == null) {
            for (ITDragonNode x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (ITDragonNode x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
	
	public Object set(int index, Object element) {
        checkElementIndex(index);
        ITDragonNode x = node(index);
        Object oldVal = x.item;
        x.item = element;
        return oldVal;
    }
	
	Object unlink(ITDragonNode x) {
        final Object element = x.item;
        final ITDragonNode next = x.next;
        final ITDragonNode prev = x.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return element;
    }
	
	void linkLast(Object e) {
        final ITDragonNode lastNode = last;
        final ITDragonNode newNode = new ITDragonNode(lastNode, e, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
        	lastNode.next = newNode;
        } 
        size++;
    }

    void linkBefore(Object e, ITDragonNode succ) {
        final ITDragonNode pred = succ.prev;
        final ITDragonNode newNode = new ITDragonNode(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }
	
	/**
	 * 检查元素是否越界
	 * @param index
	 */
	private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
	
	private void checkPositionIndex(int index) {
        if (!(index >= 0 && index <= size)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
	
	public static void main(String[] args) {
		ITDragonLinkedList itdragonList = new ITDragonLinkedList();
		for (int i = 0; i < 5; i++) {
			itdragonList.add("itdragon-" + i);
		}
//		itdragonList.remove("itdragon-3");
//		itdragonList.set(3, "itdragon-33");
		itdragonList.add(2, "add-index");
		for (int i = 0; i < itdragonList.size(); i++) {
			System.out.println(itdragonList.get(i));
		}
	}

	private static class ITDragonNode {
		Object item;
		ITDragonNode next;
		ITDragonNode prev;

		ITDragonNode(ITDragonNode prev, Object element, ITDragonNode next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}
}
