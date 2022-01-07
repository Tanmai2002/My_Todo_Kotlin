# My_Todo_Kotlin
Basic App for keeping to do list (made for Android Started Course)
## Problem Statement
One of the basic requirements of a person is to set daily ,yearly and monthly goals.. Just to keep track of activities for yoursef. An app that dont remind you now and then what you have to do.Just a wish list of to do things.

## Solution
An app that has list of all things that you have to do, biforcated as  on that day ,daily , monthly , yearly tasks.

## Functionaliy and topics used:

### Constraint Layout
Using constraint layout over legacy like Relative layout and Linear layout give more flexibility to app

### Custom RecyclerView 
Using RecyclerView with custom layout for elements gives more power of creativity and control over view.Showing all attributes of a task is only possible due to custom layout and custom adapter

### Data Binding
Use of data binding ensures that data is accessed properly and updated within the view.Use of Binding Adapters enables a creator to update the tasks on UI efficiently

### LiveData
Live data is used to update realtime values and thus update the ui. Using Live data with Data binding give a creater ability to update ui easily.

### View Binding
Some places where data binding seems tedious view binding is used.

### Room Database
All the information of task and their status is stored in room database. I have created 2 database :
1: To store all types of tasks and status for each task
2: To store status for daily tasks

## Future Scope
I plan to include various features like import and export of db of task so that user can easily share thier task todo. 
I also plan to make ui more interactive and interesting.
I also plan to display logs on the database with charts and diagrams so that user can get a pictorial view of the same.


