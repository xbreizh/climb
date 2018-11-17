<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<%@include file="../../_include/header.jsp" %>

<body>

    <div class="row text-white">
        <div class="col-sm-6 col-md-offset-3 text-center">
            <h1 class="display-3">Password Reset</h1>
            <div class="info-form">
                <form action="pwd_reset" method="POST" class=" justify-content-center">
                    <div class="form-group">
                        <label class="sr-only">Name</label>
                        <input name="login" type="text" class="form-control" placeholder="Login">
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Email</label>
                        <input name="email" type="email" class="form-control" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Name</label>
                        <input name="password" type="text" class="form-control" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label class="sr-only">Name</label>
                        <input name="passwordCheck" type="text" class="form-control" placeholder="Repeat Password">
                    </div>
                    <button type="submit" class="btn btn-success">okay, go!</button>
                </form>
            </div>
            <br>

            <r:a class="btn" action="member_new">Become Member</r:a>
        </div>
    </div>

</body>
</html>