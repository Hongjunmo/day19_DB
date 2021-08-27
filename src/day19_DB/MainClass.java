package day19_DB;

import java.util.*;

public class MainClass {
	public static void main(String[] args) {

		DBClass db = new DBClass();
		Scanner scan = new Scanner(System.in);
		
		int num;
		while(true) {
			System.out.println("1.등록 2.전체보기 3.삭제 4.수정");
			num=scan.nextInt();
			switch(num) {
			case 1:
				System.out.println("학번 입력: ");
				String stNum = scan.next();
				System.out.println("이름 입력: ");
				String name = scan.next();
				System.out.println("나이 입력: ");
				int age = scan.nextInt();
				
//				int result=db.saveDate(stNum,name,age);
				int result=db.saveDate02(stNum,name,age);
				if(result ==1) {
					System.out.println("저장 완료");
				}else {
					System.out.println("동일 아이디가 존재함.");
				}
				break;
				
			case 2:
				ArrayList<StudentDTO> list =db.getUsers();
				if(list.size()==0) {
					System.out.println("저장된 데이터가 없습니다");
					
				}else {
					for(int i=0; i<list.size(); i++) {
						System.out.println("학번 : "+list.get(i).getStNum());
						System.out.println("이름 : "+list.get(i).getName());
						System.out.println("나이 : "+list.get(i).getAge());
						System.out.println("----------------------------");
					}
				}
				break;
			
			case 3:
				System.out.println("삭제 학번 입력 :");
				String userNum =scan.next();
				int re=db.delete(userNum);
				if(re==1) {
					System.out.println("삭제완료");
				}else {
					System.out.println("해당 학번 없엉");
				}
				break;
			
			case 4:
				System.out.println("수정할 학번 입력:");
				String stNum1 = scan.next();
				System.out.println("수정할 이름 입력:");
				String name1 = scan.next();
				System.out.println("수정할 나이 입력:");
				int age1 = scan.nextInt();
				
//				int res=db.modify(stNum1,name1,age1);
				
				if (db.modify(stNum1,name1,age1)==1) {
					System.out.println("수정완료");
				}else {
					System.out.println("해당 학번 없엉");
				}
				
				
				
				break;
			
			}
			
		}
		
		
	}
}
