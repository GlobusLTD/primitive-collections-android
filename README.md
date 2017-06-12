# Primitive Collections
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.globus-ltd/collections/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.globus-ltd/collections) [![Build Status](https://travis-ci.org/GlobusLTD/primitive-collections-android.svg?branch=master)](https://travis-ci.org/GlobusLTD/primitive-collections-android)

Primitive collections are intended to be more memory efficient than using an List&lt;Number&gt; to store primitive numbers, because it avoids auto-boxing.

# Download
Gradle:
```groovy
compile 'com.globus-ltd:collections:{latest_release}'
```
Maven:
```xml
<dependency>
  <groupId>com.globus-ltd</groupId>
  <artifactId>collections</artifactId>
  <version>{latest_release}</version>
</dependency>
```

# Usage
<tt>IntArrayList</tt> and <tt>LongArrayList</tt> are resizeable-array implementations. These classes are almost equivalents to <tt>ArrayList</tt>.

Creating an instance and adding elements:
```java
IntArrayList numbers = new IntArrayList();
numbers.add(100);
numbers.add(200); // Now 'numbers' contains 2 elements - 100 and 200
numbers.add(1, 300); // Now 'numbers' contains 3 elements - 100, 300 and 200
numbers.set(2, 400); // Now 'numbers' contains 3 elements - 100, 300 and 400
```

Copying elements from another instance:
```java
IntArrayList copy = new IntArrayList(numbers); // Changes to copy instance does not affect original object
```

Iterating over elements:
```java
int size = numbers.size();
for (int i = 0; i < size; i++) {
    int element = numbers.get(i);
    ...
}
```

Searching for a specified element:
```java
int index = numbers.indexOf(100); 
boolean contains = numbers.contains(100);
```

Removing the elements:
```java
int removedElement = numbers.removeAt(1); // returns the removed element at index = 1
...
numbers.clear(); // removes all elements from 'numbers', leaving it empty.
```

Serialization and deserialization:
```java
private IntArrayList numbers;

@Override 
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
        numbers = savedInstanceState.getParcelable(KEY_NUMBERS);
    } else {
        numbers = new IntArrayList();
    }
}

...

@Override 
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelable(KEY_NUMBERS, numbers);
}
```

# License
    Copyright 2017 Globus Ltd.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
