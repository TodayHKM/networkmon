<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>检查链接</title>
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
<div class="col-lg-12">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">数据展示</h5>
            <button type="button" class="btn btn-success waves-effect waves-light m-1" id="openCrawler">开启所有爬虫</button>
            <button type="button" class="btn btn-danger waves-effect waves-light m-1"id="closeCrawler">关闭所有爬虫</button>
            <div class="table-responsive">
                <table class="table table-hover" id="linkchecks_table">
                    <thead>
                    <tr>
<%--                        <th scope="col">链接ID</th>--%>
                        <th scope="col">链接地址</th>
                        <th scope="col">链接文本</th>
                        <th scope="col">链接状态</th>
                        <th scope="col">状态信息</th>
                    </tr>
                    </thead>
                    <tbody id="infotbody">
                    <tr id="infotr">
<%--                        <td scope="row" id="ljid">1</td>--%>
<%--                        <td id="ljdz"></td>--%>
<%--                        <td id="ljwb"></td>--%>
<%--                        <td id="ljzt"></td>--%>
<%--                        <td id="ztxx"></td>--%>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div><!--End Row-->
<%--<td style="display:none">--%>
<%--    <button type="button" class="btn btn-success waves-effect waves-light btn-sm"  id="alert-success"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">点击我</font></font></button>--%>
<%--</td>--%>

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



<script type="text/javascript">
    $(function(){
        $.ajax({
            url:"${APP_PATH}/rewriteweb/linkchecksinfo",

            type:"GET",
            success:function(result){
                console.log(result);
                build_table(result);

            }
        });
    });

    function build_table(result) {
        // $("#linkchecks_table").empty();
        var information = result;
        $.each(information,function (index,item) {
            var ljdz = $("<td></td>").append(item.ljUrl);
            var ljwb = $("<td></td>").append(item.ljwb);
            var ljzt = $("<td></td>").append(item.zt=='0'?"正常":"异常");
            var ztxx = $("<td></td>").append(item.ztxx);

            $("<tr></tr>").append(ljdz)
                .append(ljwb)
                .append(ljzt)
                .append(ztxx)
                // $("#infotbody").append("#infotr")
                .appendTo("#linkchecks_table tbody");

        });

    }
    $("#openCrawler").click(function () {
        $.ajax({
            url:"${APP_PATH}/rewriteweb/updateTask0",
            type:"POST",
            dateType:"json",
            success:function(){
               alert("开启成功")
                // build_table(result);

            }
        });
    });

    $("#closeCrawler").click(function () {
        $.ajax({
            url:"${APP_PATH}/rewriteweb/updateTask1",
            type:"POST",
            dateType:"json",
            success:function(){
                alert("关闭成功")
                // build_table(result);

            }
        });
    });


</script>

</body>

</html>