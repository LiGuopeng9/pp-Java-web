
<%@ page language="java" pageEncoding="UTF-8" %>

<nav class="home_top">
	<div class="nav nav-tabs  " role="tablist">
		<a class="nav-item nav-link active home_top_item" id="" data-toggle="tab" href="#home_top_all" role="tab" aria-selected="true">综合</a> <a class="nav-item nav-link home_top_item" id="" data-toggle="tab" href="#home_top_video" role="tab" aria-selected="false">视频</a> <a class="nav-item nav-link home_top_item" id="" data-toggle="tab" href="#home_top_photo" role="tab" aria-selected="false">趣图</a> <a class="nav-item nav-link home_top_item" id="" data-toggle="tab" href="#home_top_txt" role="tab" aria-selected="false">段子</a>
	</div>
</nav>


<div class="tab-content home_body">
	<div class="tab-pane fade show active " id="home_top_all" role="tabpanel"><%@include file="home_content.jsp"%>
	</div>
	<div class="tab-pane fade" id="home_top_video" role="tabpanel"><%@include file="home_content2.jsp"%></div>
	<div class="tab-pane fade" id="home_top_photo" role="tabpanel"><%@include file="home_content3.jsp"%></div>
	<div class="tab-pane fade" id="home_top_txt" role="tabpanel"><%@include file="home_content4.jsp"%></div>
</div>




