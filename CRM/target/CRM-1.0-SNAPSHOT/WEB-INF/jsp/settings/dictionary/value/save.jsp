<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/inc/head.jsp" />
	<script type="text/javascript">
		jQuery(function ($) {
			$.ajax({
				url:"/type",
				success:function (data) {
					let arr=[];
					$(data).each(function () {
						arr.push(`
							<option value="`+this.code+`">`+this.code+`</option>
						`)
					});
					$(":input[name=vCode]").html(arr.join(""));
				}
			})
			$("#addBtn").click(function () {
				$.ajax({
					url:"/value",
					type:"POST",
					data:{
						typeCode:$(":input[name=vCode]").val(),
						value:$(":input[name=value]").val(),
						text:$(":input[name=text]").val(),
						orderNo:$(":input[name=orderNo]").val(),
					},
					success:function (data) {
						if(data){
							alert(data.msg);
							location="/settings/dictionary/value/index.html";
						}else {
							alert(data.msg)
						}
					}
				});
			});
		});
	</script>
</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>新增字典值</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button id="addBtn" type="button" class="btn btn-primary">保存</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form">


		<div class="form-group">
			<label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select name="vCode" class="form-control" id="create-dicTypeCode" style="width: 200%;">
				  <option></option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-dicValue" name="value" style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-text" class="col-sm-2 control-label">文本</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-text" name="text" style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-orderNo" name="orderNo" style="width: 200%;">
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>