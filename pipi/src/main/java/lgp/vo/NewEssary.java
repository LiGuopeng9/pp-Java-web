package lgp.vo;

public class NewEssary {
	int esId;
	String publisherId;
	String publisherName;
	String publisherImg;
	int starnum;
	int commentnum;
	int dislikenum;
	int kind;
	String mes;
	String photo;
	String video;
	
	
	
	
	public NewEssary() {
		super();
	}

	public NewEssary(int esId, String publisherId, String publisherName, String publisherImg, int starnum,
			int commentnum, int dislikenum, int kind, String mes, String photo, String video) {
		super();
		this.esId = esId;
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.publisherImg = publisherImg;
		this.starnum = starnum;
		this.commentnum = commentnum;
		this.dislikenum = dislikenum;
		this.kind = kind;
		this.mes = mes;
		this.photo = photo;
		this.video = video;
	}

	
	
	
	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherImg() {
		return publisherImg;
	}

	public void setPublisherImg(String publisherImg) {
		this.publisherImg = publisherImg;
	}

	public int getEsId() {
		return esId;
	}
	public void setEsId(int esId) {
		this.esId = esId;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public int getStarnum() {
		return starnum;
	}
	public void setStarnum(int starnum) {
		this.starnum = starnum;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public int getDislikenum() {
		return dislikenum;
	}
	public void setDislikenum(int dislikenum) {
		this.dislikenum = dislikenum;
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
	
}
