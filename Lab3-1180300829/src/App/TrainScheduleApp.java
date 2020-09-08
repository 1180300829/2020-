package App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import Board.TrainEntryBoard;
import EntryState.*;
import Factory.TrainEntryFactory;
import Location.*;
import PlanningEntry.BlockableEntryImpl;
import PlanningEntry.MultipleLacationEntryImpl;
import PlanningEntry.MultipleSortedResourceEntryImpl;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TrainEntry;
import PlanningEntryAPIs.PlanningEntryAPIs;

import Resource.Carriage;
import Timeslot.Timeslot;

public class TrainScheduleApp {

    private static List<TrainEntry<Carriage>> trainlist=new ArrayList<>(); //��������TrainEntry�ļ���
	
	 /**
     * �˵�
     */
	public static void menu() {
		System.out.println("1.����һ���µĸ����ƻ��������Ϣ(Ϊ�˷�������Ϣ���⣬����ʱ��ʱ������Ϊ��ǰʱ��һСʱǰ���ʱ��)");
		System.out.println("2.Ϊĳ�������ƻ��������Դ");
		System.out.println("3.���ĳ�������ƻ����λ��");
		System.out.println("4.���ĳ�������ƻ������Դ");
		System.out.println("5.����ĳ�������ƻ������Դ");
		System.out.println("6.ɾ��ĳ�������ƻ������Դ");
		System.out.println("7.(����)����ĳ�������ƻ���");
		System.out.println("8.����ĳ�������ƻ���");
		System.out.println("9.����ĳ�������ƻ���");
		System.out.println("10.ȡ��ĳ�������ƻ���");
		System.out.println("11.�鿴ĳ�������ƻ����״̬");
		System.out.println("****(ע�⣺ִ��12,13,14�Ĳ�����ҪΪÿһ���ƻ��������Դ��ִ��,�����쳣)****");
		System.out.println("12.�������ƻ������Ƿ����λ�ú���Դ��ռ��ͻ");
		System.out.println("13.���ĳ��������Դ���г�����ʹ�ø���Դ�ĸ����ƻ���");
		System.out.println("14.���ĳ��������Դ��ѡ�к��и���Դ��ĳ�������ƻ���г�����ǰ��ƻ���(���һ������)");
		System.out.println("15.ѡ��ĳ��λ�ã�չʾ��ǰʱ�̸�λ�õ���Ϣ��");
		System.out.println("16.��ʾ��ǰ���и����ƻ���ĸ���");
	}
	
	/**
	 * 
	 * @param args
	 * @throws ParseException ʱ���ʽ��Ϊyyyy-MM-dd HH:mm
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException {
		MultipleLacationEntryImpl a;
		MultipleSortedResourceEntryImpl<Carriage> b;
		BlockableEntryImpl c;
		String choice,weidu="��γ40��",jingdu="����112��";
		Scanner scanner=new Scanner(System.in);
		TrainState state;
		Carriage mycarriage;
		List<Carriage> allcarriage=new ArrayList<>();
		Calendar canceltimeone;
		PlanningEntryAPIs myapis=new PlanningEntryAPIs<>();
		int blockflag;
		String[] temp;
		String yixie;
		System.out.println("�ʼ��ִ�е�һ��");
		
		while(true) {
			menu();
			System.out.println("��������Ĳ�����");
			choice=scanner.nextLine();
			switch(choice) {
			case "1":
				a=new MultipleLacationEntryImpl();
				b=new MultipleSortedResourceEntryImpl<Carriage>();
				c=new BlockableEntryImpl();
				PlanningEntry origintrain=new TrainEntryFactory().getTrainEntry(a, b, c); //�ù���������������
				TrainEntry<Carriage> train=(TrainEntry<Carriage>)origintrain;
				System.out.println("�˸����ƻ����Ѿ��������,��������һЩ��Ϣ��ɳ�ʼ״̬�Ľ�����");
				List<Location> alllocation=new ArrayList<>();
				int ll=0;
				System.out.println("������������г����и���վ���ƣ����롰������ֹͣ����(ÿ����һ����һ�»س�)(eg ����)��");
				String tempname;
				while(!(tempname=scanner.nextLine()).equals("����")) {
					FlightTrainLocation from=new FlightTrainLocation(weidu,jingdu,tempname);
					alllocation.add(from);
					ll++;
				}
                train.setlocations(alllocation);
                List<Timeslot> alltime=new ArrayList<>();
                boolean hh=false;
				System.out.println("������������г�����ʱ���(ʱ�����Ӧ�ñȸ���վ����һ)(�ö��Ÿ�����ÿ����һ�԰�һ�»س�)(eg 2020-01-01 12:00,2020-01-01 14:00)��");
				for(int pp=0;pp<ll-1;pp++) {
					try {
						tempname=scanner.nextLine();
						temp=tempname.split(",");
						Timeslot mytime=new Timeslot(temp[0],temp[1]);
						alltime.add(mytime);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��1����������ȷ��ʽ����\n");
						 hh=true;
						 //e.printStackTrace();
						 break;
					}
					//tempname=scanner.nextLine();
					//temp=tempname.split(",");
					//Timeslot mytime=new Timeslot(temp[0],temp[1]);
					//alltime.add(mytime);
				}
				if(hh==true) {
					break;
				}
				train.settimeslot(alltime);
				System.out.println("�����복�κ�(eg G1020)��");
				String trainname=scanner.nextLine();
				train.setplanningentryname(trainname);
				System.out.println("�ƻ����������óɹ�");
				state = TrainWaitingState.instance;
				train.setcurrentstate(state);
				System.out.println("�ƻ��ǰ״̬���óɹ�");
				System.out.println("��Ϣ�������\n");
				trainlist.add(train);
				break;
			case "2":
				System.out.println("���������������Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				int i;
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				TrainEntry<Carriage> tempentry=trainlist.get(i);
				allcarriage=new ArrayList<>();
				boolean gg=false;
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("����δ���䳵��(Waiting)")) {
					System.out.println("�밴�մ�����������һ�鳵��ı��(��ͬ���᲻ͬ)�����ͣ���Ա����������ݣ����롰������ֹͣ����(�ö��Ÿ�����ÿ����һ�����ᰴһ�»س�)(eg A01,һ����,100,2012)��");
					while(!(tempname=scanner.nextLine()).equals("����")) {
						try {
							temp=tempname.split(",");
							mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
							allcarriage.add(mycarriage);
						}catch(ArrayIndexOutOfBoundsException e) {
							 System.out.println("δ��,������������ִ��2����������ȷ��ʽ����\n");
							 //e.printStackTrace();
							 gg=true;
							 break;
						}
						//temp=tempname.split(",");
						//mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
						//allcarriage.add(mycarriage);
					}
					if(gg==true) {
						break;
					}
					tempentry.setresource(allcarriage);
					state=((TrainState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					trainlist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�����÷ɻ���Դ����\n");
				}
				break;
			case "3":	
				System.out.println("�����ƻ���λ�ò��ɱ��");
				System.out.println();
				break;
			case "4":	
				System.out.println("��������������Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				allcarriage=tempentry.getresource();
				int j;
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")) {
					System.out.println("����������Ҫ����ĳ���ı��(eg A01)��");
					tempname=scanner.nextLine();
					for(j=0;j<allcarriage.size();j++) {
						if(allcarriage.get(j).getcarriagenumber().equals(tempname)) {
							break;
						}
					}
					if(j==allcarriage.size()) {
						System.out.println("û�иó���");
						System.out.println("\n");
						break;
					}
					Carriage tempcarriage=allcarriage.get(j);
					System.out.println("����������ĳ���ı�ţ����ͣ���Ա�����������(�ö��Ÿ���)(eg NB02,������,100,2011)��");
					try {
						temp=(scanner.nextLine()).split(",");
						mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��4����������ȷ��ʽ����\n");
						 //e.printStackTrace();
						 break;
					}
					//temp=(scanner.nextLine()).split(",");
					//mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
					tempentry.changeresource(tempcarriage,mycarriage);
					trainlist.set(i, tempentry);
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�б���������\n");
				}
				break;
			case "5":
				System.out.println("����������������Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				allcarriage=tempentry.getresource();
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")) {
					System.out.println("���������ӵĳ���ı�ţ����ͣ���Ա�����������(�ö��Ÿ���)(eg AS02,������,100,2011)��");
					try {
						temp=(scanner.nextLine()).split(",");
						mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
					}catch(ArrayIndexOutOfBoundsException e) {
						 System.out.println("δ��,������������ִ��5����������ȷ��ʽ����\n");
						 //e.printStackTrace();
						 break;
					}
					//temp=(scanner.nextLine()).split(",");
					//mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
					System.out.println("���������ӵĳ�����һ�鳵���е�λ��(��һ�ڳ���Ĭ��λ��Ϊ1)(eg 3)��");
					tempname=scanner.nextLine();
					int weizhi=Integer.parseInt(tempname);
					if(weizhi>tempentry.getresource().size()+1||weizhi<1) {
						System.out.println("���ӵĳ���λ�ò��Ϸ�\n");
						break;
					}
					tempentry.addresource(mycarriage,weizhi-1);
					trainlist.set(i, tempentry);
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��������Դ����\n");
				}
				break;
			case "6":
				System.out.println("����������ɾ����Դ�ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				allcarriage=tempentry.getresource();
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")) {
					System.out.println("����������Ҫɾ���ĳ���ı��(eg A01)��");
					tempname=scanner.nextLine();
					for(j=0;j<allcarriage.size();j++) {
						if(allcarriage.get(j).getcarriagenumber().equals(tempname)) {
							break;
						}
					}
					if(j==allcarriage.size()) {
						System.out.println("û�иó���");
						System.out.println("\n");
						break;
					}
					Carriage tempcarriage=allcarriage.get(j);
					tempentry.deleteresource(tempcarriage);
					trainlist.set(i, tempentry);
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��ɾ����Դ����\n");
				}
				break;
			case "7":
				System.out.println("����������Ҫ�����ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")||
						((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("������;����(Blocked)")) {	
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.launch(yixie)) {
					state=((TrainState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					trainlist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��������������\n");
				}
				break;
			case "8":
				System.out.println("����������Ҫ�����ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����Ѵ���ʼվ����(Running)")) {	
					System.out.println("����������Ҫ�����ĸ���վλ�ã�(eg �人)");
					yixie=scanner.nextLine();
					blockflag=tempentry.trainblock(yixie);
					if(blockflag==-1) {
						break;
					}
					state=((TrainState)tempentry.getcurrentstate()).move('b');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					trainlist.set(i, tempentry);
					System.out.println("\n");
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��������������\n");
				}
				break;
			case "9":
				System.out.println("����������Ҫ�����ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����Ѵ���ʼվ����(Running)")) {	
					System.out.println("������ָ�����");
					yixie=scanner.nextLine();
					if(tempentry.finish(yixie)) {
					state=((TrainState)tempentry.getcurrentstate()).move('a');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					trainlist.set(i, tempentry);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ�н�����������\n");
				}
				break;
			case "10":
				System.out.println("����������Ҫȡ���ĸ����ƻ���ĳ��κ�(eg G1020)��");
				tempname=scanner.nextLine();
				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ���");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
				if(((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("�����ѷ���һ�鳵��(Allocated)")||
				((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("������;����(Blocked)")||
				((TrainState)tempentry.getcurrentstate()).gettrainstate().equals("����δ���䳵��(Waiting)")) {	
					System.out.println("������ָ�ȡ��");
					yixie=scanner.nextLine();	
					if(tempentry.cancel(yixie)) {
					canceltimeone=Calendar.getInstance();
					String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(canceltimeone.getTime()); 
					canceltimeone.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str));
					System.out.println("�ú���ƻ���ȡ����ʱ��Ϊ"+str);
					state=((TrainState)tempentry.getcurrentstate()).move('b');
					tempentry.setcurrentstate(state);
					System.out.println("�ƻ��ǰ״̬���óɹ�");
					trainlist.remove(i);
					System.out.println("\n");
					}
					else {
						System.out.println("\n");
						break;
					}
				}
				else {
					System.out.println("��ǰ״̬�²���ִ��ȡ����������\n");
				}
				break;
             case "11":
            	System.out.println("����������Ҫ�鿴�ĸ����ƻ���ĳ��κ�(eg G1020)��");
 				tempname=scanner.nextLine();
 				for(i=0;i<trainlist.size();i++) {
					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
						break;
					}
				}
				if(i==trainlist.size()) {
					System.out.println("û�иüƻ�������ѱ�ȡ��");
					System.out.println("\n");
					break;
				}
				tempentry=trainlist.get(i);
 				System.out.println("�ø����ƻ����״̬Ϊ��"+((TrainState)tempentry.getcurrentstate()).gettrainstate());
 				System.out.println("\n");
				break;
             case "12":
            	System.out.println("����λ�ö�ռ��ͻ�������ѡ�������㷨�������ж�(����1ʹ�õ�һ�֣�����2ʹ�õڶ���)����ѡ������(eg 1)");
    			tempname=scanner.nextLine();
            	System.out.println("���мƻ�����λ�ö�ռ��ͻ������£�");
    			myapis.checkLocationConflict(trainlist,tempname);
    			System.out.println("���мƻ�������Դ��ռ��ͻ������£�");
    			myapis.checkResourceExclusiveConflict(trainlist);
    			System.out.println();
            	 break;
             case "13":
            	System.out.println("����������Ҫ�鿴�ĳ�����Դ�ĳ���ı�ţ����ͣ���Ա�����������(�ö��Ÿ���)(eg A01,������,100,2011)��");
            	try {
            		temp=(scanner.nextLine()).split(",");
    				mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
				}catch(ArrayIndexOutOfBoundsException e) {
					 System.out.println("δ��,������������ִ��13����������ȷ��ʽ����\n");
					 //e.printStackTrace();
					 break;
				}
            	//temp=(scanner.nextLine()).split(",");
				//mycarriage=new Carriage(temp[0],temp[1],Integer.parseInt(temp[2]),temp[3]);
 				myapis.findEntryPerResource(mycarriage, trainlist);
 				System.out.println("\n");
            	break;
             case "14":
            	System.out.println("����������Ҫ�鿴��ǰ��ƻ���ĳ��κ�(eg G1020)��");
            	tempname=scanner.nextLine();
  				for(i=0;i<trainlist.size();i++) {
 					if(trainlist.get(i).getplanningentryname().equals(tempname) ){
 						break;
 					}
 				}
 				if(i==trainlist.size()) {
 					System.out.println("û�иüƻ���");
 					System.out.println("\n");
 					break;
 				}
 				tempentry=trainlist.get(i);
 				System.out.println("��������Ҫ�鿴ǰ��ƻ���ĸø����ƻ�����ӵ�еĳ�����Դ�ı��(eg A01)��");
 				tempname=scanner.nextLine();
 				for(j=0;j<tempentry.getresource().size();j++) {
 					if(tempentry.getresource().get(j).getcarriagenumber().equals(tempname)) {
 						break;
 					}
 				}
 				mycarriage=tempentry.getresource().get(j);
                myapis.findPreEntryPerResource(mycarriage,tempentry,trainlist);	
                System.out.println("\n");	
            	break;
             case "15":
            	System.out.println("����������Ҫչʾ��ǰʱ�̵���Ϣ���λ��(eg ����)");
    			tempname=scanner.nextLine();
    			TrainEntryBoard trainboard=new TrainEntryBoard();	
    			trainboard.setrailwaylocation(tempname);
    			trainboard.getsortcomeentry(trainlist);
    			trainboard.getsorttoentry(trainlist);
    			trainboard.createTrainEntryBoard();
    			trainboard.visualize();	
    			System.out.println("\n");
            	 break;
             case "16":
                System.out.println("��ǰ���и����ƻ���ĸ���Ϊ��"+trainlist.size()+"\n");
 				break;
             default:
 				System.out.println("��������ȷָ��\n");
 				break;		
			}

			
		}
	}
}
