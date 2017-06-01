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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class to mock android.os.Parcel objects.
 * Based on {@link https://gist.github.com/Sloy/d59a36e6c51214d0b131}
 */
class MockParcel {
    
    @NonNull
    static Parcel obtain() {
        return new MockParcel().mParcel;
    }
    
    @NonNull
    private final Parcel mParcel;
    
    @NonNull
    private final List<Object> mObjects;
    
    private int mPosition = 0;
    
    private MockParcel() {
        mParcel = mock(Parcel.class);
        mObjects = new ArrayList<>();
        setupMock();
    }
    
    private void setupMock() {
        setupWrites();
        setupReads();
        setupOther();
    }
    
    private void setupWrites() {
        final Answer<Void> writeSingleValueAnswer = new Answer<Void>() {
            @Override
            public Void answer(final InvocationOnMock invocation) throws Throwable {
                final Object parameter = invocation.getArguments()[0];
                mObjects.add(parameter);
                return null;
            }
        };
        doAnswer(writeSingleValueAnswer).when(mParcel).writeString(anyString());
        doAnswer(writeSingleValueAnswer).when(mParcel).writeInt(anyInt());
        doAnswer(writeSingleValueAnswer).when(mParcel).writeLong(anyLong());
        doAnswer(writeSingleValueAnswer).when(mParcel).writeIntArray(Matchers.<int[]>any());
        doAnswer(writeSingleValueAnswer).when(mParcel).writeLongArray(Matchers.<long[]>any());
    }
    
    private void setupReads() {
        when(mParcel.readInt()).thenAnswer(readParceledValueAnswer());
        when(mParcel.readLong()).thenAnswer(readParceledValueAnswer());
        
        final Answer<Void> readIntArrayAnswer = new Answer<Void>() {
            @Override
            public Void answer(final InvocationOnMock invocation) throws Throwable {
                final int[] array = (int[]) mObjects.get(mPosition++);
                final int[] argument = (int[]) invocation.getArguments()[0];
                System.arraycopy(array, 0, argument, 0, argument.length);
                return null;
            }
        };
        doAnswer(readIntArrayAnswer).when(mParcel).readIntArray(Matchers.<int[]>any());
        
        final Answer<Void> readLongArrayAnswer = new Answer<Void>() {
            @Override
            public Void answer(final InvocationOnMock invocation) throws Throwable {
                final long[] array = (long[]) mObjects.get(mPosition++);
                final long[] argument = (long[]) invocation.getArguments()[0];
                System.arraycopy(array, 0, argument, 0, argument.length);
                return null;
            }
        };
        doAnswer(readLongArrayAnswer).when(mParcel).readLongArray(Matchers.<long[]>any());
    }
    
    @SuppressWarnings("unchecked")
    @Nullable
    private <T> Answer<T> readParceledValueAnswer() {
        return new Answer<T>() {
            @Override
            public T answer(final InvocationOnMock invocation) throws Throwable {
                return (T) mObjects.get(mPosition++);
            }
        };
    }
    
    private void setupOther() {
        final Answer<Void> setDataPositionAnswer = new Answer<Void>() {
            @Override
            public Void answer(final InvocationOnMock invocation) throws Throwable {
                mPosition = ((Integer) invocation.getArguments()[0]);
                return null;
            }
        };
        doAnswer(setDataPositionAnswer).when(mParcel).setDataPosition(anyInt());
    }
    
}
