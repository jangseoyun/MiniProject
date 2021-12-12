package com.project.mini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniApp {

	public static void main(String[] args) throws IOException {

		// ArrayList로 관리합니다
		String name;
		String ph;
		String company;

		// 시작화면
		System.out.println("##################################");
		System.out.println("#        전화번호 관리 프로그램        #");
		System.out.println("##################################");
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("----------------------------------");
		System.out.print(">메뉴번호:");

		// Scanner,List 생성
		Scanner sc = new Scanner(System.in);
		List<Mini> mList = new ArrayList<Mini>();

		// 파일 읽어올 준비(모든 파일용)
		InputStream is = new FileInputStream("C:\\javaStudy\\workspace\\미니프로젝트\\phoneDB.txt");
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// 파일 읽어오기
		while (true) {

			// data 읽어오기
			String pline = br.readLine();
			if (pline == null) {
				break;
			}
			// 나눠서 배열에 넣어두기
			String[] dataArray = pline.split(",");
			name = dataArray[0];
			ph = dataArray[1];
			company = dataArray[2];

			// 리스트에 추가
			Mini mini = new Mini(name, ph, company);
			mList.add(mini);

		}
		// 메뉴 구현
		while (true) {

			String inputNum = sc.nextLine();

			if (inputNum.equals("1")) {
				System.out.println("<1.리스트>");
				for (int i = 0; i < mList.size(); i++) {
					System.out.print((i + 1) + ". ");
					mList.get(i).showinfo();
				}
			} else if (inputNum.equals("2")) {
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String nameAdd = sc.nextLine();
				System.out.print(">휴대전화: ");
				String phAdd = sc.nextLine();
				System.out.print(">회사전화: ");
				String companyAdd = sc.nextLine();

				name = nameAdd;
				ph = phAdd;
				company = companyAdd;

				Mini miniAdd = new Mini(name, ph, company);
				mList.add(miniAdd);
				System.out.println("[등록되었습니다.]");
			} else if (inputNum.equals("3")) {
				System.out.println("<3.삭제>");
				// 문자 -> 숫자 변경
				System.out.print(">번호: ");
				String removeInput = sc.nextLine();
				int removeIndex = Integer.parseInt(removeInput);
				// 제거
				mList.remove(mList.get(removeIndex - 1));
				System.out.println("[삭제되었습니다.]");
			} else if (inputNum.equals("4")) {
				System.out.print(">이름검색:");
				String search = sc.nextLine();

				for (int i = 0; i < mList.size(); i++) {
					int findName = mList.get(i).getName().indexOf(search);

					if (findName >= 0) {
						System.out.print(i + 1 + ". ");
						mList.get(i).showinfo();
					}
				}
			} else if (inputNum.equals("5")) {
				System.out.println("##################################");
				System.out.println("#        이용해주셔서 감사합니다        #");
				System.out.println("##################################");
				break;
			} else {
				System.out.println("[다시 입력해 주세요]");
			}
			// ------if문 종료----------------------

			OutputStream ow = new FileOutputStream("C:\\javaStudy\\workspace\\미니프로젝트\\phoneDB.txt");
			OutputStreamWriter osw = new OutputStreamWriter(ow, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			// 파일로 출력
			for (Mini m : mList) {
				bw.write(m.printout());
				bw.newLine();
			}
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("----------------------------------");
			System.out.print(">메뉴번호:");

			bw.close();
		}

		// -------while문 종료----------------------

		br.close();
		sc.close();
	}

}
