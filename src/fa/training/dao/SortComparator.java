package fa.training.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fa.training.entities.Card;
import fa.training.entities.CreditCard;
import fa.training.entities.DebitCard;

public class SortComparator {

	public ArrayList<Card> sapXepTheoLoaiVaSoDu(ArrayList<Card> cards) {
		// Sử dụng Comparator để so sánh thẻ theo loại và số dư
		Comparator<Card> cardComparator = new Comparator<Card>() {
			@Override
			public int compare(Card card1, Card card2) {
				// Nếu cả hai thẻ là CreditCard
				if (card1 instanceof CreditCard && card2 instanceof CreditCard) {
					// So sánh theo loại và số dư
					CreditCard creditCard1 = (CreditCard) card1;
					CreditCard creditCard2 = (CreditCard) card2;

					int loaiSoSanh = creditCard2.getType() - creditCard1.getType();
					if (loaiSoSanh == 0) {
						return Integer.compare(creditCard2.getDuNo(), creditCard1.getDuNo());
					} else {
						return loaiSoSanh;
					}
				}
				// Nếu cả hai thẻ là DebitCard
				else if (card1 instanceof DebitCard && card2 instanceof DebitCard) {
					// So sánh theo loại và số dư
					DebitCard debitCard1 = (DebitCard) card1;
					DebitCard debitCard2 = (DebitCard) card2;

					int loaiSoSanh = debitCard2.getType() - debitCard2.getType();
					if (loaiSoSanh == 0) {
						return Integer.compare(Math.toIntExact(debitCard2.getSoDu()),
								Math.toIntExact(debitCard1.getSoDu()));
					} else {
						return loaiSoSanh;
					}
				}
				// Nếu thẻ không cùng loại, so sánh theo tên lớp
				else {
					return card2.getClass().getName().compareTo(card1.getClass().getName());
				}
			}
		};

		// Sắp xếp danh sách thẻ
		Collections.sort(cards, cardComparator);
		return cards;
	}

}
