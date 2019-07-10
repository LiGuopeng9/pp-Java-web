package lgp.vo;

public class Comment {
	int esId;
	String commenterId;
	int contentId;
	int kind;

	public Comment(int esId, String commenterId, int contentId, int kind) {
		super();
		this.esId = esId;
		this.commenterId = commenterId;
		this.contentId = contentId;
		this.kind = kind;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getEsId() {
		return esId;
	}

	public void setEsId(int esId) {
		this.esId = esId;
	}



	public String getCommenterId() {
		return commenterId;
	}

	public void setCommenterId(String commenterId) {
		this.commenterId = commenterId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

}
