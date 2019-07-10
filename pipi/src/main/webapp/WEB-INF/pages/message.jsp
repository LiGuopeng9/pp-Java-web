
<%@ page language="java"  pageEncoding="UTF-8"%>


<div class="message_top">


	<nav class="message_nav">
		<div class="nav nav-tabs  " role="tablist">
			<a class="nav-item nav-link active mes_item " id="" data-toggle="tab" href="#mes_remind" role="tab" aria-selected="true">提醒</a> <a class="nav-item nav-link mes_item" id="" data-toggle="tab" href="#mes_comment" role="tab" aria-selected="false">评论</a> <a class="nav-item nav-link mes_item" id="" data-toggle="tab" href="#mes_mail" role="tab" aria-selected="false">私信</a>

		</div>
	</nav>


	<div class="tab-content ">
		<div class="tab-pane fade show active " id="mes_remind" role="tabpanel">
			<%@include file="mes_content.jsp"%>
		</div>

		<div class="tab-pane fade" id="mes_comment" role="tabpanel">
			<%@include file="mes_content.jsp"%>
		</div>
		<div class="tab-pane fade" id="mes_mail" role="tabpanel">
			<%@include file="mes_content.jsp"%>
		</div>
	</div>

</div>

