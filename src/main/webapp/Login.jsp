<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>后台登陆界面</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <!--favicon-->
    <link rel="icon" href="${APP_PATH}/resources/images/Hannibal/jshsxh.png" type="image/x-icon">
    <!-- Bootstrap core CSS-->
    <link href="${APP_PATH}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- animate CSS-->
    <link href="${APP_PATH}/resources/css/animate.css" rel="stylesheet" type="text/css"/>
    <!-- Icons CSS-->
    <link href="${APP_PATH}/resources/css/icons.css" rel="stylesheet" type="text/css"/>
    <!-- Custom Style-->
    <link href="${APP_PATH}/resources/css/app-style.css" rel="stylesheet"/>

</head>

<body>

<!-- start loader -->
<div id="pageloader-overlay" class="visible incoming"><div class="loader-wrapper-outer"><div class="loader-wrapper-inner" ><div class="loader"></div></div></div></div>
<!-- end loader -->

<!-- Start wrapper-->
<div id="wrapper">

    <div class="card-authentication2 mx-auto my-5">
        <div class="card-group">
            <div class="card mb-0">
                <div class="bg-signin2"></div>
                <div class="card-img-overlay rounded-left my-5">
                    <h2 class="text-white">网站链接检查系统</h2>
                    <h1 class="text-white">登陆界面</h1>
                    <h2 class="card-text text-white pt-3">方便快捷<br />真实有效
                    </h2>
                </div>
            </div>

            <div class="card mb-0 ">
                <div class="card-body">
                    <div class="card-content p-3">
                        <div class="text-center">
                            <img src="${APP_PATH}/resources/images/Hannibal/jshsxh.png" alt="logo icon">
                        </div>
                        <div class="card-title text-uppercase text-center py-3">登陆</div>
                        <form>
                            <div class="form-group">
                                <div class="position-relative has-icon-left">
                                    <label for="exampleInputUsername" class="sr-only">用户名</label>
                                    <input type="text" id="exampleInputUsername" class="form-control" placeholder="用户名">
                                    <div class="form-control-position">
                                        <i class="icon-user"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="position-relative has-icon-left">
                                    <label for="exampleInputPassword" class="sr-only">密码</label>
                                    <input type="password" id="exampleInputPassword" class="form-control" placeholder="密码">
                                    <div class="form-control-position">
                                        <i class="icon-lock"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row mr-0 ml-0">
                                <div class="form-group col-6">
                                    <div class="icheck-material-primary">
                                        <input type="checkbox" id="user-checkbox" checked="" />
                                        <label for="user-checkbox">记住密码</label>
                                    </div>
                                </div>
                                <div class="form-group col-6 text-right">
                                    <a href="#">重设密码</a>
                                </div>
                            </div>
                            <a href="${APP_PATH}/indexPage"><button type="button" class="btn btn-primary btn-block waves-effect waves-light">登陆</button></a>
                            <!--<div class="text-center pt-3">

                           <div class="form-row mt-4">
                             <div class="form-group mb-0 col-6">
                              <button type="button" class="btn bg-facebook text-white btn-block"><i class="fa fa-facebook-square"></i> Facebook</button>
                            </div>
                            <div class="form-group mb-0 col-6 text-right">
                             <button type="button" class="btn bg-twitter text-white btn-block"><i class="fa fa-twitter-square"></i> Twitter</button>
                            </div>
                           </div>

                           </div>-->
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--Start Back To Top Button-->
    <a href="javaScript:void(0);" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>
    <!--End Back To Top Button-->


</div><!--wrapper-->

<!-- Bootstrap core JavaScript-->
<script src="${APP_PATH}/resources/js/jquery.min.js"></script>
<script src="${APP_PATH}/resources/js/popper.min.js"></script>
<script src="${APP_PATH}/resources/js/bootstrap.min.js"></script>

<!-- sidebar-menu js -->
<script src="${APP_PATH}/resources/js/sidebar-menu.js"></script>

<!-- Custom scripts -->
<script src="${APP_PATH}/resources/js/app-script.js"></script>

</body>
</html>
