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
    <h3>Sua Hoa Don</h3>
</div>
<div>
    <form action="/hoa-don/update/${hdDetail.id}" method="post">
        <div>
            <label class="form-label">Nhan Vien</label>
            <select class="form-control" name="idNV">
                <c:forEach items="${listNV}" var="list">
                    <option value="${list.id}" <c:if test="${hdDetail.idNV == list.id}">selected</c:if>
                    >${list.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">Khach Hang</label>
            <select class="form-control" name="idKH">
                <c:forEach items="${listKH}" var="list">
                    <option value="${list.id}" <c:if test="${hdDetail.idKH == list.id}">selected</c:if>
                    >${list.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">Ngay Mua</label>
            <input class="form-control" type="date" name="ngayMuaHang" value="${hdDetail.ngayMuaHang}">
        </div>
        <div>
            <label class="label-form">Trang Thai</label>
            <select class="form-control" name="trangThai">
                <option value="1" <c:if test="${hdDetail.trangThai == 1}">selected</c:if>
                >Da Thanh Toan</option>
                <option value="0" <c:if test="${hdDetail.trangThai == 0}">selected</c:if>
                >Cho Thanh Toan</option>
            </select>
        </div>
        <div class="mt-3" style="text-align: center">
            <button class="btn btn-success">Sua</button>
            <a class="btn btn-primary" href="/hoa-don/hien-thi">Quay Lai</a>
        </div>
    </form>
</div>
</body>
</html>
