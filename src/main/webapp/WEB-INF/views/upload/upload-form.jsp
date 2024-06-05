<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Insert title here</title>
</head>
<body>
    <h1>파일 업로드 예제</h1>

<%--    자바에서 multipart 클래스로 파일을 받으려면 form태그에 enctype="multipart/form-data" 속성을 넣어주어야 한다.--%>
    <form action="/upload/file" method="post" enctype="multipart/form-data">
    <%-- accept를 통하여 올릴수 있는 파일 형식을 지정할 수 있다.--%>
        <input type="file" name="thumbnail" id="img-input" accept="image/*">
        <button type="submit">전송</button>
    </form>

</body>
</html>