
<%@ page language="java"  pageEncoding="UTF-8"%>
<div class="explore_top">
	<div>
		<p>发现</p>
	</div>
	<img src="images/icons/icons_svg_search.svg" />
</div>
<div class="explore_mid">
	<img src="images/explore/explore_logo.png" />
</div>
<div class="explore_bottom">

	<nav class="explore_bottom_nav">
		<div class="nav nav-tabs  " role="tablist">
			<a class="nav-item nav-link active explore_bottom_item" id="" data-toggle="tab" href="#explore_bottom_left" role="tab" aria-selected="true">我的关注</a> <a class="nav-item nav-link explore_bottom_item" id="" data-toggle="tab" href="#explore_bottom_right" role="tab" aria-selected="false">热门话题</a>
		</div>
	</nav>


	<div class="tab-content ">
		<div class="tab-pane fade show active " id="explore_bottom_left" role="tabpanel">
			<%@include file="explore_content.jsp"%>
		</div>
		<div class="tab-pane fade" id="explore_bottom_right" role="tabpanel">
			<%@include file="explore_content.jsp"%>
		</div>

	</div>




</div>


