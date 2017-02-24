# Profiler

**[Livestream Link](http://www.youtube.com/c/wiseAss/live)**

Profiler is an open source App project which intends to demonstrate a functional and adequetely tested "Social Media Style" Android App.
I intend to build multiple iterations of the App, starting with basic features such as User Authentication and Data storage, moving towards features such as live Chat (it wouldn't be much of a Social Media app otherwise). 

The first iteration of Profiler has been completed, and I'll re-building it via Youtube Livestream over the course of Feb and March 2017. Although I won't be releasing the source of the completed App ahead of time, I'll update the Live-built version's Repo (this one) each episode. This should help people to have the correct starting point for each episode (in theory anyway).

I'm estimating that it will take about 7 Episodes total, but we'll jump off that bridge when we get to it. Please see the "Stream Schedule" section for a rough schedule.

### Junior Android Developers:
While I'm certain that there will be some value in watching the series and looking over my code, this is **not intended to be a beginner tutorial series.** I'll be doing my best to explain advanced topics such as Unit Testing and RxJava 2 from scratch, but I simply won't have time to explain basics like ClickListeners, Fragment creation, etc. For a softer introduction to Android Framework basics, please see my [Beginner Android Dev Series](https://www.youtube.com/playlist?list=PLEVlop6sMHCp3Wp0mqT2-OxHwVdAod1uy) 

### Intermediate/Senior Developers:
I personally consider myself to be an Intermediate Android Developer (although I'm not sure how that's quantified, so who knows). I've spent the past 2+ years working with the Android platform, and I've made a couple small to medium scale Apps. I've never worked as a Developer other than as a hobby, and I don't have a degree in anything. As a result, I may explain concepts using bizarre working class language/analogies and a distinct lack of the appropriate Jargon (this is also partly due to most of my viewer base being ESL).

As such, please feel free to suggest changes and give constructive feedback about the project. My goal is to write awesome code, not lead anyone down the wrong path; I'll give credit where it's due if you take the time to help me out. I will mention two things about feedback:
*Although research and leg work is something I'm very used to doing, I would greatly appreciate it if potential changes and suggestions be explained/referenced appropriately.  
*Be respectful and don't talk to me like I'm an idiot. I don't have time for intellectual dick measureing contests as I'm too busy doing what I love. 'nuff said.

## Topics:
One of my goals with this project, is to leverage a few of today's most popular Libraries, technologies, and Software Architecture concepts. Below is a list of the current Principles/Libs I use with the project, followed by some things I'd like to add to it later.

* Firebase (FirebaseAuth and Realtime Database) for User/Data management
* RxJava/RxAndroid 2 for concurrency (mostly to communicate between Presenter and Service Layers)
* Mockito to help with Unit Testing
* Picasso for Image Loading
* Model View Presenter Architecture

I'm also looking at adding the following Libraries for future iterations:
* Dagger 2
* Firebase Cloud Messaging
* LeakCanary

## Show Schedule:
**Streaming will begin at 8:30am PST (10pm IST), Feb. 25th 2017**

Keeping in mind that I going on random tangets will be common (I don't get to talk about Code with people much lol), here's a rough idea of the topics I'll be covering:

### Day 1: 
* Tips and tricks for setting up a new App's structure, colors, styles and so forth
* We'll create the classes necessary for our first three MVP Components
* We'll finish the UI for those components

### Day 2:
* I'll do my best to explain Model View Presenter Architecture and how it can help us with testing
* We'll set up our project to have Mock and Production build Variants, also to help with testing
* We'll write the View and Activity for our first three components

### Day 3:
* I'll do my best to explain RxJava/Android 2... and hope to hell that it makes sense to someone...
* We'll create Fake versions of our Service Layer, so that we can get testing!
* We'll write our Presenters and Contract Classes for our first three components

### Day 4:
* Unit Test all the things (well... some of the things). I'm quite happy to hear input on this one!
* Write our real Service Layer

### Day 5-8:
* Build the rest of the App. I have no idea how long that will take lol.

## Sources

It's no exaggeration to say that this project wouldn't have been possible for me to make, were it not for the following talks and open source codebases. I have to give my biggest thanks to everyone involved in the [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture) project. Much of Profiler's code is based off of code from this project, and I would've failed epicly to get it off the ground without such an awesome resource. When I'm more confident in my skills, I would love to help <3<3<3.

If becoming a better developer is your goal, please visit, read, listen, and play around with the following sources:
* [Futurice's Android Best Practices Repo](https://github.com/futurice/android-best-practices). Awesome overview of Android Development Best Practices.
* [Jake Wharton on RxJava 2](https://www.youtube.com/watch?v=htIXKI5gOQU&t=1795s). Jake does an awesome job explaining how RxJava works conceptually, and overviews the new RxJava 2 API changes. Must watch for RxJava noobs.
* [Model View Presenter by Michael Cameron](https://www.youtube.com/watch?v=AoqL1PN8hCk&t=1229s). Very solid explanation of MVP for Android, and some extra tips for testing and so forth. 
* [Testing made Sweet with a Mockito by Jeroen Mols](https://www.youtube.com/watch?v=DJDBl0vURD4&t=33s). One of my biggest frustrations with this project was how poorly people seem to Explain Unit Testing (that's partly because Android finally has some decent options for it). Not only does Jeroen explain the Mockito Testing API, but he gives plenty of insight as to what you actually want to test! This was the talk that made Unit Testing finally start to make sense for me.
* [Fragmented Podcast](http://fragmentedpodcast.com/). Donn and Kaushik are doing everyone a huge service with this podcast. If you're a noob like me, listening to these two along with their all star guest line up (as far as Android goes) should be mandatory! I truly can't think of a better place to expose myself to powerful ideas and concepts from the Demi-gods of Dev.

## Support me:
It's my personal goal to create FREE high-quality content, accesible by anyone who has an Internet connection, as I don't feel ownership over knowledge which has graciously been given to me for free (I've never taken a paid course for Android Development). 

That being said, if the 200+ hours of my time spent coding, researching, making learning aids, and producing content for this project, and the effort I take to explain things in a simple and clear way (which I often fail to do), is worth throwing a bit of money at, then please do! 

My biggest goal for this year is to be able to pay my living expenses with my online projects. Naturally, since I don't put a price tag on anything (we'll, I haven't so far), this goal will take time. If you want give thanks and support my efforts, please consider signing on as a Patreon Patron (link below). 

If you're in a position where monetary support isn't an option (believe me, I understand), then you can also help out by liking/sharing my shit on Social Media. This helps me with SEO and building an audience, and I greatly appreciate it! Lastly, drop me a comment on one of my videos. I read them. <3

[G+] (https://plus.google.com/+wiseass)
[Facebook](https://www.facebook.com/wiseassblog/)
[Twitter] (https://twitter.com/wiseAss301)
[Patreon] (https://www.patreon.com/bePatron?u=5114325)
[Primary Website] (http://wiseassblog.com/)

## License

/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
