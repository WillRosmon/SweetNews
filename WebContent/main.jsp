<%@include file="header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sn.database.objects.Article"%>
<%@ page import="com.sn.database.objects.User"%>
<%@ page import="java.util.Iterator" %>

<%-- <%@include file="sidebar.jsp" %> --%>
      <div class="mdl-layout__drawer mdl-layout--small-screen-only">
        <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font"><a class="mdl-navigation__link" href="index.html">Home</a><a class="mdl-navigation__link" href="portfolio.html">Portfolio</a><a class="mdl-navigation__link" href="about.html">About</a><a class="mdl-navigation__link" href="contact.html">Contact</a>
        </nav>
      </div>
      <main class="mdl-layout__content">
        <div class="site-content">
          <div class="container">
			
			<%
			  User user =(User) session.getAttribute("theUser");
			  Iterator<String> userit = user.getInterests().iterator();
			  
			  ArrayList<Article> articles = (ArrayList)session.getAttribute("articles");
			  int j=0;
			            		
			  while(userit.hasNext())
			  {
				String categoryname =  userit.next();  
				out.println("<section class='section--center mdl-grid site-max-width'>"+"<div class='section--center mdl-grid site-max-width' style='width:1000px;margin-bottom:-20px;'>");
				out.println("<h2 style='font-size:30px;'>"+categoryname+"</h2>"+"</div><br>");		  
				for(int i=0;i<3;i++)
				{
					Article article = articles.get(j);
					out.println("<div class='mdl-cell mdl-card mdl-shadow--4dp portfolio-card'>"+"<div class='mdl-card__media'>");
					out.println("<img class='article-image' src='"+article.getUrlToImage()+"' border='0' alt=''>"+"</div>");
					out.println("<div class='mdl-card__title'>"+"<h2 class='mdl-card__title-text'>"+article.getTitle()+"</h2></div>");
					out.println("<div class='mdl-card__supporting-text'>"+article.getDescription()+"</div></div>");
					j++;
					if(j>articles.size() || j%3==0)
						break;
				}
				out.println("</section>");
			  
			  }
			
			%>					

			<div class="homepage-footer">
			  <section class="mdl-grid site-max-width">
			      <div class="demo-card-event mdl-cell mdl-card mdl-shadow--4dp event-card portfolio-card" style="width: 1000px;height: 255px;">
			        <div class="mdl-card__title mdl-card--expand" style="margin-top: 25px;">
			        	<h2 class="mdl-card__title-text" style="margin-left: 37% !important"> Add Your Own Source! </h2>
			        	<br/><br/>
			       	</div>
			       	<div style="margin-left: 25%">
			          	<div id="submitSourceForm" class="submitSourceContainer" style="margin-left:20%;margin-bottom:25%;margin-top:2%;">
							<form action="user" method="post">
								<input type="hidden" name="action" value="add" />
								<input type="text" name="source" placeholder="Source: AP/USA Today/etc" required />	
								<br/><br/>
								<input type="text" name="category" placeholder="Category" required/>
								<br/>
								<br/>
								<input type="text" name="url" placeholder="URL" required />
								<br/><br/>
								<input type="submit" value="Submit" id="submitSource" />
							</form>
						</div>
			        </div>
			      </div>
			  </section>
			</div>
		</div>
      </div>

<%@ include file="footer.jsp" %>