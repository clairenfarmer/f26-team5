<#assign pageTitle="Create Group Profile - HobbyCircles">
<#include "/shared/head.ftlh">
<#include "/shared/nav.ftlh">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-9 mt-5">
            <div class="card shadow-sm p-4">
                <h3 class="mb-4">Create Your Group Profile</h3>

                <#if error??>
                    <div class="alert alert-danger" role="alert">${error}</div>
                </#if>

                <form action="/register/group" method="post">
                    <h4 class="mb-2">Group Information</h4>
                    <div class="mb-3">
                        <label for="name" class="form-label">Group Name *</label>
                        <input type="text" class="form-control" id="name" name="name" maxlength="100"
                            placeholder="Enter your group name" value="${(form.name)!''}" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email address *</label>
                        <input type="email" class="form-control" id="email" name="email" maxlength="254"
                            value="${(form.email)!''}" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password * (8-72 characters)</label>
                        <input type="password" class="form-control" id="password" name="password"
                            minlength="8" maxlength="72" required>
                    </div>

                    <h4 class="mb-2">Details</h4>
                    <div class="mb-3">
                        <label for="interest" class="form-label">Group Interest *</label>
                        <select class="form-select" id="interest" name="interest" required>
                            <option value="" disabled <#if !(form.interest)??>selected</#if>>Choose an interest</option>
                            <#list interests as i>
                                <option value="${i}" <#if (form.interest)?? && form.interest?string == i?string>selected</#if>>${i}</option>
                            </#list>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-warning">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>
