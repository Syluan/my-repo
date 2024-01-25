package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import fa.training.entities.Card;
import fa.training.entities.CreditCard;
import fa.training.entities.DebitCard;
import fa.training.utils.ConnectionUtil;

public class CardDao {
	public void deleteAll() {
		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement("DELETE FROM card")) {
			int count = pst.executeUpdate();

			if (count > 0) {
				System.out.println("Delete success " + count + " records");
			} else {
				System.out.println("Không tìm thấy bản ghi nào để xóa");
			}
		} catch (SQLException e) {
			System.out.println("Hệ thống đã gặp sự cố không mong muốn khi xóa bản ghi. Không thể xóa.");
			e.printStackTrace();
		}
	}

	public void showInformation(ArrayList<Card> list) {
		if (list.isEmpty()) {
			System.out.println("Danh sách rỗng");
			return;
		}
		for (Card card : list) {
			System.out.println(card.showCardInfo());
		}
	}

	public ArrayList<Card> selectAllRecord(String sql) {
		ArrayList<Card> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Card card;
				int type = rs.getInt("type");
				String cardNo = rs.getString("cardNo");
				String maKH = rs.getString("maKH");
				Date ngayPhatHanh = rs.getDate("ngayPhatHanh");
				String maTK = rs.getString("maTK");
				int soDu = rs.getInt("soDu");
				int duNo = rs.getInt("soDu");

				if (type == 1) {
					DebitCard debitCard = new DebitCard();
					debitCard.setType(type);
					debitCard.setCardNo(cardNo);
					debitCard.setMaKH(maKH);
					debitCard.setNgayPhatHanh(ngayPhatHanh);
					debitCard.setMaTK(maTK);
					debitCard.setSoDu(soDu);
					card = debitCard;
				} else if (type == 2) {
					CreditCard creditCard = new CreditCard();
					creditCard.setType(type);
					creditCard.setCardNo(cardNo);
					creditCard.setMaKH(maKH);
					creditCard.setNgayPhatHanh(ngayPhatHanh);
					creditCard.setMaTK(maTK);
					creditCard.setDuNo(duNo);
					creditCard.setHanMuc(rs.getInt("hanMuc"));
					creditCard.setNgayHetHan(rs.getDate("ngayHetHan"));
					card = creditCard;
				} else {
					// Handle unknown card type or do something else
					continue;
				}

				list.add(card);
			}
		} catch (SQLException e) {
			System.out.println("Select fail!!");
			e.printStackTrace();
		}

		return list;
	}

	 public Card findByID(String id) {

			Card card = null;
			String sql =" SELECT * FROM Card where cardNo = ?";
			Connection conn = null;
			try {
				conn = ConnectionUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				ResultSet resultSet = pst.executeQuery();
			    while (resultSet.next()) {
				if (resultSet.getString("type").equals("1")) {
				    card = new DebitCard(resultSet.getInt("type"), resultSet.getString("cardNo"),
					    resultSet.getString("maKH"), resultSet.getDate("ngayPhatHanh"),
					    resultSet.getString("maTK"), resultSet.getInt("soDu"));

				} else if (resultSet.getString("type").equals("2")) {
				    card = new CreditCard(resultSet.getInt("type"), resultSet.getString("cardNo"),
						    resultSet.getString("maKH"), resultSet.getDate("ngayPhatHanh"),
						    resultSet.getString("maTK"), resultSet.getInt("soDu"), resultSet.getInt("HanMuc"),
					    resultSet.getDate("NgayHetHan"));
				}
			    }
			} catch (SQLException e) {
			    e.printStackTrace();

			} finally {
				//Connection conn;
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return card;
		    }

	public void thanhToan(Card card, float money) {

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("Update Card set sodu = ? where cardNo = ?")) {
			pstmt.setInt(1, (int) card.thanhToan(money));
			pstmt.setString(2, card.getCardNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update table fail!! ");
			e.printStackTrace();
		}

	}

	public void nopTien(Card card, float money) {
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("Update Card set sodu = ? where cardNo = ?")) {
			pstmt.setInt(1, (int) card.nopTien(money));
			pstmt.setString(2, card.getCardNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update table fail!! ");
			e.printStackTrace();
		}
	}

	public void rutTien(Card card, float money) {
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("Update Card set sodu = ? where cardNo = ?")) {
			pstmt.setInt(1, (int) card.rutTien(money));
			pstmt.setString(2, card.getCardNo());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update table fail!! ");
			e.printStackTrace();
		}
	}
}
