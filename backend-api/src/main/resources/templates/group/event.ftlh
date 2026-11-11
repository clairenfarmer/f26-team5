<#assign pageTitle="${event.name} - HobbyCircles">
<#include "/shared/head.ftlh">
<#include "/shared/nav.ftlh">

<!-- Main header -->
<section class="section">
    <div class="container">
        <div class="dashboard-header">
            <div class="dashboard-title">Event Details</div>
            <div class="dashbaord-subtitle">View details for "${event.name}"</div>
        </div>
    </div>
</section>

<!-- Stats -->
<div class="container">

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col">
            <div class="card text-center h-100">
                <div class="card-body">
                    <#if daysUntil gte 0>
                        <h1 class="card-title">${daysUntil?c}</h1>
                        <p class="card-text">Days until event</p>
                    <#else>
                        <h1 class="card-title">-</h1>
                        <p class="card-text">This event has already passed</p>
                    </#if>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card text-center h-100">
                <div class="card-body">
                    <h1 class="card-title">${event.rsvpCount}</h1>
                    <p class="card-text">RSVPs</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card text-center h-100">
                <div class="card-body">
                    <h1 class="card-title">${event.status!""}</h1>
                    <p class="card-text">Status</p>
                </div>
            </div>
        </div>
    </div>

    <div class="card mt-4">
        <div class="card-header">
            <h2 style="font-size: 1.3rem; font-weight: 700;">Additional Details</h2>
        </div>
        <div class="card-content" style="padding: 1.5rem;">
            <p style="font-weight: 600; margin-bottom: 0.5rem;">Date and Time</p>
            <p style="color: #666; line-height: 1.5;">${event.time!""}</p>

            <p style="font-weight: 600; margin-bottom: 0.5rem;">Location</p>
            <p style="color: #666; line-height: 1.5;">${event.location!"No location provided"}</p>

            <p style="font-weight: 600; margin-bottom: 0.5rem;">Description</p>
            <p style="color: #666; line-height: 1.5;">${event.description!"No description provided"}</p>

            <p style="font-weight: 600; margin-bottom: 0.5rem;">Attendees</p>
            <#if rsvps?has_content>
                <ul style="color: #666; line-height: 1.5;">
                    <#list rsvps as rsvp>
                        <li>${rsvp.individualName!""}</li>
                    </#list>
                </ul>
            <#else>
                <p style="color: #666; line-height: 1.5;">No one has RSVP'd yet.</p>
            </#if>
        </div>
    </div>

    <a href="/hobbygroups/events/${event.id?c}/edit" class="btn btn-warning mt-4 mb-4">Edit event</a>
    <a href="/hobbygroups/dashboard" class="btn btn-dark mt-4 mb-4">Back to dashboard</a>
</div>

</body>

</html>
