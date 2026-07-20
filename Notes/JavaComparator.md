# Java Comparator — Simply

A **Comparator** answers one question: *"Given two things, which comes first?"*

## The one rule

You write a method that takes two items `(a, b)` and returns:

| Return | Meaning |
|--------|---------|
| **negative** (e.g. `-1`) | `a` comes **before** `b` |
| **zero** (`0`) | they're **equal** (order doesn't matter) |
| **positive** (e.g. `1`) | `a` comes **after** `b` |

That's the entire concept. Everything else is shortcuts.

## The mental trick for ascending order

> **`a - b`  →  ascending** (small first)
> **`b - a`  →  descending** (big first)

Why? If `a=2, b=5`: `a - b = -3` (negative) → so `2` comes before `5`. Ascending. ✅

## Sorting integers

```java
Integer[] arr = {5, 2, 8, 1};

// Ascending
Arrays.sort(arr, (a, b) -> a - b);   // 1, 2, 5, 8

// Descending
Arrays.sort(arr, (a, b) -> b - a);   // 8, 5, 2, 1
```

`(a, b) -> a - b` is a **lambda** — a short way to write the comparator without a whole class.

> ⚠️ `int[]` (primitive) does **not** accept a comparator. You need `Integer[]` (object). Common gotcha.

## Sorting strings / custom rules

```java
String[] names = {"Charlie", "Alice", "Bob"};

Arrays.sort(names, (a, b) -> a.compareTo(b));   // alphabetical
// or just: Arrays.sort(names);                 // same thing (natural order)
```

`compareTo` already returns neg/zero/pos, so lean on it instead of subtracting.

## Sorting objects by a field

```java
class Person {
    String name;
    int age;
}

Person[] people = ...;

// By age, ascending
Arrays.sort(people, (a, b) -> a.age - b.age);

// By name, alphabetical
Arrays.sort(people, (a, b) -> a.name.compareTo(b.name));
```

## The clean modern way

```java
// By age
Arrays.sort(people, Comparator.comparingInt(p -> p.age));

// By age descending
Arrays.sort(people, Comparator.comparingInt((Person p) -> p.age).reversed());

// By age, then break ties by name
Arrays.sort(people,
    Comparator.comparingInt((Person p) -> p.age)
              .thenComparing(p -> p.name));
```

## ⚠️ Warning about `a - b`

`a - b` can **overflow** with huge numbers (e.g. `Integer.MAX_VALUE - (-5)` wraps to a wrong sign). For safety with real ints, prefer:

```java
Arrays.sort(arr, (a, b) -> Integer.compare(a, b));   // ascending, no overflow
```

Use `a - b` for learning/small values; use `Integer.compare(a, b)` for production.
