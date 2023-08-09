# 1.Expereince while Working On Project
I have Spent a lot of time approximately six days working on this project and while working there was a lot of reference points in learning. Especially In regard to sitemaps.I noticed that searched took longer to execute  at the beginning /start of the application since we didnt have the data kept into the cached memory.After sending request,similar request were retrieved within a much fated time since the data was cached in memory.

# 2.Scaling app to greater than 100 users

At the Beginning my app would be hosted on a single machine shared by many other users. And as the load gets. Too much I move it my own server of approximately about 2G of RAM machine and then later Upgrade to 8Gigs 8 cores. As users keep increasing, I would add another database server and then I would continuously add additional web servers and database servers almost up to 15 different servers. At this point I would need scripts to set them up because it would take much longer time trying to configure each single server. Usually there is one entry point to site or app say (www. christianboolk.com.)
 I would have this point to a load balancer which will take the connection rather to a different web server that will handle the request. Or a different approach to this would be set up different hostnames. Therefore, a naive approach to scaling would be generally upgrade the hardware as powerful as you can and just use a few machines as possible which intent may not be cost effective. Also as a way of reducing Load on the web server , I would have Images served off a separate machine because images add a lot of load to web server Although it doesn’t take much processing, it just requires holding up a connection and therefore as a way of managing this, you can have a CDN(Content Delivery Network) which is effective for serving static assets like images. 
CDNs will have many different points scattered throughout the world and they will copy and cache files in that local server within different geographies and therefore as people try to access content. It’s much faster to access and retrieve that content.  First thing to do when setting up the CDN is having all images served from the web server. When Issuing requests to the database server, you first have to issue requests into the cache layer. Cache layers are implemented through Memcached read as Cassandra what this does it is they reduce the base server.  The caching service is sometimes hosted on the same server as web server, but it can also be separate machine as well. Put heavy queries like complicated joins inside this caching service so that the database is only hit a few hours when updating the data. The primary concern for scaling is going to be in the database server. Therefore, among key major things you should do is Making sure that all database queries are well optimized and indexed. Making sure you use keys and indexes and that every query that is going to be executed must be done so using a key for index.
Keeping queries simple and avoid joins on table side and rather do the join manually on the web server side to avoid additional load on the database server.
Also, another action to take would be setting up a master slave database replication and what this is you have two database servers, and one is coping from the other and they are basically copies of each other. The Master serves as the source of truth and that’s what you write into. The slave is a copy, and you can read the frame in it but can never write into it. This actually helps to spread the load. Always keep in mind never to hard code the endpoints for the database server, image server, web server, chat server but rather store in variables as these can easily be changed to other endpoints. 
 Also, as a wrap up Other services like Amazon web services or Google app engine can help scale applications automatically. But some would generally avoid those because they involve getting you locked up into their system and are highly costly interns of how much you pay for their services.

 # 3. Resources and citations Used 

- [ ] https://www.nginx.com/resources/glossary/load-balancing/

- [ ] https://www.cloudflare.com/learning/cdn/what-is-a-cdn/
- [ ] https://www.novixys.com/blog/how-to-extract-data-from-xml-in-java/
- [ ] https://developer.ibm.com/tutorials/convert-xml-to-data-as-a-servicedaas-using-cloudant-nosql-database/

- [ ] https://material.angular.io/

- [ ] https://start.spring.io/
