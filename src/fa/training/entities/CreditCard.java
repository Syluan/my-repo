package fa.training.entities;

import java.util.Date;

public class CreditCard extends Card {
	private int duNo;
	private int hanMuc;
	private Date ngayHetHan;
	public CreditCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreditCard(int type, String cardNo, String maKH, Date ngayPhatHanh, String maTK) {
		super(type, cardNo, maKH, ngayPhatHanh, maTK);
		// TODO Auto-generated constructor stub
	}
	
	public CreditCard(int type, String cardNo, String maKH, Date ngayPhatHanh, String maTK, int duNo, int hanMuc,
			Date ngayHetHan) {
		super(type, cardNo, maKH, ngayPhatHanh, maTK);
		this.duNo = duNo;
		this.hanMuc = hanMuc;
		this.ngayHetHan = ngayHetHan;
	}
	
	public int getDuNo() {
		return duNo;
	}
	public void setDuNo(int duNo) {
		this.duNo = duNo;
	}
	public int getHanMuc() {
		return hanMuc;
	}
	public void setHanMuc(int hanMuc) {
		this.hanMuc = hanMuc;
	}
	public Date getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(Date ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}
	@Override
	public String toString() {
		return "CreditCard [duNo=" + duNo + ", hanMuc=" + hanMuc + ", ngayHetHan=" + ngayHetHan + "]";
	}
	@Override
	public String showCardInfo() {
		return "CreditCard " +super.toString()+"duNo=" + duNo + ", hanMuc=" + hanMuc + ", ngayHetHan=" + ngayHetHan + "";
		
	}
	@Override
	public long thanhToan(float money) {
		return (long) (this.getDuNo() + money);
	}
	@Override
	public long nopTien(float money) {
		// TODO Auto-generated method stub
		return (long) (this.getDuNo() - money);
	}
	@Override
	public long rutTien(float money) {
		// TODO Auto-generated method stub
		return (long) (this.getDuNo() - money * 1.1);
	}
	
	
	
}
