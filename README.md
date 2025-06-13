# eVoto
Be careful with the .env and .env.production when working inside the root folder of the backend and frontend apps. Specially for deploying into Oracle VM's or local dev
When working in the backend it is not acceptable to use .env.production as a way to run those variables. Avoid doing that
Instead use cloud services for managing critical information 
Also, don't push your local enviroment through git to avoid security problems
