<%@ page contentType="text/html;charset=UTF-8" %>
  <%@ include file="/WEB-INF/views/include/webtaglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<link href="${ctxStatic}/modules/front/css/mobile/3dDemo/3dtest.css" type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<title>3dtest</title>
</head>
<body>
<script src="${ctxStatic}/modules/front/js/three.js"></script>
		<script>
		var scene=new THREE.Scene();
		var camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
		var renderer = new THREE.WebGLRenderer();
		renderer.setSize( window.innerWidth, window.innerHeight );
		document.body.appendChild( renderer.domElement );
		
		var geometry = new THREE.BoxGeometry( 1, 1, 1 );
		var material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
		var cube = new THREE.Mesh( geometry, material );
		scene.add( cube );
		camera.position.z = 5;
		</script>




</body>


</html>