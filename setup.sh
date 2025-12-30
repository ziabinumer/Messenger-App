#!/bin/bash

# Root project directory
PROJECT_NAME="chat-system"

# Create base directories
mkdir -p $PROJECT_NAME/{server,client,auth,profile,messaging,friends,storage}

# Server files
touch $PROJECT_NAME/server/ServerMain.java
touch $PROJECT_NAME/server/ClientHandler.java
touch $PROJECT_NAME/server/ServerSocketManager.java
touch $PROJECT_NAME/server/SessionRegistry.java
touch $PROJECT_NAME/server/ServerConfig.java

# Client files
touch $PROJECT_NAME/client/ClientMain.java
touch $PROJECT_NAME/client/ClientSocket.java
touch $PROJECT_NAME/client/InputReader.java
touch $PROJECT_NAME/client/OutputWriter.java

# Auth files
touch $PROJECT_NAME/auth/AuthService.java
touch $PROJECT_NAME/auth/UserCredentials.java
touch $PROJECT_NAME/auth/AuthValidator.java

# Profile files
touch $PROJECT_NAME/profile/UserProfile.java
touch $PROJECT_NAME/profile/ProfileService.java
touch $PROJECT_NAME/profile/PresenceStatus.java

# Messaging files
touch $PROJECT_NAME/messaging/Message.java
touch $PROJECT_NAME/messaging/TextMessage.java
touch $PROJECT_NAME/messaging/FileMessage.java
touch $PROJECT_NAME/messaging/MessageType.java

# Friends files
touch $PROJECT_NAME/friends/FriendManager.java
touch $PROJECT_NAME/friends/FriendList.java

# Storage files
touch $PROJECT_NAME/storage/UserStore.java
touch $PROJECT_NAME/storage/ProfileStore.java
touch $PROJECT_NAME/storage/FileStorage.java

# Root entry point
touch $PROJECT_NAME/Main.java

echo "Project structure '$PROJECT_NAME' created successfully."
