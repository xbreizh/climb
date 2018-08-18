<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../_include/head.jsp"%>
</head>
<body><h1>Liste des projets</h1>

<s:a action="projet_new">Créer un nouveau projet</s:a>
<s:iterator value="listProjet">
    <li>
        <s:a action="projet_detail" >
        <s:property value="nom"/>
            <s:param name="id" value="id"/>
        </s:a>
        Responsable
        <s:a action="utilisateur_detail">
            <s:param name="id" value="responsable.id" />
            <s:property value="responsable.prenom"/> <s:property value="responsable.nom"/>
        </s:a>
    </li>
</s:iterator>
</body>
</html>
