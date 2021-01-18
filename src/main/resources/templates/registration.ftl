<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
${message?ifExists}
<div class="mb-1">Registration page</div>
<@l.login "/registration" true />
</@c.page>