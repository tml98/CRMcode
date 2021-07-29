<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/inc/head.jsp" />
	<script type="text/javascript">
		jQuery(function ($) {
			/*查询*/
			$.ajax({
				url:"/type",
				type:"GET",
				success:function (data) {
					let arr=[];
					$(data).each(function (index) {
						arr.push(`
								<tr class="active">
									<td><input name="code" value="`+this.code+`" type="checkbox" /></td>
									<td>`+(index+1)+`</td>
									<td>`+this.code+`</td>
									<td>`+this.name+`</td>
									<td>`+this.description+`</td>
								</tr>`);
					});
					$("#typeData").html(arr.join(""));
				}
			});

			/*修改数据回显*/
			$("#updateBtn").click(function () {
				let codes = $("input[name=code]:checked");
				if (codes.size()!=1){
					alert("请选择一项要修改的!");
					return;
				}
				sessionStorage.setItem("code",codes.val());
				location="/settings/dictionary/type/edit.html";
			});

			/*删除数据*/
			/*全选与全不选*/
			$("#selectAll").click(function () {
				$("input[name=code]").prop("checked",this.checked);
			});
			$("input[name=code]").click(function () {
				$("#selectAll").prop("checked",$("input[name=code]:checked").size()===$("input[name=code]").size());
			});

			/*删除选中*/
			$("#deleteBtn").click(function () {
				let codes = $("input[name=code]:checked");
				console.log(codes.size());
				if(codes.size()===0){
					alert("请选择要删除的数据!!");
					return;
				}
				let ids=[];
				codes.each(function () {
					ids.push(this.value);
				});
				let id=ids.join(",");
				$.ajax({
					url:"/type/getTypeCode?code="+id,
					type:"GET",
					success:function (data) {
						var usingCode=[];
						$(data).each(function () {
							usingCode.push(this.typeCode)
						});
						if(usingCode.length>=1){
							alert("你要删除的字典编码"+usingCode+"正在被使用!不允许删除!");
							return;
						}
						if(confirm("确定要删除选中的数据吗?")){
							$.ajax({
								url:"/type/"+id,
								type:"Delete",
								success:function (data) {
									if(data){
										alert(data.msg);
										location="/settings/dictionary/type/index.html";
									}else {
										alert(data.msg);
									}
								}
							});
						}
					}
				});
			});
		});
	</script>
</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典类型列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='/settings/dictionary/type/save.html'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button id="updateBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button id="deleteBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="selectAll" /></td>
					<td>序号</td>
					<td>编码</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody id="typeData">

			</tbody>
		</table>
	</div>
	
</body>
</html>