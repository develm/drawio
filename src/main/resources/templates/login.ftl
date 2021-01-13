<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <title>Animated Form Switching with jQuery</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="Expand, contract, animate forms with jQuery wihtout leaving the page" />
        <meta name="keywords" content="expand, form, css3, jquery, animate, width, height, adapt, unobtrusive javascript"/>
		<link rel="shortcut icon" href="assets/images/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
		<script src="assets/js/cufon-yui.js" type="text/javascript"></script>
		<script src="assets/js/ChunkFive_400.font.js" type="text/javascript"></script>
		<script type="text/javascript">
			Cufon.replace('h1',{ textShadow: '1px 1px #fff'});
			Cufon.replace('h2',{ textShadow: '1px 1px #fff'});
			Cufon.replace('h3',{ textShadow: '1px 1px #000'});
			Cufon.replace('.back');
		</script>
    </head>
    <body>
		<div class="wrapper">
			<h1>simple auth boot</h1>
			<div class="content">
				<div id="form_wrapper" class="form_wrapper">
					<form class="login active" action="login" method="post">
						<h3>Login</h3>
						<div>
							<label>Username:</label>
							<input name="username" type="text" value="${errorMsg}" />
						</div>
						<div>
							<label>Password: </label>
							<input name="password" type="password" />
						</div>
						<div class="bottom">
							<div class="remember"><input name="remember-me" value="1" type="checkbox" /><span>Keep me logged in</span></div>
							<input type="submit" value="Login"></input>
							<div class="clear"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
    </body>
</html>