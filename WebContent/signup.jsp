<%@include file="header.jsp" %>
<main class="mdl-layout__content">
	<div class="site-content">
		<div class="container">
			<div class="mdl-grid site-max-width">
				<div class="mdl-cell mdl-cell--12-col mdl-card mdl-shadow--4dp welcome-card portfolio-card">
						<h2 class="mdl-card__title-text" style="margin-right: 32%">Sign Up to Create a New User</h2>
						
					<div class="mdl-card__supporting-text" style="margin-left:38%">
						<form action="user" method="post">
							<input type="hidden" name="action" value="create" />
							<input type="text" name="name" placeholder="Name" required /><br><br>
							<input type="email" name="email" placeholder="Email" required /><br><br>
							<input type="password" name="password" placeholder="Password" required/><br><br>
							<input type="password" name="confirmpassword" placeholder="Confirm Password" required/><br><br>
							<input type="submit" value="Sign Up" id="SignUpButton" />
							<p style="color: red">${msg}</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>