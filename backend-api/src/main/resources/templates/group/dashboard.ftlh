<#assign pageTitle="Dashboard - HobbyCircles">
<#include "/shared/head.ftlh">
<#include "/shared/nav.ftlh">

<!-- Main header -->
<section class="section">
    <div class="container">
        <div class="dashboard-header">
            <div class="dashboard-title">Welcome, ${group.name}!</div>
            <div class="dashbaord-subtitle">Manage your events and view your group's details</div>
        </div>
    </div>
</section>

<!-- Events -->
<section class="section">
    <div class="container">
        <h2>Upcoming Events</h2>

        <#if events?has_content>
            <#list events as event>
                <div class="event-item">
                    <div class="event-header">
                        <div>
                            <div class="event-title">${event.name}</div>
                        </div>
                    </div>
                    <div class="event-details">
                        <div class="detail-item"><span class="detail-label">Time:</span> ${event.time!""}</div>
                        <div class="detail-item"><span class="detail-label">Location:</span> ${event.location!""}</div>
                        <div class="detail-item"><span class="detail-label">Status:</span> ${event.status!""}</div>
                        <div class="detail-item"><span class="detail-label">RSVPs:</span> ${event.rsvpCount}</div>
                    </div>
                </div>
            </#list>
        <#else>
            <p>You haven't created any events yet.</p>
        </#if>
    </div>
</section>

<!-- Comments and Questions -->
<div class="container">
    <div class="card mt-4">
        <div class="card-header">
            <h2 style="font-size: 1.3rem; font-weight: 700;">Questions and Comments</h2>
        </div>
        <div class="card-content">
            <#if comments?has_content>
                <#list comments as comment>
                    <div style="padding: 1.5rem; border-bottom: 1px solid var(--light-gray);">
                        <p style="font-weight: 600;">${comment.individualName!""}</p>
                        <p style="color: #666; line-height: 1.5;">${comment.message!""}</p>
                        <#if comment.replyMessage?? && comment.replyMessage?has_content>
                            <p style="font-weight: 600;">${comment.hobbyGroupName}</p>
                            <p style="color: #666; line-height: 1.5;">${comment.replyMessage}</p>
                        </#if>
                    </div>
                </#list>
            <#else>
                <p class="p-4 mb-0">No questions or comments yet.</p>
            </#if>
        </div>
    </div>
</div>

</body>

</html>
