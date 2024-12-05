package com.amz.array;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCircularQueueTest {

    class MyCircularQueue {
        private int start = 0;
        private int count = 0;
        private int[] elements;

        public MyCircularQueue(int k) {
            elements = new int[k];
            Arrays.fill(elements, -1);
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            elements[(start + count++) % elements.length] = value;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            } else {
                count--;
                elements[start] = -1;
                start = (start + 1) % elements.length;
                return true;
            }
        }

        public int Front() {
            return elements[start];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            return elements[(start + count - 1) % elements.length];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == elements.length;
        }

        @Override
        public String toString() {
            final StringBuilder s = new StringBuilder();
            for (int i = 0; i < elements.length; i++) {
                if (i == start) {
                    s.append('(');
                    s.append(elements[i]);
                    s.append(')');
                } else {
                    s.append(elements[i]);
                }
                if (i < elements.length - 1) {
                    s.append(',');
                }
            }
            return s.toString();
        }
    }

    @Test
    public void check1() {
        List<Object> output = new ArrayList<>();
        output.add(null);
        MyCircularQueue q = new MyCircularQueue(3);
        output.add(q.enQueue(1));
        output.add(q.enQueue(2));
        output.add(q.enQueue(3));
        output.add(q.enQueue(4));
        output.add(q.Rear());
        output.add(q.isFull());
        output.add(q.deQueue());
        output.add(q.enQueue(4));
        output.add(q.Rear());
        Assertions.assertEquals(Lists.newArrayList(null, true, true, true, false, 3, true, true, true, 4), output);
    }

    @Test
    public void check2() {
        List<Object> output = new ArrayList<>();
        output.add(null);
        MyCircularQueue q = new MyCircularQueue(2);
        output.add(q.enQueue(1));
        output.add(q.enQueue(2));
        output.add(q.deQueue());
        output.add(q.enQueue(3));
        output.add(q.deQueue());
        output.add(q.enQueue(3));
        output.add(q.deQueue());
        output.add(q.enQueue(3));
        output.add(q.deQueue());
        output.add(q.Front());
        Assertions.assertEquals(Lists.newArrayList(null,true,true,true,true,true,true,true,true,true,3), output);
    }

}
