# Dog-API-Project
Command line option to save images of dogs to a local folder using the Dog API

## Motivation
This is a small and fun project to save images of dogs to your computer based on your specifications of what you want.

## Code Style
[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat)](https://github.com/feross/standard)
 
## Example Usage
![Example Usage gif](https://s8.gifyu.com/images/ezgif-3-8b9580f3c4db.gif)

## Technology/Framework Used
-[Maven](https://maven.apache.org/) </br>
-[Dog API](https://dog.ceo/dog-api/)

## Features
This project is unique in that it allows the user to select which breed of dog they would like to download images of along with how many they want.

## Installation
1. [Download and install Java](https://java.com/en/)
2. [Download and install Maven](https://maven.apache.org/download.cgi)
3. Download or clone this repository into an empty directory
4. Run any of the code examples shown below

## Code Example
To run the example test and see a breed list, navigate to the root directory containing the pom.xml file and run the `mvn test` command.
To interact with the program directly you can run the following commands:
```
java imageSearch -breed hound/blood -limit 4
```
-breed is the breed of the dog being downloaded </br>
-limit is the number of images being downloaded

## Breeds
[Full list of breeds acceptable in Dog API](https://dog.ceo/dog-api/breeds-list)

MIT © [Matthew McCracken](https://github.com/mdm16c)
