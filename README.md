# chat-app

Brief project description and purpose.

This app seeks to create a dynamic website where users can have live access to up to date stock quotes and financial news.
It is also a place where users can message each other in real time. Users can also track their investment porfolio and
create notes for themselves.

- This repository is the backend.
 Previous commits of this repository can be found here: https://github.com/Xuther001/instakeep
- The frontend can be found here: https://github.com/Xuther001/chatfrontend

Demo of the web app: https://www.youtube.com/watch?v=Rp8jBmOCXek

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

Just clone and run.

## Usage

The endpoints are:

POST /api/v1/auth/register
    Use this to register a user
    - Request body is as follows:
   
    {
     "firstname": "string",
     "lastname": "string",
     "email": "string",
     "password": "string",
     "username": "string"
    }

POST /api/v1/auth/authenticate
    Use this to authenticate a user
    - Request body is as follows:

    {
     "username": "string",
     "password": "string"
    }

POST /api/v1/notes
    Use this to create a note
    - Request body is as follows:
    
    {
    "content": "string",
    "userid": "string"
    }

GET /api/v1/notes
    Use this to get all notes regardless of who posted them
    - This will return the following list
      
      [
        {
          "id": Integer,
          "content": "string",
          "userid": "string"
        }
      ]

GET api/v1/notes/{userid}
    Use this to get all notes posted by a user
    This endpoint is having issues with CORS and has been temporarily commented out.
    - This will return the following list

      [
        {
          "id": Integer,
          "content": "string",
          "userid": "string"
        }
      ]

DELETE /api/v1/notes{id}
    This will delete note with id {id}















