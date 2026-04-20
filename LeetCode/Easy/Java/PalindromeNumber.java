 
 public class PalindromeNumber{
 
    @SuppressWarnings("ReassignedVariable")
    public static boolean isPalindrome(int x) {
        int copy_x = x;
        int check_x = copy_x % 10;
        short count = 0;
        while(copy_x / 10 >= 1){
            if(count!=0){
                check_x = check_x * 10 + copy_x % 10;
            }
            copy_x = copy_x / 10;
            count ++;    
        }
        if(count > 0){
            check_x = check_x * 10 + copy_x;
        }
        return check_x==x && x > -1;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome(19091));
    }
 }