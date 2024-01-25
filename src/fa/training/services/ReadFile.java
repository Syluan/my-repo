package fa.training.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import fa.training.entities.Card;
import fa.training.entities.CreditCard;
import fa.training.entities.DebitCard;
import fa.training.utils.ConnectionUtil;
import fa.training.utils.Validate;

public class ReadFile {
	public static ArrayList<Card> readFileAndAddToList(String nameFile) {
		Validate validate = new Validate();
		ArrayList<Card> listIm = new ArrayList<>();
//Sử dụng try-with-resources để tự động đóng tài nguyên
		try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] lineData = line.split(",", 9);
				int type = Integer.parseInt(lineData[0]);
				String cardNo = lineData[1].trim();
				String maKH = lineData[2].trim();
				Date ngayPhatHanh = validate.convertStringToDate(lineData[3].trim());
				String maTK = lineData[4].trim();

				if (type == 1) {
					int soDu = Integer.parseInt(lineData[5]);
					DebitCard debitCard = new DebitCard(type, cardNo, maKH, ngayPhatHanh, maTK, soDu);
					listIm.add(debitCard);
				} else if (type == 2) {
					int duNo = Integer.parseInt(lineData[5]);
					int hanMuc = Integer.parseInt(lineData[6]);
					Date ngayHetHan = validate.convertStringToDate(lineData[7]);

					CreditCard creditCard = new CreditCard(type, cardNo, maKH, ngayPhatHanh, maTK, duNo, hanMuc,
							ngayHetHan);
					listIm.add(creditCard);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("LOI DOC FILE");
			e.printStackTrace();
		}
		return listIm;
	}
	public static void insertDB(ArrayList<Card> list) {
	    
	    int count = 0;
	    try (Connection connection = ConnectionUtil.getConnection();
	         PreparedStatement pstmt = connection.prepareStatement(
	                 "INSERT INTO card (type, cardNo, maKH, ngayPhatHanh, maTK, soDu, hanMuc, ngayHetHan) VALUES (?,?,?,?,?,?,?,?)"
	         )
	    ) {
	        for (Card card : list) {
	            pstmt.setInt(1, card.getType());
	            pstmt.setString(2, card.getCardNo());
	            pstmt.setString(3, card.getMaKH());
	            pstmt.setDate(4, new java.sql.Date(card.getNgayPhatHanh().getTime()));
	            pstmt.setString(5, card.getMaTK());

	            if (card instanceof DebitCard) {
	                pstmt.setLong(6, ((DebitCard) card).getSoDu());
	                pstmt.setNull(7, java.sql.Types.INTEGER);
	                pstmt.setNull(8, java.sql.Types.DATE);
	            } else if (card instanceof CreditCard) {
	                pstmt.setFloat(6, ((CreditCard) card).getDuNo());
	                pstmt.setLong(7, ((CreditCard) card).getHanMuc());
	                pstmt.setDate(8, new java.sql.Date(((CreditCard) card).getNgayHetHan().getTime()));
	            }
	            pstmt.executeUpdate();
	            count++;
	        }
	    } catch (SQLException e) {
	        System.out.println("Insert table fail!! ");
	        e.printStackTrace();
	    }
	    System.out.println("Insert successfully " + count + " rows");
	}


}
