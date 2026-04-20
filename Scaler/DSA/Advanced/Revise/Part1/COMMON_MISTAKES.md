# Common Mistakes — Part 1

## Source Observations

### d09 — Row with Maximum Number of Ones
> "If two rows have the maximum number of 1 then return the row which has a lower index"
> "Expected time complexity is O(rows + columns)"
- Boundary: must track lower index row, not just any max

### d9_Arrays_2dMatrix — Matrix Transpose
> "Reason for i>=1 as a cell will be printed twice"
- When transposing in-place, only iterate upper triangle (i < j) to avoid double-swap

### d10 — First Missing Natural Number
> "Replace one unusable information with another unusable information inplace"
- In-place sign marking: values outside [1,N] are "unusable" — overwrite them with N+1 first

### d10 — Next Permutation
- Source has `// todo` — incomplete implementation to revisit

### d12 — Triple Trouble Optimization
> "instead of 32 we can find max of array and iterate up to the bit which the max number has"
- Don't hardcode 32-bit loop; find max element's bit length to optimize

### d14 — Fast Exponentiation
> "Technique : Fast Exponentiation | Binary Exponentiation"
- O(logN) instead of O(N) — critical for modular arithmetic in Day 15

### d15 — GCD
> "Works for both negative and positive number"
- Euclidean GCD handles negatives naturally

## Driver Wiring Notes
- The `Sorting()` method in `Advanced_DSA_Part1.java` is defined but **NOT called** from `implementations()` — Days 18/19 are unwired
- d9_Arrays_2dMatrix exists in Part1 directory but is NOT referenced from the driver
