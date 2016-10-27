<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="disk.hao.search.Searcher"%>   
<%@page import="disk.hao.entity.SearchResult"%>   
<%@page import="java.util.ArrayList"%>  
<%@page import="java.util.Iterator"%>  
<%@page import="java.text.SimpleDateFormat"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="./css/normalize.css" />

<link rel="stylesheet" type="text/css" href="./css/default.css">
<link rel="stylesheet" type="text/css" href="./css/styles.css">

<title>Insert title here</title>
</head>
<body>
 <header>
   <!--<h1>我即使是死了，钉在棺材里了，也要在墓里，用这腐朽的声带喊出:冬马小三！！！</h1>!-->
	 <form action="index.jsp" method="GET">
   
    <nav>
      <span>Home</span>
      <input type="text" name="query">
      <input type="submit" value="搜索">
    </nav>
    </form> 
  </header>
  
	
<%!

	public static final int PAGESIZE = 10;
	int pageCount;
	int curPage = 1;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private String query= "query";
	
%>

<!--<div class="wrapper">


 <a href="index.jsp">Home</a>
      <a href="">About</a>
      <a href="">Gallery</a>
      <a href="">Contact</a>


	<section id='steezy'>
    	<div class="htmleaf-header">
		
		</div>
	</section>-->
	
<%
		try{
			query = request.getParameter("query");
		}catch(Exception e){
			query = "query";
		}
		
		System.out.println("query------------------"+query);
		//一页放5个
			
		Searcher searcher = new Searcher();
		ArrayList<SearchResult> reList = searcher.search(query);
		
		int size = reList.size();
		pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);
		String curPageString = "1";
		try{	
			curPageString = request.getParameter("curPage");
			curPage = Integer.parseInt(curPageString);
		}catch(Exception e){
			curPageString = "1";
		}

		if(curPage>=pageCount) curPage = pageCount;
		if(curPage<=1) curPage = 1;
			
		try{
			for(int i = ((curPage-1)*PAGESIZE);i<((curPage)*PAGESIZE);i++){
				SearchResult re = reList.get(i);
				String text = re.getNameString();
				String urlString = re.getURLString();
	%>  
			          <section id="big">
			            <h1>
			            <a href="<%=urlString%>"><%=text%></a>
			            </h1>  

			         </section>    
	<%  
			}
		}catch(Exception e){
			curPageString = "1";
		}
%>
<a href = "index.jsp?query=<%=query%>&curPage=1" >首页</a>
<a href = "index.jsp?query=<%=query%>&curPage=<%=curPage-1%>" >上一页</a>
<a href = "index.jsp?query=<%=query%>&curPage=<%=curPage+1%>" >下一页</a>
<a href = "index.jsp?query=<%=query%>&curPage=<%=pageCount%>" >尾页</a>
第<%=curPage%>页/共<%=pageCount%>页

</div>


<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-2.1.1.min.js"><\/script>')</script>
	<script>
	$(function () {
	    $(window).scroll(function () {
	        var winTop = $(window).scrollTop();
	        if (winTop >= 30) {
	            $('body').addClass('sticky-header');
	        } else {
	            $('body').removeClass('sticky-header');
	        }
	    });
	});
	</script>

</body>
</html>