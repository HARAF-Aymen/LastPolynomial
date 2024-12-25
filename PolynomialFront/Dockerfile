# Étape 1 : Utiliser une image Node.js pour construire l'application React
FROM node:18 as builder

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers package.json et package-lock.json pour installer les dépendances
COPY package.json package-lock.json ./

# Installer les dépendances
RUN npm install

# Copier tous les fichiers du projet
COPY . .

# Construire l'application React pour la production
RUN npm run build

# Étape 2 : Utiliser une image Nginx pour servir les fichiers statiques
FROM nginx:alpine

# Copier les fichiers de l'application React construits depuis l'étape précédente
COPY --from=builder /app/build /usr/share/nginx/html

# Exposer le port 80
EXPOSE 80

# Commande pour démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]