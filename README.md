How does one put Spring boot app in Docker
===

Intro
---

So that I don't have to look up everything again, this is a simple 
example of a Spring Boot app that goes into Tomcat and then the 
whole thing is run in a Docker container. The details can be found
in the excellent Docker documentation.

NB! You should put any application secrets (passwords, API keys) in a
separate `.env` file in the project root directory. The docker scripts
will pick up the values from there and pass them on to the container
while it's being built. Start by copying the file `env.example` as
`.env` and edit the API key values. Make sure your `.gitignore` lists
the `.env` file so it does not accidentally get checked into version 
control.

Using images and building containers
---

Look inside the files in `docker/` for the actual stuff. To build and run
the container, just say `./build.sh && ./run.sh`. To stop the container, 
press `Ctrl+C`. To run the container "in the background", use the `-d`
(or `--detach`) option with `docker run` command (see `run.sh`).

To start an existing container again, say `docker start <containername>`.

Sharing filesystem resources between host and container
---

The directory `docker/basicdata/` will be mounted into the container (see
`run.sh`) and the software in the container can read & write the directory
contents.

Open a shell a shell inside a running container, execute
`docker exec -i -t <containername> /bin/bash`

Then edit the file in the mounted directory `vi /data/greeting.txt`, 
save and observe how the changes are visible in the host as well.

Cleaning up
---

Downloaded base images are saved on disk. Also the containers take up 
space and so do any volumes created by containers. 

To remove obsolete containers
* see what's there: `docker ps -a`
* delete what's not wanted: `docker rm -f <containerid>`

To remove obsolete images:
* see what's there: `docker images`
* delete what's not wanted: `docker rmi -f <imageid>`

To remove obsolete volumes:
* see what's there: `docker volume ls`
* delete what's not wanted: `docker volume rm <volumename>`

Stuff to do to get the app running
---

To get the application running properly, you need to get API keys from
[Wunderground](https://www.wunderground.com/weather/api) and
[ipinfo.io](https://ipinfo.io/pricing).

Then set up the environment variables for the app:

1. IPINFO_SERVICE_TOKEN=<ipinfo.io token here>
1. WUNDERGROUND_API_KEY=<wunderground weather api key here>

The app also uses [ipify](https://www.ipify.org/) to get the external
IP address in case the server is running on localhost/docker container/
private LAN. Thanks ipify! 
