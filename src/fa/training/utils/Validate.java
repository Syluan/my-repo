package fa.training.utils;

import java.util.regex.Pattern;
import java.sql.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Validate {
	 /**
	  * @author : Luanhs
	  * @birthday: 02/11/2023
	  * kiểm tra xem : định dạng ngày
	  * @param date
	  * @return
	  */
	public  java.util.Date convertStringToDate(String stringDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    java.util.Date date;
	    try {
	        date = dateFormat.parse(stringDate);
	    } catch (ParseException e) {
	        throw new RuntimeException("Invalid date string: " + stringDate, e);
	    }
	    return date;
	}
	
	 /**
	 * @author : Luanhs
	 * @birthday: 02/11/2023
	 * kiểm tra xem : Ngày có đúng định dang không ?
	 * @param date
	 * @return
	 */
	    public boolean isDateAfterToday(String date) {
		if (date == null || date.isEmpty()) {
		    return false;
		}
		LocalDate today = LocalDate.now();
		LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		return d.isAfter(today);
	    }
	    
	    /**
	     * @author : Luanhs
	     * Chuyển đổi chuỗi đại diện cho ngày tháng sang đối tượng Date
	     * @param date chuỗi đại diện cho ngày tháng cần chuyển đổi
	     * @return đối tượng Date sau khi chuyển đổi
	     */
	    public Date converStringToDate(String date) {
	        // Tách chuỗi đại diện cho ngày tháng thành mảng các chuỗi con
	        String[] dd = date.split("/");

	        // Định dạng lại chuỗi với định dạng 'yyyy-MM-dd'
	        String d = dd[2] + "-" + dd[1] + "-" + dd[0];

	        // Tạo đối tượng Date từ chuỗi định dạng
	        Date ddd = Date.valueOf(d);

	        // Trả về đối tượng Date đã được chuyển đổi
	        return ddd;
	    }
	    
	    public boolean isDateAfterToday1(String date) {

	    	if (date == null || date.isEmpty()) {
	    	    return false;
	    	}

	    	LocalDate today = LocalDate.now();

	    	LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

	    	return d.isAfter(today);
	        }
		
	
	    
	    /**
		 * @author : Luanhs
		 * @birthday: 02/11/2023
		 * kiểm tra xem : Kiểm tra ngày nhập vào so với ngày hiện tại 
		 * @param date
		 * @return
		 */
		public boolean checkDate(java.util.Date date) {
			LocalDate x = LocalDate.now();
			Date now = Date.valueOf(x);
			if (now.compareTo(date) > 0) {
				return true;
			}
			return false;

		}
	    /**
		 * @author : Luanhs
		 * @birthday: 02/11/2023
		 * kiểm tra xem : id có đúng format IMxxx (x là số)
		 * @param id
		 * @return
		 */
		public boolean checkID(String id) {
			Pattern x = Pattern.compile("^IM[0-9]{3}$");
			if (x.matcher(id).find())
				return true;
			else
				return false;

		}
		public boolean checkMoney(long  tien) {
			
			if (tien >= 0 )
				return true;
			else
				return false;

		}
		public boolean checkCard(String cardNO) {
			Pattern x = Pattern.compile("^9704[0-9]{8}$");
			if (x.matcher(cardNO).find())
				return true;
			else
				return false;

		}
		public boolean checkPassport(String passport) {
			Pattern x = Pattern.compile("^[a-zA-Z0-9]+$");
			if (x.matcher(passport).find())
				return true;
			else
				return false;

		}
//		if (validate.checkID(hanhkhach.getPassengerID()) == false) {
//			try {
//				throw new IdException(countRow + "PassengerID must be in format of  ");
//			} catch (IdException e) {
//				System.out.println(e.getMessage());
//			}
//			continue;
//		}
		/**
		 * @author : Luanhs
		 * @birthday: 02/11/2023
		 * kiểm tra xem : Kiểm tra id có trùng trong database chưa ?
		 * @param value
		 * @return
		 */
		public boolean isEmptyID(String value) {
			boolean isEmpty = false;
			Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = " Select immigrantID from xuatkhau  where immigrantID = ? ";
			try {
				conn = ConnectionUtil.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, value);
				rs = pst.executeQuery();
				if (rs.next()) {
					isEmpty = true;
				} else
					isEmpty = false;
			} catch (Exception e) {
				System.out.println("Program have an unexpected error occcurred ");
				e.printStackTrace();
			} finally {
				ConnectionUtil.close(rs, pst, conn);
			}
			return isEmpty;
		}
}