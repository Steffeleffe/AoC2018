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

Relatively simple recursive function. I had some trouble figuring out how to determine the length of each child node, in
order to parse den one at a time. Finally figured to pass a `Iterator` over the input integers, and then no problem.

Kotlin have some really convenient functions like `List.getOrNull`, `List.sum` and `List.sumBy`. These makes it easy
to do what, where in Java it would probably require a couple of extra lines each time.


Day 9: Marble Mania
-------------------

<https://adventofcode.com/2018/day/9>

First version was fairly straight forward with a `mutableList()` (an ArrayList). This worked okay for part 1, but never
finished in part 2.

Changing it for a LinkedList did not help much, as the time complexity for removing middle element (even though you
have the index), is still a O(n) operation. Removing/adding head or tail is O(1), so went with rotating the "ring" on
so that every insert and remove happens in the head or tail of the list.
 

Day 10: The Stars Align
-----------------------

<https://adventofcode.com/2018/day/10>

Very easy. I made assumption that the time where the area defined the light positions is the most compact, is also the
time where the message is written in the sky. It turned out to be the case :). 

Created multiple `data class` as this is the idiomatic way, and best open for extension. This time, however, extending 
the data structures was not needed.

Possible improvements:
* On the input the message showed itself after 10000 seconds, but could have been much longer. Each step requires some 
  computation, which could be replaced by some simple math to calculate the intersection of the lights given their start
  position and their velocity. 
* Function `findSmallestSky` is not too pretty; could probably be refactored up.


Day 11: Chronal Charge
----------------------

<https://adventofcode.com/2018/day/11>

Fairly simple part 1, but like Day 9, part 2 exposes the short comings of the naive solutions.

The way to go is reuse the already calculated smaller squares. Like a 10x10 square consist of five 5x5 squares, for 
which we already know the total power. I took the simpler optimization to find the sum by the smaller (n-1)x(n-1) square
and the add the left over 1x1 squares along the to left over edges. 

I did not actually solve it completely, as time is still too big a factor. My hack was to limited the maximum square 
size to 20 in the part 2 case (and 3 in part 1). This was enough to find the correct solution. :-/

Improvement:
* Fix the divide-and-conquer algorithm to be able to calculate total power for all 300 square sizes. See "Summed-area
table" <https://en.wikipedia.org/wiki/Summed-area_table>.


Day 12: Subterranean Sustainability
-----------------------------------

<https://adventofcode.com/2018/day/12>

Once again simple part 1, but part 2 requires some alternative approach. With hint that at some point, the sum changes
a fixed number for each new generation, it has easy to find when difference stabilizes and then extrapolate the result.

A bit ugly code especially in the `PotsHandler` class. Will probably leave as is.


Day 13: Mine Cart Madness
-------------------------

<https://adventofcode.com/2018/day/13>

Pretty easy, and part2 was an easy extension for the stuff made for part 1.

Lots of `when` constructs used :).

Not much more to add, except that TDD part sort of went out the window. Perhaps because the parsing of the input was the
most complex and most prone to coding errors.

