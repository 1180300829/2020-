package App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import Board.CourseEntryBoard;
import EntryState.*;
import Factory.CourseEntryFactory;
import Location.CourseLocation;
import PlanningEntry.*;

import PlanningEntryAPIs.PlanningEntryAPIs;
import Resource.*;
import Timeslot.Timeslot;

public class CourseCalendarApp {

    private static List<CourseEntry<Teacher>> courselist=new ArrayList<>();  //��������CourseEntry�ļ���
   	
    /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĿγ̼ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ�����ʱ��)");
		System.out.println("2.Ϊĳ���γ̼ƻ��������Դ");
		System.out.println("3.���(ɾ��λ�ú���������)ĳ���γ̼ƻ����λ��");
		System.out.println("4.ɾ��ĳ���γ̼ƻ����λ��(ɾ��λ�ú������������λ��Ȼ��ִ������Ĳ���)");	
		System.out.println("5.���ĳ���γ̼ƻ������Դ");
		System.out.println("6.����ĳ���γ̼ƻ������Դ");
		System.out.println("7.ɾ��ĳ���γ̼ƻ������Դ");
		System.out.println("8.����ĳ���γ̼ƻ���");
		System.out.println("9.����ĳ���γ̼ƻ���");
		System.out.println("10.ȡ��ĳ���γ̼ƻ���");
		System.out.println("11.�鿴ĳ���γ̼ƻ����״̬");
		System.out.println("****(ע�⣺ִ��12,13,14�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("12.���γ̼ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("13.���ĳ����ʦ��Դ���г�����ʹ�ø���Դ�Ŀγ̼ƻ���");
		System.out.println("14.���ĳ����ʦ��Դ��ѡ�к��и���Դ��ĳ���γ̼ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("15.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("16.��ʾ��ǰ���пγ̼ƻ���ĸ���");
	}
	
	/**
	 * 
	 * @param args
	 * @throws ParseException ʱ���ʽ��Ϊyyyy-MM-dd HH:mm
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		OneLocationEntryImpl a;
		MultipleSortedResourceEntryImpl<Teacher> b;
		NoBlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		CourseState state;
		List<Teacher> allteacher;
		Teacher myteacher;
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		System.out.println("�ʼ��ִ�е�һ��");
		
		while(true) {
			String[] temp;
			String yixie;
			menu();
			System.out.println("��������Ĳ�����");
			choice=scanner.nextLine();
			int j;
			switch(choice) {
			case "1":
				a=new OneLocationEntryImpl();
				b=new MultipleSortedResourceEntryImpl<Teacher>();
				c=new NoBlockableEntryImpl();
				PlanningEntry origincourse=new CourseEntryFactory().getCourseEntry(a, b, c); //�ù���������������
				CourseEntry<Teacher> course=(CourseEntry<Teacher>)origincourse;
				System.out.println("�˿γ̼ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
				System.out.println("�������������(eg ����¥32)��");
				String tempname;
				tempname=scanner.nextLine();
				CourseLocation from=new CourseLocation(weidu,jingdu,tempname);
				course.setlocations(from);
				System.out.println("������γ̵��Ͽκ��¿�ʱ��(�ö��Ÿ���)(eg 2020-01-01 15:45,2020-01-01 17:30)��");
				try {
					temp=(scanner.nextLine()).split(",");
					Timeslot mytime=new Timeslot(temp[0],temp[1]);
					course.settimeslot(mytime);
				}catch(ArrayIndexOutOfBoundsException e) {
					 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
					 //e.printStackTrace();
					 break;
				}
				//temp=(scanner.nextLine()).split(",");
				//Timeslot mytime=new Timeslot(temp[0],temp[1]);
				//course.settimeslot(mytime);
				System.out.println("������γ���(eg �������)��");
				String coursename=scanner.nextLine();
				course.setplanningentryname(coursename);
				 System.out.println("�ƻ����������óɹ�");
				state = CourseWaitingState.instance;
				course.setcurrentstate(state);
				System.out.println("��Ϣ�������\n");
				courselist.add(course);
				break;
			case "2":
				System.out.println("���������������Դ�ĵĿγ���(eg �������)��");
				tempname=scanner.nextLine();
				int i;
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println();
					break;
				}
				CourseEntry<Teacher> tempentry=courselist.get(i);
				allteacher=new ArrayList<>();
				boolean gg=false;
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")) {
					System.out.println("�밴�մ������������������ʦ�����֤�ţ��������Ա�ְ�ƣ����롰������ֹͣ����(eg 422823199812254452,����,��,��ʦ)��");
					while(!(tempname=scanner.nextLine()).equals("����")) {
						try {
							temp=tempname.split(",");
							myteacher=new Teacher(temp[0],temp[1],temp[2],temp[3]);
							allteacher.add(myteacher);
						}catch(ArrayIndexOutOfBoundsException e) {
							 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
							 gg=true;
							 break;
						}
					}
					if(gg==true) {
						break;
					}
					
					tempentry.setresource(allteacher);
					state=((CourseState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					courselist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��������ʦ��Դ����\n");
				}
				break;
			case "3":
				System.out.println("������������(��������)λ�õĿγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")
						||((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
					System.out.println("������������(��������)λ�ý��ҵ�����(eg ��֪¥15)��");
					tempname=scanner.nextLine();
					from=new CourseLocation(weidu,jingdu,tempname);
					tempentry.changelocations(from);
					courselist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�б��(��������)λ�ò���\n");
				}
				break;
			case "4":
				System.out.println("����������ɾ��λ�õĿγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")
						||((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
					System.out.println("����������ɾ��λ�ý��ҵ�����(eg ��֪¥15)��");
					tempname=scanner.nextLine();
					from=new CourseLocation(weidu,jingdu,tempname);
					tempentry.deletelocations(from);
					courselist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��ɾ��λ�ò���\n");
				}
				
				break;
			case "5":
				System.out.println("��������������Դ�Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println();
					break;
				}
				tempentry=courselist.get(i);
				allteacher=tempentry.getresource();
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
					System.out.println("����������Ҫ�������ʦ������(eg ����)��");
					tempname=scanner.nextLine();
					for(j=0;j<allteacher.size();j++) {
						if(allteacher.get(j).getteachername().equals(tempname)) {
							break;
						}
					}
					if(j==allteacher.size()) {
						System.out.println("û�и���ʦ");
						System.out.println("\n");
						break;
					}
					Teacher tempcarriage=allteacher.get(j);
					System.out.println("�������������ʦ�����֤�ţ��������Ա�ְ�ƣ����롰������ֹͣ����(eg 422823199812251134,����,��,��ʦ)��");
					try {
						temp=(scanner.nextLine()).split(",");
						myteacher=new Teacher(temp[0],temp[1],temp[2],temp[3]);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��4����������ȷ��ʽ����\n");
						 //e.printStackTrace();
						 break;
					}
					tempentry.changeresource(tempcarriage,myteacher);
					courselist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�б����Դ����\n");
				}
				break;
			case "6":
				System.out.println("����������������Դ�Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				allteacher=tempentry.getresource();
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
					System.out.println("���������ӵ���ʦ�����֤�ţ��������Ա�ְ�ƣ����롰������ֹͣ����(eg 422823199812251134,����,��,��ʦ)��");
					try {
						temp=(scanner.nextLine()).split(",");
						myteacher=new Teacher(temp[0],temp[1],temp[2],temp[3]);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��5����������ȷ��ʽ����\n");
						 //e.printStackTrace();
						 break;
					}
					System.out.println("���������ӵ���ʦ��һ����ʦ�е�λ��(��һ����ʦĬ��λ��Ϊ1)(eg 3)��");
					tempname=scanner.nextLine();
					int weizhi=Integer.parseInt(tempname);
					if(weizhi>tempentry.getresource().size()+1||weizhi<1) {
						System.out.println("���ӵ���ʦλ�ò��Ϸ�\n");
						break;
					}
					tempentry.addresource(myteacher,weizhi-1);
					courselist.set(i, tempentry);
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��������Դ����\n");
				}
				break;
			case "7":
				System.out.println("����������ɾ����Դ�Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				allteacher=tempentry.getresource();
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
					System.out.println("����������Ҫɾ������ʦ������(eg ����)��");
					tempname=scanner.nextLine();
					for(j=0;j<allteacher.size();j++) {
						if(allteacher.get(j).getteachername().equals(tempname)) {
							break;
						}
					}
					if(j==allteacher.size()) {
						System.out.println("û�и���ʦ");
						System.out.println("\n");
						break;
					}
					Teacher tempcarriage=allteacher.get(j);
					tempentry.deleteresource(tempcarriage);
					courselist.set(i, tempentry);
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��ɾ����Դ����\n");
				}
				break;
			case "8":
				System.out.println("����������Ҫ�����Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {	
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.launch(yixie)) {
					state=((CourseState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					courselist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ���Ͽβ���\n");
				}
				break;
			case "9":
				System.out.println("����������Ҫ�����Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѿ�ʼ�Ͽ�(Running)")) {
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.finish(yixie)) {
					state=((CourseState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					courselist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ���¿β���\n");
				}
				break;
			case "10":
				System.out.println("����������Ҫȡ���Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=courselist.get(i);
				if(((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ�δ������ʦ(Waiting)")
						||((CourseState)tempentry.getcurrentstate()).getcoursestate().equals("�γ��ѷ�����ʦ(Allocated)")) {
					System.out.println("������ָ�ȡ��");
					yixie=scanner.nextLine();	
					if(tempentry.cancel(yixie)) {
					canceltimeone=Calendar.getInstance();
					String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(canceltimeone.getTime()); 
					canceltimeone.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
					System.out.println("�ÿγ̼ƻ���ȡ����ʱ��Ϊ"+str);
					state=((CourseState)tempentry.getcurrentstate()).move('b');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					courselist.remove(i);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
					
				}
				else {
					System.out.println("�γ����ϿΣ��޷�ȡ��\n");
				}
				break;
			case "11":
				System.out.println("����������Ҫ�鿴�Ŀγ̼ƻ���Ŀγ�����(eg �������)��");
				tempname=scanner.nextLine();
				for(i=0;i<courselist.size();i++) {
					if(courselist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==courselist.size()) {
					System.out.println("û�иüƻ�������ѱ�ȡ��");
					System.out.println();
					break;
				}
				tempentry=courselist.get(i);
				System.out.println("�ÿγ̼ƻ����״̬Ϊ��"+((CourseState)tempentry.getcurrentstate()).getcoursestate());
				System.out.println("\n");
				break;
			case "12":
				System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
				tempname=scanner.nextLine();
				System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
				myapis.checkLocationConflict(courselist,tempname);
				System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
				myapis.checkResourceExclusiveConflict(courselist);
				System.out.println("\n");
				break;
			case "13":
				System.out.println("����������Ҫ�鿴����ʦ�����֤�ţ��������Ա�ְ�ƣ����롰������ֹͣ����(eg 422823199812254452,����,��,��ʦ)��");
            	try {
            		temp=(scanner.nextLine()).split(",");
    				myteacher=new Teacher(temp[0],temp[1],temp[2],temp[3]);
				}catch(ArrayIndexOutOfBoundsException e) {
					 System.out.println("δ��,������������ִ��13����������ȷ��ʽ����\n");
					 //e.printStackTrace();
					 break;
				}
 				myapis.findEntryPerResource(myteacher, courselist);
 				System.out.println("\n");
            	break;
			case "14":
				System.out.println("����������Ҫ�鿴��ǰ��ƻ���Ŀγ�����(eg �������)��");
            	tempname=scanner.nextLine();
  				for(i=0;i<courselist.size();i++) {
 					if(courselist.get(i).getplanningentryname().equals(tempname) ){
 						break;
 					}
 				}
 				if(i==courselist.size()) {
 					System.out.println("û�иüƻ���");
 					System.out.println("\n");
 					break;
 				}
 				tempentry=courselist.get(i);
 				System.out.println("��������Ҫ�鿴ǰ��ƻ���ĸÿγ̼ƻ�����ӵ�е���ʦ��Դ������(eg ����)��");
 				tempname=scanner.nextLine();
 				for(j=0;j<tempentry.getresource().size();j++) {
 					if(tempentry.getresource().get(j).getteachername().equals(tempname)) {
 						break;
 					}
 				}
 				myteacher=tempentry.getresource().get(j);
                myapis.findPreEntryPerResource(myteacher,tempentry,courselist);	
                System.out.println("\n");	
				break;
			case "15":
				System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����¥32)");
				tempname=scanner.nextLine();
				CourseEntryBoard flightboard=new CourseEntryBoard();
				flightboard.setclassroomlocation(tempname);
				flightboard.getsortallentry(courselist);
				flightboard.createCourseEntryBoard();
				flightboard.visualize();
				System.out.println("\n");
				break;
			case "16":
				 System.out.println("��ǰ���пγ̼ƻ���ĸ���Ϊ��"+courselist.size()+"\n");
	 			break;
			default:
				System.out.println("��������ȷָ��\n");
				break;		
			}
		}
		
		
	}
}
