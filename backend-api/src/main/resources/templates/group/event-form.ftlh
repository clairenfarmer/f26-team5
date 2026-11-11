<#assign pageTitle="New Event - HobbyCircles">
<#include "/shared/head.ftlh">
<#include "/shared/nav.ftlh">

<!-- Event Form -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-9 mt-5">
            <div class="card shadow-sm p-4">
                <h3 class="mb-4">Create a New Event</h3>

                <#if error??>
                    <div class="alert alert-danger" role="alert">${error}</div>
                </#if>

                <form action="/hobbygroups/events" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Event Name *</label>
                        <input type="text" class="form-control" id="name" name="name" maxlength="150"
                            placeholder="Enter the event name" value="${name!''}" required>
                    </div>
                    <div class="mb-3">
                        <label for="time" class="form-label">Event Date and Time *</label>
                        <input type="datetime-local" class="form-control" id="time" name="time"
                            value="${time!''}" required>
                    </div>
                    <div class="mb-3">
                        <label for="location" class="form-label">Event Location *</label>
                        <input type="text" class="form-control" id="location" name="location" maxlength="255"
                            placeholder="Enter the event location" value="${location!''}" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Event Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3" maxlength="2000"
                            placeholder="Provide a brief description of the event">${description!''}</textarea>
                    </div>
                    <button type="submit" class="btn btn-warning">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>
