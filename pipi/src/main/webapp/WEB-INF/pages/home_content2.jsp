<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<ul>
		<c:forEach items="${requestScope.newEssaries}" var="newEssary">
		<c:if test="${newEssary.kind==3}">
			<li>
				<div class="home_content">
					<div class="home_content_top">
						<img src="${newEssary.publisherImg}" />
						<p>${newEssary.publisherName}</p>
					</div>
					<div class="home_content_middle">
				
							<p>###${newEssary.mes}</p>
							<div class="home_content_middle_detial">
								<video controls>
									<source src="${newEssary.video}" type="video/mp4">
									</source>
								</video>
							</div>
					
					</div>
					<div class="home_content_bottom">
						<ul>
							<li><a onclick="star(this)" name="${newEssary.esId}"> <img src="images/icons/icons_svg_star.svg">
									<p>${newEssary.starnum}</p></a></li>
							<li><a onclick="dislike(this)" name="${newEssary.esId}"> <img src="images/icons/icons_svg_down.svg">
									<p>${newEssary.dislikenum}</p></a></li>
							<li><a href="esDetial?esId=${newEssary.esId}" name="${newEssary.esId}"> <img src="images/icons/icons_svg_review.svg">
									<p>${newEssary.commentnum}</p></a></li>
							<li><a  name="${newEssary.esId}"><img src="images/icons/icons_svg_share.svg">
									<p>123</p></a></li>
						</ul>
					</div>
				</div>
			</li>
	</c:if>

		</c:forEach>

	</ul>
</div>
<script type="text/javascript">
	//点赞收藏 the_a表示被点击的标签
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
		$(obj).prop("onclick",null).off("click");
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
		$(obj).prop("onclick",null).off("click");
	}

</script>
