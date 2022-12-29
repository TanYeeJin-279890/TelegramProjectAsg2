## Requirements for Assignment-2
[Read the instruction](https://github.com/STIW3054-A221/class-activity-soc/blob/main/Assignment-2.md)

## Your Info:
1. Matric Number : 279890
2. Name : Tan Yee Jin
3. Photo : <div align="left"><img src = "images/me.png" width="180" height="200"></div>
4. Phone Number : 010-2107377
5. Other related info (if any)

## Introduction
<p align="justify">In this assignment 2, a Meeting Room Booking System is being done via a telegram bot. There are a total of 3 classes, DatabaseSQLite which deals with the connection of JDBC and calling out the method for each table and database, JeanBot which ensures the connection of the telegram bot and controls the message that will be sent to the user according to the user input, and Main Class which instantiate TelegramBotsApi and register the new bot.</p>
<p align="justify">Next, for the booking process, user is first required to click the /start link to start the bot, and the bot will then send message for user to choose which section they are interested in. There will be /book to proceed booking, /cancel for cancel previous booking, /check to check previous and recent booking, /showlist to check previous and recent booking and /end link to end booking. User can choose to select the link that match with their action. For example, if user decided to book a meeting room, user can click /book and follow the instruction of the booking process. </p>
<p align="justify">Lastly, as for the database table, this assignment 2 consists of 4 tables which are table_room that store data for meeting room such as room id, capacity, date and time available and status, user_test that store user’s data such as I/C number, staff ID, user name, mobile phone, purpose and email, table_book that stores book_id, room_id, user_id, book date and book time and table_desc that stores data for room id and room description. Several methods has been created for data insertion, update and delete in DatabaseSQLite class.</p>
<p align="justify">Telegram Bot Link: t.me/s279890_A221_bot.</p>

## Flow Diagram of the requirements (Use: https://app.diagrams.net/)
<p style="display:block;text-align:center">
<img src = "images/flowdiagram-telegramBot.png">
</p>

## Result/Output (Screenshot of the output)
### Booking Session
<p style="display:block;text-align:center">
<img src = "images/book1.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book2.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book3.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book4.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book5.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book6.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book7.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book8.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book9.png">
</p>
<p style="display:block;text-align:center">
<img src = "images/book10.png">
</p>

1. Database(table_room)
<p style="display:block;text-align:center">
<img src = "images/book_tableroom.png">
</p>

2. Database(user_test)
<p style="display:block;text-align:center">
<img src = "images/book_usertest.png">
</p>

3. Database(table_book)
<p style="display:block;text-align:center">
<img src = "images/book_tablebook.png">
</p>

### Cancel Booking
<p style="display:block;text-align:center">
<img src = "images/cancelBook.png">
</p>

1. Database(table_room)
<p style="display:block;text-align:center">
<img src = "images/cancelBook_tableroom.png">
</p>

2. Database(user_test)
<p style="display:block;text-align:center">
<img src = "images/cancelBook_usertest.png">
</p>

3. Database(table_book)
<p style="display:block;text-align:center">
<img src = "images/cancelBook_tablebook.png">
</p>

### Display User List
<p style="display:block;text-align:center">
<img src = "images/display.png">
</p>

## UML Class Diagram
<p style="display:block;text-align:center">
<img src = "images/UML Class Diagram.png">
</p>

## Database design
<p style="display:block;text-align:center">
<img src = "images/MeetingRoom Database Diagram.png">
</p>

## YouTube Presentation
<p align="justify">YouTube Link: https://www.youtube.com/watch?v=dYHG4-C7uio</p>

## References (Not less than 10)
1. <p align="justify">Getting Started · rubenlagus/TelegramBots Wiki. (2022). Retrieved from https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started</p>
2. <p align="justify">Code Java. (2020, October 25). Java Connect to SQLite Database Example [Video]. YouTube. https://www.youtube.com/watch?v=293M9-QRZ0c</p>
3. <p align="justify">SQLite Java: Inserting Data. (2022, August 28). SQLite Tutorial. https://www.sqlitetutorial.net/sqlite-java/insert/</p>
4. <p align="justify">SQLite Java: Select Data. (2022, August 28). SQLite Tutorial. https://www.sqlitetutorial.net/sqlite-java/select/</p>
5. <p align="justify">SQlite Java - How To Use JDBC To Interact with SQLite. (2021, April 29). SQLite Tutorial. https://www.sqlitetutorial.net/sqlite-java/</p>
6. <p align="justify">SQL INNER JOIN Keyword. (2022). https://www.w3schools.com/sql/sql_join_inner.asp</p>
7. <p align="justify">Ololade, S. (2022, May 15). How to Solve the “Ambiguous Name Column” Error in SQL. Medium. https://towardsdatascience.com/how-to-solve-the-ambiguous-name-column-error-in-sql-d4c256f3d14c</p>
8. <p align="justify">SQL Joins. (2022). https://www.w3schools.com/sql/sql_join.asp</p>
9. <p align="justify">Can we call a “case” inside another case in the same switch statement in Java? (2015, July 19). Stack Overflow. https://stackoverflow.com/questions/31498571/can-we-call-a-case-inside-another-case-in-the-same-switch-statement-in-java</p>
10. <p align="justify">How to select records where a column is equal to a parameter that can be null in SQL Server. (2020, April 13). Stack Overflow. https://stackoverflow.com/questions/61192785/how-to-select-records-where-a-column-is-equal-to-a-parameter-that-can-be-null-in</p>
11. <p align="justify">How to show options in telegram bot? (2015, December 24). Stack Overflow. https://stackoverflow.com/questions/34457568/how-to-show-options-in-telegram-bot</p>
12. <p align="justify">Telegram Bot Features. (2022). https://core.telegram.org/bots/features</p>
13. <p align="justify">Telegram APIs. (2022). https://core.telegram.org/api</p>
14. <p align="justify">How to check if a row exist in the SQLite table with a condition. (2015, April 15). Stack Overflow. https://stackoverflow.com/questions/29640246/how-to-check-if-a-row-exist-in-the-sqlite-table-with-a-condition</p>

## JavaDoc
### DatabaseSQLite
<p style="display:block;text-align:center">
<img src = "images/Javadoc_DatabaseSQLite.png">
</p>

### JeanBot
<p style="display:block;text-align:center">
<img src = "images/Javadoc_JeanBot.png">
</p>