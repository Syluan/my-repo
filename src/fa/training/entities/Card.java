package fa.training.entities;

import java.util.Date;

public abstract class Card {
	private int type;
	private String cardNo;
	private String maKH;
	private Date ngayPhatHanh;
	private String maTK;
	
	public Card(int type, String cardNo, String maKH, Date ngayPhatHanh, String maTK) {
		super();
		this.type = type;
		this.cardNo = cardNo;
		this.maKH = maKH;
		this.ngayPhatHanh = ngayPhatHanh;
		this.maTK = maTK;
	}

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Card [type=" + type + ", cardNo=" + cardNo + ", maKH=" + maKH + ", ngayPhatHanh=" + ngayPhatHanh
				+ ", maTK=" + maTK + "]";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public Date getNgayPhatHanh() {
		return ngayPhatHanh;
	}

	public void setNgayPhatHanh(Date ngayPhatHanh) {
		this.ngayPhatHanh = ngayPhatHanh;
	}

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}
	
	public abstract String showCardInfo();
	
	public abstract long thanhToan(float money );
	
	public abstract long nopTien(float money );
	
	public abstract long rutTien(float money );
	
}
