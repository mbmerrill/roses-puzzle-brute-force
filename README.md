# roses-puzzle-brute-force
Brute force solution to the roses puzzle.

## Context

One year at a Yankee Swap I received a puzzle, which I'm calling the "roses puzzle."  I thought it would be fun to write code to solve it.

## The Puzzle

The puzzle consists of nine cardboard squares, henceforth referred to as "tiles."  Each tile has four pictures of a rose on it, one on each edge.  A rose is either big or small, and is one of four colors (white, pink, red, or yellow).

The puzzle is considered solved when the following criteria are met:

* The tiles are arranged in a 3x3 grid.
* Two adjacent edges (in any direction) must have the same color rose, but the sizes must be different.

Note that more than one solution may/could exist.

## The Code

The code takes a brute-force approach, running through all possible permutations to arrive at a solution.
