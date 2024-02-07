<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body{
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .loader {
            width: 480px;
            height: 480px;
            border-radius: 50%;
            display: block;
            position: relative;
            animation: rotate 1s linear infinite
        }
        .loader::before , .loader::after {
            content: "";
            width: 100%;
            height: 100%;
            box-sizing: border-box;
            position: absolute;
            inset: 0px;
            border-radius: 50%;
            border: 5px solid dodgerblue;
            animation: prixClipFix 2s linear infinite ;
        }
        .loader::after{
            inset: 8px;
            width: 100%;
            height: 100%;
            transform: rotate3d(90, 90, 0, 180deg );
            border-color: #FF3D00;
        }

        @keyframes rotate {
            0%   {transform: rotate(0deg)}
            100%   {transform: rotate(360deg)}
        }

        @keyframes prixClipFix {
            0%   {clip-path:polygon(50% 50%,0 0,0 0,0 0,0 0,0 0)}
            50%  {clip-path:polygon(50% 50%,0 0,100% 0,100% 0,100% 0,100% 0)}
            75%, 100%  {clip-path:polygon(50% 50%,0 0,100% 0,100% 100%,100% 100%,100% 100%)}
        }
    </style>
</head>
<body>
<span class="loader"></span>
<script>
    setTimeout(function (){
        window.location.href='AfficherArticlesServlet';
    }, 3000);
</script>
</body>
</html>