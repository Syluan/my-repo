package fa.training.entities;

import java.util.Date;

public class DebitCard extends Card{
	private int soDu;

	public DebitCard() {
		super();
		// TODO Auto-generated constructor stub
	}



	public DebitCard(int type, String cardNo, String maKH, Date ngayPhatHanh, String maTK, int soDu) {
		super(type, cardNo, maKH, ngayPhatHanh, maTK);
		this.soDu = soDu;
	}

	public long getSoDu() {
		return soDu;
	}

	public void setSoDu(int soDu) {
		this.soDu = soDu;
	}

	@Override
	public String toString() {
		return "DebitCard [soDu=" + soDu + "]";
	}

	@Override
	public String showCardInfo() {
		return "DebitCard "+ super.toString()+" soDu=" + soDu + "";
		
	}

	@Override
	public long thanhToan(float money) {
		// TODO Auto-generated method stub
		return (long) (this.getSoDu() - money);
	}

	@Override
	public long nopTien(float money) {
		// TODO Auto-generated method stub
		return (long) (this.getSoDu() + money);
	}

	@Override
	public long rutTien(float money) {
		// TODO Auto-generated method stub
		return (long) (this.getSoDu() - money);
	}
	
	

	
}
