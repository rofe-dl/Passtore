# Passtore

A simple desktop application that acts as a password manager by storing all passwords under a single master account. It supports multiple users, each with their own master account and password. Every entry can store the username, email, password and the name of the site for which the password is being stored.

The UI was built using SceneBuilder that generated the FXML files which are referred to in the controllers.

**It is not intended to be used practically and was developed while I was learning SQL, databases and how to make UI using JavaFX. Passwords are stored in plain text in the database.**

UI may or may not run in some Linux distros depending on the DE used (ran on Ubuntu 18.04, but ElementaryOS showed broken stage and scene upon launch). Works fine on Windows 10.

Save file goes to C:\Users\ (yourname) on Windows, and $USER/home on linux

**Required: Java 11 or above**

Used:

- Java 11
- SQLite
- JavaFX
