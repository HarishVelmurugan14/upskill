package Medium.Java;

@SuppressWarnings("ALL")
public class CoinChange {

    public static int coinChange(int[] coins, int amount) {
        int[] dpArray = new int[amount+1]; // Index Sheet
        dpArray[0] = 0;
        for(int i=1; i< amount+1;i++){
            dpArray[i] = amount+1;
        }
        for(int i=1;i<amount+1;i++){
            System.out.println("----INIT------");
            System.out.println("Current Amount - "+ i);
            System.out.println("----------");
            for(int currentCoinDenomination : coins){
                System.out.println("Current Coin - "+currentCoinDenomination);
                if(i >= currentCoinDenomination){
                     dpArray[i] = Math.min(dpArray[i], 1 + dpArray[i - currentCoinDenomination]);
                    System.out.println("Processing...");
                    System.out.println("Current Min - " + dpArray[i]);
                }
            }
            System.out.println("Final Min - " + dpArray[i]);
            System.out.println("----------");
        }
        return dpArray[amount] != amount+1 ? dpArray[amount] : -1;
    }
}
