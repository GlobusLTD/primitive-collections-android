/*
 * Copyright 2017 Globus Ltd.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.globusltd.collections;

import android.os.Parcel;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class IntArrayListTest {
    
    @Test
    public void testNewIntArrayListIsEmpty() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        assertTrue(intArrayList.isEmpty());
    }
    
    @Test
    public void testAddItem() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertThat(intArrayList.size(), is(3));
    }
    
    @Test
    public void testGetItem() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertThat(intArrayList.get(1), is(200));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        final IntArrayList intArrayList = new IntArrayList();
        assertThat(intArrayList.get(0), is(100));
    }
    
    @Test
    public void testRemoveItem() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertThat(intArrayList.remove(1), is(200));
    }
    
    @Test
    public void testToArray() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        final int[] array = intArrayList.toArray();
        assertArrayEquals(new int[] { 100, 200, 300 }, array);
    }
    
    @Test
    public void testItemsAfterRemove() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        intArrayList.remove(1);
        
        final int[] array = intArrayList.toArray();
        assertArrayEquals(new int[] { 100, 300 }, array);
    }
    
    @Test
    public void testContainsItem() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertTrue(intArrayList.contains(200));
    }
    
    @Test
    public void testDoesNotContainItem() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertFalse(intArrayList.contains(400));
    }
    
    @Test
    public void testClearItems() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        intArrayList.clear();
        
        assertTrue(intArrayList.isEmpty());
    }
    
    @Test
    public void testExistingIndexOf() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertThat(intArrayList.indexOf(200), is(1));
    }
    
    @Test
    public void testNotExistingIndexOf() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertThat(intArrayList.indexOf(400), is(-1));
    }
    
    @Test
    public void testHashcode() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        final int[] array = intArrayList.toArray();
        assertThat(intArrayList.hashCode(), is(Arrays.hashCode(array)));
    }
    
    @Test
    public void testEquals() throws Exception {
        final IntArrayList intArrayList1 = new IntArrayList();
        intArrayList1.add(100);
        intArrayList1.add(200);
        intArrayList1.add(300);
        
        final IntArrayList intArrayList2 = new IntArrayList();
        intArrayList2.add(100);
        intArrayList2.add(200);
        intArrayList2.add(300);
        
        assertTrue(intArrayList1.equals(intArrayList2));
    }
    
    @Test
    public void testNotEquals1() throws Exception {
        final IntArrayList intArrayList1 = new IntArrayList();
        intArrayList1.add(100);
        intArrayList1.add(200);
        intArrayList1.add(300);
        
        final IntArrayList intArrayList2 = new IntArrayList();
        intArrayList2.add(300);
        
        assertFalse(intArrayList1.equals(intArrayList2));
    }
    
    @SuppressWarnings("all")
    @Test
    public void testNotEquals2() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        assertFalse(intArrayList.equals(null));
    }
    
    @SuppressWarnings("all")
    @Test
    public void testNotEquals3() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.add(300);
        
        final int[] array = new int[3];
        assertFalse(intArrayList.equals(array));
    }
    
    @Test
    public void testToString() {
        final IntArrayList intArrayList = new IntArrayList();
        intArrayList.add(100);
        intArrayList.add(200);
        
        assertEquals(intArrayList.toString(), "IntArrayList [ 100, 200 ]");
    }
    
    @Test
    public void testCopyConstructor1() throws Exception {
        final IntArrayList intArrayList1 = new IntArrayList();
        intArrayList1.add(100);
        intArrayList1.add(200);
        intArrayList1.add(300);
        
        final IntArrayList intArrayList2 = new IntArrayList(intArrayList1);
        assertEquals(intArrayList1, intArrayList2);
    }
    
    @Test
    public void testCopyConstructor2() throws Exception {
        final IntArrayList intArrayList1 = new IntArrayList();
        intArrayList1.add(100);
        intArrayList1.add(200);
        intArrayList1.add(300);
        
        final IntArrayList intArrayList2 = new IntArrayList(intArrayList1);
        
        intArrayList1.remove(1);
        
        assertNotEquals(intArrayList1, intArrayList2);
    }
    
    @Test
    public void testCapacityGrowth() throws Exception {
        final IntArrayList intArrayList = new IntArrayList();
        for (int i = 0; i < 100; i++) {
            intArrayList.add(i * 100);
        }
        
        assertThat(intArrayList.size(), is(100));
    }
    
    @Test
    public void testParcelable() throws Exception {
        final IntArrayList intArrayListIn = new IntArrayList();
        intArrayListIn.add(100);
        intArrayListIn.add(200);
        intArrayListIn.add(300);
        
        final Parcel parcel = MockParcel.obtain();
        intArrayListIn.writeToParcel(parcel, 0);
        
        parcel.setDataPosition(0);
        
        final IntArrayList intArrayListOut = IntArrayList.CREATOR.createFromParcel(parcel);
        assertEquals(intArrayListIn, intArrayListOut);
    }
    
    @Test
    public void testParcelableNewArray() throws Exception {
        final IntArrayList[] array = IntArrayList.CREATOR.newArray(2);
        assertThat(array.length, is(2));
    }
    
}
