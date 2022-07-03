$(function(){
    $('.goods-list li').hover(function(){
         $(this).addClass('hover')
         $(this).find('.btn').html('加入购物车');
         $(this).find('.btn').addClass('active');
    },function(){
        $(this).removeClass('hover')
        $(this).find('.btn').html('兑换');
        $(this).find('.btn').removeClass('active');

    })

    $('.classify span').click(function(e){
        if($(".dropdown").is(':hidden')){
                $('.dropdown').show()
            }else{
                $('.dropdown').hide()
           }
         $(document).on('click', function () {
             $('.dropdown').hide();

         });
         e.stopPropagation();
       })

    $('.dropdown a').click(function(){
        var val = $(this).text()
        $('.val').text(val)
        $('.dropdown').hide()
    })


	//点击加入购物车按钮
	$('.goods-list li').click(function(){
		$(".gift_details").show()
	})
	//点击礼品详情的关闭按钮
	$('.gift_details .icon_close').click(function(){
		$(".gift_details").hide()
	})


	//循环遍历小图
	function disPlaySmallImg(){
		var imgs = ['images/1.png','images/duihuan-bg.png','images/mbot-hover.png','images/4.png','images/5.png'];
		for(var i = 0;i<imgs.length;i++){
			var content = '<div class="sm_images">'+'<img src="'+imgs[i]+'"'+ 'title="这是图片"'+'/>'
			$('.sm_container').append(content)
		}
		$('.sm_container').children(':first').addClass('selected')
	}
	disPlaySmallImg();

	//点击礼品详情的小图
	$(".sm_container .sm_images").click(function(){
		$('.sm_container .sm_images').removeClass('selected');
		$(this).addClass("selected");
		var img_src = $(this).find('img').attr('src')
		$(".big_images").find('img').attr('src',img_src)
	});

	//点击用户信息提示
	$('.userbar .arrow-bt').click(function(){
		if($('.userbar .jf_tip').css('display')=='block'){
			$(".userbar .jf_tip").hide()
		}else{
			$(".userbar .jf_tip").show()
		}
	})

	//点击兑换,价格,积分产生的sort函数
	 $(".sort .icon-sort").click(function(){
         if((!$(this).is('.desc')) && (!$(this).is('.asc'))){
			 $(this).addClass('asc');
		 }else if($(this).is('.desc')){
			 $(this).removeClass('desc')
			 $(this).addClass('asc')
		 }else if($(this).is('.asc')){
			 $(this).removeClass('asc')
			 $(this).addClass('desc')
		 }
	 })
	 
	 //点击去结算按钮触发的事件
	 $(".convert_details .js_btn").click(function(){
		 $(".conversion_alert").show();
	 })
	 $(".alert_opt .sure").click(function(){//确认结算
		 $('.conversion_alert').hide();
	 })
	 $(".alert_opt .cancel").click(function(){//取消
		 $('.conversion_alert').hide();
	 })
	 
	 
	 //点击身份证出现的事件
	 $("#indentify_search").click(function(){
		$(".jf_alert").show()
	 })
	 $(".jf_alert .cancel").click(function(){
		 $(".jf_alert").hide()
	 })
	  $(".jf_alert .sure").click(function(){
	 		 $(".jf_alert").hide()
	 })
})