
import DSA.Advanced.Part0.Advanced_DSA_Part0;
import DSA.Advanced.Part1.Advanced_DSA_Part1;
import DSA.Advanced.Part2.Advanced_DSA_Part2;
import DSA.Advanced.Part3.Advanced_DSA_Part3;
import DSA.Advanced.Part4.Advanced_DSA_Part4;
import DSA.Contest.Contest_Home;

public class Index {
    public static void main(String[] args) {

        /* ------------------------------------------ ADVANCED DSA Basics --------------------------------------------*/
        Advanced_DSA_Part0 advancedDsaPart0 = new Advanced_DSA_Part0();
        advancedDsaPart0.implementations();

        /* ------------------------------------------ ADVANCED DSA 1 -------------------------------------------------*/
        Advanced_DSA_Part1 advancedDsaPart1 = new Advanced_DSA_Part1();
        advancedDsaPart1.implementations();

        /* ------------------------------------------ ADVANCED DSA 2 -------------------------------------------------*/
        Advanced_DSA_Part2 advancedDsaPart2 = new Advanced_DSA_Part2();
        advancedDsaPart2.implementations();

        /* ------------------------------------------ ADVANCED DSA 3 -------------------------------------------------*/
        Advanced_DSA_Part3 advancedDsaPart3 = new Advanced_DSA_Part3();
        advancedDsaPart3.implementations();

        /* ------------------------------------------ ADVANCED DSA 4 -------------------------------------------------*/
        Advanced_DSA_Part4 advancedDsaPart4 = new Advanced_DSA_Part4();
        advancedDsaPart4.implementations();

        /* ------------------------------------------ CONTESTS -------------------------------------------------*/
        Contest_Home contestHome = new Contest_Home();
        contestHome.tryout();
    }
}
