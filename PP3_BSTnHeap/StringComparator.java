import java.util.Comparator;

public class StringComparator implements Comparator<String>{
    public int compare(String s1, String s2){
        String[] words1 = s1.split("@");
        String[] words2 = s2.split("@");
        String address1 = words1[0];
        String address2 = words2[0];
        return (address1.compareTo(address2));
    }
}