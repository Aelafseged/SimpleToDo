# Pre-work - *SimpleToDo*

**SimpleToDo** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Aelafseged Tewodros**

Time spent: **5** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **successfully add and remove items** from the todo list
* [ ] User can **not add empty items** into the todo list
* [ ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [ ] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [ ] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [ ] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] User is **notified** on editing or deleting item with a "Dialog",then Toast message is displayed after completion.
* [ ] **Item creation Date/time** is displayed 



## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/Aelafseged/SimpleToDo/blob/extraFeatures/app/simpletodo.gif' title='Video Walkthrough' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** [ My previous works were web applications, yet i had limited exposure with xml files, Android uses xml files extensively including animations(vector drawables) and you can easily access using R class.
You can't instantiate an activity class,need to use intent object

accessing and manipulating layout elements using code needs a bit extra codes with casting.
Always working with resources contstraints in mind. CPU, storage,RAM, network, screen size ].


**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** [ Array Adapter: it acts as a mother ship between a layout(predifined) and Array/list of objects to be displayed on to selected layout, in a context of activity/fragment, to become an adapter to the a listview,  It is important because it allows seemless harmony between different objects in our case a layout and an array of objects to be displayed into the listview.We might take it as granted but it reduces a great amount of work, you can modify those objects independently.
convertView in getView method, if it's not null used to display the old view, but in my code i don't use it ].

## Notes

Describe any challenges encountered while building the app.
*[ ] Date time formatting
*[ ] Working with Radio Button to handle priority choice

## License

    Copyright [2017] [Aelafseged Tewodros]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
