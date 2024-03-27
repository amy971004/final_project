<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모달창연습</title>
    <style>
        .modal{
            position:absolute;
            display:none;

            justify-content: center;
            top:0;
            left:0;

            width:100%;
            height:100%;



            background-color: rgba(0,0,0,0.4);
        }

        .modal_body {
            position:absolute;
            top:50%;


            width:400px;
            height:600px;

            padding:40px;

            text-align: center;

            background-color: rgb(255,255,255);
            border-radius:10px;
            box-shadow:0 2px 3px 0 rgba(34,36,38,0.15);

            transform:translateY(-50%);
        }
    </style>
</head>
<body>
<div class="modal">
    <div class="modal_body">
        <h2>모달창 제목</h2>
        <p>모달창 내용 </p>
    </div>
</div>
<button class="btn-open-modal">Modal열기</button>
<script>
    const modal = document.querySelector('.modal');
    const btnOpenModal=document.querySelector('.btn-open-modal');

    btnOpenModal.addEventListener("click", ()=>{
        modal.style.display="flex";
    });
</script>
</body>
</html>
