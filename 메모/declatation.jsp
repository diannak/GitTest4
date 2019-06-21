<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <%!
    int i = 10;
    String str = "ABCDE";
    private int sum (int a, int b){
    return a+b;
    }
    /%>
    
    <%
    out.println(" i="+i+"<br>");
      out.println(" str="+str+"<br>");
        out.println(" sum="+sum(1,5)+"<br>");
    %>
    
    
</body>
</html>