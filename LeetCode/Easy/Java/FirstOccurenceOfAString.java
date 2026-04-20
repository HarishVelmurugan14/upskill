package Easy.Java;

public class FirstOccurenceOfAString {
    @SuppressWarnings("ReassignedVariable")
    public static int  strStr(String haystack, String needle) {
        short j=0;
        short needleLength = (short) needle.length();
        short haystackLength = (short) haystack.length();
        short positionChecked=0;
        for(short i=0;i<haystackLength;i++){
            if(haystack.charAt(i)==needle.charAt(j)){
                j++;
                if(j == needleLength){
                    return positionChecked;
                }
            }else{
                j=0;
                i = positionChecked++;
                if(i > haystackLength-needleLength){
                    return -1;
                }
            }
        }
        return -1;
    }
}
