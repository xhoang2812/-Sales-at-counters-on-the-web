<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CHI TIET SAN PHAM</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="container" style="width: 40%">
<div style="text-align: center">
    <h3>CHI TIET SAN PHAM</h3>
</div>
<div>
    <form method="post" action="/san-pham/update/${sanPhamDetail.id}">
        <div>
            <label class="form-label">Mã</label>
            <input class="form-control" type="text" name="ma" value="${sanPhamDetail.ma}" >
            <c:if test="${not empty errors['ma']}">
                <small style="color:red;">${errors.get('ma')}</small>
            </c:if>
        </div>
        <div>
            <label class="form-label">Tên</label>
            <input class="form-control" type="text" name="ten" value="${sanPhamDetail.ten}">
            <c:if test="${not empty errors['ten']}">
                <small style="color:red;">${errors['ten']}</small>
            </c:if>
        </div>
        <div>

            <label class="form-label">Trạng Thái</label>
            <select class="form-control" name="trangThai" value="${sanPhamDetail.trangThai}">
                <option value="1"  <c:if test="${sanPhamDetail.trangThai == 1}">selected</c:if>>Con Hang</option>
                <option value="0"  <c:if test="${sanPhamDetail.trangThai == 0}">selected</c:if> >Het Hang</option>
            </select>
        </div>
        <div class="mt-3" style="text-align: center">
            <button class="btn btn-success" type="submit">Sua</button>
            <a class="btn btn-danger" href="/san-pham/hien-thi">Quay Lai</a>
        </div>
    </form>
</div>
</body>
</html>