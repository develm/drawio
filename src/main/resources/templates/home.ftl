<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SQL-ER</title>
<style>
span
{
cursor: pointer;background-color: #4CAF50;color: white;padding: 10px 12px;display: inline-block;font-size: 15px;
}
</style>
<link rel="stylesheet" type="text/css" href="styles/style.css"/>
</head>
<body>
<!--<div style="height: 40px;"></div>-->
<div class="container">
	<div class="price">
		<h2>SQL-ER</h2>
		<div class="category">
			<ul>
				<li class="active">sql检查</li>
				<li onclick="getUserFileList()">ER图</li>
			</ul>
<!--			<a href="javascript:;" class="prev"><span></span></a>-->
<!--			<a href="javascript:;" class="next"><span></span></a>-->
		</div>
		<div class="cont active">
            <textarea id="sqlValue" rows="18" cols="150" style="width: 100%;"></textarea>
            <p style="width: 100%;">
                <span>mysql</span>
                <span>postgresql</span>
                <span>db2</span>
                <span>oracle</span>
                <span>sqlserver</span>
                <span>sqlite</span>
                <span>hbase</span>
                <span>hive</span>
                <span>other</span>
            </p>
            <textarea id="checkValue" rows="18" cols="150" readonly="readonly" style="background: lightgrey;width: 100%;"></textarea>
		</div>
		<div class="cont">
			<ul>
				<li>
					<a href="/index">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">云服务器</h3>
							<p class="desc">稳定安全，高易用可弹性伸缩的计算服务</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">云数据库 MySQL</h3>
							<p class="desc">一体化多维度监控，高效管理海量数据库</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">对象存储 COS</h3>
							<p class="desc">可靠、安全、易用的可扩展文件存储</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">内容分发网络 CDN</h3>
							<p class="desc">多借点全网覆盖、安全稳定的内容加速服务</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">BGP高防</h3>
							<p class="desc">高达300G的防护服务和多达21线的BGP线路抵御DDoS攻击</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">云解析</h3>
							<p class="desc">向全网域名提供稳定、安全、快速的智能解析服务</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">万象优图</h3>
							<p class="desc">高效图片处理、全面的图片鉴定和识别服务</p>
						</div>
					</a>
				</li>
				<li>
					<a href="#">
						<div class="cont_main">
							<img src="images/osa_database.png" style="width: 100%;">
							<h3 class="title">直播</h3>
							<p class="desc">专业稳定快速的直播接入和分发服务</p>
						</div>
					</a>
				</li>						
			</ul>
		</div>
		
	</div>
</div>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		//选项卡切换
		$('.category ul li').click(function(){
			indexC = $(this).index();
			$(this).addClass('active').siblings().removeClass('active');
			$('.cont').eq(indexC).addClass('active').siblings().removeClass('active');
		})
		//按钮箭头
		var catew = $('.category').width()-150;
		var cateLiw = 0;
		$('.category ul li').each(function(){
			cateLiw +=$(this).outerWidth();
		})
		var i =0;
		$('.next').click(function(){
			$('.category ul').animate({
				"margin-left":-catew+'px'
			},500)
			i++;
			if((catew+150)*i+(catew+150)>=cateLiw){
				$('.prev').show();
				$('.next').hide();
			}
		})
		$('.prev').click(function(){
			$('.category ul').animate({
				"margin-left":0+'px'
			},500)
			$(this).hide();$('.next').show();
		})
		$('.cont p span').click(function(){
            $.post("/sqlCheck",{dbType:$(this).text(),sql:$("#sqlValue").val()},function(result){
                $("#checkValue").val(result);
              });
        })
	});

	function getUserFileList(){
          $.post("/getUserFileList",{},function(result){
              if(result.code == 0){
                alert("success");
              }else if(result.code == -1){
                window.location.href="/login/signin";
              }else{
                   alert(result.msg);
                 }
            });
      }
</script>
</body>
</html>
