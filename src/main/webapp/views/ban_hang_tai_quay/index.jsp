<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/27/2024
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body class="container">
<div class="card-body" style="background: black; border-radius: 5px">
    <a class="btn btn-light" href="/kich-thuoc/hien-thi">Kich Thuoc</a>
    <a class="btn btn-light" href="/khach-hang/hien-thi">Khach Hang</a>
    <a class="btn btn-light" href="/mau-sac/hien-thi">Mau Sac</a>
    <a class="btn btn-light" href="/san-pham/hien-thi">San Pham</a>
    <a class="btn btn-light" href="/san-pham-chi-tiet/hien-thi">San Pham Chi Tiet</a>
    <a class="btn btn-light" href="/nhan-vien/hien-thi">Nhan Vien</a>
    <a class="btn btn-light" href="/hoa-don/hien-thi">Hoa Don</a>
    <a class="btn btn-light" href="/hoa-don-chi-tiet/hien-thi">Hoa Don Chi Tiet</a>
    <a class="btn btn-light" href="/ban-hang/hien-thi">Ban Hang</a>
    <a class="btn btn-light" href="/login/hien-thi">Dang Xuat</a>
    <a class="btn btn-light" aria-valuetext="">Hello: ${currentUser.ten}</a>
</div>
<div class="row mt-3">
    <div class="col-7">
        <div class="card">
            <h4 class="card-header">Danh sách hoá đơn</h4>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nhan Vien</th>
                    <th>Khach Hang</th>
                    <th>Ngay Mua</th>
                    <th>Trang Thai</th>
                    <th>Chuc Nang</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHD}" var="list">
                    <tr>
                        <td>${list.id}</td>
                        <td>${list.tenNhanVien}</td>
                        <td>${list.tenKhachHang}</td>
                        <td>${list.ngayMuaHang}</td>
                        <td>${list.trangThai == 1 ? "Da Thanh Toan" : "Cho Thanh Toan"}</td>
                        <td>
                            <a class="btn btn-info" href="/ban-hang/hoa-don-detail/${list.id}">Chon</a>
                            <a class="btn btn-info" href="/ban-hang/xoa-hoa-don/${list.id}">Xoa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card mt-2">
            <h4 class="card-header">Danh sách hoá đơn chi tiết</h4>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Hoa Don</th>
                    <th>SPCT</th>
                    <th>So Luong</th>
                    <th>Don Gia</th>
                    <th>Thoi Gian</th>
                    <th>Chuc Nang</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHDCT}" var="list">
                    <tr>
                        <td>${list.id}</td>
                        <td>${list.idHoaDon}</td>
                        <td>${list.tenSP}</td>
                        <td>${list.soLuong}</td>
                        <td>${list.donGia}</td>
                        <td>${list.thoiGian}</td>
                        <td>
                            <a class="btn btn-info" href="/ban-hang/xoa-hoa-don-chi-tiet/${list.id}?idSP=${list.idSP}">Xoa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-5">
        <h2>Tạo hoá đơn</h2>
        <div class="row">
            <div>
                <form action="/ban-hang/tim-theo-sdt" method="get">
                    <div>
                        <label class="col-3">Số điện thoại</label>
                        <input class="form-control" type="text" class="col-7" value="${khDetailByPhone.sdt}"
                               name="timKiem">
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Search</button>
                </form>
            </div>
            <form action="/ban-hang/thanh-toan-hoa-don" method="post">
                <div class="mb-3">
                    <label class="col-3">Ten Khach hang</label>
                    <input type="text" class="form-control" class="col-7" name="tenKhachHang"
                           value="${khDetailByPhone.ten}">
                </div>
                <div class="mb-3">
                    <label class="col-3">ID Hoa don</label>
                    <input type="text" class="form-control" class="col-7"  name="idHoaDon" value="${hoaDonDetail.id}"
                           readonly>
                </div>
                <div>
                    <a href="/ban-hang/tao-hoa-don" class="btn btn-primary">Tạo hoá đơn</a>
                    <button class="btn btn-primary">Thanh toán</button>
                </div>
            </form>
        </div>

    </div>
</div>
<div class="mt-2 card">
    <div class="card-header">
        <h5>Danh Sach San Pham Chi Tiet</h5>
    </div>
    <div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Ma</th>
                <th>Kich Thuoc</th>
                <th>Mau Sac</th>
                <th>San Pham</th>
                <th>So Luong</th>
                <th>Don Gia</th>
                <th>Trang Thai</th>
                <th>Chuc Nang</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listSPCT}" var="list">
                <tr>
                    <td>${list.id}</td>
                    <td>${list.maSPCT}</td>
                    <td>${list.tenKT}</td>
                    <td>${list.tenMS}</td>
                    <td>${list.tenSP}</td>
                    <td>${list.soLuong}</td>
                    <td>${list.donGia}</td>
                    <td>${list.trangThai == 1 ? "Hoat Dong" : "Ngung Hoat Dong"}</td>
                    <td>
                        <a class="btn btn-danger" href="/ban-hang/them-vao-hoa-don-chi-tiet/${list.id}">Them</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
