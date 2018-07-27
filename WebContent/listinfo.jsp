<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="houtai/img/favicon.png">

<title>企业信息显示</title>


<link rel="stylesheet" href="css/jquery.autocomplete.css"
	type="text/css" />
<!-- <script type="text/javascript" src="js/jQuery.js"></script> -->
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
			var company_name = (new Function("", "return " + result))();
			;
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



<!-- Bootstrap CSS -->
<link href="houtai/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="houtai/css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="houtai/css/elegant-icons-style.css" rel="stylesheet" />
<link href="houtai/css/font-awesome.min.css" rel="stylesheet" />
<!-- full calendar css-->
<link
	href="houtai/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css"
	rel="stylesheet" />
<link href="houtai/assets/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" />
<!-- easy pie chart-->
<link
	href="houtai/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css"
	rel="stylesheet" type="text/css" media="screen" />
<!-- owl carousel -->
<link rel="houtai/stylesheet" href="css/owl.carousel.css"
	type="text/css">
<link href="houtai/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="houtai/css/fullcalendar.css">
<link href="houtai/css/widgets.css" rel="stylesheet">
<link href="houtai/css/style.css" rel="stylesheet">
<link href="houtai/css/style-responsive.css" rel="stylesheet" />
<link href="houtai/css/xcharts.min.css" rel=" stylesheet">
<link href="houtai/css/jquery-ui-1.10.4.min.css" rel="stylesheet">

<link rel="stylesheet" href="css/jquery.autocomplete.css"
	type="text/css" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->

</head>


<body>
	<!-- container section start -->
	<section id="container">


		<header class="header dark-bg" style="padding-left: 22%">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom"></div>
			</div>

			<!--logo start-->
			<a class="navbar-brand" href="index.jsp">企业信息族谱分析</a>
			<!--logo end-->

			<div class="nav search-row" id="top_menu">
				<!--  search form start -->
				<ul class="nav top-menu">
					<li>
						<form class="navbar-form" action="GetCorpInfoServlet.com"
							method="get">
							<input class="form-control" placeholder="请输入企业名称..." type="text"
								id="company_name" name="company_name" style="width: 300px">
							<button type="submit" class="btn btn-info btn-sm">查一下</button>
						</form>
					</li>
				</ul>
				<!--  search form end -->
			</div>
		</header>
		<!--header end-->


		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">

				<div class="row">
					<div class="col-lg-9">
						<c:forEach items="${list_corp }" var="c">
							<div class="profile-widget profile-widget-info">
								<div class="panel-body">
									<div class="col-lg-2 col-sm-2">
										<h4>
											<c:out value="${c.c_name }" />
										</h4>
										<div class="follow-ava">
											<img src="<c:out value="${c.c_logo }" />" />
										</div>
										<h6>
											经营状态：
											<c:out value="${c.c_status }" />
										</h6>
									</div>
									<div class="col-lg-4 col-sm-4 follow-info">
										<p>
											注册号 :
											<c:out value="${c.c_regist_num }" />
										</p>
										<p>
											人员规模:
											<c:out value="${c.c_staff_size }" />
										</p>
										<p>
											企业邮箱:
											<c:out value="${c.c_email }" />
										</p>

										<!--                                     <span><i class="icon_clock_alt"></i>11:05 AM</span> -->
										<p>
											<i class="icon_calendar"></i> 成立日期:
											<c:out value="${c.c_create_date }" />
										</p>
										<!--                                     <span><i class="icon_pin_alt"></i>NY</span> -->

									</div>
									<div class="col-lg-2 col-sm-6 follow-info weather-category">
										<ul>
											<li class="active"><i class="fa fa-phone fa-2x"> </i><br>

												<c:out value="${c.c_tel }" />

										</ul>
									</div>
									<div class="col-lg-2 col-sm-6 follow-info weather-category">
										<ul>
											<li class="active"><i class="fa fa-globe fa-2x"> </i><br>

												所属地区-<c:out value="${c.c_region }" /></li>

										</ul>
									</div>
									<div class="col-lg-2 col-sm-6 follow-info weather-category">
										<ul>
											<li class="active"><i class="fa fa-map-marker fa-2x">
											</i><br>
										<a class="btn btn-primary"
										href="GetCorpInfoServlet.com?company_name=<c:out value="${c.c_name }" />">
										详细信息
										</a>
												

										</ul>
									</div>
								</div>
							</div><br>
						</c:forEach>
					</div>
					<div class="col-sm-3">

							<div class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>下载报告</h2></span>
								</div>
								<div class="panel-body">
									<a data-toggle="modal" data-target="#loginModal"
										onclick="getCaptcha()" target="_blank"> <img
										src="/material/theme/chacha/cms/v2/images/report.png"
										alt="企查查报告" style="max-width: 95%; margin: 10px 0 20px 20px;">
									</a> <a class="btn btn-primary basePageBt" data-toggle="modal"
										data-target="#loginModal" onclick="getCaptcha()"
										target="_blank">下载报告</a>
								</div>
							</div>
							<section class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>企业图谱</h2></span>
								</div>
								<div class="panel-body">
									<a data-toggle="modal" data-target="#loginModal"> <img
										src="images/企业图谱.png" alt="企业图谱"
										style="max-width: 95%; margin: 10px 0 20px;">
									</a> <a href="tu_3.html" class="btn btn-primary basePageBt">查看企业图谱</a>
								</div>
							</section>
							<section class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>投资族谱</h2></span>
								</div>
								<div class="panel-body">
									<a data-toggle="modal" data-target="#loginModal"> <img
										src="images/投资族谱.jpg" alt="投资族谱"
										style="max-width: 95%; margin: 10px 0 20px;">
									</a> <a href="tu_2.html" class="btn btn-primary basePageBt">查看投资族谱</a>
								</div>
							</section>
							<section class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>股权结构</h2></span>
								</div>
								<div class="panel-body">
									<a data-toggle="modal" data-target="#loginModal"
										target="_blank"> <img src="images/股权结构.jpg"
										style="max-width: 95%; margin: 10px 0 20px;" alt="股权结构">
									</a> <a class="btn btn-primary basePageBt" href="tu_1.html">查看股权结构</a>
								</div>
							</section>

							<section class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>疑似关系</h2></span>
								</div>
								<div class="panel-body">
									<a data-toggle="modal" data-target="#loginModal"
										target="_blank"> <img src="images/疑似关系.jpg"
										style="max-width: 95%; margin: 10px 0 20px;" alt="疑似关系">
									</a> <a class="btn btn-primary basePageBt" href="tu_4.html">查看疑似关系</a>
								</div>
							</section>
							<section class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>您可能感兴趣的公司</h2></span>
								</div>
								<ul class="list-group no-bg auto">
									<a href="/firm_hc40f4f25912371bb15d01fbdf6294e3"
										class="list-group-item clearfix"> <span class="clear">
											<span>愛寶皮具製造有限公司</span><br>
									</span>
									</a>
									<a href="/firm_4b2005d92596f9b5850f78a2c48fb0c0"
										class="list-group-item clearfix"> <span class="clear">
											<span>芒市光斌活动策划工作室</span><br>
									</span>
									</a>
									<a href="/firm_322871d19ac38c54e2de82c7e3dd350e"
										class="list-group-item clearfix"> <span class="clear">
											<span>深圳市普勤瑞德科技有限公司</span><br>
									</span>
									</a>
									<a href="/firm_4c8a77415749dc652b91fc204571a921"
										class="list-group-item clearfix"> <span class="clear">
											<span>深圳恒创投资管理有限公司</span><br>
									</span>
									</a>
									<a href="/firm_dcc2e915cae6c33b974ea4dd2d2426a6"
										class="list-group-item clearfix"> <span class="clear">
											<span>北京中科智恒科技有限公司</span><br>
									</span>
									</a>
								</ul>
							</section>
							<section class="panel b-a">
								<div class="panel-heading b-b">
									<span class="font-bold font-15 text-dark"><h2>最新动态</h2></span>
									<a class="pull-right v3e_more"
										href="/cjob_9cce0780ab7644008b73bc2120479d31" id="job_title"
										tabid="job">查看更多&gt;</a>
								</div>
								<ul class="list-group no-bg auto">
									<a href="/postnews_e45ffbe8f4978ba49fd92712c6fac9b1.html"
										target="_blank" class="list-group-item clearfix"> <span
										class="clear"> <span>正式登陆纳斯达克！诞生不到3周年，拼多多创始人身家超越刘强东</span>
									</span> <span class="text-muted text-xs"><i class="i i-clock"></i>
											2018-07-27</span> <span class="pull-right text-muted text-xs"
										style="padding: 4px">凤凰网</span>
									</a>
									<a href="/postnews_e78caed0424266af7b79971963e09d2d.html"
										target="_blank" class="list-group-item clearfix"> <span
										class="clear"> <span>米粉卷入P2P爆雷漩涡，小米作为审核平台如何收场？</span>
									</span> <span class="text-muted text-xs"><i class="i i-clock"></i>
											2018-07-27</span> <span class="pull-right text-muted text-xs"
										style="padding: 4px">OFweek</span>
									</a>
									<a href="/postnews_a09674417c41f6cea01265c0b827a6ff.html"
										target="_blank" class="list-group-item clearfix"> <span
										class="clear"> <span>拼多多要上市了 过去这些大事你得关注</span>
									</span> <span class="text-muted text-xs"><i class="i i-clock"></i>
											2018-07-26</span> <span class="pull-right text-muted text-xs"
										style="padding: 4px">亿邦动力</span>
									</a>
									<a href="/postnews_7d4464c8fe24d8fcbfd090af22a0f0c7.html"
										target="_blank" class="list-group-item clearfix"> <span
										class="clear"> <span>P2P爆雷众生相，投机的人要自救</span>
									</span> <span class="text-muted text-xs"><i class="i i-clock"></i>
											2018-07-26</span> <span class="pull-right text-muted text-xs"
										style="padding: 4px">搜狐科技</span>
									</a>
									<a href="/postnews_228089f2a5de50abde0162acf8786dac.html"
										target="_blank" class="list-group-item clearfix"> <span
										class="clear"> <span>P2P爆雷不断，小米上演完美自救</span>
									</span> <span class="text-muted text-xs"><i class="i i-clock"></i>
											2018-07-26</span> <span class="pull-right text-muted text-xs"
										style="padding: 4px">凤凰科技</span>
									</a>
								</ul>
							</section>
						</div>
					
				
				</div>

			</section>
		</section>
	</section>

	<!-- javascripts -->
	<script src="houtai/js/jquery.js"></script>
	<script src="houtai/js/jquery-ui-1.10.4.min.js"></script>
	<script src="houtai/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
		src="houtai/js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap -->
	<script src="houtai/js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="houtai/js/jquery.scrollTo.min.js"></script>
	<script src="houtai/js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- charts scripts -->
	<script src="houtai/assets/jquery-knob/js/jquery.knob.js"></script>
	<script src="houtai/js/jquery.sparkline.js" type="text/javascript"></script>
	<script
		src="houtai/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	<script src="houtai/js/owl.carousel.js"></script>
	<!-- jQuery fhoutai/ull calendar -->
	<script src="houtai/js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<script src="houtai/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
	<!--script for this page only-->
	<script src="houtai/js/calendar-custom.js"></script>
	<script src="houtai/js/jquery.rateit.min.js"></script>
	<!-- custom select -->
	<script src="houtai/js/jquery.customSelect.min.js"></script>
	<script src="houtai/assets/chart-master/Chart.js"></script>

	<!--custome script for all page-->
	<script src="houtai/js/scripts.js"></script>
	<!-- custom script for this page-->
	<script src="houtai/js/sparkline-chart.js"></script>
	<script src="houtai/js/easy-pie-chart.js"></script>
	<script src="houtai/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="houtai/js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="houtai/js/xcharts.min.js"></script>
	<script src="houtai/js/jquery.autosize.min.js"></script>
	<script src="houtai/js/jquery.placeholder.min.js"></script>
	<script src="houtai/js/gdp-data.js"></script>
	<script src="houtai/js/morris.min.js"></script>
	<script src="houtai/js/sparklines.js"></script>
	<script src="houtai/js/charts.js"></script>
	<script src="houtai/js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src='layer/layer.js'></script>



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
				var company_name = (new Function("", "return " + result))();
				;
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



	<script>
		$('#parentIframe').on('click', function() {
			layer.open({
				type : 2,
				title : '公司地址',
				maxmin : true,
				shadeClose : true, //点击遮罩关闭层
				area : [ '1200px', '700px' ],
				content : parent.$("#map").attr('href')
			});
		});
		$('#parentIframe2').on('click', function() {
			layer.open({
				type : 2,
				title : '企业官网',
				maxmin : true,
				shadeClose : true, //点击遮罩关闭层
				area : [ '1200px', '700px' ],
				content : parent.$("#web").attr('href')
			});
		});

		$('#parentIframe3').on(
				'click',
				function() {
					layer.open({
						type : 1,
						title : '企业电话',
						maxmin : true,
						area : [ '300px', '180px' ],
						shadeClose : true, //点击遮罩关闭
						content : '<br><center><h3>' + $('#tel').text()
								+ '</h3></center>'
					});

				});
	</script>

</body>
</html>