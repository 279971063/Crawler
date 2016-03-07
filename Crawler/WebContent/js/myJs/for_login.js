//改变验证码图片
		function changeImage(){
			document.getElementById("ch").disabled=true;
			//document.getElementById("changedimage").src="/RestrantAffair/GetKeyServlet?ts="+new Date().getTime();
		}
		//jquery验证表单
		$(document).ready(function(){
			 $(":submit[id=submit]").click(function (check) {
            var val = $(":text[id=name]").val();
            if (val == "") {
              	$("#failure").html("用户名不能为空");
                $(":text[id=Name]").focus();
                check.preventDefault(); 
            }
            if($(":password[id=password]").val()==""){
            	$("#failure").html("密码不能为空");
                $(":text[id=password]").focus();
                check.preventDefault(); //此处阻止提交表单  
            }
        });
        	$("#dialog").dialog({
        		autoOpen:false,
        		closeOnEscape: true,//按ESC键时，是否可关闭dialog  true为可关闭，false为不可关闭
					draggable: true,//是否可拖动
					buttons: {"确定":function(){$("#reg").submit();},"取消":function(){$(this).dialog("close");}},//添加按钮
					height: 320,
					width: 400,
					show: "scale",//打开dialog的效果
					hide: "scale",//关闭dialog的效果
					modal: true,//true表示为模式dialog
					position: "center",//显示dialog的位置
					resizable: true,//是否可调整大小
					title: "我要注册",
					bgiframe:true
        	});
        	$("#register").click(function(){
        		$("#dialog").dialog("open");
        	});
        	$( "#birthday" ).datepicker({ dateFormat: 'yy-m-d' });
		});