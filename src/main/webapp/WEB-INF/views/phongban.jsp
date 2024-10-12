<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Phong Ban</title>
</head>
<body>
<table>

    <thead>
    <tr>
        <th>ID</th>
        <th>Mã</th>
        <th>Phòng Ban</th>
        <th>Số Lượng Phòng Ban</th>
        <th>Số Lượng Nhân Viên</th>
        <th>Trạng Thái</th>
        <th>Tên Loại Phòng Ban</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${phongban}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.ma}</td>
            <td>${item.ten}</td>
            <td>${item.soluongPB}</td>
            <td>${item.soluongNV}</td>
            <td>${item.loaiPhongBan.trangThai}</td>
            <td>${item.loaiPhongBan.tenloaiPB}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>