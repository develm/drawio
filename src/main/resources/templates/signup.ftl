
<!doctype html>
<html  lang="en">

    <head>
        <!-- meta data -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- title of site -->
        <title>Sign up</title>

        <!--bootstrap.min.css-->
        <link rel="stylesheet" href="../styles/bootstrap.min.css">
		
		<!-- bootsnav -->
		<link rel="stylesheet" href="../styles/bootsnav.css" >
        
        <!--style.css-->
        <link rel="stylesheet" href="../styles/login.css">

    </head>
	
	<body>
		<!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->
		
		<!-- signin end -->
		<section class="signin signup">
			<div class="container">

				<div class="sign-content">
					<h2>注册</h2>

					<div class="signin-form">
						<div class=" ">
							<div class=" ">
								<form action="/login/signUpSubmit">
									<div class="form-group">
										<label for="userPhone">手机号</label>
										<input type="phone" class="form-control" id="userPhone" name="userPhone" placeholder="请输入手机号">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="userPassword">密码</label>
										<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="请输入密码">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="rePassword">确认密码</label>
										<input type="password" class="form-control" id="rePassword" name="rePassword" placeholder="请重新输入密码">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="msgCode">手机验证码</label>
										<input type="email" class="form-control" id="msgCode" name="msgCode" placeholder="请输入手机验证码" style="width: 80%;">
									</div><!--/.form-group -->
									<div class="ui-form-explain">
										<button type="button" onclick="sendMsgCode()" class="btn signin_btn" style="width: 15%;align-self: auto;margin-left: 85%;margin-top: -90px;height: 36px;">发送验证码</button>
									</div>
								</form><!--/form -->
							</div><!--/.col -->
						</div><!--/.row -->

					<div class="signin-footer">
						<button type="button" onclick="signUp()" class="btn signin_btn signin_btn_two" data-toggle="modal" data-target=".signin_modal">
						注册
						</button>
						<p style="margin-right: inherit;">
							已有账号 ?
							<a href="/login/signin">登录</a>
						</p>
					</div><!--/.signin-footer -->

				</div><!--/.sign-content -->

			</div><!--/.container -->

		</section><!--/.signin -->
    <script src="../js/jquery.min.js"></script>
    <script type="text/javascript">
        function sendMsgCode(){
              $.post("/login/sendMsgCode",{},function(result){
                  if(result.code == 0){
                    alert("success");
                  }else{
                       alert(result.msg);
                     }
                });
        }

        function signUp(){
            var sendData={
                "userPhone":$("#userPhone").val(),
                "msgCode":$("#msgCode").val(),
                "userPassword":$("#userPassword").val(),
                "rePassword":$("#rePassword").val(),
            };
              $.post("/login/sendMsgCode",sendData,function(result){
                  if(result.code == 0){
                    alert("success");
                  }else{
                       alert(result.msg);
                     }
                });
        }
    </script>
    </body>
</html>