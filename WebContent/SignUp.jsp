<%@include file="header.jsp" %>
     <div class="mdl-layout__drawer mdl-layout--small-screen-only">
        <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font"><a class="mdl-navigation__link" href="index.html">Home</a><a class="mdl-navigation__link" href="portfolio.html">Portfolio</a><a class="mdl-navigation__link" href="about.html">About</a><a class="mdl-navigation__link" href="contact.html">Contact</a>
        </nav>
      </div>
      <main class="mdl-layout__content">
        <div class="site-content">
          <div class="container"><div class="mdl-grid site-max-width">
    <div class="mdl-cell mdl-cell--12-col mdl-card mdl-shadow--4dp welcome-card portfolio-card">
        <div class="mdl-card__title">
          <h2 class="mdl-card__title-text">Sign Up to Create a New User</h2>
        </div>
        <div class="mdl-card__supporting-text">
          <form action="" method="post">
          <input type="text" name="name" placeholder="Name" required /><br><br>
          <input type="email" name="email" placeholder="Email" required /><br><br>
          <input type="password" name="password" placeholder="Password" required/><br><br>
		  <input type="password" name="confirmpassword" placeholder="Confirm Password" required/><br><br>
		  <input type="submit" value="Sign Up" id="SignUpButton" />
		  </form>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>