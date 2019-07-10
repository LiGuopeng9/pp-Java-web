package lgp.vo;

public class Content {
	int contentId;
	String mes;
	String photo;
	String video;
	
	
	
	
	
	public Content(String mes, String photo, String video) {
		super();
		this.mes = mes;
		this.photo = photo;
		this.video = video;
	}
	
	
	
	public Content(int contentId, String mes, String photo, String video) {
		super();
		this.contentId = contentId;
		this.mes = mes;
		this.photo = photo;
		this.video = video;
	}



	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	
}
