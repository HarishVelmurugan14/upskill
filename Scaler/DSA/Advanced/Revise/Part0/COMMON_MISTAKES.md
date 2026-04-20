# Common Mistakes — Part 0

## Source Code Annotation Issues
- **ContributionTechnique_4 line 57**: `// Complexity : Time : [ O(N^12 ]` — typo in source, should be O(N²). Also missing closing bracket.

## Key Observations from Source Comments

### specialIndices (d03)
> "On removing a index from an array and form a new array, sum of even indexed values should match the odd indexed values"
- After removing index i, all elements at indices > i shift left by 1 → their even/odd parity flips

### smallest subarray with min & max (d04_SubArrays)
> "smallest sub array always contains min and max at the edges, only one min and max in result answer"
- Scan from right, maintain last-seen positions of current global min and max

### maxProfitOnOnlyOneTransaction (d04_SubArrays)
> "All we need is the maximum possible elements to the right of each element"
- Build suffix max array or carry max from right

### Contribution Technique (ContributionTechnique_4)
> "Number of times each element will contribute in the sum of subArray is given by (i+1)(N-i)"
- Source notes: "asked in google, meta — refer notebook for definition"

### Good SubArrays (ContributionTechnique_4)
- Even length → sum < B; Odd length → sum > B — easy to confuse the two conditions

## Memory Management Gotchas (d7)
From source comments:
- `int a` passed to method → **separate copy**, changes don't affect caller (prints 10)
- `int[] a` passed to method → **same memory location**, changes affect caller (prints 50)
- `int[] a` re-initialized inside method (`a = new int[]{...}`) → **new location**, caller unaffected (prints 10)
- Return re-initialized array to propagate changes → caller gets new data (prints 100)
