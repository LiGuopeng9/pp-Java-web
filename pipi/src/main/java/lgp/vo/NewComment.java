package lgp.vo;

public class NewComment {
	int esId;
	String commmenterId;
	int contentid;
	String commenterName;
	String commenterImg;
	int kind;
	String mes;
	String photo;
	String video;

	public NewComment() {
		super();
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public String getCommenterImg() {
		return commenterImg;
	}

	public void setCommenterImg(String commenterImg) {
		this.commenterImg = commenterImg;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
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

	public int getEsId() {
		return esId;
	}

	public void setEsId(int esId) {
		this.esId = esId;
	}

	public String getCommmenterId() {
		return commmenterId;
	}

	public void setCommmenterId(String commmenterId) {
		this.commmenterId = commmenterId;
	}

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

}
