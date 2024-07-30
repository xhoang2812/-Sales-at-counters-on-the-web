<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="container" style="width: 40%">
<div style="text-align: center">
    <h3>Them Moi Nhan Vien</h3>
</div>
<div>
    <form action="/nhan-vien/store" method="post">
        <div>
            <label class="label-form">Ma</label>
            <input type="text" class="form-control" name="ma" value="${data.ma}">
            <c:if test="${not empty errors.get('ma')}">
                <small style="color:red;">${errors.get('ma')}</small>
            </c:if>
        </div>
        <div>
            <label class="label-form">Ten</label>
            <input type="text" class="form-control" name="ten" value="${data.ten}">
            <c:if test="${not empty errors.get('ten')}">
                <small style="color:red;">${errors.get('ten')}</small>
            </c:if>
        </div>
        <div>
            <label class="label-form">UserName</label>
            <input type="text" class="form-control" name="tenDangNhap" value="${data.tenDangNhap}">
            <c:if test="${not empty errors.get('tenDangNhap')}">
                <small style="color:red;">${errors.get('tenDangNhap')}</small>
            </c:if>
        </div>
        <div>
            <label class="label-form">Password</label>
            <input type="password" class="form-control" name="matKhau" value="${data.matKhau}">
            <c:if test="${not empty errors.get('matKhau')}">
                <small style="color:red;">${errors.get('matKhau')}</small>
            </c:if>
        </div>
        <div>
            <label class="label-form">Trang Thai</label>
            <select class="form-control" name="trangThai">
                <option value="1">Admin</option>
                <option value="0">User</option>
            </select>
        </div>
        <div class="mt-3" style="text-align: center">
            <button class="btn btn-success">Them</button>
            <a class="btn btn-primary" href="/nhan-vien/hien-thi">Quay Lai</a>
        </div>
    </form>
</div>
</body>
</html>
