Advent of Code 2018
===================

If time permits, make some code :).

Day 1: Chronal Calibration
--------------------------

<https://adventofcode.com/2018/day/1>

Fairly simple task. What took the most time was setting up the folder structures in IntelliJ. Decided to go with Gradle.

Learned that:

* `Set` does not have add method, but `MutableSet` has. Makes sense, but a bit weird coming from Java.

Possible improvements:

* Played around with replacing the `while(true)` with making the input list as infinite sequence:
`generateSequence { intArray.asIterable() }`. Gave it a shot, but it ended out with being a bit more verbose.


Day 2: Inventory Management System
----------------------------------

<https://adventofcode.com/2018/day/2>

Slightly more tricky, with potential for missing input if for loop is terminated wrongly. Added test cases for this.
 
This time got to play with extension function on String.

Learned that:

* `require` and `check` can be used to check argument and state respectively. A bit similar td `assert` but does not need
jvm argument `-ea` to be set.
* Old school for-loops are working on ranges, and not with index variable. E.g. `0..4` (end is included) and 
`0 until 4` (end is excluded). If you want to loop on indices in list, you would use `for (i in list.indices)`.

Possible improvements:

* The double for-loop in `identifyRelatedBoxes` could perhaps be replaced with something more idiomatic. 
* The `String.almostTheSame` can be optimized to stop as soon as edit distance > 1.