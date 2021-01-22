# The Rover Problem

Tackle the [Rover Problem](https://thoughtworks.jiveon.com/docs/DOC-7982).

Honestly I would prefer a solution in
[APL](http://archive.vector.org.uk/art10011550), extremely well-suited to the
task. For the golf version of this problem, APL is ideal. However, I was
unable to find a workable text editor on Mac OS X for the language, not to
mention keyboard.

## Approach

The Rover Problem describes a series of vector additions in terms of simple
commands to a hypothetical Mars Rover vehicle.

The rover may 1) turn left (L), 2) turn right (R), or 3) move forward 1. It
has an initial compass orientation relative to the Euclidean plane, and begins
at the origin. There are no restrictions on the instructions; the vehicle may
spin in a circle or traverse a lengthy line or any mixture of these.

Your goal is to state the final plane coordinates and orientation of the
vehicle after processing the instruction string.

### Vectors

The initial orientation sets the direction of the first vector. Any sequence
of turns is the sum of each turn, e.g., turning left twice is pointing in the
opposite direction.

So the vectors are a direction and a distance, as learned in grade school, and
one initially sums the turns before encountering the first distance.
Thereafter the instruction string can be treated as a sum of vectors:

&sum; _dir_ &times; _dist_

### Parsing

1. Treat the initial orientation as a sequence of turn instructions prefixed
   to the instruction string.
2. Read the instruction string as a series of pairs: a direction (sequence of
   turns) paired with a distance (a sequence of moves).
3. Each vector takes the previous vector for its initial direction.
4. Ignore unpaired trailing turn instructions at the end of the string.

### Calculation

Distances are simple sums of the X-axis 1-vector, ``[ 1 0 ]``. So three (3)
move instructions in sequence is ``[ 3 0 ]``.

Recalling that rotations may be represented in the 2-plane as square matricies
using the Circle Group, SO(2):

| Rotation | 2x2 Matrix       |
|----------|------------------|
| 0        | ``[  1  0, 0  1 ]`` |
| &pi;/2   | ``[  0 -1, 1  0 ]`` |
| &pi;     | ``[ -1  0, 0 -1 ]`` |
| 3&pi;/2  | ``[  0  1, -1  0 ]`` |

Using matrices, rotations are products; distances remain sums. This can be
seen in that ``[ 0 -1, 1 0 ] ^ 3`` (three lefts) is ``[ 0 1, -1 0 ]`` (one
right).

## Releases

### 3

Over-engineered math example. Parsing still primitive.

### 2

"Math"-style works correctly using rotation matrices from the circle group.

### 1

Testing. Vanilla "C" version (in Java) works correctly. Quite succinct.

### 0

It builds. Completely empty otherwise. This is a tracer release for Github.
