<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>皮皮</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<script src="lib/jquery-3.4.1.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 样式表 -->
<link rel="stylesheet" href="css/comment.css" />
<link rel="stylesheet" href="css/public.css" />

<style>
</style>
</head>


<body>
	<div class="comment_top">
		
		<p>帖子详情</p>
		<img src="images/icons/icons_svg_back.svg" onclick="backToMain()" />
	</div>

	<div class="comment_mid">
		<div class="comment_mid_top">
			<img src="${requestScope.newEssary.publisherImg}" />
			<p>${requestScope.newEssary.publisherName}</p>
		</div>
		<div class="comment_mid_middle">

			<c:if test="${requestScope.newEssary.kind==1}">
				<p>###${requestScope.newEssary.mes}</p>

			</c:if>
			<c:if test="${requestScope.newEssary.kind==2}">
				<p>###${requestScope.newEssary.mes}</p>
				<div class="comment_mid_middle_detial">
					<img src="${requestScope.newEssary.photo}" />
				</div>
			</c:if>
			<c:if test="${requestScope.newEssary.kind==3}">
				<p>###${requestScope.newEssary.mes}</p>
				<div class="comment_mid_middle_detial">
					<video controls> <source src="${requestScope.newEssary.video}" type="video/mp4"> </source> </video>
				</div>
			</c:if>
		</div>

		<div class="comment_mid_bottom">
			<ul>
				<li><a onclick="star(this)" name="${requestScope.newEssary.esId}"> <img src="images/icons/icons_svg_star.svg">
						<p>${requestScope.newEssary.starnum}</p></a></li>
				<li><a onclick="dislike(this)" name="${requestScope.newEssary.esId}"> <img src="images/icons/icons_svg_down.svg">
						<p>${requestScope.newEssary.dislikenum}</p></a></li>
				<li><a onclick="comment(this)" name="${requestScope.newEssary.esId}"> <img src="images/icons/icons_svg_review.svg">
						<p>${requestScope.newEssary.commentnum}</p></a></li>
				<li><a onclick="share(this)" name="${requestScope.newEssary.esId}"><img src="images/icons/icons_svg_share.svg">
						<p>123</p></a></li>
			</ul>
		</div>
	</div>
	</div>




	<div class="comment_bottom">
		<ul>
			<c:forEach  items="${requestScope.newComments}" var="newComment">
				<li class="comment_item">
					<div class="comment_item_top">
						<img src="${newComment.commenterImg}" />
						<p>${newComment.commenterName}</p>
					</div>
					<div class="comment_item_bottom">
						<c:if test="${newComment.kind==1}">
							<p>###${newComment.mes}</p>
						</c:if>
						<c:if test="${newComment.kind==2}">
							<p>###${newComment.mes}</p>
							<div class="comment_item_bottom_detial">
								<img src="${newComment.photo}" />
							</div>
						</c:if>
						<c:if test="${newComment.kind==3}">
							<p>###${newComment.mes}</p>
							<div class="comment_item_bottom_detial">
								<video controls> <source src="${newComment.video}" type="video/mp4"> </source> </video>
							</div>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>

	</div>
	<div id="comment_bottom_p">
		<p>到底了，没有更多评论了哦！</p>
	</div>
	<div class="comment_input">
		<form class="comment_form" action="comment?esId=${requestScope.newEssary.esId}" method="post" enctype="multipart/form-data">
			<div class="form-group row">
				<input type="text" class="form-control  col-9" name="input1" id="input1" placeholder="多发帖，说不定你就火了..."></input>
				<button class="btn btn-primary  col-3" type="submit">发表</button>
			</div>
			<div class="form-group row">
				<input type="file" name="myfile" id="myfile" />
			</div>
		</form>
	</div>
	<script type="text/javascript">
		//点赞收藏 the_a表示被点击的标签
		function backToMain()
		{
			 window.location.href = 'toMain';
		}
		function star(obj) {
			var num = parseInt($(obj).find('p').text());
			num++;
			$(obj).find('p').text(num);

			$.ajax({
				type : "post",
				url : "star",
				data : {
					"esId" : obj.name
				},
				success : function(data) {
				}
			})
			$(obj).prop("onclick", null).off("click");
		}
		//不喜欢
		function dislike(obj) {

			var num = parseInt($(obj).find('p').text());
			num++;
			$(obj).find('p').text(num);
			$.ajax({
				type : "post",
				url : "dislike",
				data : {
					"esId" : obj.name
				},
				success : function(data) {
				}
			})
			$(obj).prop("onclick", null).off("click");
		}
		//评论
		function comment(obj) {

		}
	</script>

</body>
</html>
