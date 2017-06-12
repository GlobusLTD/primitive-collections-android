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

public class LongArrayListTest {
    
    @Test
    public void testNewLongArrayListIsEmpty() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        assertTrue(longArrayList.isEmpty());
    }
    
    @Test
    public void testAddItem() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertThat(longArrayList.size(), is(3));
    }

    @Test
    public void testAddItemAt1() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);

        longArrayList.add(1, 300);

        assertThat(longArrayList.size(), is(3));
    }

    @Test
    public void testAddItemAt2() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);

        longArrayList.add(1, 300);

        assertThat(longArrayList.get(2), is(200L));
    }

    @Test
    public void testSetItem1() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);

        assertThat(longArrayList.set(0, 300), is(100L));
    }

    @Test
    public void testSetItem2() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.set(0, 300);

        assertThat(longArrayList.get(0), is(300L));
    }
    
    @Test
    public void testGetItem() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertThat(longArrayList.get(1), is(200L));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        final LongArrayList longArrayList = new LongArrayList();
        assertThat(longArrayList.get(0), is(100L));
    }
    
    @Test
    public void testRemoveItem() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertThat(longArrayList.removeAt(1), is(200L));
    }
    
    @Test
    public void testToArray() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        final long[] array = longArrayList.toArray();
        assertArrayEquals(new long[] { 100, 200, 300 }, array);
    }
    
    @Test
    public void testItemsAfterRemove() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        longArrayList.removeAt(1);
        
        final long[] array = longArrayList.toArray();
        assertArrayEquals(new long[] { 100, 300 }, array);
    }
    
    @Test
    public void testContainsItem() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertTrue(longArrayList.contains(200));
    }
    
    @Test
    public void testDoesNotContainItem() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertFalse(longArrayList.contains(400));
    }
    
    @Test
    public void testClearItems() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        longArrayList.clear();
        
        assertTrue(longArrayList.isEmpty());
    }
    
    @Test
    public void testExistingIndexOf() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertThat(longArrayList.indexOf(200), is(1));
    }
    
    @Test
    public void testNotExistingIndexOf() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertThat(longArrayList.indexOf(400), is(-1));
    }
    
    @Test
    public void testHashcode() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        final long[] array = longArrayList.toArray();
        assertThat(longArrayList.hashCode(), is(Arrays.hashCode(array)));
    }
    
    @Test
    public void testEquals() throws Exception {
        final LongArrayList longArrayList1 = new LongArrayList();
        longArrayList1.add(100);
        longArrayList1.add(200);
        longArrayList1.add(300);
        
        final LongArrayList longArrayList2 = new LongArrayList();
        longArrayList2.add(100);
        longArrayList2.add(200);
        longArrayList2.add(300);
        
        assertTrue(longArrayList1.equals(longArrayList2));
    }
    
    @Test
    public void testNotEquals1() throws Exception {
        final LongArrayList longArrayList1 = new LongArrayList();
        longArrayList1.add(100);
        longArrayList1.add(200);
        longArrayList1.add(300);
        
        final LongArrayList longArrayList2 = new LongArrayList();
        longArrayList2.add(300);
        
        assertFalse(longArrayList1.equals(longArrayList2));
    }
    
    @SuppressWarnings("all")
    @Test
    public void testNotEquals2() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        assertFalse(longArrayList.equals(null));
    }
    
    @SuppressWarnings("all")
    @Test
    public void testNotEquals3() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        longArrayList.add(300);
        
        final int[] array = new int[3];
        assertFalse(longArrayList.equals(array));
    }
    
    @Test
    public void testToString() {
        final LongArrayList longArrayList = new LongArrayList();
        longArrayList.add(100);
        longArrayList.add(200);
        
        assertEquals(longArrayList.toString(), "LongArrayList [ 100, 200 ]");
    }
    
    @Test
    public void testCopyConstructor1() throws Exception {
        final LongArrayList longArrayList1 = new LongArrayList();
        longArrayList1.add(100);
        longArrayList1.add(200);
        longArrayList1.add(300);
        
        final LongArrayList longArrayList2 = new LongArrayList(longArrayList1);
        assertEquals(longArrayList1, longArrayList2);
    }
    
    @Test
    public void testCopyConstructor2() throws Exception {
        final LongArrayList longArrayList1 = new LongArrayList();
        longArrayList1.add(100);
        longArrayList1.add(200);
        longArrayList1.add(300);
        
        final LongArrayList longArrayList2 = new LongArrayList(longArrayList1);
        
        longArrayList1.removeAt(1);
        
        assertNotEquals(longArrayList1, longArrayList2);
    }
    
    @Test
    public void testCapacityGrowth() throws Exception {
        final LongArrayList longArrayList = new LongArrayList();
        for (int i = 0; i < 100; i++) {
            longArrayList.add(i * 100);
        }
        
        assertThat(longArrayList.size(), is(100));
    }
    
    @Test
    public void testParcelable() throws Exception {
        final LongArrayList longArrayListIn = new LongArrayList();
        longArrayListIn.add(100);
        longArrayListIn.add(200);
        longArrayListIn.add(300);
        
        final Parcel parcel = MockParcel.obtain();
        longArrayListIn.writeToParcel(parcel, 0);
        
        parcel.setDataPosition(0);
        
        final LongArrayList longArrayListOut = LongArrayList.CREATOR.createFromParcel(parcel);
        assertEquals(longArrayListIn, longArrayListOut);
    }
    
    @Test
    public void testParcelableNewArray() throws Exception {
        final LongArrayList[] array = LongArrayList.CREATOR.newArray(2);
        assertThat(array.length, is(2));
    }
    
}
