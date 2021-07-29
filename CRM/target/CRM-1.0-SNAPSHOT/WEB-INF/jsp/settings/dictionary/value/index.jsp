<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/inc/head.jsp" />
	<script type="text/javascript">
		jQuery(function ($) {
			$.ajax({
				url:"/value",
				type:"GET",
				success:function (data) {
					let arr=[];
					$(data).each(function (index) {
						arr.push(`
								<tr class="active">
									<td><input name="cValue" value="`+this.id+`" type="checkbox" /></td>
									<td>`+(index+1)+`</td>
									<td>`+this.value+`</td>
									<td>`+this.text+`</td>
									<td>`+this.orderNo+`</td>
									<td>`+this.typeCode+`</td>
								</tr>`);
					});
					$("#valueData").html(arr.join(""));
				}
			});
			/*删除数据*/
			/*全选与全不选*/
			$("#selectAll").click(function () {
				$("input[name=cValue]").prop("checked",this.checked);
			});
			$("input[name=cValue]").click(function () {
				$("#selectAll").prop("checked",$("input[name=cValue]:checked").size()===$("input[name=cValue]").size());
			});

			/*删除选中*/
			$("#deleteBtn").click(function () {
				let vs = $("input[name=cValue]:checked");
				console.log(vs.size());
				if(vs.size()===0){
					alert("请选择要删除的数据!!");
					return;
				}
				let ids=[];
				vs.each(function () {
					ids.push(this.value);
				});
				let id=ids.join(",");
				if(confirm("确定要删除选中的数据吗?")){
					$.ajax({
						url:"/value/"+id,
						type:"Delete",
						success:function (data) {
							if(data){
								alert(data.msg);
								location="/settings/dictionary/value/index.html";
							}else {
								alert(data.msg);
							}
						}
					});
				}
			});
			/*修改数据回显*/
			$("#updateBtn").click(function () {
				let vs = $("input[name=cValue]:checked");
				if (vs.size()!=1){
					alert("请选择一项要修改的!");
					return ;
				}
				sessionStorage.setItem("vs",vs.val());
				location="/settings/dictionary/value/edit.html";
			});
		});
	</script>
</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典值列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='/settings/dictionary/value/save.html'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button id="updateBtn" type="button" class="btn btn-default" ><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button id="deleteBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input id="selectAll" type="checkbox" /></td>
					<td>序号</td>
					<td>字典值</td>
					<td>文本</td>
					<td>排序号</td>
					<td>字典类型编码</td>
				</tr>
			</thead>
			<tbody id="valueData">
			</tbody>
		</table>
	</div>
	
</body>
</html>