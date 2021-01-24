
<!doctype html>
<html  lang="en">

    <head>
        <!-- meta data -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- title of site -->
        <title>Sign in</title>

        <!--bootstrap.min.css-->
        <link rel="stylesheet" href="../styles/bootstrap.min.css">

		<!-- bootsnav -->
		<link rel="stylesheet" href="../styles/bootsnav.css" >

        <!--style.css-->
        <link rel="stylesheet" href="../styles/login.css">

    </head>
	
	<body>

		<!-- signin end -->
		<section class="signin">
			<div class="container">

				<div class="sign-content">
					<h2>登录</h2>

					<div class="row">
						<div class="col-sm-12">
							<div class="signin-form">
								<form action="login/signInSubmit">
									<div class="form-group">
									    <label for="userPhone">手机号</label>
										<input type="phone" class="form-control" id="userPhone" name="userPhone" placeholder="请输入手机号">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="userPassword">密码</label>
									    <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="请输入密码">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="imgCode">验证码</label>
										<input type="text" class="form-control" id="imgCode" name="imgCode" placeholder="请输入验证码" style="width: 80%;">
										<div class="ui-form-explain"><img src="../images/yzm.jpg" class="yzm-img" style="position: absolute;right: 10px;top: 190px;"></div>
									</div><!--/.form-group -->
								</form><!--/form -->
							</div><!--/.signin-form -->
						</div><!--/.col -->
					</div><!--/.row -->

					<div class="row">
						<div class="col-sm-12">
							<div class="signin-password">
								<div class="password-circle">
									<div class="single-password-circle">
										<input type="radio" id="radio01" name="radio">
											<label for="radio01">
												<span class="round-boarder">
													<span class="round-boarder1"></span>
												</span>记住密码
											</label>
									</div><!--/.single-password-circle-->
									<div class="single-forgot-password-circle text-right
									">
										<a href="/login/signin">清空</a>
									</div><!--/.single-password-circle-->
								</div><!--/.password-circle-->
							</div><!--/.signin-password -->
						</div><!--/.col -->
					</div><!--/.row -->

					<div class="row">
						<div class="col-sm-12">
							<div class="signin-footer">
								<button type="button" onclick="signIn()" class="btn signin_btn signin_btn_two" data-toggle="modal" data-target=".signin_modal">
								登录
								</button>
								<p style="margin-right: inherit;">
									还没有账号 ?
									<a href="/login/signup">注册</a>
								</p>
							</div><!--/.signin-footer -->
						</div><!--/.col -->
					</div><!--/.row -->

				</div><!--/.sign-content -->

			</div><!--/.container -->

		</section><!--/.signin -->
		
		<!-- signin end -->
    <script src="../js/jquery.min.js"></script>
    <script type="text/javascript">
        function refreshCode(){
              $.post("/login/sendMsgCode",{},function(result){
                  if(result.code == 0){
                    alert("success");
                  }else{
                       alert(result.msg);
                     }
                });
        }

        function signIn(){
            var sendData={
                "userPhone":$("#userPhone").val(),
                "imgCode":$("#imgCode").val(),
                "userPassword":$("#userPassword").val()
            };
              $.post("/login/signInSubmit",sendData,function(result){
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