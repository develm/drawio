
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
								<form action="login/signUpSubmit">
									<div class="form-group">
										<label for="userPhone">手机号</label>
										<input type="phone" class="form-control" id="userPhone" placeholder="请输入手机号">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="msgCode">验证码</label>
										<input type="email" class="form-control" id="msgCode" placeholder="请输入验证码">
									</div><!--/.form-group -->
									<div class="form-group">
										<label for="userPassword">密码</label>
										<input type="password" class="form-control" id="userPassword" placeholder="请输入密码">
									</div><!--/.form-group -->
								</form><!--/form -->
							</div><!--/.col -->
						</div><!--/.row -->

					<div class="signin-footer">
						<button type="button" class="btn signin_btn signin_btn_two" data-toggle="modal" data-target=".signin_modal">
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

    </body>
	
</html>