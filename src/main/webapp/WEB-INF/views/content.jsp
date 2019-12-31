<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%--<jsp:forward page="/leaders"></jsp:forward>--%>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="icon" href="${APP_PATH}/resources/images/Hannibal/jshsxh.png" type="image/x-icon">
    <!-- simplebar CSS-->
    <link href="${APP_PATH}/resources/plugins/simplebar/css/simplebar.css" rel="stylesheet"/>
    <!-- Bootstrap core CSS-->
    <link href="${APP_PATH}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <!--Data Tables -->
    <link href="${APP_PATH}/resources/plugins/bootstrap-datatable/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="${APP_PATH}/resources/plugins/bootstrap-datatable/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css">
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
<div class="col-12 col-lg-12 col-xl-8">
    <h6 class="mb-0 text-uppercase">数据展示</h6>
    <hr>

    <div class="card">
        <div class="card-body">

            <!--//checktask-->
            <div class="row row-group m-0 border-bottom border-light">
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin" id="rwid1"></h5>
                        <p class="mb-0">任务ID</p>
                        <%--                        checktask--%>
                        <%--                        "JCKSSJ" IS '检查开始时间';--%>
                    </div>
                </div>
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin" id="rwmc2"></h5>
                        <p class="mb-0">任务名称</p>
                        <%--                        "JCJSSJ" IS '检查结束时间';--%>
                        <%--                        checktask--%>
                    </div>
                </div>
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin" id="wzzt3"></h5>
                        <p class="mb-0">网站状态</p>
                        <%--                        checktask--%>
                        <%--                        "URL_YCCS" IS 'url异常次数';--%>

                    </div>
                </div>
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin"id="urlyccs4"></h5>
                        <p class="mb-0">'url异常次数</p>
                        <%--                        check task "YMJZYCCS" IS '页面加载异常次数';--%>
                    </div>
                </div>
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin" id="ymjzyccs5"></h5>
                        <p class="mb-0">页面加载异常次数</p>
                        <%--                        checktask--%>
                        <%--                        "LJCSYCCS" IS '链接超时异常次数';--%>
                    </div>
                </div>

                <!--//linkchecks-->
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin" id="ljcsyccs6"></h5>
                        <p class="mb-0">链接超时异常次数</p>
                        <%--                        COMMENT ON COLUMN "LINKCHECKS"."SFCS" IS '是否超时（0是1否)';--%>
                    </div>
                </div>
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin" id="jckssj7"></h5>
                        <p class="mb-0">检查开始时间</p>
                        <%--                        checktask wzzt--%>
                    </div>
                </div>
                <!--//pagechecks-->
                <div class="col-12 col-lg-4 col-xl-4">
                    <div class="card-body text-center">
                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>
                        <h5 class="mb-0 text-linkedin"id="jcjssj8"></h5>
                        <p class="mb-0">检查结束时间</p>
                        <%--                        COMMENT ON COLUMN "PAGECHECKS"."YJZZT" IS '页加载状态（0正常1异常）';--%>
                    </div>
                </div>
<%--                <div class="col-12 col-lg-4 col-xl-4">--%>
<%--                    <div class="card-body text-center">--%>
<%--                        <i class="zmdi zmdi-linkedin-box text-linkedin fa-2x mb-2"></i>--%>
<%--                        <h5 class="mb-0 text-linkedin" id="ynhls9">9</h5>--%>
<%--                        <p class="mb-0">页内坏链数</p>--%>
<%--                        &lt;%&ndash;                        // COMMENT ON COLUMN "PAGECHECKS"."YNHLS" IS '页内坏链数';&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                </div>--%>
                <%--&lt;%&ndash;                //链接文本&ndash;%&gt;linkchecks--%>
                <%--&lt;%&ndash;                页标题&ndash;%&gt;pagechecks--%>
                <!--//specialurls无
                //task无
                //warns无
            -->

            </div>
        </div>
    </div>
</div>
<a href="javaScript:void(0);" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>
<!--End Back To Top Button-->

<!--Start footer-->

<!--End footer-->


</div><!--End wrapper-->

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



<script type="text/javascript">
    $(function(){
        $.ajax({
            url:"${APP_PATH}/rewriteweb/info",
            type:"GET",
            success:function(result){
                console.log(result);
                build_table(result);

            }
        });
    });

    function build_table(result) {
        var information = result;
        $.each(information,function (index,information) {
            // alert(information.id);
            $("#rwid1").empty();
            $("#rwmc2").empty();
            $("#wzzt3").empty();
            $("#urlyccs4").empty();
            $("#ymjzyccs5").empty();
            $("#ljcsyccs6").empty();
            $("#jckssj7").empty();
            $("#jcjssj8").empty();

            $("#rwid1").append(information.id);
            $("#rwmc2").append(information.rwmc);
            $("#wzzt3").append(information.wzzt);
            $("#urlyccs4").append(information.urlYccs);
            $("#ymjzyccs5").append(information.ymjzyccs);
            $("#ljcsyccs6").append(information.ljcsyccs);
            $("#jckssj7").append(information.jckssj);
            $("#jcjssj8").append(information.jcjssj);
            // $("#ynhls9").append(information.id);
        })

    }


</script>



</div>
</body>
</html>
