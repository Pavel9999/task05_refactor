<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Home Page</title>

       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



       <link rel="stylesheet" href="resources/css/menu.css">
       <link rel="stylesheet" href="resources/css/submit.css">
       <link rel="stylesheet" href="resources/css/footer.css" >
	   <link rel="stylesheet" href="resources/css/slider.css" >


       <fmt:setLocale value="${sessionScope.local}" />
       <fmt:setBundle basename="local" var="loc" />

       <fmt:message bundle="${loc}" key="local.guest_login"
                    var="guest_login_text" />
       <fmt:message bundle="${loc}" key="local.login"
                    var="login_text" />
       <fmt:message bundle="${loc}" key="local.singup"
                    var="singup_text" />



   </head>
   <body>
    
    <div class="navbar">
  <a href="#home">Home</a>  
  <a href="#news">Contact </a>
  
<form action="controller" method="post">
    <input type="hidden" name="command" value="change_locale" />
    <input type="hidden" name="local" value="ru" />
    <fmt:setLocale value="ru" scope="session"/>
    <input class="btnsub" type="submit" value="Русский" style="float: left; margin-left: 60px;"/>
</form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="change_locale" />
    <input type="hidden" name="local" value="de" />
    <input class="btnsub" type="submit" value="Deutch" style="float: left;"/>
</form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="change_locale" />
    <input type="hidden" name="local" value="en"  />
    <fmt:setLocale value="en" scope="session"/>
    <input class="btnsub" type="submit" value="English" style="float: left; margin-right: 160px; "/>
</form>


    <form action="controller" method="POST">
        <input type="hidden" name="command" value="enterAsGuest">
        <input type="hidden"  name="acc_type" value="guest" />
        <input type="submit" value="${guest_login_text}" style="float: left; color:red" />
    </form>

    <form action="controller" method="POST">
        <input type="hidden" name="command" value="goToAuthorizationPage">

        <input type="submit" value="${login_text}" style="float: left; color:green"/>
    </form>

    <form action="controller" method="POST">
        <input type="hidden" name="command" value="goToRegistrationPage">

        <input type="submit" value="${singup_text}" style="float: left; color:yellow" />
    </form>


  </div>
</div> 
<br>


<div class="slideshow-container">

<div class="mySlides fade">
  <div class="numbertext">1 / 3</div>
  <img src="resources/img/prokat1.jpg" style="width:100%">
  
</div>

<div class="mySlides fade">
  <div class="numbertext">2 / 3</div>
  <img src="resources/img/prokat4.jpg" style="width:100%">
  
</div>

<div class="mySlides fade">
  <div class="numbertext">3 / 3</div>
  <img src="resources/img/prokat5.jpg" style="width:100%">
  
</div>

<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
<a class="next" onclick="plusSlides(1)">&#10095;</a>

</div>
<br>

<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
</div>

<script>
var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none"; 
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1} 
  slides[slideIndex-1].style.display = "block"; 
  setTimeout(showSlides, 2000); // Change image every 2 seconds
}
</script>






<footer id="myFooter">
        <div class="container">
            <ul>
                <li><a href="#">CompanyInformation</a></li>
                <li><a href="#">Contactus</a></li>
                <li><a href="#">Reviews</a></li>
                <li><a href="#">Term</a></li>
            </ul>
        <p class="footer-copyright">CopyrightText</p>
        </div>
       
    </footer>


 
      
      
      











      
      
             
   </body>
</html