<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<%@include file="../../_include/header.jsp" %>

<body>
<%--<h2>Création d'un projet</h2>

    <s:form action="projet_new">
        <s:textfield name="projet.nom" label="Nom" requiredLabel="true"/>
        <s:select name="projet.responsable.id" label="Responsable"
                  list="listUtilisateur" listKey="id" listValue="prenom"
                  emptyOption="true"
                  requiredLabel="true"/>
        <s:checkbox name="projet.cloture" label="Cloture"/>
        <s:submit value="OK"/>
    </s:form>--%>
</body>
</html>
