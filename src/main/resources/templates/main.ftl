<#import "parts/common.ftl" as c>
<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main" value="${filter?ifExists}">
            <input class="form-control" name="filter" type="text" placeholder="Find by tag..."/>
            <button class="btn btn-primary ml-2" type="submit">Find</button>
        </form>
    </div>
</div>

<#include "parts/messageEdit.ftl"/>

<#include "parts/messageList.ftl"/>

</@c.page>