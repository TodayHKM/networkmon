<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>数据展示</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <!--favicon-->
    <link rel="icon" href="${APP_PATH}/resources/images/favicon.ico" type="image/x-icon"/>
    <!-- simplebar CSS-->
    <link href="${APP_PATH}/resources/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
    <!-- Bootstrap core CSS-->
    <link href="${APP_PATH}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- animate CSS-->
    <link href="${APP_PATH}/resources/css/animate.css" rel="stylesheet" type="text/css"/>
    <!-- Icons CSS-->
    <link href="${APP_PATH}/resources/css/icons.css" rel="stylesheet" type="text/css"/>
    <!-- Sidebar CSS-->
    <link href="${APP_PATH}/resources/css/sidebar-menu.css" rel="stylesheet"/>
    <!-- Custom Style-->
    <link href="${APP_PATH}/resources/css/app-style.css" rel="stylesheet"/>

</head>

<body>
<div id="wrapper">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header text-uppercase">输入任务名称</div>
                <div class="card-body">
                    <form>
                        <div class="form-group row">
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="rwinput">
                            </div>
                            <!--<button type="button" class="btn btn-primary btn-lg btn-block waves-effect waves-light m-1 col-sm-2 ">测试您的网站</button>-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="card">
<%--                <div class="card-header text-uppercase">输入网站链接</div>--%>
                <div class="card-header text-uppercase">输入网站链接</div>
                <div class="card-body">
                    <form>
                        <div class="form-group row">
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="ljinput">
                            </div>
                            <a href="${APP_PATH}/ljwb"><button type="button" class="btn btn-primary btn-lg btn-block waves-effect waves-light m-1 col-sm-2 "id="updateurl">测试您的网站</button></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div><!--End Row-->
</div>
<!-- Bootstrap core JavaScript-->
<script src="${APP_PATH}/resources/js/jquery.min.js"></script>
<script src="${APP_PATH}/resources/js/popper.min.js"></script>
<script src="${APP_PATH}/resources/js/bootstrap.min.js"></script>

<!-- simplebar js -->
<script src="${APP_PATH}/resources/plugins/simplebar/js/simplebar.js"></script>
<!-- sidebar-menu js -->
<script src="${APP_PATH}/resources/js/sidebar-menu.js"></script>

<!-- Custom scripts -->
<script src="${APP_PATH}/resources/js/app-script.js"></script>

<!--Data Tables js-->
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/jquery.dataTables.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/dataTables.bootstrap4.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/dataTables.buttons.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/buttons.bootstrap4.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/jszip.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/pdfmake.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/vfs_fonts.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/buttons.html5.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/buttons.print.min.js"></script>
<script src="${APP_PATH}/resources/plugins/bootstrap-datatable/js/buttons.colVis.min.js"></script>
<script charset="utf-8" src="${APP_PATH}/resources/plugins/alerts-boxes/js/sweetalert.min.js"></script>
<script charset="utf-8" src="${APP_PATH}/resources/plugins/alerts-boxes/js/sweet-alert-script.js"></script>


<script>

$("#updateurl").click(function () {
    var ljinput =  $("#ljinput").val();
    var rwinput =  $("#rwinput").val();
    $.ajax({
        url:"${APP_PATH}/rewriteweb/insertTask",
        data: {'ljinput':ljinput,'rwinput':rwinput},
        type:"POST",
        dateType:"json",
        success:function(){
            console.log("success");
            // build_table(result);

        }
    });
});

// 南京晓庄
// http://www.njxzc.edu.cn/
</script>
</body>

</html>