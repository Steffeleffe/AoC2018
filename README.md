Advent of Code 2018
===================

If time permits, make some code :).

Learn some Kotlin, and use TDD.

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

* `require` and `check` can be used to check argument and state respectively. A bit similar to `assert` but does not need
jvm argument `-ea` to be set.
* Old school for-loops are working on ranges, and not with index variable. E.g. `0..4` (end is included) and 
`0 until 4` (end is excluded). If you want to loop on indices in list, you would use `for (i in list.indices)`.

Possible improvements:

* The double for-loop in `identifyRelatedBoxes` could perhaps be replaced with something more idiomatic. 
* The `String.almostTheSame` can be optimized to stop as soon as edit distance > 1.


Day 3: No Matter How You Slice It
---------------------------------

<https://adventofcode.com/2018/day/3>

Did initial version with `Array<Array<Int>>` which worked for part 1. Replacing this with a map instead, cut down on all
the index fiddling.

Played around with data classes. Adding properties using `get()` is pretty smart, escpicially for part 2, when the count
was changed to be size of list instead of Int variable. No changes needed in caller.

Got way too late, so some methods could definitely be improved :).


Day 4: Repose Record
--------------------

<https://adventofcode.com/2018/day/4>

Probably should not be doing these challenges if too tired :). It definitely caused lack of good method names. 

Green tests on the example date confused me when it then failed on the actual input. Took some time to figure out where
the bug was.

Day 5: Alchemical Reduction
---------------------------

<https://adventofcode.com/2018/day/5>

Very easy one today. Reduce by using stack, and a single pass.

Nothing more to add, really :).

