<#assign pageTitle="Sign Up - HobbyCircles">
    <#include "/shared/head.ftlh">
        <#include "/shared/nav.ftlh">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-9 mt-5">
                        <div class="card shadow-sm p-4">
                            <h3 class="mb-4">Create Your User Profile</h3>
                            <form action="/registerIndividual" method="post">
                                <h4 class="mb-2">User Information</h4>
                                <div class="mb-3">
                                    <label for="name" class="form-label">Name *</label>
                                    <input type="text" class="form-control" id="name" name="name"
                                        placeholder="Enter your preferred name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email address *</label>
                                    <input type="email" class="form-control" id="email" name="email"
                                        aria-describedby="emailHelp" placeholder="example@ex.com" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password *</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>

                                <h4 class="mb-2">Details</h4>
                                <div class="mb-3">
                                    <label for="interests" class="form-label">Select your hobbies</label>
                                    <div class="row row-cols-3 g-2">
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="CRAFTS" id="int-crafts">
                                                <label class="form-check-label" for="int-crafts">Crafts</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="MUSIC" id="int-music">
                                                <label class="form-check-label" for="int-music">Music</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="SPORTS" id="int-sports">
                                                <label class="form-check-label" for="int-sports">Sports</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="GAMES" id="int-games">
                                                <label class="form-check-label" for="int-games">Games</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="OUTDOORS" id="int-outdoors">
                                                <label class="form-check-label" for="int-outdoors">Outdoors</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="TECHNOLOGY" id="int-technology">
                                                <label class="form-check-label" for="int-technology">Technology</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="EDUCATION" id="int-education">
                                                <label class="form-check-label" for="int-education">Education</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="FOOD" id="int-food">
                                                <label class="form-check-label" for="int-food">Food</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="FILM" id="int-film">
                                                <label class="form-check-label" for="int-film">Film</label>
                                            </div>
                                        </div>
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="interests"
                                                    value="OTHER" id="int-other">
                                                <label class="form-check-label" for="int-other">Other</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <#if error??>
                                <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
                                    ${error}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                </#if>
                                <button type="submit" class="btn btn-warning">Submit</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            </body>

            </html>