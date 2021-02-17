<#include "security.ftl">
<#import "pager.ftl" as p>

<@p.pager url page />

<div class="card-columns" id="message-list">
    <#list page.content as messages>
    <div class="card my-3" data-id="${messages.id}">
        <#if messages.filename??>
            <img src="/img/${messages.filename}" class="card-img-top" />
        </#if>
    <div class="m-2">
        <span>${messages.text}</span><br/>
        <i>#${messages.tag}</i>
    </div>
    <div class="card-footer text-muted container">
        <div class="row">
            <a class="col align-self-center"
               href="/user-messages/${messages.authorId}">${messages.authorName}</a>
            <a class="col align-self-center" href="/messages/${messages.id}/like">
                <#if messages.meLiked>
                    <i class="fas fa-heart"></i>
                <#else>
                    <i class="far fa-heart"></i>
                </#if>
            ${messages.likes}
            </a>
            <#if messages.authorId == currentUserId>
                <a class="col btn btn-primary" href="/user-messages/${messages.authorId}?message=${messages.id}">
                    Edit
                </a>
            </#if>
    </div>
</div>
</div>
<#else>
No message
</#list>
</div>

<@p.pager url page />