<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input class="form-control ${(usernameError??)?string('is-invalid','')}" type="text"
                   value="<#if user??>${user.username}</#if>" name="username" placeholder="User name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input class="form-control ${(passwordError??)?string('is-invalid','')}" type="password"
                   name="password" placeholder="Password"/>
            <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
        </#if>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password :</label>
        <div class="col-sm-6">
            <input class="form-control ${(password2Error??)?string('is-invalid','')}" type="password"
                   name="password2" placeholder="Retype password"/>
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email :</label>
        <div class="col-sm-6">
            <input class="form-control ${(emailError??)?string('is-invalid','')}" type="email"
                   value="<#if user??>${user.email}</#if>" name="email" placeholder="some@some.com"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
    <div class="col-sm-5">
        <div class="g-recaptcha" data-sitekey="6LcIFDMaAAAAANl2SWx0QIRXiYdthqvQPPDXomA2"></div>
        <#if captchaError??>
            <div class="alert alert-danger" role="alert">
                ${captchaError}
            </div>
        </#if>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a class="mt-3" href="/registration">Add new user</a></#if>
    <button class="btn btn-primary mt-3" type="submit"><#if isRegisterForm> Create <#else>Sign In</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Sign out</button>
</form>
</#macro>