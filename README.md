
# Data Structures and Algorithms

In this project, popular algorithms will be implemented. Also necessary data structures which will be used in that algorithms will be implemented. Java language used in implementations. Current progress is below.

## Data Structures

1. <b>List</b> ([com.omeraytekin.data_structures.List](src/com/omeraytekin/data_structures/List.java))</br>
List is an interface which includes necessary methods for list operations (eg. insert, delete, search). It extends Iterable interface which is in java.lang API. Thus this interface ensures that list items can be iterate over forEach. 
    1. <b>ArrayList</b> ([com.omeraytekin.data_structures.ArrayList](src/com/omeraytekin/data_structures/ArrayList.java))</br>
    First implementation of list is by using dynamic array. In dynamic array there is an initial sized array. When this array is full, new bigger array will be created. This new array size will be old array size multiplied by grow factor. After creating new array all elements of old array will be moved to new array.</br>
    <b>Note:</b> When grow factor is between 1 and 2 and array size is small, it can cause multiplying and flooring array size and grow factor is equal to old array size. To prevent this situation, after multiplying and flooring array size and grow factor, It'll be increased by 1.</br>
    To create ArrayList object there is 3 ways.</br>
    - Using default constructor: By using default constructor, initial array size will be 32 and grow factor will be 2.</br>
    ```java
    List<Integer> list = new ArrayList<>();
    ```
    - Using initial size constructor: By using initial size constructor, initial array size will be given value and grow factor will be 2.</br>
    ```java
    List<Integer> list = new ArrayList<>(32);
    ```
    - Using initial size and grow factor constructor: By using initial size constructor, initial array size and grow factor will be given value</br>
    ```java
    List<Integer> list = new ArrayList<>(32, 1.5);
    ```
    2. <b>LinkedList</b> ([com.omeraytekin.data_structures.LinkedList](src/com/omeraytekin/data_structures/LinkedList.java))</br>
    [TBA]
2. <b>Stack</b> ([com.omeraytekin.data_structures.Stack](src/com/omeraytekin/data_structures/Stack.java))</br>
    [TBA]
    1. <b>ArrayStack</b> ([com.omeraytekin.data_structures.ArrayList](src/com/omeraytekin/data_structures/ArrayStack.java))</br>
    [TBA]
    2. <b>LinkedListStack</b> ([com.omeraytekin.data_structures.ArrayList](src/com/omeraytekin/data_structures/LinkedListStack.java))</br>
    [TBA]
