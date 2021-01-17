<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <@l.logout />
    <span><a href="/user">User list</a></span>
    <div>
        <form method="post">
            <input name="text" type="text" placeholder="Введите сообщение">
            <input name="tag" type="text" placeholder="Тэг">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
        <form method="get" action="/main" value="${filter}">
            <input name="filter" type="text" placeholder="Найти...">
            <button type="submit">Найти</button>
        </form>
    </div>


    <div>Список сообщений</div>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
    <#else>
    No messages
    </#list>
</@c.page>