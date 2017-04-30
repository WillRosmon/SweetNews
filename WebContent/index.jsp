<%@include file="header.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sn.database.objects.Article"%>
<%@ page import="com.sn.database.objects.User"%>
<%@ page import="java.util.Iterator" %>

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
		    
		    <%
			  User user =(User) session.getAttribute("index");
			  Iterator<String> userit = user.getInterests().iterator();
			  
			  ArrayList<Article> articles = (ArrayList)session.getAttribute("indexlist");
			  int j=0;
			            		
			  while(j+1 < articles.size())
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
					if(j%3==0)
						break;					
				}
				out.println("</section>");
			  }
			
			%>					
		    

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
