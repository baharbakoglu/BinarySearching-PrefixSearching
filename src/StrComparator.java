
import java.util.Comparator;

public class StrComparator implements Comparator<String> {

	public int compare(String str1, String str2) {

    String strName1 = str1.toUpperCase();
    String strName2 = str2.toUpperCase();

    /*ascending order*/
    return strName1.compareTo(strName2);

  }
}
