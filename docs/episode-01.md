# Episode 1: Introduction & Getting Started
## Helidon SE 4.3.2 Complete Tutorial Series

**Episode Duration:** 12-15 minutes  
**Recording Date:** [Your Date]  
**Episode Code:** https://github.com/[your-username]/helidon-taskflow/tree/episode-01

---

## PRE-PRODUCTION CHECKLIST

### Environment Setup
- [ ] Java 21 JDK installed and verified (`java --version`)
- [ ] Maven 3.8+ installed (`mvn --version`)
- [ ] IDE ready (IntelliJ IDEA recommended, VS Code acceptable)
- [ ] Terminal with readable font (16pt+, Fira Code or JetBrains Mono)
- [ ] Clean workspace directory (`~/helidon-tutorial/`)
- [ ] Internet connection (for Maven downloads)

### Recording Setup
- [ ] Close unnecessary applications
- [ ] Clear IDE recent projects
- [ ] Set IDE font size to 18-20pt
- [ ] Enable line numbers
- [ ] Disable IDE notifications/popups
- [ ] Start with clean terminal (clear history)
- [ ] Open browser tabs: Helidon docs, GitHub

### Audio Setup
- [ ] Microphone tested (no background noise)
- [ ] Room quiet, phone silenced
- [ ] Glass of water nearby
- [ ] Practice script once before recording

---

## SCRIPT

### SEGMENT 1: INTRODUCTION (90 seconds)
**[AVATAR ON SCREEN - Professional background]**

#### Your Voice Script:

"Welcome to this comprehensive Helidon SE tutorial series! I'm [Your Name], and over the next several episodes, we're going to build a production-ready task management platform called TaskFlow, while thoroughly exploring Helidon SE version 4.3.2.

Now, if you're coming from Spring Boot or Jakarta EE, you might be wondering: what exactly is Helidon, and why should I care?

Helidon is Oracle's lightweight microservices framework for Java. But here's what makes it special: it's built from the ground up to leverage Java's virtual threads - also called Project Loom. This means you can write simple, blocking-style code that performs like asynchronous code. No callbacks, no complex reactive programming - just clean, readable Java.

There are two flavors of Helidon: MP, which stands for MicroProfile and is similar to Jakarta EE with annotations and dependency injection, and SE, which stands for Standard Edition. SE is what we're focusing on - it's lighter, more explicit, and gives you complete control over your application's behavior.

In this episode, we'll get our development environment set up, create our first Helidon application, and lay the foundation for TaskFlow - our real-world project that we'll build throughout this series.

Let's dive in!"

**[Transition to screen recording]**

---

### SEGMENT 2: DOCUMENTATION WALKTHROUGH (3-4 minutes)
**[Screen: Browser with Helidon documentation open]**

#### Your Voice Script:

"First, let's look at the official Helidon documentation. I have it open here at helidon.io/docs/v4/se/introduction.

**[Scroll through introduction page]**

The documentation tells us that Helidon SE is based on these core principles:

First, it's designed to be 'Tiny' - minimal runtime footprint and fast startup times. This is crucial for cloud-native deployments where you're spinning up and down instances frequently.

Second, it embraces 'Functional Style' - you build your application using functional programming concepts rather than heavy annotation-based magic. This makes debugging easier because you can see exactly what's happening.

Third, it's 'Reactive' - built on top of virtual threads and reactive streams, giving you excellent performance and resource utilization.

**[Navigate to Getting Started page]**

Now, the documentation shows us we have a few ways to create a Helidon project. The quickstart approach uses Maven archetypes, which is fine, but there's actually a better way that the docs mention briefly: the Helidon CLI.

Here's the first gap the documentation doesn't fully explain: WHY use the CLI over Maven archetypes? The CLI gives you an interactive experience where you can choose exactly what features you want, it sets up project structure best practices, and it's updated more frequently than the archetypes. For learning and production projects, I strongly recommend the CLI.

But there's a catch - and this is something the docs don't emphasize enough - you MUST use Java 21 or later. Not Java 17, not Java 11. Why? Because Helidon 4.x is built specifically to leverage virtual threads, which were fully released in Java 21. If you try to use an older version, you'll get runtime errors that aren't immediately obvious.

Let me show you how to verify your Java version and get started properly."

---

### SEGMENT 3: LIVE CODING - ENVIRONMENT SETUP (2 minutes)
**[Screen: Terminal, full screen]**

#### Your Voice Script:

"Let me first verify my Java version. In my terminal, I'll type:

**[Type and execute]**
```bash
java --version
```

**[Show output]**

Perfect - I'm running Java 21.0.1. If you see anything less than 21, you'll need to install a newer JDK. I recommend downloading from Adoptium.net or using SDKMan if you're on Mac or Linux.

Now let's verify Maven:

**[Type and execute]**
```bash
mvn --version
```

**[Show output]**

Good - Maven 3.9.5. You need at least Maven 3.8, but newer is better.

Now, the Helidon documentation tells us to install the Helidon CLI, but the installation instructions can be a bit sparse. Let me show you the complete process.

**[For Mac/Linux users]**
```bash
# Download and install Helidon CLI
curl -O https://helidon.io/cli/latest/darwin/helidon
chmod +x ./helidon
sudo mv ./helidon /usr/local/bin/
```

**[For Windows users - show on screen as text]**
For Windows users, download the executable from helidon.io/cli/latest/windows/helidon.exe and add it to your PATH.

Let me verify the installation:

**[Type and execute]**
```bash
helidon --version
```

**[Show output]**

Excellent! The CLI is ready. Now we can create our project."

---

### SEGMENT 4: PROJECT CREATION (3 minutes)
**[Screen: Terminal]**

#### Your Voice Script:

"Let's create our TaskFlow project. I'll create a directory for our work and navigate into it:

**[Type and execute]**
```bash
mkdir helidon-tutorial
cd helidon-tutorial
```

Now, here's where the magic happens. The documentation shows running `helidon init`, but it doesn't explain what happens during this interactive process. Let me walk you through it:

**[Type and execute]**
```bash
helidon init
```

**[As prompts appear, explain each one]**

First prompt: 'Helidon flavor' - we're selecting SE, so I'll type '1' and press Enter.

**[Select 1 - Helidon SE]**

Next: 'Application type' - we want the 'quickstart' which is a minimal web application. Type '1'.

**[Select 1 - quickstart]**

Now it asks for 'Media support' - this is for JSON processing. We want JSON-B, which is the modern Jakarta standard. I'll select '2'.

**[Select 2 - JSON-B]**

Next is the GroupId - this is standard Maven convention. I'll use 'com.taskflow'.

**[Type: com.taskflow]**

ArtifactId - this becomes your project folder name. I'll use 'taskflow-api'.

**[Type: taskflow-api]**

Package name - it auto-suggests based on our groupId. I'll just hit Enter to accept 'com.taskflow'.

**[Press Enter]**

Java version - definitely select '21'.

**[Select 21]**

And... done! The CLI just generated our entire project structure. This is something the documentation glosses over, but what it actually did was:

1. Created a complete Maven project with proper dependency versions
2. Set up the recommended package structure
3. Configured the pom.xml with all necessary Helidon dependencies
4. Created a basic Main.java with a working web server
5. Included example tests and configuration files

Let's look at what was created:

**[Execute]**
```bash
cd taskflow-api
ls -la
```

**[Show the directory structure]**

You can see we have:
- `pom.xml` - our Maven configuration
- `src/main/java` - our application code
- `src/main/resources` - configuration files
- `src/test/java` - test cases

Let me quickly show you the pom.xml because there's important information here the docs don't highlight:

**[Open pom.xml in editor]**
```bash
code pom.xml
# or vim pom.xml
# or whatever editor you prefer
```

---

### SEGMENT 5: UNDERSTANDING THE GENERATED CODE (4 minutes)
**[Screen: IDE with pom.xml open]**

#### Your Voice Script:

"Looking at the pom.xml, there are some critical things to understand that the documentation assumes you know:

**[Scroll to properties section]**

First, notice the Helidon version property at the top - it's set to 4.3.2. This is important because Helidon dependencies are tightly version-coupled. Don't mix versions or you'll get cryptic runtime errors.

**[Scroll to dependencies]**

Here in the dependencies section, we have several key libraries:

`helidon-webserver` - This is the core web server built on virtual threads. The documentation calls it 'Níma' which is the internal project name. This is NOT a servlet container - it's a completely new, virtual thread-native web server.

`helidon-http-media-jsonb` - This handles JSON serialization using JSON-B, the Jakarta standard.

`helidon-config-yaml` - For YAML configuration support.

And for testing, we have `helidon-webserver-testing-junit5` which is incredibly useful and often overlooked in the docs.

Now let's look at the actual application code:

**[Open src/main/java/com/taskflow/Main.java]**

This Main.java file is the heart of your application. Let me walk through it section by section, because the documentation shows you WHAT it does, but not WHY it's structured this way.

**[Scroll to the main method]**

```java
public static void main(String[] args) {
    WebServer server = WebServer.builder()
            .config(Config.global())
            .routing(Main::routing)
            .build()
            .start();
    
    System.out.println("WEB server is up! http://localhost:" + server.port() + "/greet");
}
```

Here's what's happening that the docs don't explain clearly:

`WebServer.builder()` - This uses the builder pattern. You're constructing a web server instance programmatically. This is very different from Spring Boot where a lot happens via annotations.

`.config(Config.global())` - This loads configuration from application.yaml. The 'global' config is a singleton that Helidon manages. Important note: this is loaded ONCE at startup. If you need dynamic config, that's a different approach we'll cover later.

`.routing(Main::routing)` - This is a method reference to the routing method below. This is where you define all your HTTP endpoints.

`.build().start()` - This actually starts the server. Notice it's synchronous - the server starts and the thread waits. This works great with virtual threads because it doesn't block precious OS threads.

**[Scroll to routing method]**

```java
static void routing(HttpRouting.Builder routing) {
    routing
        .register("/greet", new GreetService());
}
```

The routing method receives a Builder object. You chain method calls to register routes. The documentation shows this pattern but doesn't explain that you can also use lambda expressions or method references instead of Service classes.

**[Scroll to GreetService class]**

```java
public class GreetService implements HttpService {
    @Override
    public void routing(HttpRules rules) {
        rules
            .get("/", this::getDefaultMessage);
    }
    
    private void getDefaultMessage(ServerRequest request, 
                                   ServerResponse response) {
        // ... implementation
    }
}
```

This GreetService is a separate class implementing HttpService. Here's a key insight the docs don't emphasize: you DON'T have to use separate Service classes. For simple routes, you can define handlers directly in your routing method. But Service classes are cleaner for grouping related endpoints.

The handlers receive `ServerRequest` and `ServerResponse` objects. These are Helidon's abstractions for HTTP. Coming from Spring, you might expect @RequestMapping and returning objects directly. In Helidon SE, you explicitly read from the request and write to the response. It's more verbose but also more explicit and easier to debug.

Let's run this application and see it work:

---

### SEGMENT 6: RUNNING THE APPLICATION (2 minutes)
**[Screen: Split - terminal on left, browser on right]**

#### Your Voice Script:

"To run the application, the documentation says to use Maven. Let me show you:

**[In terminal]**
```bash
mvn clean package
mvn exec:java
```

**[Wait for startup messages]**

Watch the console output carefully. You'll see messages about the server starting. One thing the docs don't highlight: that startup time? Usually under 2 seconds, even on the first run. Compare that to a typical Spring Boot application which might take 10-15 seconds. This is the benefit of Helidon's minimal footprint and virtual threads.

**[Point out the key log message]**
See this line: 'WEB server is up! http://localhost:8080/greet'

The server is running. Let's test it. I'll open a browser...

**[Switch to browser, navigate to http://localhost:8080/greet]**

**[Show the JSON response]**

Perfect! We get back:
```json
{"message": "Hello World!"}
```

This is the default greeting endpoint. Now let's try the personalized greeting:

**[Navigate to http://localhost:8080/greet/John]**

**[Show response]**
```json
{"message": "Hello John!"}
```

Excellent! The path parameter works. One thing that often confuses newcomers - and the docs don't emphasize - is that there's no automatic browser refresh or hot reload like you get with Spring DevTools. If you change code, you need to stop the server (Ctrl+C) and restart it.

However, there's a trick you can use during development:

**[Back to terminal, stop the server with Ctrl+C]**

Instead of mvn exec:java, you can use:

```bash
mvn compile exec:java
```

This compiles AND runs in one step, which is slightly faster during development iterations.

But let me show you an even better way that the documentation barely mentions: using the Helidon CLI's dev mode:

**[Execute]**
```bash
helidon dev
```

**[Show the dev mode output]**

This watches for file changes and automatically recompiles and restarts. Much better for development!

**[Stop the dev server]**

Alright, we have a working Helidon application. Now let's transform this into our TaskFlow project."

---

### SEGMENT 7: BUILDING TASKFLOW FOUNDATION (3-4 minutes)
**[Screen: IDE with project open]**

#### Your Voice Script:

"For our TaskFlow application, we need to set up a proper structure. The generated code is a good starting point, but we need to organize it for a real-world application.

First, let's understand our domain. TaskFlow will manage tasks, so we need:
- A Task model
- A service to handle task operations
- REST endpoints for CRUD operations
- Proper package organization

Let's start by creating a better package structure. The docs don't really cover project organization best practices, so let me show you what works well:

**[Create directory structure in IDE]**

Under `src/main/java/com/taskflow/`, I'll create:
- `model` - for our domain models
- `service` - for business logic
- `web` - for HTTP/REST handling

**[Create these packages in IDE]**

Now, let's create a simple Task model. I'll create a new file:

**[Create file: src/main/java/com/taskflow/model/Task.java]**

```java
package com.taskflow.model;

import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Note: In the next episode, we'll add JSON-B annotations here
    
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = TaskStatus.TODO;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { 
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
```

Notice I'm updating the updatedAt timestamp in the setters. This is a pattern the Helidon docs don't cover, but it's important for tracking changes.

Now the TaskStatus enum:

**[Create file: src/main/java/com/taskflow/model/TaskStatus.java]**

```java
package com.taskflow.model;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE,
    CANCELLED
}
```

Simple and clean. Now let's create a basic TaskService that will manage our tasks. For now, we'll just use an in-memory list. In later episodes, we'll replace this with a database.

**[Create file: src/main/java/com/taskflow/service/TaskService.java]**

```java
package com.taskflow.service;

import com.taskflow.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskService {
    // Using ConcurrentHashMap for thread-safety
    // This is important with virtual threads - multiple requests 
    // can execute simultaneously
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.put(task.getId(), task);
        return task;
    }
    
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    
    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }
    
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return getTaskById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            return existingTask;
        });
    }
    
    public boolean deleteTask(Long id) {
        return tasks.remove(id) != null;
    }
}
```

A critical point here that the Helidon docs don't emphasize: I'm using `ConcurrentHashMap` instead of a regular `HashMap`. Why? Because with virtual threads, you can have many requests executing concurrently. A regular HashMap isn't thread-safe and would cause data corruption. This is something developers coming from Spring might miss because Spring's singleton beans handle thread safety differently.

Now let's create the HTTP service to expose these operations as REST endpoints:

**[Create file: src/main/java/com/taskflow/web/TaskResource.java]**

```java
package com.taskflow.web;

import com.taskflow.model.Task;
import com.taskflow.service.TaskService;
import io.helidon.http.Status;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class TaskResource implements HttpService {
    private final TaskService taskService;
    
    public TaskResource() {
        this.taskService = new TaskService();
    }
    
    @Override
    public void routing(HttpRules rules) {
        rules
            .get("/", this::getAllTasks)
            .get("/{id}", this::getTaskById)
            .post("/", this::createTask);
        // We'll add PUT and DELETE in the next episode
    }
    
    private void getAllTasks(ServerRequest request, ServerResponse response) {
        response.send(taskService.getAllTasks());
    }
    
    private void getTaskById(ServerRequest request, ServerResponse response) {
        long id = Long.parseLong(request.path().pathParameters().get("id"));
        
        taskService.getTaskById(id)
            .ifPresentOrElse(
                response::send,
                () -> response.status(Status.NOT_FOUND_404).send()
            );
    }
    
    private void createTask(ServerRequest request, ServerResponse response) {
        // For now, we'll just create a simple task
        // In the next episode, we'll properly parse JSON from request body
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("Created from API");
        
        Task created = taskService.createTask(task);
        response.status(Status.CREATED_201).send(created);
    }
}
```

Notice how we're explicitly handling the response status codes. The docs show this pattern but don't explain WHY it's better than implicit responses. By being explicit, you have complete control and it's immediately clear what HTTP status each endpoint returns.

Finally, let's update our Main.java to use TaskResource instead of GreetService:

**[Open Main.java, modify routing method]**

```java
static void routing(HttpRouting.Builder routing) {
    routing
        .register("/api/tasks", new TaskResource())
        .register("/greet", new GreetService()); // Keep this for now
}
```

I'm registering our TaskResource under `/api/tasks`. This is a common REST convention - put your API endpoints under `/api`. The docs don't really cover URL design best practices, but this prefix makes it clear what's your API vs what might be static content or health checks.

Let's test this!

**[Save all files]**

---

### SEGMENT 8: TESTING TASKFLOW (2 minutes)
**[Screen: Terminal and browser/curl]**

#### Your Voice Script:

"Let's run our application and test the new endpoints:

**[In terminal]**
```bash
mvn clean compile exec:java
```

**[Wait for startup]**

Great, the server is running. Let me test our endpoints using curl. First, let's create a task:

**[In second terminal or show on screen]**
```bash
curl -X POST http://localhost:8080/api/tasks
```

**[Show response]**

We get back a JSON response with our task, including an ID and timestamps. Perfect!

Now let's get all tasks:

```bash
curl http://localhost:8080/api/tasks
```

**[Show response - array with one task]**

Excellent! We see our task in an array.

Let's get a specific task by ID:

```bash
curl http://localhost:8080/api/tasks/1
```

**[Show response]**

Perfect! And if we try a non-existent ID:

```bash
curl http://localhost:8080/api/tasks/999
```

**[Show 404 response]**

We correctly get a 404 Not Found status. This is exactly what we want.

One thing to note: our JSON looks a bit ugly because we haven't configured proper JSON serialization yet. In the next episode on Configuration and JSON handling, we'll make this much cleaner. But functionally, everything works!"

---

### SEGMENT 9: PROJECT STRUCTURE REVIEW (1 minute)
**[Screen: IDE showing project structure]**

#### Your Voice Script:

"Let's review what we've built. Our project now has:

**[Highlight in IDE]**

- A `model` package with Task and TaskStatus
- A `service` package with TaskService handling business logic
- A `web` package with TaskResource exposing REST endpoints
- A modified Main.java that wires everything together

This separation of concerns - model, service, web - is a pattern that will serve us well as the application grows. The Helidon documentation doesn't prescribe a specific structure, but this three-layer approach is battle-tested in production applications.

One quick note about what we're missing: we haven't added any tests yet, we're using in-memory storage that disappears when the app stops, and our JSON handling is basic. We'll address all of these in upcoming episodes.

But right now, we have a working foundation for TaskFlow!"

---

### SEGMENT 10: COMMON PITFALLS & TIPS (2 minutes)
**[Avatar back on screen]**

#### Your Voice Script:

"Before we wrap up, let me share some common pitfalls I see developers encounter with Helidon SE that the documentation doesn't warn you about:

**Pitfall #1: Java Version Confusion**
Helidon 4.x REQUIRES Java 21+. Not 17, not 11. If you get weird runtime errors about virtual threads or strange NullPointerExceptions, first thing to check: your Java version.

**Pitfall #2: Thread Safety Assumptions**
Coming from Spring or Jakarta EE, you might assume your services are thread-safe by default. In Helidon SE, if you're using mutable shared state, YOU need to handle thread safety. That's why we used ConcurrentHashMap. Regular collections will cause subtle, hard-to-debug problems under load.

**Pitfall #3: Dependency Versions**
Always use the Helidon BOM (Bill of Materials) in your pom.xml, which the CLI sets up for you. Don't manually specify versions for Helidon dependencies. The framework is tightly coupled, and version mismatches cause runtime failures.

**Pitfall #4: Blocking Operations**
Even though Helidon uses virtual threads which make blocking operations cheap, you still shouldn't make long-running blocking calls in your handlers. We'll cover proper async patterns in later episodes, but for now, keep your handlers fast.

**Best Practices to Start With:**

1. **Always use the builder pattern** - you'll see .builder() everywhere in Helidon. Embrace it.

2. **Be explicit** - Unlike frameworks with lots of magic, Helidon SE expects you to wire things up explicitly. This makes code more verbose but also more debuggable.

3. **Keep your Main class simple** - resist the urge to put business logic there. Use it only for wiring and startup.

4. **Organize by feature** - as your app grows, group related classes by feature rather than by layer. So instead of all services in one package, consider packages like 'task', 'user', 'project', each with their own models, services, and resources.

The documentation has all the technical details, but these practical tips come from real-world experience."

---

### SEGMENT 11: RECAP & NEXT EPISODE PREVIEW (1 minute)
**[Avatar on screen with bullet points appearing]**

#### Your Voice Script:

"Let's recap what we accomplished in this episode:

✓ Set up our development environment with Java 21 and Helidon CLI
✓ Created our first Helidon SE application
✓ Understood the core components: WebServer, routing, and services
✓ Built the foundation for TaskFlow with a proper package structure
✓ Created our Task model and TaskService
✓ Exposed REST endpoints for task management
✓ Tested our API with real HTTP requests

We now have a working, runnable foundation. But there's a lot we need to improve.

**In the next episode**, we're going to dive deep into Configuration Management. We'll learn how to:
- Externalize configuration with application.yaml
- Handle environment-specific settings
- Configure our JSON serialization properly
- Set up logging
- And prepare for database integration

This will make our TaskFlow application much more production-ready.

**Before the next episode**, I encourage you to:
- Clone the episode-01 code from the GitHub repository (link in the description)
- Experiment with adding more fields to the Task model
- Try creating additional endpoints
- Read through the Helidon Config documentation

If you found this helpful, please subscribe and hit the notification bell. Drop any questions in the comments below - I read every single one.

The complete source code for this episode is available on GitHub at the link in the description. Each episode has its own branch, so you can follow along or jump to any point in the series.

Thanks for watching, and I'll see you in the next episode where we tackle configuration management!"

**[End screen with:**
- Subscribe button
- Next episode thumbnail
- GitHub link
- Social media links]**

---

## POST-PRODUCTION CHECKLIST

### Video Editing
- [ ] Cut out long pauses and "ums"
- [ ] Add zoom-in effects on important code sections
- [ ] Add text callouts for key concepts
- [ ] Insert lower thirds with current topic
- [ ] Add background music at low volume (10-15%)
- [ ] Color grade for consistency
- [ ] Add chapter markers at each segment

### Assets Needed
- [ ] Intro animation (5 seconds)
- [ ] Outro animation (10 seconds)
- [ ] Lower third template with episode number
- [ ] Animated bullets for recap section
- [ ] GitHub logo and link animation
- [ ] Subscribe button animation

### YouTube Metadata
- [ ] Title: "Helidon SE 4.3.2 Tutorial - Episode 1: Getting Started & Building TaskFlow Foundation"
- [ ] Description with timestamps, GitHub link, resources
- [ ] Tags: Helidon, Java, Microservices, Virtual Threads, REST API, Java 21
- [ ] Thumbnail with episode number and topic
- [ ] End screen with next video and subscribe
- [ ] Add to "Helidon SE Tutorial" playlist

### Code Repository
- [ ] Create branch: episode-01-getting-started
- [ ] Ensure code compiles and runs
- [ ] Add README.md with:
    - What was covered
    - How to run the code
    - Prerequisites
    - Links to documentation
- [ ] Tag the commit: v1.0-episode-01
- [ ] Push to GitHub

### Companion Materials
- [ ] Create PDF cheat sheet with:
    - Key commands used
    - Common pitfalls
    - Additional resources
- [ ] Link to cheat sheet in video description

---

## TIME ESTIMATES

### Recording Time
- **Segments 1-2 (Intro + Docs):** 6 minutes recording
- **Segments 3-6 (Setup + Running):** 12 minutes recording
- **Segments 7-8 (Building TaskFlow):** 10 minutes recording
- **Segments 9-11 (Review + Wrap):** 5 minutes recording
- **Total Raw Footage:** ~35-40 minutes

### Editing Time
- **Cuts and trimming:** 2 hours
- **Adding effects/callouts:** 2 hours
- **Audio mixing:** 1 hour
- **Color grading:** 30 minutes
- **Rendering tests:** 30 minutes
- **Total Editing:** ~6 hours

### Expected Final Runtime
- **Final video length:** 12-15 minutes

---

## ADDITIONAL NOTES

### Camera/Avatar Settings
- Avatar positioned in bottom right corner during coding
- Full screen avatar for intro, recap, and pitfalls
- Smooth transitions between avatar and screen

### Audio Notes
- Record room tone at beginning (30 seconds of silence)
- Use same mic position throughout
- Consistent distance from microphone
- Edit out clicks, pops, and mouth sounds

### Common Recording Issues
- If you make a mistake, clap hands (creates visual spike in audio)
- Leave 2-second buffer before and after segments
- If code doesn't work,</parameter> <parameter name="new_str">### Common Recording Issues
- If you make a mistake, clap hands (creates visual spike in audio)
- Leave 2-second buffer before and after segments
- If code doesn't work, stop, fix, and reshoot that segment
- Save project frequently (IDE auto-save can fail)

### Voice Tips

- Stay hydrated - have water nearby
- Pace yourself - don't rush technical terms
- Emphasize key words slightly
- Pause between major concepts
- Smile while talking (it comes through in voice)
- Re-record if you stumble - viewers appreciate clarity

---

## RESOURCES NEEDED
### Documentation Links to Reference

- https://helidon.io/docs/v4/se/introduction
- https://helidon.io/docs/v4/se/guides/quickstart
- https://helidon.io/docs/v4/se/webserver

### Tools/Software

- OBS Studio or screen recording software
- Audio editor (Audacity, Adobe Audition)
- Video editor (DaVinci Resolve, Camtasia, Premiere)
- Avatar platform (HeyGen, Synthesia, D-ID)

### Example Code Repository
GitHub Structure:
```
helidon-taskflow/
├── README.md (series overview)
├── episode-01-getting-started/
│   ├── README.md (episode-specific)
│   ├── taskflow-api/
│   │   ├── pom.xml
│   │   └── src/
│   └── EPISODE_NOTES.md
├── episode-02-configuration/
└── ...
```</parameter>
```
