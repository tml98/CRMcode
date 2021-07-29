<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script >
    jQuery(function ($) {
        if($("select[owner]").size()!==1){
            $.ajax({
                url:"/user",
                type:"GET",
                success:function (data) {
                    let arr=[];
                    console.log(data);
                    $(data).each(function () {
                        arr.push(`
							<option>`+this+`</option>
						`)
                    });
                    $("select[owner]").html(arr.join(""))
                }
            });
        }
    });
</script>
