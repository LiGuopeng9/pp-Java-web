package lgp.vo;

public class Essary {
	int esId;
	int contentId;
	String publisherId;
	int starnum;
	int commentnum;
	int dislikenum;
	int kind;
	
	
	
	public Essary(int esId, int contentId, String publisherId, int starnum, int commentnum, int dislikenum, int kind) {
		super();
		this.esId = esId;
		this.contentId = contentId;
		this.publisherId = publisherId;
		this.starnum = starnum;
		this.commentnum = commentnum;
		this.dislikenum = dislikenum;
		this.kind = kind;
	}
	public Essary(int contentId, String publisherId, int starnum, int commentnum, int dislikenum, int kind) {
		super();
		this.contentId = contentId;
		this.publisherId = publisherId;
		this.starnum = starnum;
		this.commentnum = commentnum;
		this.dislikenum = dislikenum;
		this.kind = kind;
	}
	public int getEsId() {
		return esId;
	}
	public void setEsId(int esId) {
		this.esId = esId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
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
	
	
	
	
	


}
