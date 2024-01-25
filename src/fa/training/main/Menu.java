package fa.training.main;

import java.util.ArrayList;
import java.util.Scanner;

import fa.training.dao.CardDao;
import fa.training.dao.SortComparator;
import fa.training.entities.Card;
import fa.training.services.Constants;
import fa.training.services.ReadFile;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	static CardDao cardDao = new CardDao();

	private static void menuDuNo() {
			System.out.println("nhap id can ");
			String id = sc.nextLine();
			
			Card card = cardDao.findByID(id);
			if (card == null) {
				System.out.println("Card not found. Please enter a valid ID.");
				
			}
			System.out.println("nhap so tien ");
			long money = Long.parseLong(sc.nextLine());
			do {
			
			System.out.println("So Du Va So No");
			System.out.println("1. Thanh Toan");
			System.out.println("2. Nop tien");
			System.out.println("3. Rut Tien");
			System.out.println("4. Quay ve menu chinh");

			System.out.println("Chon chuc nang");

			String choice = sc.nextLine();
			switch (choice) {
			case "1":
				cardDao.thanhToan(card, money);
				mainMenu();
				break;
			case "2":
				cardDao.nopTien(card, money);
				mainMenu();
				break;
			case "3":
				cardDao.rutTien(card, money);
				mainMenu();
				break;
			case "4":
				mainMenu();
				break;
			default:
				break;
			}
		} while (true);
	}

	private static void mainMenu() {

		try {
			while (true) {
				System.out.println("1: ĐỌC FILE VÀ INSERT VÀO DATABASE");
				System.out.println("2: HIỂN THỊ DATABASE LÊN");
				System.out.println("3: Update");
				System.out.println("4: Sort in descending order by balance");
				System.out.println("5: Delete all");
				System.out.println("0: Exit");
				String choose = sc.nextLine();

				switch (choose) {
				case "1":
					ReadFile.insertDB(ReadFile.readFileAndAddToList("card.csv"));
					break;

				case "2":
					cardDao.showInformation(cardDao.selectAllRecord(Constants.SHOW_ALL));

					break;

				case "3":
					//cardDao.thanhToan(cardDao.findByID("970477770000"), 5000000);
					menuDuNo();

					break;

				case "4":
					ArrayList<Card> cards = cardDao.selectAllRecord(Constants.SHOW_ALL);
					SortComparator sortComparator = new SortComparator();
					sortComparator.sapXepTheoLoaiVaSoDu(cards);
					cardDao.showInformation(cards);
					break;

				case "5":
					cardDao.deleteAll();

					break;

				case "0":
					System.out.println("Exited");
					return;

				default:
					System.out.println("Invalid choice");
				}
			}
		} catch (Exception e) {
			System.out.println("An unexpected error occurred!");
		}
	}

	public static void main(String[] args) {
		mainMenu();
	}
}
