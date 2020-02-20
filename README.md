## The road 'till now and the motivation for this Jackson-deserialization-PoC:
Since I haven't coded a single line of Java in over a decade, now... I've been reading up on the tools that I'll require. Prior to skimming the _References_, the only Jackson I knew was Michael `¯\_(ツ)_/¯`
For those of you who don't know this yet:
* Jackson is a Java library for handling JSON data.
* Deserialization is a common vulnerability that affects a lot of systems. It consists on malformed data or unexpected data that could be used to abuse application logic, deny service, or execute arbitrary code, when deserialized
* Spring a framework... more like an ecosystem that I still not fully understand. From what I've grasped so far, it's purpose is to perform some form of ORM (Object-Relational Mapping) and provide the tools to facilitate java integrations.

# My process 
## From the top:
First step: reading up on the documentation. Apparently, deserialization in Jackson is similar to the one in python (pickle). I haven't coded or exploited that one but I'm familiar with the PHP one, which I believe is the most common deserialization flavor on the internet. In short, the difference here is going to be that I must code in Java and wrap everything on a spring app.
To that end, I've found two websites that should help me create a simple webapp for the PoC [1]. I think I'll be using the RESTful version, since it already responds with a JSON "hello world".
The tricky part will be to figure out a way for building the PoC around this basic webapp. I'm counting on my ability to figure out a couple of dummy classes that I'll be able to include in the project along the way... something like `class Person` or `class User`. _The idea being that I will use those with the Jackson methods that are vulnerable, hence the PoC_
I haven't decided what the scope is going to be. 'Consequences', according to OWASP [2].

I plan on updating this post as I move forward.

## Spring 101
I'm starting by installing Gradle [5], then Spring Tool Suite [3], [4], [5]. Took me a while to figure this out but.. apparently `3.9.1` was the last tool version. After versionn `4.x` they're integrated into other IDEs like Eclipse and VScode. I followed the medium article and installed the old version, but just in case I need it in the future, I added the extension to my VScode.
After firing up STS 3.9.1, I loaded up the RESTful app tutorial. A LOT of files were imported! Gradle files, Policy files, Catalina_something files(?) Who the heck is Catalina? It's gonna take me some time to figure out what to make of all this. So... a bit of backtracking... what if I don't use STS?

I'm going to follow [1b] for creating a rest-service that answers with a Hello World. This is much more amicable, since there is a step by step explanation on how the code works, what it is expected to do and what I'm supposed to be doing. There are two main `src` folders: `initial` and `complete`. As I wish to learn, I'm going to be following the `initial` path... but if I just wanted to check out the code, I'd be taking advantage of the `complete` source code. In the end, after I've completed the walkthrough, the code should be pretty similar... but I find that typing the code myself is a different educational experience, at least that's how my brain works. You may choose either path.

One of the first things I've noticed on this tutorial, was a caption stating that `This application uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON. Jackson is included by default by the web starter.` So I feel like I'm already on the right track. It's not like I'm gonna have to add many Jackson functions on top of this... but I wonder if the rest of the Jackson code that comes ahead is vulnerable or not. Food for thought, right now I'm somewhat occupied trying to complete the task at hand: getting my first Spring-based REST web app working. I'll deal with Jackson and vulnerabilities later.

A minute goes by... and I've got my first class coded in java. It's been years, but the sitax feels like C++, which I'm really comfortable with. A simple class, two private methods, a constructor and two getters. Then things get complicated. Note to self: I've got to read up on `@RestController`, `@RequestParam` and `@GetMapping`. I can't help but wonder if a naive use of this annotations might lead to unsanitized variables. For now, back to the task at hand: I'll create the class that handles the HTTP GET requests for greetings.

Continuing with the tutorial, I learn that `Spring’s MappingJackson2HttpMessageConverter is automatically chosen to convert the Greeting instance to JSON`. this is something interesting. I might need to google this converter for vulnerabilities that are relevant to this challenge.

After a couple of minutes speed reading about Spring annotations, I'm finally ready to run the code. Gradle does the job, a Spring initialization log shows up on the terminal and I fire up my browser. `localhost:8080/greeting` responds with `{"id":1,"content":"Hello, World!"}`. Success!
Then I refresh and the id starts to climb, as it should. Trying the parameter: `localhost:8080/greeting?name=foo` gets a proper reply `{"id":5,"content":"Hello, foo!"}`

So far, so good... but for now, I've got to cut this one short. 


To be continued...






----------------------------------------------------

----------------------------------------------------

----------------------------------------------------

----------------------------------------------------
# References and links I use
General References:
https://github.com/no-sec-marko/java-web-vulnerabilities
https://www.owasp.org/images/d/d7/Marshaller_Deserialization_Attacks.pdf.pdf
https://github.com/FasterXML/jackson
https://www.baeldung.com/jackson-object-mapper-tutorial
https://cwe.mitre.org/data/definitions/502.html
https://dan.lousqui.fr/explaining-and-exploiting-deserialization-vulnerability-with-python-en.html
[2] https://owasp.org/www-community/vulnerabilities/Deserialization_of_untrusted_data

Spring tutorials:
Explicacion sobre que es Spring y cual es su motivacion -> https://www.youtube.com/watch?v=gq4S-ovWVlM
[1a] getting started -> https://spring.io/guides/gs/serving-web-content/
[1b]    -> https://spring.io/guides/gs/rest-service/
[3] https://spring.io/guides/gs/sts/ || https://medium.com/@nditah/how-to-install-spring-tool-suite-3-on-ubuntu-18-30ca339a0787
[4] install spring on VScode https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack
[5] https://gradle.org/install/

consuming RESTful apps https://spring.io/guides/gs/consuming-rest/

https://spring.io/guides -> https://spring.io/projects -> dowload demo https://start.spring.io/
https://www.javatpoint.com/spring-tutorial

Spring serialization:
https://www.baeldung.com/spring-boot-jsoncomponent
https://snyk.io/vuln/SNYK-JAVA-ORGSPRINGFRAMEWORKSECURITY-31509 -> https://snyk.io/vuln/SNYK-JAVA-COMFASTERXMLJACKSONCORE-31507
https://docs.spring.io/spring-boot/docs/1.4.0.M3/reference/html/howto-spring-mvc.html


JackSON reserialization vulns 
https://www.youtube.com/watch?v=Kd75BubLsUo
https://medium.com/@cowtowncoder/on-jackson-cves-dont-panic-here-is-what-you-need-to-know-54cd0d6e8062
https://adamcaudill.com/2017/10/04/exploiting-jackson-rce-cve-2017-7525/
https://github.com/FasterXML/jackson-databind/issues/1599 -> https://nvd.nist.gov/vuln/detail/CVE-2017-7525
https://github.com/mbechler/marshalsec


MadGadget:
https://stackoverflow.com/questions/42580717/mad-gadget-de-serialisation-vulnerability-java-example -> https://github.com/frohoff/ysoserial 
        -> https://www.youtube.com/watch?v=VaLSYzEWgVE
        -> https://github.com/frohoff/ysoserial/tree/master/src/main/java/ysoserial/exploit


How to prevent this:
https://github.com/kantega/notsoserial
