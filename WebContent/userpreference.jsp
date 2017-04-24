<%@include file="header.jsp" %>
<main class="mdl-layout__content">
	<div class="site-content">
		<div class="container">
			<div class="mdl-grid site-max-width">
				<div class="mdl-cell mdl-cell--12-col mdl-card mdl-shadow--4dp welcome-card portfolio-card" style="background-color: #3E4EB8;color: white;">
<!-- 					<div class="mdl-card__title mdl-card--expand"> -->
						<h2 class="mdl-card__title-text" style="margin-right:35%">What's Your Passion?</h2>
<!-- 					</div> -->
					<div class="mdl-card__supporting-text" style="margin-left: 45%;color: white;">
						<form action="" method="post">
							<input type="checkbox" name="preference" name="business">Business<br><br>
							<input type="checkbox" name="preference" name="technology">Technology<br><br>
							<input type="checkbox" name="preference" name="sports">Sports<br><br>
							<input type="checkbox" name="preference" name="travel">Travel<br><br>
							<input type="checkbox" name="preference" name="politics">Politics<br><br><br>
							<input type="submit" value="Submit" id="choosepreference" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>