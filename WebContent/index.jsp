<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>企业信息族谱分析</title>


<link rel="stylesheet" href="css/jquery.autocomplete.css" type="text/css" />
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>

<script type="text/javascript">
$.ajax({
	url : 'GetCorpNameServlet.com',
	type : 'GET',
	dataType : 'text',
	timeout : 2000,
	error : function() {
		$.messager.alert("错误", "执行错误");
	}, //错误执行方法
	success : function(result) {
		var company_name = (new Function("", "return " + result))();;
		$().ready(function() {
			$("#company_name").autocomplete(company_name, {
				max : 5, //列表里的条目数  
				width : 500, //提示的宽度，溢出隐藏  
				scrollHeight : 150, //提示的高度，溢出显示滚动条  
				matchContains : false, //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示  
				autoFill : false
			//是否自动填充  
			});
		});
	}
})	
</script>


<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/elegant-font/code/style.css">
<link rel="stylesheet" href="assets/css/animate.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/media-queries.css">
<link rel="stylesheet" href="assets/css/jquery.autocomplete.css">
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>
	<!-- Loader -->
	<div class="loader">
		<div class="loader-img"></div>
	</div>

	<!-- Top content -->
	<div class="top-content">

		<div class="top-content-gradient gradient"></div>

		<!-- Top menu -->
		<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html">企业信息族谱分析</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="top-navbar-1">
					<ul class="nav navbar-nav navbar-right">
						<li><span class="li-text"> 第7届 中国软件杯 A组赛题 &nbsp &nbsp
								队名：【桥东十块】 </span></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 text">
						<h1 class="wow fadeInLeftBig" data-wow-duration="1.5s"
							data-wow-delay="1s">
							企业信息族谱<strong>分析</strong>
						</h1>
						<div class="description wow fadeInLeftBig"
							data-wow-duration="1.5s" data-wow-delay="2s">
							<p>
								利用数据挖掘技术，对企业登记信息进行族谱分析，通过企业登记的股东和对外投资情况，展示各家企业的族谱分析，同时计算出企业的实际控制人.
							</p>
						</div>

						<div class="jlform wow fadeInUp" data-wow-duration="1.5s"
							data-wow-delay="3s">
							<form class="form-inline" role="form"
								action="GetCorpInfoServlet.com" method="get">
								<div class="form-group">
									<label class="sr-only">搜索框</label> <input type="text"
										id="company_name" name="company_name"
										placeholder="请输入企业名称..."
										class="subscribe-email form-control" style="width: 500px" />
								</div>
								<button type="submit" class="btn">查一下</button>
							</form>
							<div class="success-message"></div>
							<div class="error-message"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- More info -->
	<div class="features-container section-container">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 features section-description wow fadeIn">
					<h2>功能介绍</h2>
					<div class="divider-1 wow fadeInUp">
						<span></span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3 features-box wow fadeInUp">
					<div class="features-box-icon">
						<span aria-hidden="true" class="icon_profile"></span>
					</div>
					<h3>股权结构</h3>
					<p>展现该主体各股东出资比例，并标记出最大股东、企业股东、自然人股东.</p>
				</div>
				<div class="col-sm-3 features-box wow fadeInDown">
					<div class="features-box-icon">
						<span aria-hidden="true" class="icon_pencil"></span>
					</div>
					<h3>投资族谱</h3>
					<p>提供市场主体对外投资、股东再投资族谱。投资族谱可逐级进行延伸.</p>
				</div>
				<div class="col-sm-3 features-box wow fadeInUp">
					<div class="features-box-icon">
						<span aria-hidden="true" class="icon_cogs"></span>
					</div>
					<h3>企业图谱</h3>
					<p>提取市场主体股东信息以及对外投资信息，挖掘企业之间的相互关联，展现企业族谱。企业族谱可向上逐级展示一级、二级、三级……,向下可层层延伸至分公司.</p>
				</div>
				<div class="col-sm-3 features-box wow fadeInDown">
					<div class="features-box-icon">
						<span aria-hidden="true" class="icon_pin"></span>
					</div>
					<h3>疑似关系</h3>
					<p>提供该主体主要人员对外投资信息以及主要人员在外任职信息.</p>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 footer-copyright">&copy; 中北大学 软件学院</div>
			</div>
		</div>
	</footer>


	<!-- Javascript -->
	<!-- <script src="assets/js/jquery-1.11.1.min.js"></script> -->
	<!-- <script src="houtai/js/jquery-1.8.3.js"></script> -->
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/jquery.countdown.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/retina-1.1.0.min.js"></script>
	<script src="assets/js/scripts.js"></script>

</body>

</html>