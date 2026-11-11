<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Hobby Circles</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <#if userRole?? && userRole=="individual">
                <a class="nav-link" href="/individuals/browse-groups">Browse Groups</a>
                <a class="nav-link" href="/individuals/calendar">View Calendar</a>
                <a class="nav-link" href="/individuals/dashboard">Dashboard</a>
                <#elseif userRole?? && userRole=="group">
                <a class="nav-link" href="/groups/event-form">Create an Event</a>
                <a class="nav-link" href="/groups/calendar">View Calendar</a>
                <a class="nav-link" href="/groups/group-profile">View Profile</a>
                <#else>
                <#-- Just placeholder for now   -->
                <a class="nav-link" href="/features">Features</a>
                <a class="nav-link" href="/how-it-works">How it Works</a>
                <a class="nav-link" href="/login">Login</a>
                <a class="nav-link" href="/register">Register</a>
                </#if>
            </div>
        </div>
    </div>
</nav>