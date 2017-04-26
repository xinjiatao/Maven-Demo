//HOME js
$(function() {
	getTableData(1);
	
});
var pageSize = 10;

/**
 * 拉取分页数据
 * @param pageNum 页码
 */
function getTableData(pageNum){
    $.ajax({
        url : $.xjt.contextPath + "/home/selectTableData.do",
        type : "get",
        data : {"page" : pageNum, "rows" : pageSize},
        dataType : "json",
        success : function(data) {
            $("#total_count").text(data.totalCount);
            var list = data.rows;
            var html = "";
            for (var i in list) {
                if (i % 2 == 0) {
                    html += "<div class=\"info_list_type1\">";
                } else {
                    html += "<div class=\"info_list_type2\">";
                }
                html +=
                    "<div class=\"info_list_content_s6\">" + list[i].id + "</div>" +
                    "<div class=\"info_list_content_s4\">" + list[i].nickname + "</div>" +
                    "<div class=\"info_list_content_s3\">" + list[i].email + "</div>" +
                    "<div class=\"info_list_content_s1\">" + list[i].create_time + "</div>" +
                    "<div class=\"info_list_content_s2\">" + list[i].last_login_time + "</div>" +
                    "<div class=\"info_list_content_s5\">" + list[i].status + "</div>" +
                    "</div>";
            }
            $("#infoList").html(html);
            // 调用分页插件
            $("#paging").smartpaginator({
                totalrecords : data.totalCount,
                recordsperpage : pageSize,
                length : 5,
                next : '下一页',
                prev : '上一页',
                first : '首页',
                last : '尾页',
                go : '确定',
                initval : pageNum,
                onchange : function(pageNum) {
                	getTableData(pageNum);
                },
                hash : ""
            });
        }
    });
	
}

function doLogin() {
    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    
    var data = {
            "username" : username,
            "password" : password
        };
    
    $.ajax({
        type:"POST",
        url:$.xjt.contextPath + "/home/subRegister.do",
        data:data,
        success:function(data){
            alert("登录成 功");
        },
        error:function(e) {
            alert("失 败"+e);
        }
    });
}

function doTable(){
    var data = {
            "id" : 10
        };
    $.ajax({
        type:"POST",
        url:$.xjt.contextPath + "/home/selectTableData.do",
        data:data,
        success:function(data){
            alert("成 功");
        },
        error:function(e) {
            alert("失 败"+e);
        }
    });
}