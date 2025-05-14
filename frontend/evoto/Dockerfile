FROM node:20-alpine AS build
WORKDIR /app

# install dependencies
COPY package*.json ./
RUN npm ci

# copy source and build
COPY . .
RUN npm run build              # → /app/dist

#################### 2️⃣  Runtime stage ##################
FROM nginx:1.25-alpine
# copy the compiled Vite assets into nginx’s web root
COPY --from=build /app/dist/ /usr/share/nginx/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
CMD ["nginx", "-g", "daemon off;"]
