<#import "parts/common.ftl" as c>
<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main" value="${filter?ifExists}">
            <input class="form-control" name="filter" type="text" placeholder="Найти по тэгу...">
            <button class="btn btn-primary ml-2" type="submit">Найти</button>
        </form>
    </div>
</div>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group mt-3">
                <input class="form-control" name="text" type="text" placeholder="Введите сообщение">
            </div>
            <div class="form-group">
                <input class="form-control" name="tag" type="text" placeholder="Тэг">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input name="file" type="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group">
                <button class="btn btn-primary mb-2" type="submit">Добавить</button>
            </div>
        </form>
    </div>
</div>
<div class="card-columns">
<#list messages as message>
<div class="card my-3">
        <#if message.filename??>
            <img class="card-img-top" src="/img/${message.filename}">
        </#if>
    <div class="m-2">
        <span>${message.text}</span>
        <i>${message.tag}</i>
    </div>
    <div class="card-footer text-muted">
        <strong>${message.authorName}</strong>
    </div>
</div>

<#else>
No messages
</#list>
</div>
</@c.page>