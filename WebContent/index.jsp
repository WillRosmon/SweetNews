<%@include file="header.jsp" %>
<main class="mdl-layout__content">
	<div class="site-content">
		<div class="container">
			<div class="mdl-grid site-max-width">
				<div class="mdl-cell mdl-cell--12-col mdl-card mdl-shadow--4dp welcome-card portfolio-card">
					<div class="mdl-card__title">
						<img src="Images/img_6264.jpg" />
						<h2 class="mdl-card__title-text">Welcome</h2>
					</div>
				</div>
			</div>
			<section class="section--center mdl-grid site-max-width">
				<c:forEach items="${requestScope.articles}" var="a">
				    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card">
				        <div class="mdl-card__media">
				            <img class="article-image" src="${a.urlToImage}" border="0" alt="">
				        </div>
				        <div class="mdl-card__title">
				            <h2 class="mdl-card__title-text">${a.title}</h2>
				        </div>
				        <div class="mdl-card__supporting-text">
				        	${a.description}
				        </div>
				    </div>
			    </c:forEach>
		    </section>
		    
		    
<!-- 			<section class="section--center mdl-grid site-max-width"> -->
<!-- 			    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card"> -->
<!-- 			        <div class="mdl-card__media"> -->
<!-- 			            <img class="article-image" src="https://cdn3.tnwcdn.com/wp-content/blogs.dir/1/files/2017/04/oNfiQw3.jpg" border="0" alt=""> -->
<!-- 			        </div> -->
<!-- 			        <div class="mdl-card__title"> -->
<!-- 			            <h2 class="mdl-card__title-text">Create effective and dazzling business plans with the help of Bizplan Premium - just $69</h2> -->
<!-- 			        </div> -->
<!-- 			        <div class="mdl-card__supporting-text"> -->
<!-- 			        	Putting together a business plan can be almost as nerve-racking as starting the business itself. How do you make sure to include all the components you need so potential stakeholders ... -->
<!-- 			        </div> -->
<!-- 			    </div> -->
<!-- 			    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card"> -->
<!-- 			        <div class="mdl-card__media"> -->
<!-- 			            <img class="article-image" src="https://cdn3.tnwcdn.com/wp-content/blogs.dir/1/files/2017/04/ou5fXgO.jpg" border="0" alt=""> -->
<!-- 			        </div> -->
<!-- 			        <div class="mdl-card__title"> -->
<!-- 			            <h2 class="mdl-card__title-text">Study to be a Cisco networking master with nine certifications for just $49</h2> -->
<!-- 			        </div> -->
<!-- 			        <div class="mdl-card__supporting-text"> -->
<!-- 			            If you're going to be an IT specialist, knowing all there is to know about Cisco Systems hardware and networks isn't a bad place to start. Cisco remains the preeminent tech titan ... -->
<!-- 			        </div> -->
<!-- 			    </div> -->
<!-- 			    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card"> -->
<!-- 			        <div class="mdl-card__media"> -->
<!-- 			            <img class="article-image" src="https://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2015/07/growth123.jpg" border="0" alt=""> -->
<!-- 			        </div> -->
<!-- 			        <div class="mdl-card__title"> -->
<!-- 			            <h2 class="mdl-card__title-text">Fundamental growth hacking for new 'solopreneurs'</h2> -->
<!-- 			        </div> -->
<!-- 			        <div class="mdl-card__supporting-text"> -->
<!-- 			            Successful digital marketing and website optimization = more business. Yes, it's a fact and that's great... But what about hacking your way to growth as a new entrepreneur working ... -->
<!-- 			        </div> -->
<!-- 			    </div> -->
<!-- 			</section> -->

			<div class="homepage-footer">
			  <section class="mdl-grid site-max-width">
			      <div class="mdl-cell mdl-card mdl-cell--8-col mdl-cell--4-col-tablet  mdl-shadow--4dp portfolio-card">
			        <div class="mdl-card__title">
			            <h2 class="mdl-card__title-text">Testimonials</h2>
			        </div>
			        <ul class="demo-list-three mdl-list">
			            <li class="mdl-list__item mdl-list__item--three-line">
			              <span class="mdl-list__item-primary-content">
			                <i class="material-icons mdl-list__item-avatar">person</i>
			                <span>Great news articles from great sources!</span>
			                <span class="mdl-list__item-text-body">
			                  UserFirst UserLast
			                </span>
			              </span>
			            </li>
			            <li class="mdl-list__item mdl-list__item--three-line">
			              <span class="mdl-list__item-primary-content">
			                <i class="material-icons  mdl-list__item-avatar">person</i>
			                <span>Only the news I want to read!</span>
			                <span class="mdl-list__item-text-body">
			                UserFirst UserLast
			                </span>
			              </span>
			            </li>
			        </ul>
			      </div>
			      <div class="demo-card-event mdl-cell mdl-card mdl-shadow--4dp event-card portfolio-card">
			        <div class="mdl-card__title mdl-card--expand">
			        	<h2 class="mdl-card__title-text" style="margin-left: 40% !important; margin-bottom: 44px"> Login </h2>
			   		</div>
					<div>
			      		<div id="login" class="loginContainer" style="margin-left:25%;margin-bottom:25%">
							<form action="user?action=login" method="post">
								<input type="hidden" name="action" value="login" />
								<input type="email" name="email" placeholder="Email" required />	
								<br/><br/>
								<input type="password" name="password" placeholder="Password" required/>
								<br/>
								<br/>
								<input type="submit" value="Log in" id="loginButton" />
								<p style="color: red">${msg}</p>
							</form>
						</div>
			        </div>
    			</div>
 			 </section>
		</div>
	</div>
</div>

<%@ include file="footer.jsp" %>
