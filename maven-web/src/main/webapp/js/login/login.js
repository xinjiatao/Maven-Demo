//LOGIN js
$(function() {
	$(".btn").click(function(){
	    is_hide();
	});
	
	window.onload = function()
	{
	    var error = $("#error").val() ;
	    if(error != ""){
	        $("#ts").html("用户名或密码错误！");
	        is_show();
	    }
	    $(".connect p").eq(0).animate({"left":"0%"}, 600);
	    $(".connect p").eq(1).animate({"left":"0%"}, 400);
	};
	
	login();
	
	//=================================================//
	var u = $("input[name=username]");
	var p = $("input[name=password]");
//	$("#submit").live('click',function(){
//	    if(u.val() == '' || p.val() =='')
//	    {
//	        $("#ts").html("用户名或密码不能为空~");
//	        is_show();
//	        return false;
//	    }
//	    else{
//	        var reg = /^[0-9A-Za-z]+$/;
//	        if(!reg.exec(u.val()))
//	        {
//	            $("#ts").html("用户名错误");
//	            is_show();
//	            return false;
//	        }
//	    }
//	});
	

});

function is_hide(){ $(".alert").animate({"top":"-40%"}, 300); };
function is_show(){ $(".alert").show().animate({"top":"45%"}, 300); };


function login(){
	//登录操作
	$('#login').click(function(){
		
	    var username = $('.username').val();
	    var password = $('.password').val();
	    if(username == '') {
	    	$("#ts").html("用户名或密码不能为空~");
	    	 is_show();
	        return false;
	    }
	    if(password == '') {
	    	$("#ts").html("用户名或密码不能为空~");
	    	 is_show();
	        return false;
	    }
	    var pswd = MD5(username +"#" + password),
	    data = {pswd:pswd,email:username,rememberMe:$("#rememberMe").is(':checked')};
	    //var load = layer.load();
	    $.post($.xjt.contextPath + "/home/submitLogin.do",data ,function(result){
	    	//layer.close(load);
			if(result && result.status != 200){
				//layer.msg(result.message,function(){});
				$("#ts").html("用户名或密码不正确~");
				$('.password').val('');
				 is_show();
				return;
			}else{
				//layer.msg('登录成功！');
				setTimeout(function(){
					alert("登录成功！");
					//登录返回
	    			//window.location.href= result.back_url || "${basePath}/";
				},1000);
			}
		},"json");
	    
	});
}

