# Documentation du Projet : Service de Gestion de Polynômes

## Table des matières

1. Introduction
   - Contexte et objectifs
   - Vue d'ensemble du projet

2. Architecture Technique
   - Structure du projet
   - Technologies utilisées
   - Conteneurisation et déploiement

3. Fonctionnalités
   - Services proposés
   - Interface utilisateur
   - Gestion des méthodes

4. Guide d'installation
   - Prérequis
   - Installation et configuration
   - Déploiement

5. Guide d'utilisation
   - Interface utilisateur
   - Utilisation des services
   - Récupération des résultats

---

## 1. Introduction

### 1.1 Contexte et objectifs

Le projet de gestion de polynômes est une plateforme complète permettant de calculer, analyser et manipuler des polynômes mathématiques en utilisant diverses méthodes. Ce projet vise à simplifier l'utilisation des algorithmes complexes de résolution de polynômes tout en offrant une interface conviviale et performante.

**Objectifs principaux :**
- Permettre la résolution et la factorisation de polynômes.
- Offrir un ensemble de méthodes mathématiques avancées.
- Fournir une solution déployable pour les chercheurs et étudiants en mathématiques.

### 1.2 Vue d'ensemble du projet

Le projet se compose de trois parties principales :

1. **Backend (Spring Boot)**
   - API RESTful pour la gestion des calculs.
   - Implémentation des méthodes de résolution.
   - Intégration à une base de données MySQL.

2. **Frontend (React)**
   - Interface utilisateur web.
   - Configuration des paramètres de calcul.
   - Visualisation des résultats.

3. **Service de Découverte (Eureka)**
   - Gestion des services distribués.
   - Enregistrement et découverte des services.

---

## 2. Architecture Technique

### 2.1 Architecture Globale

L'architecture est basée sur un modèle microservices avec les composants suivants :

1. **Eureka Server** : Service de découverte.
2. **API Gateway** : Gestion des requêtes et routage.
3. **Services de Calcul** :
   - Polynôme Service
   - Factorisation Service
   - Racines Service
4. **Base de Données MySQL** : Stockage des polynômes et résultats.
5. **Frontend (React)** : Interface utilisateur web.

### 2.2 Structure Détaillée du Projet

```plaintext
LastPolynomial/
├── Eureka/         # Service de découverte
├── Orchestration/               # API Gateway
├── polynomial-service/    # Service de calcul des polynômes
├── factorization-service/ # Service de factorisation
├── roots-service/         # Service de calcul des racines
├── PolynomialFront/              # Interface utilisateur               
└── docker-compose.yml                # Configuration Docker
```

### 2.3 Technologies Utilisées

#### Backend (Spring Boot)
- **Langage** : Java 17
- **Framework** : Spring Boot
- **Base de données** : MySQL
- **Outils de build** : Maven

#### Frontend (React)
- **Langage** : JavaScript/React
- **Framework** : React avec Tailwind CSS
- **Dépendances principales** : Axios, React Router

#### Conteneurisation
- **Docker** : Isolation des services
- **Docker Compose** : Gestion des conteneurs

### 2.4 Communication entre les Services

1. **API RESTful** : Communication entre le frontend et les microservices.
2. **Service Discovery (Eureka)** : Enregistrement dynamique des services.
3. **Base de données** : Intégration MySQL pour le stockage persistant.

---

## 3. Fonctionnalités

### 3.1 Services Proposés

1. **Polynôme Service**
   - Ajout et gestion des polynômes.
   - Calcul des coefficients et des termes.

2. **Racines Service**
   - Calcul des racines réelles et complexes.
   - Implémentation de méthodes avancées comme Newton-Raphson.

3. **Factorisation Service**
   - Décomposition des polynômes en facteurs.
   - Utilisation de méthodes algorithmiques optimisées.

### 3.2 Interface Utilisateur

1. **Configuration des Polynômes** :
   - Entrée intuitive pour les coefficients et les degrés.
   - Validation des données en temps réel.

2. **Visualisation des Résultats** :
   - Graphiques interactifs.
   - Export des résultats en format texte ou image.

---

## 4. Guide d'installation

### 4.1 Prérequis

1. **Système** :
   - OS : Windows, macOS ou Linux
   - RAM : 4 Go minimum
   - Java JDK 17
   - Node.js 16+
   - Docker et Docker Compose

2. **Dépendances** :
   - Maven pour le backend
   - npm pour le frontend

### 4.2 Installation et Configuration

#### Backend
```bash
# Cloner le projet
git clone https://github.com/HARAF-Aymen/LastPolynomial

# Accéder au service backend
cd LastPolynomial

# Installer les dépendances
mvn clean install

# Lancer le service
mvn spring-boot:run
```

#### Frontend
```bash
# Accéder au dossier frontend
cd PolynomialFront

# Installer les dépendances
npm install

# Démarrer le serveur
npm start
```

#### Docker
```bash
# Lancer tous les services
cd LastPolynomial

docker-compose up --build
```

---

## 5. Guide d'utilisation

1. **Accéder à l'interface utilisateur** :
   - Ouvrir le navigateur à l'adresse http://localhost:3000

2. **Ajouter un Polynôme** :
   - Remplir le formulaire pour entrer les coefficients.
   - Choisir la méthode de calcul.

3. **Afficher les Résultats** :
   - Visualiser les racines et facteurs .
   - Exporter les données si nécessaire.

---






https://github.com/user-attachments/assets/cd13c109-a346-4562-abee-f0b6ed4a4a33

