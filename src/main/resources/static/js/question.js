/**
 * 
 */
 
 $(document).ready(function(){
  $("dt").click(function(){
     $(this).toggleClass("on").next().toggleClass("on");
    $("dt").not(this).removeClass("on").next().removeClass("on")
  });
});