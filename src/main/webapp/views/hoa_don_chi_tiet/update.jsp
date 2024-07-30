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
    <h3>Sua Hoa Don Chi Tiet</h3>
</div>
<div>
    <form action="/hoa-don-chi-tiet/update/${hdctDetail.id}" method="post">
        <div>
            <label class="form-label">Hoa Don</label>
            <select class="form-control" name="idHoaDon">
                <c:forEach items="${listHD}" var="list">
                    <option value="${list.id}" <c:if test="${hdctDetail.idHoaDon == list.id}">selected</c:if>
                    >${list.id}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">SPCT</label>
            <select class="form-control" name="idSPCT">
                <c:forEach items="${listSPCT}" var="list">
                    <option value="${list.id}" <c:if test="${hdctDetail.idSPCT == list.id}">selected</c:if>
                    >${list.tenSP}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">So Luong</label>
            <input class="form-control" type="text" name="soLuong" value="${hdctDetail.soLuong}">
            <c:if test="${not empty errors.get('soLuong')}">
                <small style="color: red">${ errors.get('soLuong')}</small>
            </c:if>
        </div>
        <div>
            <label class="form-label">Don Gia</label>
            <input class="form-control" type="text" name="donGia" value="${hdctDetail.donGia}">
            <c:if test="${not empty errors.get('donGia')}">
                <small style="color: red">${ errors.get('donGia')}</small>
            </c:if>
        </div>
        <div>
            <label class="form-label">Ngay Mua</label>
            <input class="form-control" type="date" name="thoiGian" value="${hdctDetail.thoiGian}">
        </div>
        <div>
            <label class="label-form">Trang Thai</label>
            <select class="form-control" name="trangThai">
                <option value="1" <c:if test="${hdctDetail.trangThai == 1}">selected</c:if>
                >Hoat Dong</option>
                <option value="0" <c:if test="${hdctDetail.trangThai == 0}">selected</c:if>
                >Ngung Hoat Dong</option>
            </select>
        </div>
        <div class="mt-3" style="text-align: center">
            <button class="btn btn-success">Sua</button>
            <a class="btn btn-primary" href="/hoa-don-chi-tiet/hien-thi">Quay Lai</a>
        </div>
    </form>
</div>
</body>
</html>
