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


Day 6: Chronal Coordinates
--------------------------

<https://adventofcode.com/2018/day/6>

Again a bit too tired, so naming a bit lacking. Puzzle itself was fairly simple.

Wondering if I should have combined all days into the same Gradle project. There's a bit of overhead of creating new
project per day. That would perhaps be more in the spirit of Kotlin, of code being concise and pragmatic.


Day 7: The Sum of Its Parts
---------------------------

<https://adventofcode.com/2018/day/7>

Part 2 was less trouble than expected, but required the `DirectedGraph` class to be changed. Moving logic into the graph
class also made the part1 function less complex. I'm sure Uncle Bob would have something to say about this :).

A few annoyances, I should investigate further:
* In IntelliJ, the "Create test" shortcut does not seem to support Kotlin.
* In IntelliJ, running the unit tests in continuous mode don't work that well.
* How should you create tests of private functions in classes?
 
 
 Day 8: Memory Maneuver
 ----------------------
 
 <https://adventofcode.com/2018/day/8>
 
 Relatively simple recursive function. I had some trouble figuring out how to detemine the lenght of each child node, in
 order to parse den one at a time. Finally figured to pass a `Iterator` over the input integers, and then no problem.
 
 Kotlin have some really convenient functions like `List.getOrNull`, `List.sum` and `List.sumBy`. These makes it easy
 to do what, where in Java it would probably require a couple of extra lines each time.
 
 
 Day 9: Marble Mania
 -------------------
 
 <https://adventofcode.com/2018/day/9>
 
 First version was fairly straight forward with a `mutableList()` (an ArrayList). This worked okay for part 1, but never
 finished in part 2.
 
 Changing it for a LinkedList didn't do much, as the the time complexity for removing middle element (eventhough you
 have the index), is still a O(n) operation. Removing/adding head or tail is O(1), so went with rotating the "ring" on
 every insert.
  