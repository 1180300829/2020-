package App;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import Board.FlightEntryBoard;
import EntryState.*;
import Factory.FlightEntryFactory;
import Location.*;
import PlanningEntry.*;
import PlanningEntryAPIs.PlanningEntryAPIs;
import Resource.Flight;
import Timeslot.*;

public class FlightScheduleApp {

	private static List<FlightEntry<Flight>> flightlist=new ArrayList<>(); //��������FlightEntry�ļ���
	
	
	 /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĺ���ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ��ǰʱ��һСʱǰ���ʱ��)");
		System.out.println("2.Ϊĳ������ƻ��������Դ");
		System.out.println("3.���ĳ������ƻ����λ��");
		System.out.println("4.���ĳ������ƻ������Դ");
		System.out.println("5.(����)����ĳ������ƻ���");
		System.out.println("6.����ĳ������ƻ���");
		System.out.println("7.ȡ��ĳ������ƻ���");
		System.out.println("8.�鿴ĳ������ƻ����״̬");
		System.out.println("9.����ĳ������ƻ���");
		System.out.println("****(ע�⣺ִ��10,11,12�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("10.��⺽��ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("11.���ĳ���ɻ���Դ���г�����ʹ�ø���Դ�ĺ���ƻ���");
		System.out.println("12.���ĳ���ɻ���Դ��ѡ�к��и���Դ��ĳ������ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("13.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("14.��ʾ��ǰ���к���ƻ���ĸ���");
	}
	
	/**
	 * 
	 * @param args
	 * @throws ParseException ʱ���ʽ��Ϊyyyy-MM-dd HH:mm
	 * @throws IOException �ļ����������
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException, IOException {
		MultipleLacationEntryImpl a;
		OneDistinguishResourceEntryImpl<Flight> b;
		BlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		FlightState state;
		Flight myflight;
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		String fileentry,fileoneline;
		int blockflag;
		List<String> allfileentry=new ArrayList<>();
		System.out.println("�ʼ��ִ�е�һ��");
		
		while(true) {
			String[] temp;
			String yixie;
			menu();
			System.out.println("��������Ĳ�����");
			choice=scanner.nextLine();
			switch(choice) {
			case "1":
				a=new MultipleLacationEntryImpl();
				b=new OneDistinguishResourceEntryImpl<Flight>();
				c=new BlockableEntryImpl();
				PlanningEntry originflight=new FlightEntryFactory().getFlightEntry(a, b, c); //�ù���������������
				FlightEntry<Flight> flight=(FlightEntry<Flight>)originflight;
				System.out.println("�˺���ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
				List<Location> alllocation=new ArrayList<>();
				int ll=0;
				System.out.println("������������г����л������ƣ�ֻ����������(ÿ����һ����һ�»س�)(eg ����)��");
				String tempname;
				while(ll<3) {
					tempname=scanner.nextLine();
					FlightTrainLocation from=new FlightTrainLocation(weidu,jingdu,tempname);
					alllocation.add(from);
					ll++;
				}
                flight.setlocations(alllocation);
                List<Timeslot> alltime=new ArrayList<>();
                boolean hh=false;
				System.out.println("������������г�����ʱ���(ʱ�����Ӧ�ñȷɻ�������һ)(�ö��Ÿ�����ÿ����һ�԰�һ�»س�)(eg 2020-01-01 12:00,2020-01-01 14:00)��");
				for(int pp=0;pp<ll-1;pp++) {
					try {
						tempname=scanner.nextLine();
						temp=tempname.split(",");
						Timeslot mytime=new Timeslot(temp[0],temp[1]);
						alltime.add(mytime);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
						 hh=true;
						 break;
					}
				}
				if(hh==true) {
					break;
				}
				flight.settimeslot(alltime);
				System.out.println("�����뺽���(eg AH3567)��");
				String flightname=scanner.nextLine();
				flight.setplanningentryname(flightname);
			    System.out.println("�ƻ����������óɹ�");
				state = FlightWaitingState.instance;
				flight.setcurrentstate(state);
				System.out.println("�ƻ��ǰ״̬���óɹ�");
				System.out.println("��Ϣ�������\n");
				flightlist.add(flight);
				break;
			case "2":
				System.out.println("���������������Դ�ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				int i;
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println();
					break;
				}
				FlightEntry<Flight> tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("����δ����ɻ�(Waiting)")) {
					System.out.println("�����������ɻ���ţ����ͺţ���λ��������(eg N8981,C88,100,2.5)��");
					try {
						temp=(scanner.nextLine()).split(",");
						myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
						if(tempentry.setresource(myflight)) {
							 System.out.println("�������óɹ�");
						}
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
						 break;
					}
					state=((FlightState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("�ɻ���Դ�������\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�����÷ɻ���Դ����\n");
				}
				break;
			case "3":
				System.out.println("���������յ�λ�ò��ɱ��");
				System.out.println("\n");
				break;
			case "4":
				System.out.println("��������������Դ�ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")) {
					System.out.println("�������µķɻ���ţ����ͺţ���λ��������(eg B1104,A22,100,2.5)��");
					try {
						temp=(scanner.nextLine()).split(",");
						myflight=new Flight(temp[0],temp[1],Integer.parseInt(temp[2]),Double.parseDouble(temp[3]));
						tempentry.changeresource(myflight);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��4����������ȷ��ʽ����\n");
						 break;
					}
					flightlist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�б���ɻ���Դ����\n");
				}
				break;
			case "5":
				System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")||
						((FlightState)tempentry.getcurrentstate()).getflightstate().equals("������;����(Blocked)")) {	
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.launch(yixie)) {
					state=((FlightState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ����ɲ���\n");
				}
				break;
			case "6":
				System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("���������(Running)")) {
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.finish(yixie)) {
					state=((FlightState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�н������\n");
				}
				break;
			case "7":
				System.out.println("����������Ҫȡ���ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("����δ����ɻ�(Waiting)")
						||((FlightState)tempentry.getcurrentstate()).getflightstate().equals("�����ѷ���ɻ�(Allocated)")||
						((FlightState)tempentry.getcurrentstate()).getflightstate().equals("������;����(Blocked)")) {
					System.out.println("������ָ�ȡ��");
					yixie=scanner.nextLine();	
					if(tempentry.cancel(yixie)) {
					canceltimeone=Calendar.getInstance();
					String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(canceltimeone.getTime()); 
					canceltimeone.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
					System.out.println("�ú���ƻ���ȡ����ʱ��Ϊ"+str);
					state=((FlightState)tempentry.getcurrentstate()).move('b');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.remove(i);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��������ɣ��޷�ȡ��\n");
				}
				break;
			case "8":
				System.out.println("����������Ҫ�鿴�ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ�������ѱ�ȡ��");
					System.out.println();
					break;
				}
				tempentry=flightlist.get(i);
				System.out.println("�ú���ƻ����״̬Ϊ��"+((FlightState)tempentry.getcurrentstate()).getflightstate());
				System.out.println("\n");
				break;
			case "9":
				System.out.println("����������Ҫ�����ĺ���ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=flightlist.get(i);
				if(((FlightState)tempentry.getcurrentstate()).getflightstate().equals("���������(Running)")) {	
					System.out.println("����������Ҫ�����ķɻ���λ�ã�(eg �人)");
					yixie=scanner.nextLine();
					blockflag=tempentry.trainblock(yixie);
					if(blockflag==-1) {
						break;
					}
					state=((FlightState)tempentry.getcurrentstate()).move('b');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					flightlist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��������������\n");
				}
				break;
			case "10":
				System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
				tempname=scanner.nextLine();
				System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
				myapis.checkLocationConflict(flightlist,tempname);
				System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
				myapis.checkResourceExclusiveConflict(flightlist);
				System.out.println("\n");
				break;
			case "11":
				System.out.println("����������Ҫ�鿴�ķɻ���Դ�ķɻ����(eg N8981)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getresource().getflightnumber().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���ӵ�и���Դ");
					System.out.println();
					break;
				}
				tempentry=flightlist.get(i);
				myapis.findEntryPerResource(tempentry.getresource(), flightlist);
				System.out.println("\n");
				break;
			case "12":
				System.out.println("����������Ҫ�鿴��ǰ��ƻ���ĺ����(eg AH3567)��");
				tempname=scanner.nextLine();
				for(i=0;i<flightlist.size();i++) {
					if(flightlist.get(i).getplanningentryname().equals(tempname)) {
						break;
					}
				}
				if(i==flightlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println();
					break;
				}
				tempentry=flightlist.get(i);
				myapis.findPreEntryPerResource(tempentry.getresource(),tempentry,flightlist);
				System.out.println("\n");
				break;
			case "13":
				System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����)");
				tempname=scanner.nextLine();
				FlightEntryBoard flightboard=new FlightEntryBoard();	
				flightboard.setairportlocation(tempname);
				flightboard.getsortcomeentry(flightlist);
				flightboard.getsorttoentry(flightlist);
				flightboard.createFlightEntryBoard();
				flightboard.visualize();	
				System.out.println("\n");
				break;
			case "14":
				System.out.println("��ǰ���к���ƻ���ĸ���Ϊ��"+flightlist.size()+"\n");
				break;
			default:
				System.out.println("��������ȷָ��\n");
				break;	
				
			}
		}
		
		
	}
	
	/**
	 * ������Ž���ת�����ڱȽ�
	 * @param a ��ת�������
	 * @return ת����ĺ����
	 */
	public static String changeformat(String a) {  //ת������Ž����ж�
		if(a.length()==6) {
			return a;
		}
		else if(a.length()==4) {
			return a.substring(0, 2)+"00"+a.substring(2);	
		}
		else{
			return a.substring(0, 2)+"0"+a.substring(2);
		}
	}
}
