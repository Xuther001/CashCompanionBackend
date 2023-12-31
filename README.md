# chat-app

Brief project description and purpose.

This app seeks to create a dynamic website where users can have live access to up-to-date stock quotes and financial news.
It is also a place where users can message each other in real-time. Users can also track their investment portfolio and
create notes for themselves. ChatGPT is also integrated to assist users for any inquiries they may have.

- This repository is the backend.
- The frontend can be found here: https://github.com/Xuther001/CashCompanionFrontend

Demo of the web app: https://www.youtube.com/watch?v=WgF6D-FXNug

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

Just clone and run.

Make sure to change the configurations in application.properties to match your PostgreSQL configurations.

## Usage

The endpoints are:

**POST: /api/v1/auth/register**<br>
    Use this to register a user<br>
    - Request body is as follows:<br>
   
    {
     "firstname": "string",
     "lastname": "string",
     "email": "string",
     "password": "string",
     "username": "string"
    }

**POST: /api/v1/auth/authenticate**<br>
    Use this to authenticate a user<br>
    - Request body is as follows:<br>

    {
     "username": "string",
     "password": "string"
    }

**POST: /api/v1/notes**<br>
    Use this to create a note<br>
    - Request body is as follows:<br>
    
    {
    "content": "string",
    "userid": "string"
    }

**GET: /api/v1/notes**<br>
    Use this to get all notes regardless of who posted them<br>
    - This will return the following list<br>
      
      [
        {
          "id": Integer,
          "content": "string",
          "userid": "string"
        }
      ]

**GET: api/v1/notes/{userid}**<br>
    Use this to get all notes posted by a user<br>
    This endpoint is having issues with CORS and has been temporarily commented out.<br>
    - This will return the following list<br>

      [
        {
          "id": Integer,
          "content": "string",
          "userid": "string"
        }
      ]

**DELETE: /api/v1/notes{id}**<br>
    This will delete note with id {id}
