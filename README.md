# spring-boot-secure-api

**Goals**

* Implement a secure API using the JSON Web Token open standard (RFC 7519). 
* Implementation to consume the API from a React JS application.


### Part 1: Implement API


1) Download, compile and run the Spring Boot project from the repository.
 
2) Verify that authentication works with the following command (from the console):
   
   ```
   curl -H "Content-Type: application/json" -X POST -d '{"username":"xyz","password":"password"}' http://localhost:8080/user/login
   ```

3) Open the following url and try to understand the message form the server, and then try to find the code where the exception is thrown:
http://localhost:8080/api/test

4) Comment the line 13 *@Bean* on the *SpringBootSecureApiApplication* class. Re-run the project and verify the endpoint again:
http://localhost:8080/api/test
                    
3) Why do you think it works now ? Discuss your findings with your classmates.                    
                    	
4) Implement the logic to verify the user's credentials on the *UserController* and the *UserServiceImpl*.

5) Add the *TaskController* and services implemented on the previous codelab. 

6) Verify that your API has all the resources offered secured, under the */api* path.


7) Add the following annotation on top of your *@RequestMapping* annotation on your REST controllers (to avoid the [Cross-origin access restriction](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)  )
    
    ````
    @CrossOrigin(origins = "http://localhost:3000")
    ````
    
### Part 2: Consume API from ReactJS project

1. Open the React JS project Task Planner App.

2. Install the axios node package

    ````
     npm install axios --save
    ````

##### Implement Login View
    
3. Make a first request to the API to authenticate on the method *componentDidMount()*:

    ```` Javascript
           axios.post('http://localhost:8080/user/login', {
                 username: 'xyz',
                 password: 'password'
             })
                 .then(function (response) {
                     console.log(response.data);
                 })
                 .catch(function (error) {
                     console.log(error);
                 });
    ````
    
4. Save the authentication token using the local Storage.

5. Implement the logic to validate if that if the user has a valid token, then redirects to the Main View.

6. Create an instance of the Axios client that contains the token inside the Authorization header

    ```` Javascript
        this.axios = axios.create({
                baseURL: 'http://localhost:8080/api/',
                timeout: 1000,
                headers: {'Authorization': 'Bearer '+token}
            });
    ````
    
    
7. Make a request with the Axios client instance to the API to retrieve the Tasks List.


8. Create an additional service to Post the Task data to the API.