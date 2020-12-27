<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(document).on('ready', function() {
		$('.post-wrapper').slick({
			  slidesToShow: 3,
			  slidesToScroll: 1,
			  autoplay: false,
			  autoplaySpeed: 2000,
			  infinite: false,
			  nextArrow:$('none'),
			  prevArrow:$('none'),
			});
		
		$('.post-wrapper2').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  autoplay: true,
			  fade: true,
			  autoplaySpeed: 3000,
			  infinite: true,
			  speed: 500,
			  nextArrow:$('none'),
			  prevArrow:$('none'),
			});
		
		$('.post-wrapper3').slick({
			  slidesToShow: 3,
			  slidesToScroll: 1,
			  autoplay: true,
			  autoplaySpeed: 3000,
			  nextArrow:$('none'),
			  prevArrow:$('none'),
			});
		
	});
 	
	window.onload=function(){
		var xhr = null;
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var arr=JSON.parse(xhr.responseText);
				
				for(var i=0; i<arr.toparr.length;i++){
					var topsimg = document.getElementById("topsimg"+i);
					topsimg.src = "<%=request.getContextPath()%>/product/"+arr.toparr[i].image;
					var toplink = document.getElementById("toplink"+i);
					toplink.innerHTML = arr.toparr[i].itemname;
					var topstock = document.getElementById("topstock"+i);
					topstock.innerHTML =arr.toparr[i].stock+"개"
				}
				for(var i=0;i<arr.bottomarr.length;i++){
					var bottomimg = document.getElementById("bottomsimg"+i);
					bottomimg.src="<%=request.getContextPath()%>/product/"+arr.bottomarr[i].image;
					var bottomlink = document.getElementById("bottomlink"+i);
					bottomlink.innerHTML = arr.bottomarr[i].itemname;
					var bottomdate = document.getElementById("bottomdate"+i);
					bottomdate.innerHTML="입고일 : "+arr.bottomarr[i].storedate;
				}
			}
		}
		xhr.open('get','<%=request.getContextPath() %>/eventMenu',true);
		xhr.send();
	}
</script>
<div class="page-wrapper" style="position:relative;">
      <!--page slider -->
      <div class="post-slider">
        <h1 class="silder-title">품절 임박</h1>
        <div class="post-wrapper" id="wrapper1">
          <div class="post">
            <img class="slider-image" id="topsimg0">
            <div class="post-info">
              <h4><a href="#" id ="toplink0"></a></h4>
              <i class="fas fa-boxes" style="height:10%;" id="topstock0"></i>
            </div>
          </div>
          <div class="post">
            <img class="slider-image" id="topsimg1">
            <div class="post-info">
              <h4><a href="#" id ="toplink1"></a></h4>
              <i class="fas fa-boxes" style="height:10%;" id="topstock1"></i>
            </div>
          </div>
          <div class="post">
            <img class="slider-image" id="topsimg2">
            <div class="post-info">
              <h4><a href="#" id ="toplink2"></a></h4>
              <i class="fas fa-boxes" style="height:10%;" id="topstock2"></i>
            </div>
          </div>
          <div class="post">
            <img class="slider-image" id="topsimg3">
            <div class="post-info">
              <h4><a href="#" id ="toplink3"></a></h4>
              <i class="fas fa-boxes" style="height:10%;" id="topstock3"></i>
            </div>
          </div>        
        </div>
      </div>
      <!--post slider-->
</div>
<div class="page-wrapper2" style="position:relative;">
 <!--post slider2 -->
      <div class="post-slider2">
      <h1 class="silder-title">이벤트</h1>
        <div class="post-wrapper2">
          <div class="post">
            <img src="<%=request.getContextPath() %>/images/event1.png" class="slider-image">
          </div>
          <div class="post">
            <img src="<%=request.getContextPath() %>/images/event6.png" class="slider-image">
          </div>
          <div class="post">
            <img src="<%=request.getContextPath() %>/images/event3.png" class="slider-image">
          </div>
        </div>
      </div>
      <!--post slider-->
</div>
<br><br>
<div class="page-wrapper" style="position:relative;">
 <!--post slider3 -->
      <div class="post-slider">
      <h1 class="silder-title">최근 입고 상품</h1>
        <div class="post-wrapper" id="wrapper2">
          <div class="post">
            <img class="slider-image" id="bottomsimg0">
            <div class="post-info">
              <h4><a href="#" id="bottomlink0"></a></h4>
              <i class="fas fa-calendar-day" style="height:10%;" id="bottomdate0"></i>
            </div>
          </div>
          <div class="post">
            <img class="slider-image" id="bottomsimg1">
            <div class="post-info">
              <h4><a href="#" id="bottomlink1"></a></h4>
              <i class="fas fa-calendar-day" style="height:10%;" id="bottomdate1"></i>
            </div>
          </div>
          <div class="post">
            <img class="slider-image" id="bottomsimg2">
            <div class="post-info">
              <h4><a href="#" id="bottomlink2"></a></h4>
              <i class="fas fa-calendar-day" style="height:10%;" id="bottomdate2"></i>
            </div>
          </div>
          <div class="post">
            <img class="slider-image" id="bottomsimg3">
            <div class="post-info">
              <h4><a href="#" id="bottomlink3"></a></h4>
              <i class="fas fa-calendar-day" style="height:10%;" id="bottomdate3"></i>
            </div>
          </div>
        </div>
      </div>
      <!--post slider-->
</div>