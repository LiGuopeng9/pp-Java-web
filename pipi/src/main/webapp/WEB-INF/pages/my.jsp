<%@ page language="java" pageEncoding="UTF-8"%>

<div class="my">
	<div class="my_top">
		<div>
			<img src="<%=request.getSession().getAttribute("userImg")%>" />
		</div>
		<div>


			<p>
				<%=request.getSession().getAttribute("userName")%>
			</p>

			<div>
				<p>皮友号:</p>
				<p id="userid" name="userid">
					<%=request.getSession().getAttribute("userId")%>
				</p>
			</div>

		</div>
	</div>
	<img class="my_mid" src="images/my/my_logo.png" />
	<div class="my_bottom">


		<ul>
			<li class="my_item">
				<button class="btn btn-light" onclick="my_publish()">我的发帖</button>
			</li>
			<li class="my_item">
				<button class="btn btn-light" onclick="my_comment()">我的评论</button>

			</li>
			<li class="my_item">
				<button class="btn btn-light" onclick="my_star()">我的收藏</button>

			</li>

			<li class="my_item">
				<button class="btn btn-light" onclick="changemy()">资料修改</button>

			</li>
			<li class="my_item">
				<button class="btn btn-light">设置</button>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	function my_publish() {
		window.location.href = 'selectmy?option=publish';
	}
	function my_star() {
		window.location.href = 'selectmy?option=star';
	}
	function my_comment() {
		window.location.href = 'selectmy?option=comment';
	}
	function changemy() {
		window.location.href = 'tochangemy';
	}
</script>
