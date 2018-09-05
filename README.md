# spring-boot-secure-api

**Goals**

* Implement a secure API using JSON Web Token on a Spring Boot Project. 
* Implementation to consume the API from a React JS application.


### Part 1: Implement API


1) Include the following dependency on your project:


```
compile('io.jsonwebtoken:jjwt:0.6.0')
```

2) Go to class *JwtFilter* and uncomment the *doFilter* method implementation.

3) Go to the class *UserController* and uncomment the lines 60 and 61.

4) Run the project using the Gradle command *bootRun*.

5) Verify that authentication works with the following command (from the console):

```
curl -H "Content-Type: application/json" -X POST -d '{"username":"xyz","password":"password"}' http://localhost:8080/user/login
```

6) Implement the API for the TODO object. In order to do that you need to follow the next steps:

* Create a model class for the TODO object inside the models package (do not forget to add the getters and setters methods).
* Create a *TodoService* and *TodoServiceImpl* that has the following methods and the corresponding implementations:

    ``` Java
     List<Todo> getTodoList();
     Todo addTodo( Todo todo );
    ```
* Make sure you use correctly the annotations [@Autowired](https://stackoverflow.com/questions/19414734/understanding-spring-autowired-usage) and [@Service](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html) in order to do the proper dependencies injection configuration.

7) Create the *TodoController* to handle the API request that will handle the TODO logic and annotate the class with the following annotations:

    ````
    @RestController
    @RequestMapping( "api" )
    ````
8) Add the following annotation on top of your *@RequestMapping* annotation on your User and Todo controllers (to avoid the [Cross-origin access restriction](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)  )
    
    ````
    @CrossOrigin(origins = "http://localhost:3000")
    ````
    
### Part 2: Consume API from ReactJS project

1. Open the previous project (Todo App) using React JS.

2. Install the axios node package

    ````
     npm install axios --save
    ````
    
3. Make a request to Spring Boot API to authenticate:

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

5. Implement the logic to validate if the user has a token and its been authenticated then redirect to the TODO App.

6. Create an instance of the Axios client that contains the token as a header

    ```` Javascript
        this.axios = axios.create({
                baseURL: 'http://localhost:8080/api/',
                timeout: 1000,
                headers: {'Authorization': 'Bearer '+token}
            });
    ````
    
    
7. Make a request with the created Axios client to the API to retrieve the TODO List.


8. Change the TODO logic so it uses the data retrieve from the API.


9. Create an additional service to Post the TODO data to the API.