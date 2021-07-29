<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/inc/head.jsp" />
	<script type="text/javascript">
		jQuery(function ($) {
			$.ajax({
				url:"/value/"+sessionStorage.getItem("vs"),
				success:function (data) {
					if(data){
						$(":input[name=id]").prop("value",data.id);
						$(":input[name=vCode]").prop("value",data.typeCode);
						$(":input[name=value]").prop("value",data.value);
						$(":input[name=text]").prop("value",data.text);
						$(":input[name=orderNo]").prop("value",data.orderNo);
					}
				}
			});
			$("#updateBtn").click(function () {
				$.ajax({
					url:"/value",
					type:"PUT",
					data:{
						id:$(":input[name=id]").val(),
						typeCode:$(":input[name=vCode]").val(),
						value:$(":input[name=value]").val(),
						text:$(":input[name=text]").val(),
						orderNo:$(":input[name=orderNo]").val()
					},
					success:function (data) {
						if(data.success){
							alert(data.msg);
							location="/settings/dictionary/value/index.html";
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
		<h3>修改字典值</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button id="updateBtn" type="button" class="btn btn-primary">更新</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form">

		<input type="hidden" name="id" />
					
		<div class="form-group">
			<label for="edit-dicTypeCode" class="col-sm-2 control-label">字典类型编码</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-dicTypeCode" name="vCode" style="width: 200%;" value="" readonly>
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-dicValue" name="value" style="width: 200%;" value="">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-text" class="col-sm-2 control-label">文本</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-text" name="text" style="width: 200%;" value="">
			</div>
		</div>
		
		<div class="form-group">
			<label for="edit-orderNo" class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-orderNo" name="orderNo" style="width: 200%;" value="">
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>