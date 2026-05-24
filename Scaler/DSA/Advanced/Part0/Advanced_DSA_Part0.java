package DSA.Advanced.Part0;

@SuppressWarnings("DataFlowIssue")
public class Advanced_DSA_Part0 {
    public void implementations() {
        PrefixSum();
        CarryForwardAndSubArrays();
    }

    private void PrefixSum() {

        /* ------------------------- Day 03 - DSA: Arrays : Prefix Sum  ----------------------------------------------*/
        d03_Arrays_PrefixSum d03_Arrays_PrefixSum = new d03_Arrays_PrefixSum();

        // Q1, Q2, Q5, Q6, AQ1 : Pick One
        d03_Arrays_PrefixSum.rangeSumQuery(null, null); // Q3
        d03_Arrays_PrefixSum.specialIndices(); // Q4
        d03_Arrays_PrefixSum.inPlacePrefixSum(); // Q7

        d03_Arrays_PrefixSum.equilibriumIndex(); // AQ2
        d03_Arrays_PrefixSum.inPlacePrefixSum(); // AQ3
    }

    private void CarryForwardAndSubArrays() {

        /* ------------------------- Day 04 - DSA: Arrays : Carry Forward & Sub Arrays -------------------------------*/
        d04_Arrays_CarryForward d04_arrays_carryForward = new d04_Arrays_CarryForward();
        d04_Arrays_SubArraysWithCarryForward d04_arrays_subArraysWithCarryForward = new d04_Arrays_SubArraysWithCarryForward();

        // Q1, Q3, AQ4 : Pick One
        d04_arrays_subArraysWithCarryForward.smallestSubArrayWithBothMinAndMaxElements(null); // Q2
        d04_arrays_subArraysWithCarryForward.numberOfPossibleSubArrays(21); // Q3
        d04_arrays_subArraysWithCarryForward.subArrayInAGivenRange(null, 1, 3); // Q4
        d04_arrays_subArraysWithCarryForward.storeAllSubArraysInA2DMatrix(null); // Q5
        d04_arrays_carryForward.numberOfAGPairsInAString("ABCGAGG"); // Q6

        d04_arrays_carryForward.maxPossibleElementConsideringNEdgeElements(); // AQ1
        d04_arrays_carryForward.leaderElements(); // AQ2
        d04_arrays_subArraysWithCarryForward.maxProfitOnOnlyOneTransaction(); // AQ3
    }
}
