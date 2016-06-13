<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-push-3">
				<div class="login-form">
					<form class="form-horizontal login" method="POST"
						AUTOCOMPLETE="off" action="j_security_check" name="login">
						<%
							String query = request.getParameter("err");
							int i = 0;
							if (null != query) {
								i = Integer.parseInt(query);
								if (1 == i) {
						%>
						<div class="alert alert-danger" role="alert">* Please, check
							your User Name and Password.</div>
						<%
							}
							}
						%>
						<div class="jumbotron clearfix">
							<div class="form-group">

								<label for="userName" class="col-sm-3 control-label">Username</label>
								<div class="col-sm-9">
									<div class="form-control-container">
										<div class="row">
											<div class="col-xs-1 col">
												<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
											</div>
											<div class="col-xs-11 input">
												<input type="text" name="j_username" class="form-control"
													id="userName">
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="col-sm-3 control-label">Password</label>
								<div class="col-sm-9">
									<div class="form-control-container">
										<div class="row">
											<div class="col-xs-1 col">
												<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
											</div>
											<div class="col-xs-11 input">
												<input type="password" name="j_password"
													class="form-control" id="inputPassword">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group text-center">
							<button class="btn btn-lg btn-primary login" type="submit">LOGIN</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>