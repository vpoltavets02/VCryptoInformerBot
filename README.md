# VCryptoInformerBot

Telegram bot for informing users about changes in cryptocurrency world

## Table of Contents
* [General](#general-information)
* [Tecnologies](#techlogies)
* [Setup](#setup)
* [Screenshots](#screenshots)

## General information
This bot was created as a studying project for my portfolio. I was interested in creating something different from a common backend application with Spring Boot, so I decided to create my own telegram bot. The main purposes of this bot are:
* Displaying the list of all available tokens with command `/all_tokens`
* Adding/removing tokens from user list using `/add` and `/remove` commands respectively
* Getting statistic with using command `/stat`
* Hourly informing users about token price changes in their token lists

## Techlogies
For writing this project, I used the next list of technologies:
* Java 17
* Spring Boot 3.2.3
* Spring Data JPA
* Telegram Bot API 6.9.7.1
* PostgreSQL
* Log4j

## Setup
To run this project, clone it to your computer with using git bash:
```bash
git clone https://github.com/vpoltavets02/VCryptoInformerBot.git
```
Create database for project and select it. Then run a [script](https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/src/main/resources/schema.sql) for creating <b>User</b> table in your created database

Create your own telegram bot with [BotFather](https://t.me/botfather) and get unique token to access the HTTP API

Fill [application.properties](https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/src/main/resources/application.properties) with your configuration information

Run application with using your IDE

## Screenshots
Execution of `/start` and `/help` commands:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/1.jpg" width="25%" heigth="25%"/>

Execution of `/all_tokens` command:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/2.jpg" width="25%" heigth="25%"/>

The list of available commands by clicking on commands button:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/3.jpg" width="25%" heigth="25%"/>

Execution of `/stat` command when user doesn't have any tokens in their list and execution of `/add` command:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/4.jpg" width="25%" heigth="25%"/>

User got a message that he/she reached the maximum number of tokens in their list because the maximum number of tokens in the list is <b>5</b>

Executiion of `/stat` and `/remove` commands:

<img src="https://github.com/vpoltavets02/VCryptoInformerBot/blob/master/screenshots/5.jpg" width="25%" heigth="25%"/>
