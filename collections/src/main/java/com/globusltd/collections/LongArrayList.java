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
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * LongArrayList holds longs to Objects. It is intended to be more memory efficient
 * than using an List&lt;Long&gt; to store primitive longs, because it avoids
 * auto-boxing.
 */
public class LongArrayList implements Parcelable {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MIN_CAPACITY_INCREMENT = 12;

    private int mSize;
    private long[] mItems;

    /**
     * Constructs a new instance of {@code LongArrayList}.
     */
    public LongArrayList() {
        mSize = 0;
        mItems = new long[DEFAULT_CAPACITY];
    }

    public LongArrayList(@NonNull final LongArrayList longArrayList) {
        mSize = longArrayList.mSize;
        mItems = new long[newCapacity(mSize)];
        final long[] src = longArrayList.mItems;
        System.arraycopy(src, 0, mItems, 0, mSize);
    }

    private LongArrayList(@NonNull final Parcel in) {
        mSize = in.readInt();
        final int length = in.readInt();
        mItems = new long[length];
        in.readLongArray(mItems);
    }

    /**
     * Adds the specified number at the end of this {@code LongArrayList}.
     *
     * @param item the number to add.
     * @return always true
     */
    public boolean add(final long item) {
        final long[] a = mItems;
        final int s = mSize;
        ensureCapacity(s + 1);
        a[s] = item;
        mSize = s + 1;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    public void add(final int index, final long element) {
        final long[] a = mItems;
        final int s = mSize;
        if (index < 0 || index >= s) {
            throwIndexOutOfBoundsException(index, s);
        }

        ensureCapacity(s + 1);
        System.arraycopy(a, index, a, index + 1, s - index);
        a[index] = element;
        mSize = s + 1;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public long set(final int index, final long element) {
        final int size = mSize;
        if (index < 0 || index >= size) {
            throwIndexOutOfBoundsException(index, size);
        }

        final long[] a = mItems;
        final long oldValue = a[index];
        a[index] = element;
        return oldValue;
    }

    /**
     * Returns the element at the specified location in this list.
     *
     * @param index the index of the element to return.
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if {@code location < 0 || location >= size()}
     */
    public long get(final int index) {
        final int size = mSize;
        if (index < 0 || index >= size) {
            throwIndexOutOfBoundsException(index, size);
        }
        return mItems[index];
    }

    /**
     * Removes the element at the specified location from this list.
     *
     * @param index the index of the object to removeAt.
     * @return the removed element.
     * @throws IndexOutOfBoundsException when {@code location < 0 || location >= size()}
     */
    public long removeAt(final int index) {
        final long[] a = mItems;
        int s = mSize;
        if (index < 0 || index >= s) {
            throwIndexOutOfBoundsException(index, s);
        }

        final long result = a[index];
        System.arraycopy(a, index + 1, a, index, --s - index);
        a[s] = 0L;
        mSize = s;
        return result;
    }

    /**
     * Searches this {@code LongArrayList} for the specified number.
     *
     * @param item the number to search for.
     * @return {@code true} if {@code item} is an element of this
     * {@code LongArrayList}, {@code false} otherwise
     */
    public boolean contains(final long item) {
        return indexOf(item) >= 0;
    }

    /**
     * Removes all elements from this {@code LongArrayList}, leaving it empty.
     *
     * @see #isEmpty()
     * @see #size()
     */
    public void clear() {
        if (mSize != 0) {
            mSize = 0;
        }
    }

    /**
     * Returns the number of elements in this {@code LongArrayList}.
     */
    public int size() {
        return mSize;
    }

    /**
     * Returns if this {@code LongArrayList} contains no elements. This implementation
     * tests, whether {@code size} returns 0.
     *
     * @return {@code true} if this {@code LongArrayList} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    public boolean isEmpty() {
        return (mSize == 0);
    }

    /**
     * Searches this list for the specified number and returns the index of the
     * first occurrence.
     *
     * @param item the number to search for.
     * @return the index of the first occurrence of the number, or -1 if it was
     * not found.
     */
    public int indexOf(final long item) {
        final long[] a = mItems;
        final int s = mSize;
        for (int i = 0; i < s; i++) {
            if (item == a[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a new array containing all elements contained in this
     * {@code LongArrayList}.
     *
     * @return an array of the elements from this {@code LongArrayList}
     */
    public long[] toArray() {
        final int s = mSize;
        final long[] result = new long[s];
        System.arraycopy(mItems, 0, result, 0, s);
        return result;
    }

    /**
     * Returns the hash code value for this LongArrayList. Two lists are defined to have
     * the same hashcode if they contain the same elements in the same order.
     *
     * @return the hash code value for this LongArrayList.
     */
    @Override
    public int hashCode() {
        final long[] a = mItems;
        int hashCode = 1;
        for (int i = 0, s = mSize; i < s; i++) {
            final long e = a[i];
            final int elementHash = (int) (e ^ (e >>> 32));
            hashCode = 31 * hashCode + elementHash;
        }
        return hashCode;
    }

    /**
     * Compares the specified object with this list for equality. Returns
     * <tt>true</tt> if and only if the specified object is also a LongArrayList, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements <tt>e1</tt> and
     * <tt>e2</tt> are <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>.) In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.
     *
     * @param o the object to be compared for equality with this list
     * @return <tt>true</tt> if the specified object is equal to this LongArrayList
     */
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof LongArrayList)) {
            return false;
        }

        final LongArrayList that = (LongArrayList) o;
        final int s = mSize;
        if (that.size() != s) {
            return false;
        }

        final long[] a = mItems;
        for (int i = 0; i < s; i++) {
            long eThis = a[i];
            long eThat = that.get(i);
            if (eThis != eThat) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        final int size = mSize;
        final long[] items = mItems;

        final StringBuilder sb = new StringBuilder();
        sb.append("LongArrayList [ ");
        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            if (i < size - 1) {
                sb.append(", ");
            } else {
                sb.append(" ]");
            }
        }
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(mSize);
        dest.writeInt(mItems.length);
        dest.writeLongArray(mItems);
    }

    private void ensureCapacity(final int minCapacity) {
        final long[] a = mItems;
        final int s = mSize;
        if (minCapacity > a.length - 1) {
            long[] newArray = new long[newCapacity(minCapacity)];
            System.arraycopy(a, 0, newArray, 0, s);
            mItems = newArray;
        }
    }

    private static void throwIndexOutOfBoundsException(final int index, final int size) {
        throw new IndexOutOfBoundsException("Invalid index " + index + ", size is " + size);
    }

    private static int newCapacity(final int currentCapacity) {
        final int increment = (currentCapacity < (MIN_CAPACITY_INCREMENT / 2) ?
                MIN_CAPACITY_INCREMENT : currentCapacity >> 1);
        return currentCapacity + increment;
    }

    public static final Creator<LongArrayList> CREATOR = new Creator<LongArrayList>() {

        @Override
        public LongArrayList createFromParcel(final Parcel in) {
            return new LongArrayList(in);
        }

        @Override
        public LongArrayList[] newArray(final int size) {
            return new LongArrayList[size];
        }

    };

}
