<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.appweb.utils.Utilidad"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container center-align"> 
            <div class="row">
                <div class="col l12 s12">
                    <img src="<%= request.getContextPath() %>/wwwroot/img/logo.png" style="width:60px; height:60px;" />
                    <h1>UbicaMóvil</h1> 
                </div>
            </div>            
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>