package Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	/**
	 * ���һ���ļ��ж���ĺ����ַ�����ʽ�Ƿ���ϱ���Ҫ��
	 * @param S ���жϵ��ַ���
	 * @return ����Ҫ�󷵻�true�������Ϸ���false
	 */
	 public boolean checkwhethercorrect(String S){
			//���ʱ��ĸ�ʽ�Ƿ���ϱ���Ҫ��
		   	Pattern pattern1 = Pattern.compile("((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])"
		    		+ "|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])"
		    		+ "|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))"
		    		+ "|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|"
		    		+ "([1-2][0-9])|(3[01])))"
		    		+ "|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))"
		    		+ "|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])"
		    		+ "|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|)))");
		   	//�������ĸ�ʽ�Ƿ���ϱ���Ҫ��
		   	Pattern pattern2 = Pattern.compile("((Flight):(20[012][0-9]-[01][0-9]-[0123][0-9]),([A-Z][A-Z]((\\d{2})|"
		   			+ "(\\d{3})|(\\d{4})))\n\\{\n(DepartureAirport):([a-zA-z]+)\n(ArrivalAirport):([a-zA-z]+)"
		   			+ "\n(DepatureTime):(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(ArrivalTime)"
		   			+ ":(20[012][0-9]-[01][0-9]-[0123][0-9])(\\s[012][0-9]:[0-6][0-9])\n(Plane):([BN]\\d{4})\n\\{\n(Type)"
		   			+ ":([A-Za-z0-9]+)\n(Seats:)(([5-9][0-9])|([1-5][0-9][0-9])|(600))\n(Age):(([0-9]|([1-2][0-9])|(30))"
		   			+ "(\\.[0-9])?)\n\\}\n\\}\n)");
		   	Matcher tomacher = pattern1.matcher(S);
		   	if(tomacher.matches()) {
		   		tomacher = pattern2.matcher(S);
		    	if(tomacher.matches())
		    		return true;
		   	}
		   	return false;
		}
	 
	 /**
	  * �õ�һ���ַ�������������ַ���
	  * @param name ǰ����ַ���
	  * @param S �����ַ���
	  * @return �����ַ�����ȥǰ���ַ�������ַ���
	  */
	 public String getAllinformation(String name,String S) {
			Pattern pattern = Pattern.compile("(?<="+name+").+");
			Matcher mc = pattern.matcher(S);
			while(mc.find())
				return mc.group();
			return "";
		}
}
