package Easy.Java;

public class LastWordLength {
    public static int lengthOfLastWordOld(String s) {
        String[] arr = s.split(" ");
        System.out.println((arr[arr.length-1]));
        return (arr[arr.length-1]).length();
    }
    @SuppressWarnings("ReassignedVariable")
    public static int lengthOfLastWord(String s) {
        short length=0;
        s= s.trim();
        for(int i=s.length()-1; i>=0; i--){
            System.out.print(s.charAt(i)+"\t");
            if(s.charAt(i) == ' '){
                break;
            }else{
                length++;
            }
        }
        return length;
    }
}
