<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FE Heros Head</title>
<link rel="stylesheet" href="${basePath }asset/css/bootstrap.min.css">
<style type="text/css">
.main {
	
}

#heroHeadImg {
	width: 30%;
	margin: 0 auto;
	max-width: 350px;
	float: left;
	overflow-y: scroll;
	height: 500px;
}

#hero_p {
	min-width: 70%;
	float: left;
	border-left: 1px;
}

.hero_head_img {
	width: 60px;
	height: 60px;
	cursor: pointer;
}

.mouseMoveCss {
	border: solid 2px green;
	margin: 1px;
	margin-bottom: 0;
}

ul li {
	float: left;
	list-style-type: none;
	margin: 5px;
	margin-bottom: 0;
}

.hide {
	display: none;
}

.mouseMoveLi img {
	border: solid 1px green;
}

.curLi img {
	border: solid 2px red;
}

#search_div{
	min-height: 100px;
}
</style>
</head>
<body>
	<div class="main">
		<div id="search_div">
			<a href="javascript:void(0);" onclick="changeM();">不习惯3D布局?</a>
		</div>
		<div id="heroHeadImg">
			<ul></ul>
		</div>
		<div id="hero_p"></div>
		<div class="move_div_mouse hide"></div>
		<div class="click_div hide"></div>
	</div>


	<script type="text/javascript" src="${basePath }asset/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		var startPage = 1, count = 10, _total_all;
		~function() {
			loadHeroHead(startPage, count);
			initHeroHeadEvent();
		}();

		function loadHeroHead(startPage, count) {
			if (startPage >= _total_all / count) {
				return false;
			}
			$.ajax({
				url : "${basePath}feh/findHeros.action",
				data : {
					startPage : startPage,
					count : count
				},
				type : "post",
				async : false,
				success : function(result) {
					var _data = result.data;
					$.each(_data, function(i, elt) {
						var _hero = elt.hero;
						var _heroName = elt.heroName;
						var aid = _hero.aid;
						$("#heroHeadImg ul").append("<li><img class='hero_head_img' title='" + _heroName.nameJp + "' alt='" + _heroName.nameJp + "' src='${basePath}img/heroHead/" + aid + ".action'/></li>");
						//$("#heroHeadImg ul").append("<li><img class='hero_head_img'/></li>");
					})
					_total_all = result.total;
					console.log(result)
				}
			});
			return true;
		}

		$(document).ready(function() {
			while ($("#heroHeadImg").get(0).scrollHeight == $("#heroHeadImg").height()) {
				!loadHeroHead(++startPage, count);
			}
			$("#heroHeadImg").scroll(function() {
				if ($("#heroHeadImg").scrollTop() <= 0) {
					//	console.log("滚动条已经到达顶部为0");
				}
				if ($("#heroHeadImg").scrollTop() >= $("#heroHeadImg").get(0).scrollHeight - $("#heroHeadImg").height()) {
					!loadHeroHead(++startPage, count);
				}
			});
		});

		function initHeroHeadEvent() {
			$("#heroHeadImg").on("mousemove", ".hero_head_img", function() {
				$(this).closest("li").addClass("mouseMoveLi");
			});
			$("#heroHeadImg").on("mouseout", ".hero_head_img", function() {
				$(this).closest("li").removeClass("mouseMoveLi");
			});
			$("#heroHeadImg").on("click", ".hero_head_img", function() {
				$(".hero_head_img").closest("li").removeClass("curLi");
				$(this).closest("li").addClass("curLi");
			});
		}
		
		function changeM(){
			var iframe = parent.$("#center");
			console.log(iframe.attr("src", "${basePath }jump/center/hero-head-3d.action"));
		}
		
	</script>
</body>
</html>