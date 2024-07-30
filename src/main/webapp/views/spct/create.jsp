<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>THEM MOI SAN PHAM</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="container" style="width: 40%">
<div style="text-align: center">
    <h3>THEM MOI SAN PHAM</h3>
</div>
<div>
    <form method="post" action="/san-pham-chi-tiet/store">
        <div>
            <label class="form-label">Mã</label>
            <input class="form-control" type="text" name="maSPCT" value="${data.maSPCT}">
            <c:if test="${not empty errors['maSPCT']}">
                <small style="color:red;">${errors['maSPCT']}</small>
            </c:if>
        </div>
        <div>
            <label class="form-label">San Pham</label>
            <select class="form-control" name="idSanPham" value="${data.idSanPham}">
                <c:forEach items="${listSP}" var="list">
                    <option value="${list.id}">${list.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">Kich Thuoc</label>
            <select class="form-control" name="idKichThuoc" value="${data.idKichThuoc}">
                <c:forEach items="${listKT}" var="list">
                    <option value="${list.id}">${list.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">Mau Sac</label>
            <select class="form-control" name="idMauSac" value="${data.idMauSac}">
                <c:forEach items="${listMS}" var="list">
                    <option value="${list.id}">${list.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label class="form-label">So Luong</label>
            <input class="form-control" type="text" name="soLuong" value="${data.soLuong}">
            <c:if test="${not empty errors['soLuong']}">
                <small style="color:red;">${errors['soLuong']}</small>
            </c:if>
        </div>
        <div>
            <label class="form-label">Don Gia</label>
            <input class="form-control" type="text" name="donGia" value="${data.donGia}">
            <c:if test="${not empty errors['donGia']}">
                <small style="color:red;">${errors['donGia']}</small>
            </c:if>
        </div>
        <div>
            <label class="form-label">Trạng Thái</label>
            <select class="form-control" name="trangThai" value="${data.trangThai}">
                <option value="1"
                >Hoat Dong
                </option>
                <option value="0"
                >Ngung Hoat Dong
                </option>
            </select>
        </div>
        <div class="mt-3" style="text-align: center">
            <button class="btn btn-success" type="submit">Thêm</button>
            <a class="btn btn-danger" href="/san-pham-chi-tiet/hien-thi">Quay Lai</a>
        </div>
    </form>
</div>
</body>
</html>