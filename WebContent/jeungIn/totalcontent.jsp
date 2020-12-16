<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(document).on('ready', function() {
		$('.post-wrapper').slick({
			  slidesToShow: 3,
			  slidesToScroll: 1,
			  autoplay: true,
			  autoplaySpeed: 2000,
			  nextArrow:$('.next'),
			  prevArrow:$('.prev'),
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
 
</script>
<div class="page-wrapper" style="position:relative;">
      <!--page slider -->
      <div class="post-slider">
        <h1 class="silder-title">Trending Posts</h1>
        <i class="fas fa-chevron-left prev"></i>
        <i class="fas fa-chevron-right next"></i>
        <div class="post-wrapper">
          <div class="post">
            <img src="img/paris.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#" class="post-subject">Lorem ipsu eiusmod tempor incididunt ut </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/architecture.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#"> Commodo odio aenean sed  </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/paris.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#">Quis hendrerit dolor magna eget est lorem ipsum dolor sit. </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/gyungju.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#">Elit at imperdiet dui accumsan sit.</a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
        </div>
      </div>
      <!--post slider-->
</div>
<div class="page-wrapper2" style="position:relative;">
 <!--post slider2 -->
      <div class="post-slider2">
        <div class="post-wrapper2">
          <div class="post">
            <img src="img/paris.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#" class="post-subject">Lorem ipsu eiusmod tempor incididunt ut </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/architecture.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#"> Commodo odio aenean sed  </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/paris.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#">Quis hendrerit dolor magna eget est lorem ipsum dolor sit. </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/gyungju.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#">Elit at imperdiet dui accumsan sit.</a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
        </div>
      </div>
      <!--post slider-->
</div>

<div class="page-wrapper3" style="position:relative;">
 <!--post slider3 -->
      <div class="post-slider3">
        <div class="post-wrapper3">
          <div class="post">
            <img src="img/paris.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#" class="post-subject">Lorem ipsu eiusmod tempor incididunt ut </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/architecture.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#"> Commodo odio aenean sed  </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/paris.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#">Quis hendrerit dolor magna eget est lorem ipsum dolor sit. </a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
          <div class="post">
            <img src="img/gyungju.jpg" class="slider-image">
            <div class="post-info">
              <h4><a href="#">Elit at imperdiet dui accumsan sit.</a></h4>
              <i class="far fa-user" style="height:10%;">Awa Melvine</i>
            </div>
          </div>
        </div>
      </div>
      <!--post slider-->
</div>