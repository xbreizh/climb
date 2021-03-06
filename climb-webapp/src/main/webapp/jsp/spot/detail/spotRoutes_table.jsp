<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="Routes" ></div>
<br><br><br><br><br><br><br><br><br><br><br><br>
<s:if test="spot.routeList.size() > 0">
    <h2>Routes</h2>
    <div class="info">
        <p>
            The best way to identify routes is with a photo or a schema.<br>
            While we work on implementing a better solution, please send us your document at<br>
            <a href="mail:<s:text name="contact.email"/>"><s:text name="contact.email"/></a>
        </p>
    </div>
        <table class="table  table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Name </th>
                <th>Height </th>
                <th>Grade</th>
                <th>Creator</th>
                <th>Comment(s)</th>
            </tr>
            </thead>
            <tbody>
    <s:iterator value="%{routeList}">
            <tr>
                <td>
                    <s:a action="route_detail" style="display:block;text-decoration:none;" >
                        <button type="button" class="btn btn-light">Details</button>
                        <s:param name="id" value="id"/>
                    </s:a>
                </td>
                <td><s:a action="route_detail" style="display:block;text-decoration:none;" >
                    <s:property value="name" escapeHtml="false"/>
                    <s:param name="id" value="id"/>
                    </s:a>
                </td>
                <td><s:a action="route_detail" style="display:block;text-decoration:none;" >
                    <s:property value="height" escapeHtml="false"/>
                    <s:param name="id" value="id"/>
                    </s:a>
                </td>
                <td><s:a action="route_detail" style="display:block;text-decoration:none;" >
                    <s:property value="grade" escapeHtml="false"/>
                    <s:param name="id" value="id"/>
                </s:a>
                </td>
                <td><s:a action="member_detail" style="display:block;text-decoration:none;" >
                    <s:property value="memberRoute.login" escapeHtml="false"/>
                    <s:param name="id" value="memberRoute.id"/>
                </s:a>
                </td>
                <td>
                    <s:a action="route_detail" style="display:block;text-decoration:none;" >
                        <button type="button" class="btn btn-primary">Comments</button>
                        <s:param name="id" value="id"/>
                    </s:a>
                </td>
                <s:if test="session.user.role == 'superadmin' || session.user.id == route.memberRoute.id">
                    <td>
                        <s:form action="route_update" method="POST">
                            <s:hidden name="route" value="%{id}"  requiredLabel="true"/>
                            <s:submit class="btn btn-warning" value="Edit"/>
                        </s:form>
                    </td>
                    <td>
                        <s:form action="route_delete" method="POST">
                            <s:hidden name="route" value="%{id}"  requiredLabel="true"/>
                            <s:submit class="btn btn-danger" value="Delete"/>
                        </s:form>

                    </td>
                </s:if>
            </tr>
    <tr>
        <td  colspan="8">
            <div id="${id}" style="display: none">
                <s:form style="display: inline;" action="createCommentRoute" method="POST">
                    <s:hidden name="comment.route.id" value="%{id}"/>
                    <s:hidden name="comment.memberComment.id" value="%{session.user.id}" label="Member:"/>
                    <s:textarea name="comment.text" placeholder="New Comment"  requiredLabel="true" onclick=""/>
                    <s:submit class="btn btn-success" value="Comment"/>

                </s:form>
                <s:if test="%{commentList.size()>0}">
                    <s:a action="route_detail" style="display:block;text-decoration:none;" >
                        See <s:property value="%{commentList.size()}"/> comments
                        <s:param name="id" value="id"/>
                    </s:a>
                </s:if><s:else>
                Be the first to comment!
            </s:else>
            </div>
        </td>
    </tr>
    </s:iterator>
            </tbody>
        </table>

</s:if>
<s:else>
    <h2> No route yet for this Spot </h2>
</s:else>
<s:if test="#session.user">
    <s:a action="createRoute">
        <s:param name="id" value="id"/>
        <button type="button" class="btn btn-warning">New Route</button>
    </s:a>
</s:if>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>