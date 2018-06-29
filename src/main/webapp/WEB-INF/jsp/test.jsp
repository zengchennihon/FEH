<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<link rel="stylesheet" href="${basePath }asset/css/bootstrap.min.css">
<style type="text/css">
#top {
	width: 100%;
	height: 90px;
}

#left {
	width: 30%;
}

#center {
	width: 70%;
	float: right;
}

#left, #center{
	overflow: scroll;
	height: 900px;
}

#botton {
	width: 100%;
	height: 150px;
}
</style>
</head>
<body>
	<div class="main">
		<iframe id="top"></iframe>
		<iframe id="left"></iframe>
		<iframe id="center"></iframe>
		<iframe id="botton"></iframe>
	</div>




	<script type="text/javascript"
		src="${basePath }asset/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		~function() {
			var url = "${basePath }feh/findAllHeros.action";
			var _url = "${basePath }feh/testStr.action";
			
			var _json = {"skillId": 1, "hp": 2};
			
			$.ajax({
				url: _url,
				type: "post",
				data: JSON.stringify(_json),
				contentType: "application/json;charset=utf-8",
				success: function(res){
					console.log(res);
				}
				
			});
			
			
		}();
	</script>
</body>
</html>