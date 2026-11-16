<#assign pageTitle="Individual Dashboard - HobbyCircles">
    <#include "/shared/head.ftlh">
        <#include "/shared/nav.ftlh">

            <!-- Main header -->
            <section class="section">
                <div class="container">
                    <div class="dashboard-header">
                        <div class="dashboard-title">Welcome, ${individual.name()}!</div>
                        <div class="dashbaord-subtitle">Manage your active groups and RSVP'd events</div>
                    </div>
                </div>

                <!-- Stats -->
                <div class="container">
                    <div class="row row-cols-1 row-cols-md-3 g-4">
                        <div class="col">
                            <div class="card text-center h-100">
                                <div class="card-body">
                                    <h1 class="card-title">${rsvps?size}</h1>
                                    <p class="card-text">Upcoming Events</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card text-center h-100">
                                <div class="card-body">
                                    <h1 class="card-title">${individual.interests()?size}</h1>
                                    <p class="card-text">Interests Tagged</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card text-center h-100">
                                <div class="card-body">
                                    <h1 class="card-title">${comments?size}</h1>
                                    <p class="card-text">Comments Written</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Upcoming events -->
            <section class="section">
                <div class="container">
                    <h2>Upcoming Events</h2>

                   <#list rsvps as rsvp>

                    <div class="event-item">
                        <div class="event-header">
                            <div>
                                <div class="event-title">${rsvp.eventName()}</div>
                                <span class="event-status event-upcoming">RSVP'd</span>
                            </div>
                        </div>
                        <div class="event-details">
                            <div class="detail-item"><span class="detail-label">Date & Time:</span> ${rsvp.eventTime()}
                            </div>
                            <div class="detail-item"><span class="detail-label">Location:</span> ${rsvp.eventLocation()} </div>
                        </div>
                        <div style="display: flex; gap: 1rem; margin-top: 1rem;">
                            <button class="btn btn-primary" onclick="window.location.href='events.html'">View
                                Details</button>
                            <button class="btn btn-outline" onclick="alert('Event cancelled')"
                                style="color: var(--danger-color); border-color: var(--danger-color);">Cancel</button>
                        </div>
                    </div>
                    <#else>
                        <p>You haven't RSVP'd to any events yet.</p>
                    </#list>

                </div>
            </section>

<!-- The rest of the code is for use cases not yet implemented -->
            
            <!-- Active Groups -->
            <section class="section">
                <div class="container">
                    <h2>Active Groups</h2>

                    <div class="row row-cols-1 row-cols-md-3 g-4">
                        <div class="col">
                            <div class="card">
                                <img src="assetsUser/crochet.jpeg" class="card-img-top" alt="crochet supplies">
                                <div class="card-body">
                                    <h5 class="card-title">Crochet Club</h5>
                                    <p class="card-text">Share projects, get advice for all skill levels!</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="assetsUser/games.jpg" class="card-img-top" alt="board game pieces">
                                <div class="card-body">
                                    <h5 class="card-title">Table Top Gaming</h5>
                                    <p class="card-text">Bring your favorite board/card games to our bi-weekly
                                        gatherings! Inquire for DND campaigns.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="assetsUser/crossStitch.webp" class="card-img-top"
                                    alt="cross stitching supplies">
                                <div class="card-body">
                                    <h5 class="card-title">Cross Stitching Club</h5>
                                    <p class="card-text">Share patterns and advice ranging from beginners to experts.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="assetsUser/books.jpg" class="card-img-top" alt="books">
                                <div class="card-body">
                                    <h5 class="card-title">Book Club</h5>
                                    <p class="card-text">Read a new book each month and join us for a potluck style
                                        discussion!</p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

    </body>

</html>