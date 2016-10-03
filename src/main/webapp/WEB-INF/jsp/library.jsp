<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>test</title>

	<link rel="stylesheet" href="static/css/bootstrap-3.3.7/bootstrap.css">
	<link rel="stylesheet" href="static/css/library/library.css">

	<script src="static/js/angularjs/angular.js"></script>
	<script src="static/js/library/library.js"></script>

</head>
<body>
	<div ng-app="library" ng-controller="list">
		<div class="list-view" ng-repeat="list in listView">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="left-img">
							<img src="static\img\about_me\img1.jpg" alt="" class="img-thumbnail">
						</div>

						<div class="right-content">
							<div class="title">
								这是标题
							</div>

							<div class="brief-intro">
								这里是内容
							</div>
							
						</div>
					</div>
				</div>
			</div>
	</div>	
</body>
</html>