# roses-puzzle-brute-force
Brute force solution to the roses puzzle.

## Context

One year at a Yankee Swap I received a puzzle, which I'm calling the "roses puzzle."  I thought it would be fun to write code to solve it.

## The Puzzle

The puzzle consists of nine cardboard squares, henceforth referred to as "tiles."  Each tile has four pictures of a rose on it, one on each edge.  A rose is either big or small, and is one of four colors (white, pink, red, or yellow).

Note that any given tile can have any combination of sizes and colors; the distribution is not identical across the set of tiles.  For example, a particular tile might have 3 large roses and only 1 small rose, and might have two white roses and no yellow roses.

The puzzle is considered solved when the following criteria are met:

* The tiles are arranged in a 3x3 grid.
* Two adjacent edges (in any direction) must have the same color rose, but the sizes must be different.

Note that more than one solution could exist.

## The Code

The code takes a brute-force approach, running through all possible permutations to arrive at a solution.

The goal is to output a solution to the puzzle in a format that makes it easy to check with the physical puzzle whether the solution is correct.

## A Solution

The following is an example solution, as displayed by this code:

     r | r | P 
    w1p|P5w|W2w
     Y | y | R 
    ---+---+---
     y | Y | r 
    P7w|W6p|P8Y
     R | r | Y 
    ---+---+---
     r | R | y 
    Y4P|p3W|w9r
     w | y | P 

This format allows you to see which tile is in which position (the ID number in the center of each cluster).  It also allows you to check the edge criteria for correctness; the letter on each edge is the first letter of the color of the rose; the case of that letter (upper or lower) denotes big or small, respectively.

## Time Required

As might be expected from a brute-force solution, this takes a while to run.  ~3.25 hrs on my machine.

## Output

If the logging configuration is left as-is, it will create output/roses.log, which contains some logging output and any solutions it finds.  It will overwrite this file every run.
