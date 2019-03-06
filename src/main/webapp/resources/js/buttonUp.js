$(document).ready(function(){

	$.fn.btnToTop=function(){
        $(this).hide().removeAttr("href");
        if($(window).scrollTop()!="0"){
            $(this).fadeIn("slow")
      }
      var btnTopDiv=$(this);
      $(window).scroll(function(){
        if($(window).scrollTop()=="0"){
        $(btnTopDiv).fadeOut("slow")
        }else{
        $(btnTopDiv).fadeIn("slow")
      }
      });
        $(this).click(function(){
          $("html, body").animate({scrollTop:0},"slow")
        })
      }
    $(function() {$(".button-top").btnToTop();});
	
}); // ready