<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>UbicaMovil</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container center-align"> 
            <br>
            <br>
            <br>
            <br>
            <div class="row">
                <div class="col l12 s12">
                    <img src="<%= request.getContextPath() %>/wwwroot/img/logo.png" style="width:100px; height:100px;" />
                    <h2><strong>UbicaMóvil</strong></h2> 
                </div>
            </div>
           </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>