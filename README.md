# Platform Science Android Coding Challenge

## Assumptions
* For the sake of counting vowels and consonants in names, the letter y is treated as a vowel
* For the length of driver names, the space between first and last names is included
* For the length of street names, only the street _name_ is used. The numeric address and unit type and number are ignored
* For the length of street names, any spaces inside the street names are included

## Architectural decisions
I have implemented an MVVM architecture with uni-directional dataflow.

At the data level, there is a service that is responsible for parsing the json data, and representing that data as remote models.

At the domain level, there is a repository that is responsible for fetching data from the service, and mapping it to domain models that are more useful in the higher layers.  There are also a handful of interactors to encapsulate business logic for fetching data and calculating suitability scores.

At the ui level, there is a ViewModel that is responsible for maintaining the state of fetched data, and user interactions.  Then there are a handful of Composable functions for rendering UI.

There is a single activity which owns the ViewModel, and calls the initial Composable function with a State object derived from the data in the ViewModel.

## Technical decisions
Although I have used Moshi and Gson in most of my previous work, I chose kotlinx serialization for parsing json because it is the most straightforward solution to implement in a new project.  

I decided not to implement any dependency injection framework because I am most familiar with Koin, but it has some significant drawbacks compared to Dagger and Hilt.  I would like to learn Dagger and Hilt, but I didn't feel that this project was the right place to do it.

I decided not to implement the Jetpack Compose Navigation framework because it is straightforward to acheive good animations, and user interactions without is in this small list/detail project structure.

I wrote several unit tests for components in the data and domain layers to validate my work on the business logic. 

## Running the project
Clone the repository, and open the project in Android Studio Dolphin or later.

To run the unit tests, expand the `java` folder, then right click the `test` package, and select "Run Tests in 'com.jonfha...'"

![Screen Shot 2022-11-29 at 7 51 31 PM](https://user-images.githubusercontent.com/561521/204703550-0f4b9980-c350-4a63-a43c-40927b3c2766.png)

To run the app, connect an emulator or physical device, make sure that "App" is selected as the target run configuration,  and click the run button.

![Screen Shot 2022-11-29 at 7 51 31 PM](https://user-images.githubusercontent.com/561521/204703792-d3227b50-de19-44f4-b552-f6c470953a66.png)


