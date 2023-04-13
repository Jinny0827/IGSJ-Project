package ezen.project.IGSJ.review.dto;

public class ReviewDTO {

	/*
	 *	rvno	int
		pno	varchar(200)
		rvContent	text
		rvWriter	varchar(50)
		rvRegDate	datetime
	 * 
	*/

	private int rvno;
	private String pno;
	private String rvContent;
	private String rvWriter;
	private String rvRegDate;

	public int getRvno() {
		return rvno;
	}

	public void setRvno(int rvno) {
		this.rvno = rvno;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getRvContent() {
		return rvContent;
	}

	public void setRvContent(String rvContent) {
		this.rvContent = rvContent;
	}

	public String getRvWriter() {
		return rvWriter;
	}

	public void setRvWriter(String rvWriter) {
		this.rvWriter = rvWriter;
	}

	public String getRvRegDate() {
		return rvRegDate;
	}

	public void setRvRegDate(String rvRegDate) {
		this.rvRegDate = rvRegDate;
	}

	@Override
	public String toString() {
		return "ReviewDTO [rvno=" + rvno + ", pno=" + pno + ", rvContent=" + rvContent + ", rvWriter=" + rvWriter + ", rvRegDate=" + rvRegDate + "]";
	}

}
