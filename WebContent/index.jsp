<%@include file="header.jsp" %>
<%-- <%@include file="sidebar.jsp" %> --%>
      <div class="mdl-layout__drawer mdl-layout--small-screen-only">
        <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font"><a class="mdl-navigation__link" href="index.html">Home</a><a class="mdl-navigation__link" href="portfolio.html">Portfolio</a><a class="mdl-navigation__link" href="about.html">About</a><a class="mdl-navigation__link" href="contact.html">Contact</a>
        </nav>
      </div>
      <main class="mdl-layout__content">
        <div class="site-content">
          <div class="container"><div class="mdl-grid site-max-width">
    <div class="mdl-cell mdl-cell--12-col mdl-card mdl-shadow--4dp welcome-card portfolio-card">
        <div class="mdl-card__title">
          <h2 class="mdl-card__title-text">Welcome</h2>
        </div>
        <div class="mdl-card__supporting-text">
          SweetNews was founded on the premise that there are too many new sources out there that provide what is being called "fake news".
          <br/>
          To eliminate this, we provide curated news based upon your own interests from <strong>only</strong> verified resources!
          <br/>
          Feel free to look around and thanks for stopping by!
        </div>
    </div>
</div>
<section class="section--center mdl-grid site-max-width">
	
    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card">
        <div class="mdl-card__media">
            <img class="article-image" src="img/portfolio1.jpg" border="0" alt="">
        </div>
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Rocky Peak</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Enim labore aliqua consequat ut quis ad occaecat aliquip incididunt. Sunt nulla eu enim irure enim nostrud aliqua consectetur ad consectetur sunt ullamco officia. Ex officia laborum et consequat duis.
        </div>
    </div>
    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card">
        <div class="mdl-card__media">
            <img class="article-image" src="img/portfolio2.jpg" border="0" alt="">
        </div>
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Night Shadow</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Enim labore aliqua consequat ut quis ad occaecat aliquip incididunt. Sunt nulla eu enim irure enim nostrud aliqua consectetur ad consectetur sunt ullamco officia. Ex officia laborum et consequat duis.
        </div>
    </div>
    <div class="mdl-cell mdl-card mdl-shadow--4dp portfolio-card">
        <div class="mdl-card__media">
            <img class="article-image" src="img/portfolio3.jpg" border="0" alt="">
        </div>
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Sky Reach</h2>
        </div>
        <div class="mdl-card__supporting-text">
            Enim labore aliqua consequat ut quis ad occaecat aliquip incididunt. Sunt nulla eu enim irure enim nostrud aliqua consectetur ad consectetur sunt ullamco officia. Ex officia laborum et consequat duis.
        </div>
    </div>
</section>

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
                <span>Amazing people, always ready to help!</span>
                <span class="mdl-list__item-text-body">
                  Bryan Cranston, CEO, Amazing.com
                </span>
              </span>
            </li>
            <li class="mdl-list__item mdl-list__item--three-line">
              <span class="mdl-list__item-primary-content">
                <i class="material-icons  mdl-list__item-avatar">person</i>
                <span>Awesome work, they can do almost anything..</span>
                <span class="mdl-list__item-text-body">
                Aaron Paul, Marketing Lead, Awesome.com
                </span>
              </span>
            </li>
        </ul>
      </div>
      <div class="demo-card-event mdl-cell mdl-card mdl-shadow--4dp event-card portfolio-card">
        <div class="mdl-card__title mdl-card--expand">
        	<h2 class="mdl-card__title-text" style="margin-left: 40% !important"> Login </h2>
       	</div>
       	<div>
          	<div id="login" class="loginContainer" style="float:right;margin-left:25%;margin-bottom:25%">
				<form action="" method="post">
					<input type="hidden" name="action" value="login" />
<!-- 					<label>Email</label> -->
					<input type="email" name="email" placeholder="Email" required />	
					<br/><br/>
<!-- 					<label>Password</label> -->
					<input type="password" name="password" placeholder="Password" required/>
					<br/>
					<br/>
					<input type="submit" value="Log in" id="loginButton" />
				</form>
			</div>
        </div>
      </div>
  </section>
</div></div>
        </div>

<%@ include file="footer.jsp" %>
