<#assign pageTitle="Login - HobbyCircles">
    <#include "/shared/head.ftlh">
        <#include "/shared/nav.ftlh">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-9 mt-5">
                        <div class="card shadow-sm p-4">
                            <h3 class="mb-4">Welcome Back to Hobby Circles</h3>
                            <form action="/login" method="post">
                                <div class="mb-3">
                                    <label for="role">Logging in as</label>
                                    <select id="role" name="role" required>
                                    <option value="individual">Individual</option>
                                    <option value="group">Hobby Group</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email Address</label>
                                    <input id="email" name="email" type="email" placeholder="you@example.com" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input id="password" name="password" type="password" required>
                                </div>

                                <div>
                                    <button type="submit" class="btn btn-warning"
                                        >Login</button>
                                </div>
                                
                            </form>

                        </div>
                    </div>
                </div>
            </div>

            </body>

            </html>