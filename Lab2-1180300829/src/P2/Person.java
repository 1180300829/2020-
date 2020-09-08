package P2;

import java.util.ArrayList;


public class Person {
	private String myname;  //��������
	private static ArrayList<String> allperson =new ArrayList<String>(); //���������˵�����
	
	 // Abstraction function:
    // AF(myname)=�˵�����
    // Representation invariant:
    // û���ظ�����
    // Safety from rep exposure:
    // ��myname����Ϊprivate
    // ����myname��inmutable�������ڷ���ʱ����Ҫ����defensive copies
	
	public Person(String name) {     //���췽��
		if(allperson.contains(name)) {       //�����������ֱ����Ѵ���ʱ
			System.out.println("�������Ѵ���");
		}
		else {   //��������ʱ
			this.myname=name;
			allperson.add(name);
		}
	}
	
	/**
	 * ���ر�������
     * @return ��������
     */
	public String getmyname() {  
		return this.myname;
	}
}
