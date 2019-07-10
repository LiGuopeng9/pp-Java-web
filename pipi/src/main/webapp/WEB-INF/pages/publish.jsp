<%@ page language="java" pageEncoding="UTF-8"%>
<div class="publish_top">

	<p>发帖子</p>
	<button class="btn btn-primary" onclick="publish_submit();">发表</button>

</div>
<div class="publish_mid">
	<form id="publish_form" action="publish" method="post" enctype="multipart/form-data">
		<textarea class="form-control" name="input1" id="input1" placeholder="多发帖，说不定你就火了..."></textarea>

		<input type="file" name="myfile" id="myfile" />
	</form>
</div>
<script type="text/javascript">
	function publish_submit() {
		$("#publish_form").submit();

	}
</script>

