<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
     Message editor
</a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group mt-3">
                <input class="form-control ${(textError??)?string('is-invalid','')}"
                       value="<#if message??>${message.text}</#if>" name="text" type="text" placeholder="Введите сообщение"/>
                <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
    </div>
    <div class="form-group">
        <input class="form-control "
               value="<#if message??>${message.tag}</#if>" name="tag" type="text" placeholder="Тэг"/>
        <#if tagError??>
        <div class="invalid-feedback">
            ${tagError}
        </div>
    </#if>
</div>
<div class="form-group">
    <div class="custom-file">
        <input name="file" type="file" id="customFile"/>
        <label class="custom-file-label" for="customFile">Choose file</label>
    </div>
</div>
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
<div class="form-group">
    <button class="btn btn-primary mb-2" type="submit">Save message</button>
</div>
</form>
</div>
</div>