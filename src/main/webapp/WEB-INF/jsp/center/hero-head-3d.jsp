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

#search_div {
	min-height: 100px;
}

html, body {
	height: 100%;
}

body {
	background-color: #000;
	margin: 0;
	font-family: Helvetica, sans-serif;;
	overflow: hidden;
}

a {
	color: #ffffff;
}

#info {
	position: absolute;
	width: 100%;
	color: #ffffff;
	padding: 5px;
	font-family: Monospace;
	font-size: 13px;
	font-weight: bold;
	text-align: center;
	z-index: 1;
}

#menu {
	position: absolute;
	bottom: 20px;
	width: 100%;
	text-align: center;
}

.element {
	width: 120px;
	height: 160px;
	box-shadow: 0px 0px 12px rgba(0, 255, 255, 0.5);
	border: 1px solid rgba(127, 255, 255, 0.25);
	text-align: center;
	cursor: default;
}

.element:hover {
	box-shadow: 0px 0px 12px rgba(0, 255, 255, 0.75);
	border: 1px solid rgba(127, 255, 255, 0.75);
}

.element .number {
	position: absolute;
	top: 20px;
	right: 20px;
	font-size: 12px;
	color: rgba(127, 255, 255, 0.75);
}

.element .symbol {
	position: absolute;
	top: 40px;
	left: 0px;
	right: 0px;
	font-size: 60px;
	font-weight: bold;
	color: rgba(255, 255, 255, 0.75);
	text-shadow: 0 0 10px rgba(0, 255, 255, 0.95);
}

.element .details {
	position: absolute;
	bottom: 15px;
	left: 0px;
	right: 0px;
	font-size: 12px;
	color: rgba(127, 255, 255, 0.75);
}

button {
	color: rgba(127, 255, 255, 0.75);
	background: transparent;
	outline: 1px solid rgba(127, 255, 255, 0.75);
	border: 0px;
	padding: 5px 10px;
	cursor: pointer;
}

button:hover {
	background-color: rgba(0, 255, 255, 0.5);
}

button:active {
	color: #000000;
	background-color: rgba(0, 255, 255, 0.75);
}

.add-rotate{
	animation: _container 10s linear infinite;
}
</style>
</head>
<body>
	<div id="container"></div>
	<div id="info">
		<a href="javascript:void(0);" onclick="changeM();">不习惯3D布局?</a>
	</div>
	<div id="menu">
		<button id="table">TABLE</button>
		<button id="sphere">SPHERE</button>
		<button id="helix">HELIX</button>
		<button id="grid">GRID</button>
	</div>

	<script type="text/javascript" src="${basePath }asset/js/jquery-3.3.1.min.js"></script>
	<script src="${basePath }asset/js/three/three.min.js"></script>
	<script src="${basePath }asset/js/Tween.min.js"></script>
	<script src="${basePath }asset/js/three/TrackballControls.js"></script>
	<script src="${basePath }asset/js/three/CSS3DRenderer.js"></script>
	<script type="text/javascript">
		var startPage = 1, count = 10, _total_all;
		var countNum = 0;
		var table = [];

		var camera, scene, renderer;
		var controls;

		var objects = [];
		var targets = {
			table : [],
			sphere : [],
			helix : [],
			grid : []
		};

		function init() {
			camera = new THREE.PerspectiveCamera(40, window.innerWidth / window.innerHeight, 1, 10000);
			camera.position.z = 3000;
			scene = new THREE.Scene();
			// table
			for (var i = 0; i < table.length; i += 5) {
				var element = document.createElement('div');
				element.className = 'element';
				element.style.backgroundColor = 'rgba(0, 127, 127, 0.25)';
				var number = document.createElement('div');
				number.className = 'number';
				number.textContent = (i / 5) + 1;
				element.appendChild(number);
				var symbol = document.createElement('div');
				symbol.className = 'symbol';
				$(symbol).html("<img aid='" + table[i] + "' class='hero_head_img' title='" + table[i + 1] + "' alt='" + table[i + 1] + "' src='${basePath}img/heroHead/" + table[i] + ".action'/>");
				element.appendChild(symbol);
				var details = document.createElement('div');
				details.className = 'details';
				details.innerHTML = table[i + 1] + '<br>' + table[i + 2];
				element.appendChild(details);
				var object = new THREE.CSS3DObject(element);
				object.position.x = Math.random() * 4000 - 2000;
				object.position.y = Math.random() * 4000 - 2000;
				object.position.z = Math.random() * 4000 - 2000;
				scene.add(object);
				objects.push(object);
				//
				var object = new THREE.Object3D();
				object.position.x = (table[i + 3] * 140) - 1330;
				object.position.y = -(table[i + 4] * 180) + 990;
				targets.table.push(object);
			}
			// sphere
			var vector = new THREE.Vector3();
			for (var i = 0, l = objects.length; i < l; i++) {
				var phi = Math.acos(-1 + (2 * i) / l);
				var theta = Math.sqrt(l * Math.PI) * phi;
				var object = new THREE.Object3D();
				object.position.x = 800 * Math.cos(theta) * Math.sin(phi);
				object.position.y = 800 * Math.sin(theta) * Math.sin(phi);
				object.position.z = 800 * Math.cos(phi);
				vector.copy(object.position).multiplyScalar(2);
				object.lookAt(vector);
				targets.sphere.push(object);
			}
			// helix
			var vector = new THREE.Vector3();
			for (var i = 0, l = objects.length; i < l; i++) {
				var phi = i * 0.175 + Math.PI;
				var object = new THREE.Object3D();
				object.position.x = 900 * Math.sin(phi);
				object.position.y = -(i * 8) + 450;
				object.position.z = 900 * Math.cos(phi);
				vector.x = object.position.x * 2;
				vector.y = object.position.y;
				vector.z = object.position.z * 2;
				object.lookAt(vector);
				targets.helix.push(object);
			}
			// grid
			for (var i = 0; i < objects.length; i++) {
				var object = new THREE.Object3D();
				object.position.x = ((i % 5) * 400) - 800;
				object.position.y = (-(Math.floor(i / 5) % 5) * 400) + 800;
				object.position.z = (Math.floor(i / 25)) * 1000 - 2000;
				targets.grid.push(object);
			}
			//
			renderer = new THREE.CSS3DRenderer();
			renderer.setSize(window.innerWidth, window.innerHeight);
			renderer.domElement.style.position = 'absolute';
			$(renderer.domElement).css("overflow", "scroll");
			document.getElementById('container').appendChild(renderer.domElement);
			//
			controls = new THREE.TrackballControls(camera, renderer.domElement);
			controls.rotateSpeed = 0.5;
			controls.minDistance = 500;
			controls.maxDistance = 6000;
			controls.addEventListener('change', render);
			var button = document.getElementById('table');
			button.addEventListener('click', function(event) {
				transform(targets.table, 2000);
			}, false);
			var button = document.getElementById('sphere');
			button.addEventListener('click', function(event) {
				transform(targets.sphere, 2000);
			}, false);
			var button = document.getElementById('helix');
			button.addEventListener('click', function(event) {
				transform(targets.helix, 2000);
			}, false);
			var button = document.getElementById('grid');
			button.addEventListener('click', function(event) {
				transform(targets.grid, 2000);
			}, false);
			transform(targets.table, 5000);
			//
			window.addEventListener('resize', onWindowResize, false);
		}

		function transform(targets, duration) {
			TWEEN.removeAll();
			for (var i = 0; i < objects.length; i++) {
				var object = objects[i];
				var target = targets[i];

				new TWEEN.Tween(object.position).to({
					x : target.position.x,
					y : target.position.y,
					z : target.position.z
				}, Math.random() * duration + duration).easing(TWEEN.Easing.Exponential.InOut).start();
				new TWEEN.Tween(object.rotation).to({
					x : target.rotation.x,
					y : target.rotation.y,
					z : target.rotation.z
				}, Math.random() * duration + duration).easing(TWEEN.Easing.Exponential.InOut).start();
			}
			new TWEEN.Tween(this).to({}, duration * 2).onUpdate(render).start();
		}

		function onWindowResize() {
			camera.aspect = window.innerWidth / window.innerHeight;
			camera.updateProjectionMatrix();
			renderer.setSize(window.innerWidth, window.innerHeight);
			render();
		}

		function animate() {
			requestAnimationFrame(animate);
			TWEEN.update();
			controls.update();
		}

		function render() {
			renderer.render(scene, camera);
		}
		/**
			用于监听dom被绑定事件, 通过addEventListener添加的事件
		*/
		(function() {
            Element.prototype.eventListenerList = {};
            Element.prototype._addEventListener = Element.prototype.addEventListener;
            Element.prototype._removeEventListener = Element.prototype.removeEventListener;
            Element.prototype.addEventListener = function(a,b,c) {
                this._addEventListener(a,b,c);
                if(!this.eventListenerList[a]) this.eventListenerList[a] = [];
                this.eventListenerList[a].push(b);
            };
            Element.prototype.removeEventListener = function(a,b,c){
                this._removeEventListener(a, b,c);
                if(this.eventListenerList[a]){
                    var arr = this.eventListenerList[a];
                    for(var i =0;i<arr.length;i++){
                        if(arr[i].toString() === b.toString()){
                            this.eventListenerList[a].splice(i,1);
                        }
                    }
                }
            }
        })();

		~function() {
			loadHeroHead(startPage, count);
			initEvent();

			//渲染3D视图
			init();
			animate();
		}();

		function loadHeroHead(startPage, count) {
			$.ajax({
				url : "${basePath}feh/findHeros.action",
				data : {
					startPage : startPage,
					count : 0
				},
				type : "post",
				async : false,
				success : function(result) {
					if (result.code === 0) {
						var _data = result.data;
						$.each(_data, function(i, elt) {
							var _hero = elt.hero;
							var _heroName = elt.heroName;
							var aid = _hero.aid;
							table.push(aid, _heroName.nameJp, "", countNum % 18, parseInt(countNum / 18));
							countNum++;
						})
						_total_all = result.total;
					} else {
						alert(result.msg);
					}
				}
			});
			return true;
		}

		function initEvent() {
			$("#container").on("click", ".element, .element.symbol", function() {
				showHeroDetails($(this));
			});
			
			//获取dom通过addEventListener绑定的事件
			var _events = $("#container").get(0).eventListenerList
			console.log(_events)
			//鼠标按下事件, 此处左键按下旋转,右键按下拖动
			$.each(_events.mousedown, function(i, elt) {
				console.log(i,elt)
			})
			//滚轮滑动事件, 此处禁用滚动条,变为放大缩小
			$.each(_events.wheel, function(i, elt) {
				console.log(i,elt)
			})
		}

		function showHeroDetails(_$) {
			var aid = _$.find(".hero_head_img").attr("aid");
			$.ajax({
				url : '${basePath}feh/findHeroDetails.action',
				data : {
					aid : aid
				},
				type : 'post',
				success : function(result) {
				    console.log(result)
					if (result.code === 0) {
						var _data = result.data;
						var hero = _data.hero, heroName = _data.heroName, heroStars = _data.heroStars, 
							heroDetails = _data.heroDetails;
						console.log(_data)
					} else {
						alert(result.msg);
					}
				}
			});
		}

		function changeM() {
			var iframe = parent.$("#center");
			console.log(iframe.attr("src", "${basePath }jump/center/hero-head.action"));
		}
	</script>
</body>
</html>