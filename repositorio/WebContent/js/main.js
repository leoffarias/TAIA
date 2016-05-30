$(document).ready(function(){
	$('.slide').slick({
		infinite: true,
		slidesToShow: 3,
		slidesToScroll: 1,
		responsive: [
		             {
		               breakpoint: 1000,
		               settings: {
		                 slidesToShow: 1,
		               }
		             }
		             ]
	});
});