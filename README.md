Fava is a functional  library for java, it's an extention for Java collection framework.

## User Fava List

The two essential class is `FList` and `FArrayList`. `FList` extends JDK's `List`, `FArrayList` extends `ArrayList`

### Create Fava List

Create from exist list
```java
    List originalList = ....   //JDK list
    FList fList = new FArrayList(originalList);
```

Because `FArrayList` extends `ArrayList`, you also can create it directly
```java
    List<String> fList = new FArrayList<String>();
    fList.add("one");  //use jdk's function
```

### Use Functional Operation

Suppose you have a `FList` contains [1,2,3,4,5]

    FList<Integer> ints = FLists.create(1,2,3,4,5);

Each operation

```java
    int.each(new Consumer<Integer>(){
        public void apply(Integer e) {
            System.out.print(e);
        }
    });
```
    
Find the first element match the Predicate

```java
    int result = int.find(new Predicate<Integer>(){    
        public boolean apply(Integer e){
            return e > 3;
        }
    });
```
    
Find all elements match the Predicate

```java
    FList<Integer> results = ints.findAll(new Predicate<Integer>(){
        public boolean apply(Integer e){
            return e % 2 == 0;  //the int value is even
        }
    });    
   ``` 
Convert list to another different type list, use `map()` or `collect()`

```java
    FList<String> results = ints.map(new Function<Integer,String>(){
        public String apply(Integer e){
            return String.valueOf(e);
        }
    });
```

Judge is there any element match Predicate

```java
    boolean hasAnyBiggerThanThree = ints.any(new Predicate<Integer>(){
        public boolean apply(Integer e) {
            return e > 3;
        }
    });
   ```
 
Judge all elements match Predicate

```java
    boolean allBiggerThanThree = ints.all(new Predicate<Integer>(){
        public boolean apply(Integer e) {
            return e > 3;
        }
    });
   ``` 
Fold operation, such as sum list

```java
    int result = ints.fold(0, new FoldFunction<Integer, Integer>(){
        public Integer apply(Integer element, Integer init) {
            return element + init;
        }
    });
   ``` 
    
There are more functions such as `reject`, `deleteIf`, please see Fava' java doc.     

### Functions in fava

There are three basic function shape in fava. 

* _Consumer_  - operate on object and not return
* _Predicate_ - operate on object and return bool value
* _Function_ - operaton on object and return another value
