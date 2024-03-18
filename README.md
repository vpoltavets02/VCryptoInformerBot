# VCryptoInformerBot

Telegram bot for informing users about changes in cryptocurrency world. You can try it [here](https://t.me/vcryptoinformerbot)

## Table of Contents
* [General](#general-information)
* [Tecnologies](#techlogies)
* [Setup](#setup)
* [Screenshots](#screenshots)

## General information
This bot was created as a studying project for my portfolio. I was interested in creating something different from a common backend application with Spring Boot, so I decided to create my own telegram bot. For controling bot, user need to use commands menu. The main functions of this bot are:
* Displaying profile of user in the bot using command 👤<b>Profile</b>
* Turning on/off notifications from bot with help of 🔔<b>Notifications</b> command
* Adding tokens to user list using ➕<b>Add</b> command
* Removing tokens from user list with ➖<b>Remove</b> command
* Getting statistic with using command 📊<b>Statistic</b>
* Hourly informing users about token price changes in their token lists
* Getting the whole list of all available commands using ❓help command

## Techlogies
For writing this project, I used the next list of technologies:
* Java 17
* Spring Boot 3.2.3
* Spring Data JPA
* Telegram Bot API 6.9.7.1
* PostgreSQL
* Apache Maven
* Log4j

## Setup
1. To run this project, clone it to your computer with using git bash:
```bash
git clone https://github.com/vpoltavets02/VCryptoInformerBot.git
```
2. Create database for project and select it. Then run a [script](https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/src/main/resources/schema.sql) for creating <b>User</b> table in your created database

3. Create your own telegram bot with [BotFather](https://t.me/botfather) and get unique token to access the HTTP API

4. Fill [application.properties](https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/src/main/resources/application.properties) with your configuration information

5. Run application with using your IDE

## Screenshots
* Execution of `start` and `help` commands:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/1.jpg" target="_blank" width="25%" heigth="25%"/>

* Execution of `add` command:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/2.jpg" target="_blank" width="25%" heigth="25%"/>

* Execution of `statistic` command:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/3.jpg" target="_blank" width="25%" heigth="25%"/>

* Turning off notifications with using `notifications` command:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/4.jpg" target="_blank" width="25%" heigth="25%"/>

* Getting user profile after `profile` command execution:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/5.jpg" target="_blank" width="25%" heigth="25%"/>
