<%@ taglib prefix="s" uri="/struts-tags" %>



<%@include file="../_include/header.jsp" %>

<s:if test="#session.user.role =='superadmin'">
<h2>Comments</h2><br>

<br><br>


    <%@include file="menu_all_comments.jsp" %>
    <%@include file="commentRouteTable.jsp" %>
    <%@include file="commentSpotTable.jsp" %>


</s:if>
<s:else>

    <img alt="Access forbidden!" style=" max-width: 50%; height: auto;" src=" https://previews.123rf.com/images/corund/corund1511/corund151100022/48297160-access-denied-sign-clipping-path-included.jpg">

</s:else>
<%@include file="../_include/footer.jsp" %>