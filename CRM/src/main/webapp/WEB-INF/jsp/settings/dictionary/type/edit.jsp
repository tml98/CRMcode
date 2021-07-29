<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/inc/head.jsp" />
	<script type="text/javascript">
		jQuery(function ($) {
			$.ajax({
				url:"/type/"+sessionStorage.getItem("code"),
				success:function (data) {
					if(data){
						$("#code").prop("value",data.code);
						$("#name").prop("value",data.name);
						$("#description").val(data.description);
					}
				}
			});
			$("#updateBtn").click(function () {
				$.ajax({
					url:"/type",
					type:"PUT",
					data:{
						code:$("#code").val(),
						name:$("#name").val(),
						description:$("#description").val()
					},
					success:function (data) {
						if(data.success){
							alert(data.msg);
							location="/settings/dictionary/type/index.html";
						}else {
							alert(data.msg);
						}
					}
				})
			})
		})
	</script>
</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>修改字典类型</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button id="updateBtn" type="button" class="btn btn-primary">更新</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form">
					
		<div class="form-group">
			<label for="create-code" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" readonly id="code" style="width: 200%;" placeholder="编码作为主键，不能是中文" value="sex">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-name" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="name" style="width: 200%;" value="性别">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="description" style="width: 200%;"></textarea>
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>