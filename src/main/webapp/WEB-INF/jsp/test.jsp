<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<link rel="stylesheet" href="${basePath }asset/css/bootstrap.min.css">
<style type="text/css">
.main{
	margin-left: 100px;
	margin-right: 100px;
}
#top {
	width: 100%;
	height: 90px;
}

#center {
	height: 900px;
	width: 100%;
	float: right;
}

#botton {
	width: 100%;
	height: 150px;
}
</style>
</head>
<body>
	<div class="main">
		<iframe id="top" src=""></iframe>
		<iframe id="center" src="${basePath }jump/center/hero-head.action"></iframe>
		<iframe id="botton" src=""></iframe>
	</div>




	<script type="text/javascript" src="${basePath }asset/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		~function() {

		}();
	</script>
</body>
</html>